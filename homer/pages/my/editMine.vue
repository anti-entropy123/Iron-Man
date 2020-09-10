<template>
	<view class="edit-container">
		<view style="text-align: center;">
			<navigation :config="config"/>
		</view>
		<view class="edit-img edit-item">
			<view>头像</view>
			<view>头像</view>
		</view>
		<view class="edit-name edit-item">
			<view>昵称</view>
			<view>昵称</view>
		</view>
		<view class="edit-phone edit-item">
			<view>手机号</view>
			<view>手机号</view>
		</view>
	</view>

</template>

<script>
	export default  {
		data() {
			return{
				config: {
					type:0,//0无title，1有title
					title: '',
					back:true
				}
			}
		}
	}
</script>

<style scoped>
	.edit-container {
		display: flex;
		flex-direction: column;
		background-color: #f0f0f0;
		height: 100vh;
	}

	.edit-item {
		height: 150rpx;
		background-color: #fff;
		border-bottom: 1rpx solid #c3c3c3;
		padding-left: 8rpx;
		padding-right: 45rpx;
		line-height: 150rpx;
		display: flex;
		justify-content: space-between;
		position: relative;
	}

	.edit-item::after {
		content: '>';
		/* position: absolute;
	  right: 0;
	  top:55rpx;
	  height: 40rpx;
	  width: 40rpx;
	  background-color: blue; */
	}
</style>
