<template>
	<view class="finish-box">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>

		<view class="main">
			<view class="alllist">
				<view class="cardbox" v-for="(card, index) in cards" :key="index" @tap="gotodetail(card.cardId,card.type)">
					<image v-if="card.cover" :src='card.cover' class="cardimg" mode="aspectFill"></image>
		
					<view style="float:left;margin-bottom:30upx;">
		
						<view class="titlebox">
							<view class="cardtitle">{{card.title}}</view>
							<text v-if="card.type==1||card.type==2">{{"|" + card.squares[0] + "㎡"}}</text>
						</view>
						<view style="clear: both;"></view>
		
						<view class="contentbox">
							<text class="cardcontent">{{card.location}}</text>
							<!-- <view class="cardcontent">{{card.introduce}}</view> -->
						</view>
						<view style="clear: both;"></view>
		
						<view class="contentbox" v-if="card.introduction">
							<view class="cardcontent">{{card.introduction}}</view>
						</view>
						<view style="clear: both;"></view>
		
						<view class="pricebox">
							<view class="cardprice" v-if="card.type==1||card.type==2">{{card.prices[0]}}元</view>
							<view class="cardprice" v-else>{{card.prices[0]+"~"+card.prices[1]}}元</view>
						</view>
		
					</view>
		
					<view class="typebox" :class="colorstyle[card.type]">
						<view style="line-height:100upx;">{{card.type|filters1}}</view>
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
					title: "未完成订单",
					back: true
				},
				loadingType: 0, //定义加载方式 0---contentdown  1---contentrefresh 2---contentnomore
				contentText: {
					contentdown: '上拉显示更多',
					contentrefresh: '正在加载...',
					contentnomore: '没有更多数据了'
				},
				page: 1,
				colorstyle: ['', 'chuzucolor', 'chushoucolor', 'qiuzucolor', 'qiushoucolor', 'zhaoshiyoucolor'],
				cards:[],
				userId:'',
			}
		},
		components: {
			navigation,
			uniLoadMore
		},
		filters: {
			filters1: function(arg) {
				if (arg == 1) {
					return '出租';
				} else if (arg == 2) {
					return '出售';
				} else if (arg == 3) {
					return '求租';
				} else if (arg == 4) {
					return '求购';
				} else if (arg == 5) {
					return '找室友';
				}
			},
		},
		onLoad(options) {
			if (uni.getStorageSync('userId')) {
				this.userId = uni.getStorageSync('userId')
			} else {
				setTimeout(() => {
					uni.showToast({
						title: '请先授权登录',
						icon: 'none'
					})
				}, 100);
				uni.switchTab({
					url: '../my/my'
				})
			}
			this.getnewList();
		},
		onShow() {
			this.getnewList();
		},
		onPullDownRefresh: function() {
			//下拉刷新的时候请求一次数据
			this.getnewList();
		},
		onReachBottom: function() {
			this.getmorenews();
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			gotodetail(e,t){
				uni.navigateTo({
					url:'../order/detail?Id='+e +'&type='+t
				})
			},
			getnewList() {
				this.page = 1;
				this.loadingType = 2;
				uni.showNavigationBarLoading();
				this.$http
					.get('/api/incomplete/all/', {
						page: this.page,
						userId: this.userId
					})
					.then(res => {
						console.log(res)
						console.log(res.data.cards.length);
						if (res.data.cards.length < 8) {
							this.loadingType = 2;
						} else {
							this.loadingType = 0;
						}
						this.page = 2; // 下次加载第2页
						this.cards = res.data.cards;
			
						uni.hideNavigationBarLoading(); //关闭加载动画
						uni.stopPullDownRefresh(); //得到数据后停止下拉刷新
						uni.hideLoading();
					})
					.catch(err => {
						uni.hideLoading()
					})
			},
			getmorenews() {
				if (this.loadingType !== 0) {
					return false;
				}
				this.loadingType = 1;
				uni.showNavigationBarLoading(); //显示加载动画
				this.$http
					.get('/api/incomplete/all/', {
						page: this.page,
						userId: this.userId
					})
					.then(res => {
						console.log(res)
						if (res.data.cards == null) {
							console.log(2);
							this.loadingType = 2;
							uni.hideNavigationBarLoading(); //关闭加载动画
							return;
						} else if (res.data.cards.length < 8) {
							this.loadingType = 2;
							uni.hideNavigationBarLoading(); //关闭加载动画
						} else {
							this.loadingType = 0; //将loadingType归0重置
							uni.hideNavigationBarLoading(); //关闭加载动画
						}
						this.page++;
						this.cards = this.cards.concat(res.data.cards); //将数据拼接在一起
					})
					.catch(err => {});
			}
		},
	}
</script>

<style scoped>
	.finish-box {
		
	}
	
	.alllist{
		width:100%;
	}
	
	.margintop{
		margin-top:290upx;
	}
	
	.cardbox{
		margin:30upx 30upx 0 30upx;
		border-bottom: 2upx #e6e6e6 solid;
		padding:0;
		position: relative;
	}
	.cardimg{
		float:left;
		width:230upx;
		height:180upx;
		margin-bottom:30upx;
	}
	.titlebox{
		margin-left:15upx;
		float:left;
		font-size:35upx;
		font-weight: bold;
	}
	.cardtitle{
		float:left;
		display:block;
		text-overflow:ellipsis;
		white-space:nowrap;
		overflow:hidden;
		max-width:266upx;
		margin-right:6upx;
	}
	.contentbox{
		margin: 0 0 0 15upx;
		float:left;
		font-size:25upx;
	}
	.cardcontent{
		float:left;
		display:block;
		text-overflow:ellipsis;
		white-space:nowrap;
		overflow:hidden;
		max-width:330upx;
	}
	.pricebox{
		margin-left:15upx;
		margin-top:10upx;
		font-weight: bold;
	}
	.cardprice{
		font-size:40upx;
		color:#FF7900;
	}
	.typebox{
		width:80upx;
		height:40upx;
		border-radius: 10upx;
		position:absolute;
		bottom:30upx;
		right:10upx;
		display: flex;
		align-items: center;
		justify-content: space-around;
		font-weight: bold;
		opacity: 0.7;
	}
	.chuzucolor{
		border:4upx #F56C6C solid;
		color:#F56C6C;
		font-size:30upx;
		line-height: 40upx;
	}
	.chushoucolor{
		border:4upx #00aaff solid;
		color:#00aaff;
		font-size:30upx;
		line-height: 40upx;
	}
	.qiuzucolor{
		border:4upx #E6A23C solid;
		color:#E6A23C;
		font-size:30upx;
		line-height: 40upx;
	}
	.qiushoucolor{
		border:4upx #67C23A solid;
		color:#67C23A;
		font-size:30upx;
		line-height: 40upx;
	}
	.zhaoshiyoucolor{
		border:4upx #aa55ff solid;
		color:#aa55ff;
		font-size:20upx;
		line-height: 40upx;
	}
</style>