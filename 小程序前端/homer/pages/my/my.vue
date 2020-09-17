<template>
	<view class="mine-container">
		<view style="text-align: center;">
			<navigation :config="config" />
		</view>
		<view class="user-box" v-if="userId!=''">
			<view class="avator-box" @tap="goEdit">
				<image :src="avatar" mode="aspectFit"></image>
			</view>
			<view class="user-right" @tap="goEdit">
				<view class="user-id">
					<text class="user-name">{{name}}</text>
					<image src="../../static/edit.png" class="editicon"></image>
				</view>
				<view class="user-phone">
					<text>手机号: {{mobile}}</text>
				</view>
			</view>
		</view>
		<view class="user-box" v-else>
			<button class="loginbtn" plain="true" open-type="getUserInfo" @getuserinfo="getuserinfo">请授权登录</button>
		</view>
		<view class="order">
			<view class="order-btn" @tap="goFinish">
				<image src="../../static/order.png" style="width:70upx;flex: 1;margin-top:10upx;justify-content: center;" mode="widthFix"></image>
				<text>已完成订单</text>
			</view>
			<view class="order-btn" @tap="goUnfinish">
				<image src="../../static/thinking.png" style="width:70upx;flex: 1;margin-top:10upx;justify-content: center;" mode="widthFix"></image>
				<text>未完成订单</text>
			</view>
		</view>
		<view class="msglist">
			<view class="title">
				<image src="../../static/messageicon.png" mode="widthFix"></image>
				<text>消息队列</text>
			</view>
			<view v-for="(message,index) in messages" :key="index" class="msgbox">
				<image :src="message.avatarUrl" mode="" class="messageavatar" @tap="gotoother(message.userId)"></image>
				<view class="messgaetext">
					<text style="color:#10518e;margin-right:10upx;" @tap="gotoother(message.userId)">@{{message.nickname}}</text>
					<text>{{message.type|filters1}}</text>
				<viwe class="statetext">
					<text v-if="message.status" style="color:#707070">已批阅</text>
					<text v-else-if="message.type==0" style="color:#10518E" @tap="gotodetail(message.cardId)">>>去审批</text>
					<text v-else-if="message.type==1" style="color:#09BB07" @tap="agreejoin(message.applyId)">同意</text>
				</viwe>
				</view>
				<view style="clear: both;"></view>
			</view>
			<view style="display: flex;align-items: center;justify-content: space-around;margin-top:20upx;">
				<view style="color:rgba(119,119,119,1);font-size:25upx;margin-bottom:30upx;">没有更多数据了</view>
			</view>
		</view>
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
				mobile: '',
				firstTime: true,
				messages: [],
				
			}
		},
		components: {
			navigation
		},
		filters: {
			filters1: function(arg) {
				if (arg == 0) {
					return '申请你的资源了'
				} else if (arg == 1) {
					return '申请加入你的队伍';
				} else if (arg == 2) {
					return '已同意你的合租入队申请';
				} else if (arg == 3) {
					return '退出了你的队伍';
				} else if (arg == 4) {
					return '已将你们的队伍解散';
				} else if (arg == 5) {
					return '已同意你的下单申请';
				} else if (arg == 6) {
					return '回复了你的评论';
				} else if (arg == 7) {
					return '评论了你的帖子';
				}
			},
		},
		onLoad() {
			if (uni.getStorageSync('userId') && uni.getStorageSync('token')) {
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
		onShow(){
			if (uni.getStorageSync('userId') && uni.getStorageSync('token')) {
				this.getuser()
				this.getmessage()
			}
		},
		onPullDownRefresh: function() {
			//下拉刷新的时候请求一次数据
			if (uni.getStorageSync('userId') && uni.getStorageSync('token')) {
				this.getuser()
				this.getmessage()
			}
		},
		methods: {
			goEdit: function() {
				uni.navigateTo({
					url: '../my/editMine'
				})
			},
			goFinish: function() {
				uni.navigateTo({
					url: '../my/finishOrder'
				})
			},
			goUnfinish: function() {
				uni.navigateTo({
					url: '../my/unfinishOrder'
				})
			},
			gotoother(e){
				if (this.userId!=e){
					uni.navigateTo({
						url:'../my/other?userId='+e
					})
				}
			},
			//获取微信头像昵称
			getuserinfo(e) {
				console.log(2)
				console.log(e.detail.userInfo);
				let user = e.detail.userInfo;
				this.name = user.nickName;
				this.avatar = user.avatarUrl;
				let url = 'https://tjuyjn.top:1314/api/login/'
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
							_this.getmessage()
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
					this.name = res.name;
					this.avatar = res.avatarUrl;
					if (!res.mobile) {
						setTimeout(() => {
							uni.showToast({
								title: '请先绑定手机号',
								icon: 'none'
							});
						}, 100);
						uni.navigateTo({
							url: '../my/sms'
						})
					} else {
						this.mobile = res.mobile
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
			},
			getmessage(){
				this.$http.get('/api/message/getAll/', {
					userId: this.userId
				}).then(res => {
					this.messages = res.data.messages
				}).catch((e) => {});
			},
			agreejoin(e){
				let _this = this
				uni.showModal({
					title: '提示',
					content: '是否确认同意加入你的队伍',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							_this.$http.post('/api/detail/processApply/', {
								applyId:e
							}).then(res => {
								console.log(res)
								uni.showToast({
									title: '操作成功',
									icon: 'success'
								});
								_this.getmessage()
							}).catch(err => {
								console.log(err)
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			gotodetail(e){
				uni.navigateTo({
					url:'../order/detail?Id='+e +'&type='+0
				})
			}
		}
	}
</script>

<style scoped>
	.mine-container {
		background-color: #F8F8F8;
		width:100vw
	}

	.user-box {
		height: 240rpx;
		padding-left: 20rpx;
		display: flex;
		align-items: center;
		margin: 40rpx 0 20rpx;
		overflow: hidden;
		background-color: #fff;
		position: relative;
	}

	.avator-box {
		height: 140rpx;
		width: 140rpx;
		overflow: hidden;
		margin-left: 50upx;
	}

	.avator-box image {
		height: 140rpx;
		width: 140rpx;
		border-radius: 50%;
	}
	
	.loginbtn {
		border: none
	}
	
	.editicon{
		width:30upx;
		height:30upx;
		margin-left:30upx;
		position: relative;
		margin-top:15upx;
	}

	.user-right {
		margin-left: 30rpx;
	}

	.user-id {
		height:60upx;
	}
	
	.user-name{
		font-weight: bold;
		font-size: 45upx;
		line-height: 60upx;
	}
	
	.user-phone {
		margin-top: 20rpx;
		font-size: smaller;
	}

	.order {
		display: flex;
		margin: 20rpx 0;
		background-color: #fff;
		height:190upx;
		justify-content: space-around;
		text-align: center;	
	}

	.order-btn {
		width: 250upx;
		height: 130upx;
		margin-top:30upx;
		border-radius: 10rpx;
		/* vertical-align: middle; */
		align-items: center;
	}
	
	.order-btn text{
		font-size: 30upx;
		flex: 1;
		display: flex;
		justify-content: center;
	}
	
	.msglist{
		width:100vw;
		background: #FFFFFF;
		margin: 20rpx 0;
		padding:20rpx 0;
	}
	
	.title {
		padding-bottom: 20rpx;
		border-bottom: 2upx #e6e6e6 solid;
		display: flex;
		font-size: 40upx;
		font-weight: bold;
		justify-content: center;
		text-align: center;
	}
	
	.title image{
		width:60upx;
	}
	
	.title text{
		line-height: 60upx;
		vertical-align: middle;
	}
	
	.msgbox{
		margin:30upx 0;
		border-bottom: 2upx #e6e6e6 solid;
		padding:0 100upx 30upx;
		position: relative;
	}
	
	.messageavatar{
		width:80upx;
		height:80upx;
		float:left;
	}
	
	.messgaetext{
		margin-left:50upx;
		float:left;
		width:300upx;
		font-size:32upx;
		height:80upx;
	}
	
	.statetext{
		position: absolute;
		top:0;
		right:50upx;
		font-size:30upx;
		line-height: 80upx;
		height:80upx;
	}
</style>
