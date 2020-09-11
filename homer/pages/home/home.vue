<template>
	<view class="home">
		<view style="text-align: center;">
			<navigation :config="config" />
		</view>

		<view class="main">
			<view class="color-box"></view>
			<view class="search-bar">
				<view class="search-bar__form">
					<icon class="icon-search_in-box" type="search" size="14"></icon>
					<view class="search-bar__box">
						<input type="text" @tap="gotosearch()" class="search-bar__input" placeholder="房屋租售我只看钢铁直男怎样租房" v-model="value1" />
					</view>
				</view>
			</view>
			<view class="iconBox">

			</view>

			<view class="mapButton" @tap="gotomap()">
				<image class="mapImg" mode="aspectFill" src="../../static/map.png" />
				<text class="mapText">点击进入地图模式</text>
			</view>

			<view class="alllist">
				<view class="cardbox" v-for="(card, index) in cards" :key="index" @tap="gotodetail(card.cardId)">
					<image v-if="card.cover" :src='card.cover' class="cardimg" mode="aspectFill"></image>

					<view style="float:left">

						<view class="titlebox">
							<view class="cardtitle">{{card.title}}</view>
							<text>{{"|" + card.square + "㎡"}}</text>
						</view>
						<view style="clear: both;"></view>

						<view class="contentbox">
							<text style="float:left">{{card.location}}</text>
							<view class="cardcontent">{{card.introduce}}</view>
						</view>
						<view style="clear: both;"></view>

						<view class="pricebox">
							<view class="cardprice">{{card.price}}</view>
							<text class="yuan">元</text>
						</view>

					</view>
					<view style="clear: both;"></view>
				</view>
			</view>
			<uni-load-more :loadingType="loadingType" :contentText="contentText"></uni-load-more>
		</view>
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	import uniLoadMore from '../../components/uni-load-more/uni-load-more.vue';
	export default {
		data() {
			return {
				config: {
					type: 1, //0无title，1有title
					title: "首页",
					back: false
				},
				cards: [{
						cardId: "1",
						type: "1",
						title: "出租宿舍出租宿舍出租宿舍出租宿舍",
						cover: "../../static/map.png",
						introduce: "我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗",
						price: "1000",
						square: "100.55",
						location: "诚园8斋"
					},
					{
						cardId: "2",
						type: "1",
						title: "出租宿舍",
						cover: "../../static/map.png",
						introduce: "我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗我要买吗",
						price: "1000",
						square: "100",
						location: "诚园8斋"
					},
				],
				loadingType: 0, //定义加载方式 0---contentdown  1---contentrefresh 2---contentnomore
				contentText: {
					contentdown: '上拉显示更多',
					contentrefresh: '正在加载...',
					contentnomore: '没有更多数据了'
				},
			}
		},
		components: {
			navigation,
			uniLoadMore
		},
		methods: {
			gotomap() {
				uni.navigateTo({
					url: '../home/map'
				})
			},
			gotosearch() {
				uni.navigateTo({
					url: '../home/search'
				})
			},
			gotodetail(e) {
				uni.navigateTo({
					url: '../order/detail?Id=' + e
				})
			},

		},
		onLoad() {
			uni.setStorage({
				key: 'name',
				data: 'yjn',
			});
			uni.setStorage({
				key: 'phone',
				data: '18722648040',
			});
			uni.setStorage({
				key: 'image',
				data: '../../static/yjn.png',
			})
		},

	}
</script>

<style scoped>
	.home {
		background: #ffffff;
		height: 100vh;
	}

	.main {
		position: relative
	}

	.color-box {
		position: absolute;
		height: 25px;
		background: #10518e;
		width: 100%;
		top: -20upx;
	}

	.search-bar {
		position: relative;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		box-sizing: border-box;
		width: 684upx;
		background: transparent;
		margin: 20upx auto;
		box-shadow: 0 5upx 5upx 2upx #e6e6e6;
	}

	.icon-search_in-box {
		position: absolute;
		left: 10px;
		top: 8px;
	}

	.search-bar__form {
		position: relative;
		-webkit-box-flex: 1;
		-webkit-flex: auto;
		flex: auto;
		display: flex;
		flex-direction: row;
		background: #FFFFFF;
		opacity: 1;
	}

	.search-bar__box {
		text-align: left;
		position: relative;
		padding-left: 30px;
		width: 100%;
		box-sizing: border-box;
		z-index: 1;
	}

	.search-bar__input {
		/* height: 76.38upx; */
		height: 30px;
		font-size: 12px;
		/* */
	}

	.iconBox {
		height: 200upx;
	}

	.mapButton {
		width: 720upx;
		height: 150upx;
		position: relative;
		box-shadow: 0px 2px 6px rgba(233, 233, 233, 0.5);
		border-radius: 30upx;
		margin: 14upx auto;
		overflow: hidden;
	}

	.mapImg {
		width: 720upx;
		height: 150upx;
		position: absolute;
		top: 0;
		left: 0;
		opacity: 0.3;
		z-index: 1;
	}

	.mapText {
		color: #707070;
		font-size: 45upx;
		z-index: 2;
		font-weight: bold;
		line-height: 150upx;
		display: flex;
		justify-content: center;
		text-align: center;
	}

	.alllist {
		width: 100%;
	}

	.cardbox {
		margin: 30upx 30upx 0 30upx;
		border-bottom: 2upx #e6e6e6 solid;
		padding: 0;
	}

	.cardimg {
		float: left;
		width: 230upx;
		height: 180upx;
		margin-bottom: 30upx;
	}

	.titlebox {
		margin-left: 15upx;
		float: left;
		font-size: 35upx;
		font-weight: bold;
	}

	.cardtitle {
		float: left;
		display: block;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
		max-width: 266upx;
		margin-right: 6upx;
	}

	.contentbox {
		margin: 10upx 0 10upx 15upx;
		float: left;
		font-size: 25upx;
	}

	.cardcontent {
		margin-left: 20upx;
		float: left;
		display: block;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
		max-width: 330upx;
	}

	.pricebox {
		margin-left: 15upx;
		font-weight: bold;
	}

	.cardprice {
		font-size: 40upx;
		color: #F0AD4E;
		float: left;
	}

	.yuan {
		font-size: 28upx;
		color: #F0AD4E;
	}
</style>
