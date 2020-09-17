<template>
	<view class="home">
		<view style="text-align: center;">
			<navigation :config="config"/>
		</view>
		
		<view class="main">
			<view class="color-box"></view>
			<view class="search-bar">
					<view class="search-bar__form">
						<icon class="icon-search_in-box" type="search" size="14"></icon>
						<view class="search-bar__box">
							<input  type="text" @tap="gotosearch()" class="search-bar__input" placeholder="房屋租售我只看钢铁直男怎样租房" v-model="value1"/>
						</view>
					</view>
			</view>
			<view class="iconBox">
				<view class="icon">
					<image src="../../static/icon1.png" mode="widthFix" style="width:100upx" @tap="gotolist(1)"></image>
					<text class="icontext">出租</text>
				</view>
				<view class="icon">
					<image src="../../static/icon2.png" mode="widthFix" style="width:100upx" @tap="gotolist(2)"></image>
					<text class="icontext">出售</text>
				</view>
				<view class="icon">
					<image src="../../static/icon3.png" mode="widthFix" style="width:100upx" @tap="gotolist(3)"></image>
					<text class="icontext">求租</text>
				</view>
				<view class="icon">
					<image src="../../static/icon4.png" mode="widthFix" style="width:100upx" @tap="gotolist(4)"></image>
					<text class="icontext">求购</text>
				</view>
				<view class="icon">
					<image src="../../static/icon5.png" mode="widthFix" style="width:100upx" @tap="gotolist(5)"></image>
					<text class="icontext">找室友</text>
				</view>
			</view>
			
			
				<view class="mapButton" @tap="gotomap()">
					<image class="mapImg" mode="aspectFill" src="../../static/map.png" />
					<text class="mapText">点击进入地图模式</text>
				</view>
			
			
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
					type:1,//0无title，1有title
					title: "首页",
					back:false
				},
				cards: [],
				loadingType: 0, //定义加载方式 0---contentdown  1---contentrefresh 2---contentnomore
				contentText: {
					contentdown: '上拉显示更多',
					contentrefresh: '正在加载...',
					contentnomore: '没有更多数据了'
				},
				page:1,
				colorstyle:['','chuzucolor','chushoucolor','qiuzucolor','qiushoucolor','zhaoshiyoucolor']
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
		onLoad() {
			uni.showLoading({
				title: '加载中',
				mask: true
			});
			if (uni.getStorageSync('userId')) {
				this.userId = uni.getStorageSync('userId')
				this.getnewList();
			} else {
				uni.hideLoading();
				setTimeout(() => {
					uni.showToast({
						title: '请先授权登录',
						icon: 'none'
					})
				}, 500);
				uni.switchTab({
				   url: '../my/my'　
				})
			}
		},
		onShow() {
			if (uni.getStorageSync('userId')) {
				this.getnewList();
			}
		},
		onPullDownRefresh: function() {
			//下拉刷新的时候请求一次数据
			this.getnewList();
		},
		onReachBottom: function() {
			this.getmorenews();
		},
		methods: {
			gotomap(){
				uni.navigateTo({
					url:'../home/map'
				})
			},
			gotosearch(){
				uni.navigateTo({
					url:'../home/search'
				})
			},
			gotodetail(e,t){
				uni.navigateTo({
					url:'../order/detail?Id='+e +'&type='+t
				})
			},
			gotolist(e){
				uni.navigateTo({
					url:'../order/orderlist?type='+e
				})
			},
			getnewList(){
				this.page = 1;
				this.loadingType = 2;
				uni.showNavigationBarLoading();
				this.$http
					.get('/api/home/getCards/', {
						type:0,
						page:this.page
					})
					.then(res => {
						console.log(res)
						console.log(res.data.cards.length);
						if (res.data.cards.length < 8) {
							console.log('我来了');
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
					.catch(err => {uni.hideLoading()})
			},
			getmorenews(){
				if (this.loadingType !== 0) {
					return false;
				}
				this.loadingType = 1;
				uni.showNavigationBarLoading(); //显示加载动画
				this.$http
					.get('/api/home/getCards/', {
						type:0,
						page:this.page
					})
					.then(res => {
						console.log(res)
						if (res.data.cards == null) {
							console.log(2);
							this.loadingType = 2;
							uni.hideNavigationBarLoading(); //关闭加载动画
							return;
						} else if (res.data.cards.length < 8){
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
		}
	}
</script>

<style>
.home {
	background: #ffffff;
	height: 100vh;
}

.main{
	position:relative
}

.color-box{
	position: absolute;
	height:25px;
	background: #10518e;
	width:100%;
	top:-20upx;
}

.search-bar {
	position: relative;
	display: -webkit-box;
	display: -webkit-flex;
	display: flex;
	box-sizing: border-box;
	width: 684upx;
	background:transparent;
	margin:  20upx auto;
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

.iconBox{
	width:calc(100vw - 60upx);
	padding:0 30upx;
	display:flex;
	justify-content: space-around;
	text-align: center;
	margin-bottom:20upx;
}

.icon{
	width:100upx;
	vertical-align: middle;
	text-align: center;
	margin:10upx 10upx 0;
}

.icontext{
	font-size:30upx;
	color:#707070;
	margin-top:-5upx;
}

/* .mapback{
	background:#F8F8F8;
	width:100%;
	height:190upx;
} */

.mapButton{
	width: 720upx;
	height:150upx;
	position:relative;
	box-shadow:0px 2px 6px rgba(233,233,233,0.5);
	border-radius:30upx;
	margin:20upx auto;
	overflow: hidden;
}
.mapImg{
	width: 720upx;
	height:150upx;
	position:absolute;
	top:0;
	left:0;
	opacity: 0.3;
	z-index: 1;
}
.mapText{
	color:#707070;
	font-size:45upx;
	z-index: 2;
	font-weight: bold;
	line-height: 150upx;
	display:flex;
	justify-content: center;
	text-align: center;
}
.alllist{
	width:100%;
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
	/* background:#F56C6C;
	color:#FFFFFF; */
	color:#F56C6C;
	font-size:30upx;
	line-height: 40upx;
}
.chushoucolor{
	border:4upx #00aaff solid;
	/* background:#00aaff;
	color:#FFFFFF; */
	color:#00aaff;
	font-size:30upx;
	line-height: 40upx;
}
.qiuzucolor{
	border:4upx #E6A23C solid;
	/* background:#E6A23C;
	color:#FFFFFF; */
	color:#E6A23C;
	font-size:30upx;
	line-height: 40upx;
}
.qiushoucolor{
	border:4upx #67C23A solid;
	/* background:#67C23A;
	color:#FFFFFF; */
	color:#67C23A;
	font-size:30upx;
	line-height: 40upx;
}
.zhaoshiyoucolor{
	border:4upx #aa55ff solid;
	/* background:#aa55ff;
	color:#FFFFFF; */
	color:#aa55ff;
	font-size:20upx;
	line-height: 40upx;
}
</style>
