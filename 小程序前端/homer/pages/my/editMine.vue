<template>
	<view class="edit-container">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>
		<view class="edit-img edit-item-image" @tap="getImage">
			<view class="edit-item-title">头像</view>
			<view class="edit-img-up">
				<image :src="userInfo.avatarUrl"></image>
			</view>
			<image src="../../static/right.png" class="edit-item-image-right"></image>
		</view>
		<view class="edit-name edit-item" @tap="modifyName">
			<view class="edit-item-title">昵称</view>
			<view>
				{{userInfo.name}}
			</view>
			<image src="../../static/right.png" class="edit-item-right"></image>
		</view>
		<view class="edit-phone edit-item">
			<view class="edit-item-title">手机号</view>
			<view v-if="userInfo.mobile!=null">{{userInfo.mobile}}</view>
		</view>
		<view class="edit-sex edit-item" >
			<view class="edit-item-title">性别</view>
			<view class="uni-list-cell">
				<view class="uni-list-cell-db" >
					<picker @change="bindPickerChange" :value="index" :range="array">
						<view style="width:500rpx;height:120rpx;position:relative">
							<view class="uni-input" style="line-height:120rpx;vertical-align: center;float:right">{{array[index]}}</view>
							<image src="../../static/right.png" class="edit-item-right _right" ></image>
						</view>
					</picker>
				</view>
			</view>
		</view>
		<view class="edit-profile edit-item" @tap="modifyProfile">
			<view class="edit-item-title">个人简介:</view>
			<view class="profile" v-if="userInfo.introduce!=null">{{userInfo.introduce}}</view>
			<image src="../../static/right.png" class="edit-item-right "></image>
		</view>
		
		<view class="prompt-box" v-if="visible">
			<view class="prompt">
				<view class="prompt-top">
					<view class="prompt-title" v-if="type==0">新昵称</view>
					<view class="prompt-title" v-else>新个人签名</view>
					<textarea class="textarea" value="" placeholder="请输入..." :maxlength="100" v-model="value"
					 placeholder-class="plc" adjust-position fixed />
					</view>
				<slot></slot>
				<view class="prompt-buttons">
					<button class="prompt-cancle" @click="close">取消</button>
					<button class="prompt-confirm" @tap="handleset()">确定</button>
				</view>
			</view>
		</view>
		
	</view>

</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				array: [ '女', '男',],
				index: 1,
				config: {
					type: 0, //0无title，1有title
					title: '个人资料',
					back: true
				},
				userInfo: {
					name: '',
					image: '',
					phone: '',
					sex: '',
					profile: ''
				},
				userId:'',
				visible:false,
				value:'',
				type:0,
			}
		},
		components: {
			navigation,
		},
		onLoad() {
			if (uni.getStorageSync('userId')) {
				this.userId = uni.getStorageSync('userId')
				this.getuser()
			} else {
				this.customConduct()
			}
		},
		onShow(){
			this.getuser()
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			close(){
				this.visible = false
				this.value = ''
			},
			modifyName() {
				this.type = 0
				this.visible = true
				this.value = ''
				// uni.navigateTo({
				// 	url: '../my/editMineDetail'
				// })
			},
			modifyProfile() {
				this.type = 1
				this.visible = true
				this.value = ''
				// uni.navigateTo({
				// 	url: '../my/editMineProfile'
				// })
			},
			bindPickerChange: function(e) {
				console.log('picker发送选择改变，携带值为', e.target.value)
				this.index = Number(e.target.value)
				this.setSex()
			},
			getuser(){
				this.$http.get('/api/person/info/', {
					userId: this.userId
				}).then(res => {
					this.userInfo = res
					this.index = res.sex
				}).catch((e) => {});
			},
			setSex(){
				let _this = this
				this.$http.post('/api/person/update/', {
					userId: this.userId,
					name: this.userInfo.name,
					sex: this.index,
					introduce: this.userInfo.introduce,
					avatarUrl: this.userInfo.avatarUrl,
				}).then(res => {
					uni.showToast({
						title: '修改成功',
						icon: 'none'
					})
					_this.getuser()
				}).catch((e) => {});
			},
			getImage() {
				uni.chooseImage({
					count: 1,
					success: (res) => {
						console.log(res)
						let url = res.tempFilePaths[0]
						this.uploadimage(url)
					},
					fail: (err) => {
						console.log(err)
					}
				});
			},
			uploadimage(url){
				let upload_task = uni.uploadFile({
					url: 'https://tjuyjn.top:1314/api/publish/image',
					filePath: url,
					name: 'image',
					header: {
						Authorization: 'Bearer ' + uni.getStorageSync('token')
					},
					success: (res) => {
						console.log(res)
						console.log(JSON.parse(res.data))
						let data = JSON.parse(res.data)
						if (data.result == 1) {
							this.setavatar(data.url);
						} else {
							uni.showToast({
								title: data.message,
								icon: 'none'
							})
						}
				
					}
				})
				upload_task.onProgressUpdate((res) => {});
			},
			setavatar(e){
				let _this = this
				this.$http.post('/api/person/update/', {
					userId: this.userId,
					name: this.userInfo.name,
					sex: this.userInfo.sex,
					introduce: this.userInfo.introduce,
					avatarUrl: e,
				}).then(res => {
					uni.showToast({
						title: '修改成功',
						icon: 'none'
					})
					_this.getuser()
				}).catch((e) => {});
			},
			handleset(){
				let _this = this
				if (this.type == 0){
					this.$http.post('/api/person/update/', {
						userId: this.userId,
						name: this.value,
						sex: this.userInfo.sex,
						introduce: this.userInfo.introduce,
						avatarUrl: this.userInfo.avatarUrl,
					}).then(res => {
						uni.showToast({
							title: '修改成功',
							icon: 'none'
						})
						_this.visible = false
						_this.getuser()
					}).catch((e) => {});
				} else {
					this.$http.post('/api/person/update/', {
						userId: this.userId,
						name: this.userInfo.name,
						sex: this.userInfo.sex,
						introduce: this.value,
						avatarUrl: this.userInfo.avatarUrl,
					}).then(res => {
						uni.showToast({
							title: '修改成功',
							icon: 'none'
						})
						_this.visible = false
						_this.getuser()
					}).catch((e) => {});
				}
			}
		}
	}
