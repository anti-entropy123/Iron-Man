<template>
	<view class="add-box">
		<view style="text-align: center;">
			<navigation :config="config" />
		</view>
		<view class="publish-title">
			发布类型
		</view>
		<view class="publish-container">
			<view class="chuzu publish-item" @tap="goPublishDetail('出租')">我要出租</view>
			<view class="zufang publish-item" @tap="goPublishDetail('租房')">我要租房</view>
			<view class="chushou publish-item" @tap="goPublishDetail('出售')">我要出售</view>
			<view class="maifang publish-item" @tap="goPublishDetail('买房')">我要买房</view>
			<view class="find-friends publish-item" @tap="goPublishDetail('找室友')">我要找室友</view>
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
					title: "发布",
					back: false
				},
			}
		},
		components: {
			navigation,
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			goPublishDetail(e) {
				uni.navigateTo({
					url: '../add/addDetail?type=' + e
				})

			},

		}
	}
</script>

<style scoped>
	/* pages/publish/publish.wxss */
	.publish-title {
		display: flex;
		justify-content: center;
		align-items: center;
		height: 100rpx;
		font-size: xx-large;
		margin-top: 20rpx;
	}

	.publish-container {
		display: grid;
		grid-template-columns: 50% 50%;
		grid-template-rows: 100px 100px 100px;
		grid-row-gap: 20rpx;
		grid-column-gap: 20rpx;
		padding: 20px;
	}

	.publish-item {
		border-radius: 10rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		box-shadow: 6rpx 6rpx 6rpx #c0c0c0;
		color: #fff;
	}

	.chuzu {
		background-color: #F56C6C;
	}

	.chuzu:active {
		background-color: #f0ec77;
	}

	.zufang {
		background-color: #00aaff;
	}

	/* 	.zufang:active {
		background-color: #6ace65;
	} */

	.chushou {
		background-color: #E6A23C;
	}

	/* 	.chushou:active {
		background-color: #759ae9;
	}
 */
	.maifang {
		background-color: #67C23A;
	}

	/* 	.maifang:active {
		background-color: #b870eb;
	} */

	.find-friends {
		background-color: #aa55ff;
		grid-column-start: 1;
		grid-column-end: 3;
	}

	/* 	.find-friends:active {
		background-color: #eea358;
		grid-column-start: 1;
		grid-column-end: 3;
	} */
</style>
