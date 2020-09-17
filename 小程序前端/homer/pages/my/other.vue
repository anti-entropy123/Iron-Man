<template>
	<view class="edit-container">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>
		<view class="edit-img edit-item-image">
			<view class="edit-item-title">头像</view>
			<view class="edit-img-up">
				<image :src="userInfo.avatarUrl"></image>
			</view>
		</view>
		<view class="edit-name edit-item">
			<view class="edit-item-title">昵称</view>
			<view>
				{{userInfo.name}}
			</view>
		</view>
		<view class="edit-phone edit-item">
			<view class="edit-item-title">手机号</view>
			<view v-if="userInfo.mobile!=null">{{userInfo.mobile}}</view>
		</view>
		<view class="edit-sex edit-item" >
			<view class="edit-item-title">性别</view>
			<view v-if="userInfo.sex!=null">
				{{array[userInfo.sex]}}
			</view>
			<image src="../../static/right.png" class="edit-item-right"></image>
		</view>
		<view class="edit-profile edit-item">
			<view class="edit-item-title">个人简介:</view>
			<view class="profile" v-if="userInfo.introduce!=null">{{userInfo.introduce}}</view>
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
					title: 'TA的资料',
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
			}
		},
		components: {
			navigation,
		},
		onLoad(options) {
			this.userId = options.userId
		},
		onShow(){
			this.getuser()
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			getuser(){
				this.$http.get('/api/person/info/', {
					userId: this.userId
				}).then(res => {
					this.userInfo = res
				}).catch((e) => {});
			},
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
</style>