</script>

<style scoped>
	.edit-container {
		display: flex;
		flex-direction: column;
		background-color: #FFFFFF;
		height: 100vh;
	}

	.edit-item-image {
		height: 160rpx;
		border-bottom: 1rpx solid #f5f5f5;
		padding-left: 8rpx;
		padding-right: 45rpx;
		align-items: center;
		display: flex;
		justify-content: space-between;
		position: relative;

	}

	.edit-item {
		height: 120rpx;
		border-bottom: 1rpx solid #f5f5f5;
		padding-left: 8rpx;
		padding-right: 45rpx;
		align-items: center;
		display: flex;
		justify-content: space-between;
		position: relative;

	}

	.edit-item-title {
		margin-left: 20rpx;
	}

	.edit-img-up {
		height: 110rpx;
		width: 110rpx;
	}

	.edit-img-up image {
		height: 110rpx;
		width: 110rpx;
		border-radius: 50%;
	}

	.edit-item-image-right {
		height: 30rpx;
		width: 30rpx;
		position: absolute;
		right: 5rpx;
		top: 65rpx;
	}

	.edit-item-right {
		height: 30rpx;
		width: 30rpx;
		position: absolute;
		right: 5rpx;
		top: 45rpx;
	}

	.edit-item-image:active,
	.edit-item:active {
		background-color: #e0e0e0;
	}

	.profile {
		width: 200px;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
		text-align: right;
	}
	
	.prompt-box {
		position: fixed;
		left: 0;
		top: 0;
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;
		height: 100vh;
		background: rgba(0, 0, 0, 0.2);
		transition: opacity 0.2s linear;
		z-index:20;
	}
	
	.prompt {
		position: relative;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		align-items: center;
		width: 600upx;
		min-height: 300upx;
		background: white;
		border-radius: 20upx;
		overflow: hidden;
	}
	
	.prompt-top {
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 100%;
	}
	
	.prompt-title {
		margin: 20upx 0;
		color: #333;
		display:block;
		text-overflow:ellipsis;
		white-space:nowrap;
		overflow:hidden;
		max-width:266upx;
	}
	
	.prompt-input {
		width: 520upx;
		font-size: 28upx;
		border-radius: 8upx;
	}
	
	.prompt-buttons {
		display: flex;
		width: 100%;
		margin-top: 20upx;
	}
	
	button {
		border-radius: 0;
	}
	
	.prompt-cancle {
		background: #FFFFFF;
		width:50%;
		font-size:16px;
	}
	
	.prompt-confirm {
		color: #6ba1ff;
		background: #FFFFFF;
		width:50%;
		font-size:16px;
	}
	
	.textarea {
		position: relative;
		font-size: 22.22upx;
		box-sizing: border-box;
		padding:25.69upx 22.22upx 0;
		font-weight: bold;
		width: 500upx;
		height: 240upx;
		background: rgba(255, 255, 255, 1);
		border: 2upx solid rgba(245, 245, 245);
		border-radius: 20.83upx;
		color: rgba(147, 147, 147, 1);
		font-family: SourceHanSansCN-Regular;
	}
	
	._right{
		right:-40rpx;
	}
</style>
