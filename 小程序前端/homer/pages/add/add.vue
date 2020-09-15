<template>
	<view class="add-box">
		<view style="text-align: center;">
			<navigation :config="config" />
		</view>
		<view class="publish-title">
			发布类型
		</view>
		<view class="publish-container">
			<view class="chuzu publish-item-left" @tap="gotoadd(1)">我要出租</view>
			<view class="zufang publish-item-right" @tap="gotoadd(2)">我要出售</view>
			<view class="chushou publish-item-left" @tap="gotoadd(3)">我要求租</view>
			<view class="maifang publish-item-right" @tap="gotoadd(4)">我要求购</view>
			<view class="find-friends publish-item-left" style="width:calc(100vw - 40px);" @tap="gotoadd(5)">我要找室友</view>
			<view style="clear: both;"></view>
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
		components:{
			navigation
		},
		methods: {
			gotoadd(e){
				uni.navigateTo({
					url:'../add/adddetail?type='+e
				})
			}

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
		margin: 20px;
		width:calc(100vw - 40px);
	}

	.publish-item-left {
		float:left;
		height:200upx;
		width:320upx;
		margin-bottom:20upx;
		border-radius: 10rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		box-shadow: 6rpx 6rpx 6rpx #c0c0c0;
		color:#fff;
	}
	
	.publish-item-right {
		float:right;
		height:200upx;
		width:320upx;
		margin-bottom:20upx;
		border-radius: 10rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		box-shadow: 6rpx 6rpx 6rpx #c0c0c0;
		color:#fff;
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
