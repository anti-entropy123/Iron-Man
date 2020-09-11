<template>
	<view class="finish-box">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>

		<view class="finish-order-list">
			<view class="card-box" v-for="(card, index) in finishCards" :key="index" @tap="gotodetail(card.cardId)">
				<view class="finish-order-title">
					<view class="finish-order-date">{{card.date}}</view>
					<view class="finish-order-type">{{card.type|filters}}</view>
				</view>
				<view>
					<image v-if="card.cover" :src='card.cover' class="card-img" mode="aspectFill"></image>
					<view style="float:left">

						<view class="title-box">
							<view class="card-title">{{card.title}}</view>
							<text>{{"|" + card.square + "㎡"}}</text>
						</view>
						<view style="clear: both;"></view>

						<view class="content-box">
							<text style="float:left">{{card.location}}</text>
							<view class="card-content">{{card.introduce}}</view>
						</view>
						<view style="clear: both;"></view>

						<view class="price-box">
							<view class="card-price">{{card.price}}</view>
							<text class="yuan">元</text>
						</view>

					</view>
					<view style="clear: both;"></view>
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
				config: {
					type: 1, //0无title，1有title
					title: "完成订单",
					back: true
				},
				finishCards: [{
						cardId: "1",
						type: "1",
						title: "出租宿舍出租宿舍出租宿舍出租宿舍",
						cover: "../../static/map.png",
						introduce: "我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗",
						price: "1000",
						square: "100.55",
						location: "诚园8斋",
						date: "2020/09/10"
					},
					{
						cardId: "2",
						type: "2",
						title: "出租宿舍",
						cover: "../../static/map.png",
						introduce: "我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗",
						price: "1000",
						square: "100",
						location: "诚园8斋",
						date: "2020/09/11"
					},
					{
						cardId: "2",
						type: "3",
						title: "出租宿舍",
						cover: "../../static/map.png",
						introduce: "我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗",
						price: "1000",
						square: "100",
						location: "诚园8斋",
						date: "2020/09/12"
					},
					{
						cardId: "2",
						type: "4",
						title: "找室友",
						cover: "../../static/map.png",
						introduce: "我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗",
						price: "1000",
						square: "100",
						location: "诚园8斋",
						date: "2020/09/13"
					},
				],
			}
		},
		components: {
			navigation,
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			gotodetail(e) {
				uni.navigateTo({
					url: '../order/detail?Id=' + e
				})
			},

		},
		filters: {
			filters: function(arg) {
				if (arg == 1) {
					return '出租单';
				} else if (arg == 2) {
					return '出售单';
				} else if (arg == 3) {
					return '求租单';
				} else if (arg == 4) {
					return '求购单';
				} else if (arg == 5) {
					return '找室友单';
				}
			},
		}
	}
</script>

<style scoped>
	.finish-box {
		background: #f4f4f4;
		height: 100vh;
	}

	.finish-order-list {
		width: 100%;
	}

	.finish-order-title {
		display: flex;
		justify-content: space-between;
	}

	.card-box {
		margin: 30upx 30upx 0 30upx;
		border-bottom: 2upx #e6e6e6 solid;
		padding: 0;
	}

	.card-img {
		float: left;
		width: 230upx;
		height: 180upx;
		margin-bottom: 30upx;
	}

	.title-box {
		margin-left: 15upx;
		float: left;
		font-size: 35upx;
		font-weight: bold;
	}

	.card-title {
		float: left;
		display: block;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
		max-width: 266upx;
		margin-right: 6upx;
	}

	.content-box {
		margin: 10upx 0 10upx 15upx;
		float: left;
		font-size: 25upx;
	}

	.card-content {
		margin-left: 20upx;
		float: left;
		display: block;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
		max-width: 330upx;
	}

	.price-box {
		margin-left: 15upx;
		font-weight: bold;
	}

	.card-price {
		font-size: 40upx;
		color: #F0AD4E;
		float: left;
	}

	.yuan {
		font-size: 28upx;
		color: #F0AD4E;
	}
</style>
