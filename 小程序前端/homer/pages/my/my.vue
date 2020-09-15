<template>
	<view class="mine-container">
		<view style="text-align: center;">
			<navigation :config="config" />
		</view>
		<view class="user-box" v-if="userId!=''">
			<view class="avator-box">
				<image :src="avatar" mode="aspectFit"></image>
			</view>
			<view class="user-right">
				<view class="user-id">
					{{name}}
				</view>
				<view class="user-phone">
					<text>手机号: 18722648040</text>
				</view>
				<view class="user-edit" @tap="goEdit">编辑信息</view>
			</view>
		</view>
		<view class="user-box" v-else>
			<button class="loginbtn" plain="true" open-type="getUserInfo" @getuserinfo="getuserinfo">请授权登录</button>
		</view>
		<view class="title">订单</view>
		<view class="order">
			<view class="order-btn" @tap="goFinish">
				已完成订单
			</view>
			<view class="order-btn" @tap="goUnfinish">
				未完成订单
			</view>
		</view>
		<view class="title">消息队列</view>
		<block v-for="(msg,key,index) in msgList" :key="index">
			<view class="msg-list">
				<view class="msg-item">
					{{key}},{{msg.msg}}
				</view>
			</view>
		</block>
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				config: {
					type: 1, //0无title，1有title
					title: "我的",
					back: false
				},
				code: '',
				userId: '',
				avatar: '',
				name: '',
				firstTime: true,
				msgList: [{
					type: '0',
					msg: '你的card有人申请了!',
					cardId: '1'
				}, {
					type: '1',
					msg: '你的队伍有人申请加入了!',
					cardId: '1'
				}, {
					type: '2',
					msg: '你的入队申请通过了!',
					cardId: '1'
				}, {
					type: '3',
					msg: '你的队伍有人退出了!',
					cardId: '1'
				}, {
					type: '4',
					msg: '你的队伍解散了!',
					cardId: '1'
				}, {
					type: '5',
					msg: '你的card有人申请了!',
					cardId: '1'
				}]
			}
		},
		components: {
			navigation
		},
		onReady() {
			if (uni.getStorageSync('userId')&&uni.getStorageSync('token')) {
				this.userId = uni.getStorageSync('userId')
				this.getuser()
			} else {
			let _this = this
			console.log(1)
			uni.login({
				provider: 'weixin',
				success: function(response) {
					console.log(response)
					_this.code = response.code
				},
				fail: (err) => {
					console.log(err)
				}
			})
			}
		},
		methods: {
			goEdit: function() {
				// wx.navigateTo({
				//   url: '../my/editMine'　
				// })
			},
			goFinish: function() {
				// wx.navigateTo({
				//   url: '../my/finish'　
				// })
			},
			goUnfinish: function() {
				// wx.navigateTo({
				//   url: '../my/unfinish'　
				// })
			},
			//获取微信头像昵称
			getuserinfo(e) {
				console.log(2)
				console.log(e.detail.userInfo);
				let user = e.detail.userInfo;
				this.name = user.nickName;
				this.avatar = user.avatarUrl;
				let url = 'http://188.131.227.20:1314/api/login/'
				let _this = this
				uni.request({
					url: url,
					method: 'POST',
					data: {
						code: _this.code
					},
					success: res => {
						console.log(res.data);
						_this.userId = res.data.userId
						_this.firstTime = res.data.firstTime
						uni.setStorageSync('token', res.data.token)
						uni.setStorageSync('userId', res.data.userId)
						if (!res.data.firstTime) {
							_this.getuser()
						} else {
							_this.sendfirst()
						}
						uni.setStorageSync('avatar', _this.avatar)
						uni.setStorageSync('name', _this.name)
					},
					fail: err => {
				
					}
				})
			},
			//获取数据库中头像昵称
			getuser() {
				this.$http.get('/api/person/info/', {
					userId: this.userId
				}).then(res => {
					if (res.result == 1) {
						this.name = res.name;
						this.avatar = res.avatarUrl;
					}
				}).catch((e) => {});
			},
			sendfirst() {
				this.$http.post('/api/person/update/', {
					userId: this.userId,
					name: this.name,
					sex: null,
					introduce: null,
					avatarUrl: this.avatar,
				}).then(res => {
					uni.showToast({
						title: '登录成功',
						icon: 'none'
					})
				}).catch((e) => {});
			}
		}
	}
</script>

<style scoped>
	.mine-container {
		background-color: #F8F8F8;
	}

	.user-box {
		height: 240rpx;
		padding-left: 20rpx;
		display: flex;
		align-items: center;
		margin: 10rpx 6rpx 10rpx 6rpx;
		border-radius: 10rpx;
		overflow: hidden;
		background-color: #fff;
		box-shadow: 6rpx 6rpx 6rpx #c0c0c0;
		position: relative;
	}

	.avator-box {
		height: 160rpx;
		width: 160rpx;
		overflow: hidden;
		margin-left: 20upx;
	}

	.avator-box image {
		height: 140rpx;
		width: 140rpx;
		border-radius: 50%;
	}

	.user-right {
		margin-left: 10rpx;
	}

	.user-id {
		font-weight: bold;
		font-size: large;
	}

	.user-phone {
		margin-top: 20rpx;
		font-size: smaller;
	}

	.user-edit {
		font-size: small;
		position: absolute;
		bottom: 20rpx;
		right: 40rpx;
	}

	.order {
		display: flex;
		padding: 0 30rpx;

	}

	.order-btn {
		flex: 1;
		width: 200rpx;
		height: 120rpx;
		background-color: rgba(16, 81, 142, 0.25);
		border-radius: 10rpx;
		text-align: center;
		line-height: 120rpx;
	}

	.order-btn:first-child {
		margin-right: 20rpx;
	}

	.title {
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		margin-left: 15rpx;
		font-size: larger;
		font-weight: bold;
	}

	.msg-list {
		display: flex;
		flex-direction: column;
	}

	.msg-item {
		height: 80rpx;
		line-height: 80rpx;
		padding-left: 20rpx;
		margin: 10rpx 15rpx 10rpx 15rpx;
		background-color: #fff;
		border-radius: 6rpx;
		box-shadow: 5rpx 5rpx 5rpx #c0c0c0;
	}

	.loginbtn {
		border: none
	}
</style>
