<template>
	<view class="sms">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>
		
		<image src="../../static/background.png" mode="widthFix" class="background"></image>
		
		<image src="../../static/MBRYlogo.png" style="width:500upx;margin:250upx auto 80upx;display: flex;" mode="widthFix"></image>
		
		<view class="inputinfo" style="margin:100upx 0">
				
			<view class="search-bar" style="margin-top: 16upx;">
				<view class="search-bar__form">
					<image class="icon-search_in-box" src="../../static/phone.png"></image>
					<view class="search-bar__box1">
						<input type="number" class="search-bar__input" placeholder="请输入您的手机号" v-model="phone" style="width: 250upx;" />
					</view>
					<button @tap="handlegetcode()" :disabled="CodeStatus" class="codebtn">{{ CodeBtn }}</button>
				</view>
			</view>
			<view class="search-bar" style="margin-top: 16upx;">
				<view class="search-bar__form">
					<image class="icon-search_in-box" src="../../static/message.png"></image>
					<view class="search-bar__box"><input type="number" class="search-bar__input" placeholder="请输入您的验证码" v-model="code" /></view>
				</view>
			</view>
			
		</view>
		
		<image class="surebtn" @tap="handlesure()" src="../../static/surebtn.png" mode="widthFix">确定</image>
		
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				config: {
					type: 1, //0无title，1有title
					title: "手机验证",
					back: true,
				},
				CodeStatus: false,
				CodeBtn: '获取验证码',
				phone: '',
				code: '',
			}
		},
		components: {
			navigation
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			handlegetcode() {
				const mobile = /^[1][3,4,5,7,8,6][0-9]{9}$/;
				if (/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.phone)) {
					this.$http
						.post('/api/base/getCheckCode/', {
							number: this.phone
						})
						.then(res => {
							this.timeOut();
							uni.showToast({
								title: '获取验证码成功',
								icon: 'none'
							});
						})
						.catch(err => {
							console.log(err);
						});
				} else {
					uni.showToast({
						title: '手机号格式错误',
						icon: 'none'
					});
				}
			},
			timeOut() {
				// 获取验证码按钮倒计时
				let t = 60000;
				this.CodeStatus = true;
				let _this = this;
				let timeOut = setInterval(function() {
					t -= 1000;
					_this.CodeBtn = t / 1000;
					if (t <= 0) {
						clearTimeout(timeOut);
						_this.CodeBtn = '获取验证码';
						_this.CodeStatus = false;
					}
				}, 1000);
			},
			handlesure(){
				if (this.code==''||this.phone==''){
					uni.showToast({
						title: '请填写完整信息',
						icon: 'none'
					});
					return;
				}
				this.$http
					.post('/api/base/bindMobile/', {
						checkcode: this.code,
						mobileNumber: this.phone
					})
					.then(res => {
						setTimeout(() => {
							uni.showToast({
								title: '绑定成功',
								icon: 'success'
							});
						}, 100);
						uni.switchTab({
							url:'../my/my'
						})
					})
					.catch(err => {
						console.log(err);
					});
			}
		},
	}
</script>

<style lang="scss">
	.search-bar {
		position: relative;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		box-sizing: border-box;
		align-items: center;
		width: 530upx;
		margin-left: 106upx;
		border-bottom: 1px solid #10518E;
	}
	
	.icon-search_in-box {
		position: absolute;
		left: 10px;
		top: 9px;
		width: 50upx;
		height: 50upx;
	}
	
	.search-bar__form {
		position: relative;
		-webkit-box-flex: 1;
		-webkit-flex: auto;
		flex: auto;
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	
	.search-bar__box {
		text-align: left;
		position: relative;
		padding-left: 50px;
		/* 	padding-right: 30px; */
		width: 100%;
		box-sizing: border-box;
		z-index: 1;
	}
	.search-bar__box1 {
		text-align: left;
		position: relative;
		padding-left: 50px;
		/* 	padding-right: 30px; */
	
		box-sizing: border-box;
		z-index: 1;
	}
	.search-bar__input {
		/* height: 76.38upx; */
		height: 38px;
		line-height: 38px;
		font-size: 14px;
		color: rgba(112, 112, 112, 1);
	}
	
	.search-bar__cancel-btn {
		margin-left: 10px;
		line-height: 28px;
		color: #09bb07;
		white-space: nowrap;
	}
	.in_info {
		box-sizing: border-box;
		width: 588.88upx;
		height: 74.3upx;
		background: rgba(226, 200, 26, 1);
		border-radius: 20.83upx;
		font-size: 34.72upx;
		color: rgba(121, 108, 19, 1);
		text-align: left;
		padding-left: 29.16upx;
	}
	.main {
		display: flex;
		justify-content: center;
		align-items: center;
		margin: 31.25upx 0;
	}
	.main text {
		width: 100px;
		text-align: right;
	}
	.surebtn{
		width:250upx;
		margin-left:-125upx;
		z-index:10;
		position:relative;
		top:100upx;
		left:50%;
	}
	.content {
		text-align: center;
	}
	.codebtn {
		width: 188upx;
		height: 56upx;
		line-height: 56upx;
		background: #45a4d6;
		border: 2upx solid #45a4d6;
		border-radius: 28upx;
		color: rgba(255, 255, 255, 1);
		font-size: 12px;
		font-weight: bold;
		padding:0;
	}
	
	.background{
		width:100vw;
		position: absolute;
		bottom:0;
		left:0
	}
	
</style>
