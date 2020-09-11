<template>
	<view class="edit-container">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>
		<view class="edit-img edit-item-image" @tap="upLoadImage">
			<view class="edit-item-title">头像</view>
			<view class="edit-img-up">
				<image :src="userInfo.image"></image>
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
		<view class="edit-phone edit-item" @tap="modifyPhone">
			<view class="edit-item-title">手机号</view>
			<view>{{userInfo.phone}}</view>
			<image src="../../static/right.png" class="edit-item-right"></image>
		</view>
		<view class="edit-sex edit-item" @tap="modifySex">
			<view class="edit-item-title">性别</view>
			<view>{{userInfo.sex}}</view>
			<image src="../../static/right.png" class="edit-item-right"></image>
		</view>
		<view class="edit-profile edit-item" @tap="modifyProfile">
			<view class="edit-item-title">个人简介:</view>
			<view class="profile">{{userInfo.profile}}</view>
			<image src="../../static/right.png" class="edit-item-right "></image>
		</view>
	</view>
	</view>

</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		onLoad() {
			let _this = this
			uni.getStorage({
				key: 'image',
				success(e) {
					_this.userInfo.image = e.data //这就是你想要取的token
				}
			})
			uni.getStorage({
				key: 'phone',
				success(e) {
					_this.userInfo.phone = e.data //这就是你想要取的token
				}
			})
			uni.getStorage({
				key: 'name',
				success(e) {
					_this.userInfo.name = e.data //这就是你想要取的token
				}
			})
		},
		data() {
			return {

				config: {
					type: 0, //0无title，1有title
					title: '个人资料',
					back: true
				},
				userInfo: {
					name: '',
					image: '',
					phone: '',
					sex: '男',
					profile: '解甲归田,只为月光'
				}
			}
		},
		components: {
			navigation,
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			upLoadImage() {
				console.log("image")
			},
			modifyName() {
				console.log("name")
			},
			modifyPhone() {
				console.log("phone")
			},
			modifySex() {
				console.log("sex")
			},
			modifyProfile() {
				console.log("profile")
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
</style>
