<template>
	<view class="orderlist">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>
		
		<view class="navigation" v-bind:class="type!=5? 'fixedheight':'' ">
			<view class="weui-search-bar">
					<view class="weui-search-bar__form">
						<icon class="weui-icon-search_in-box" type="search" size="14"></icon>
						<view class="weui-search-bar__box">
							<input type="text" @confirm="getnewList()" class="weui-search-bar__input" placeholder="请在此处输入您想搜索的位置..." v-model="location"/>
						</view>
					</view>
			</view>
			
			<view class="remove" @tap="removeBY">恢复默认设置</view>
			
			<view style="margin-top:30upx">
				<view style="margin-left: 30upx;display: flex;align-items: center;">
					<text style="font-size: 22upx;color:#515151;font-family:PingFang SC">价格:</text>
					<view style="align-items: center;margin-left: 10upx;">
						<input type="digit" value="" placeholder="最低价格" v-model="minPrice" class="input_digit" @input="onKeyInput"/>
						<view style="float:left;margin-left:10upx;"> ~ </view>
						<input type="digit" value="" placeholder="最高价格" v-model="maxPrice" class="input_digit" @input="onKeyInput"/>
						<view style="float:left;margin-left:10upx;font-size: 22upx;color:#515151;font-family:PingFang SC;line-height:40upx"> 元 </view>
					</view>
				</view>
			</view>
			
			<view style="margin-top:18upx">
				<view style="margin-left: 30upx;display: flex;align-items: center;">
					<text style="font-size: 22upx;color:#515151;font-family:PingFang SC">面积:</text>
					<view style="align-items: center;margin-left: 10upx;">
						<input type="digit" value="" placeholder="最小面积" v-model="minSquare" class="input_digit" @input="onKeyInput"/>
						<view style="float:left;margin-left:10upx;"> ~ </view>
						<input type="digit" value="" placeholder="最大面积" v-model="maxSquare" class="input_digit" @input="onKeyInput"/>
						<view style="float:left;margin-left:10upx;font-size: 22upx;color:#515151;font-family:PingFang SC;line-height:40upx"> ㎡ </view>
					</view>
				</view>
			</view>
			
			<view style="margin-top:18upx;margin-bottom:18upx">
				<view style="margin-left: 30upx;display: flex;align-items: center;">
					<!-- <image src="../../static/homelist_time.png" mode="widthFix" style="width: 32upx;float:left;"></image> -->
					<text style="font-size: 22upx;color:#515151;font-family:PingFang SC">户型:</text>
					<radio-group @change="checkunittype" style="margin-left:20upx;">
						<label>
							<radio value="0" :checked="remove[0]"/>
							<text style="color:#515151;font-size:22upx;font-family:PingFang SC;">一室一厅</text>
						</label>
						<label style="margin-left:10upx;">
							<radio value="1" :checked="remove[1]"/>
							<text style="color:#515151;font-size:22upx;font-family:PingFang SC;">两室一厅</text>
						</label>
						<label style="margin-left:10upx;">
							<radio value="2" :checked="remove[2]"/>
							<text style="color:#515151;font-size:22upx;font-family:PingFang SC;">三室一厅</text>
						</label>
						<label style="margin-left:10upx;">
							<radio value="3" :checked="remove[3]"/>
							<text style="color:#515151;font-size:22upx;font-family:PingFang SC;">三室两厅</text>
						</label>
						<label style="margin-left:10upx;">
							<radio value="4" :checked="remove[4]"/>
							<text style="color:#515151;font-size:22upx;font-family:PingFang SC;">其他</text>
						</label>
					</radio-group>
				</view>
			</view>
			
			<view style="margin-top:18upx;margin-bottom:18upx" v-if="type==5">
				<view style="margin-left: 30upx;display: flex;align-items: center;">
					<!-- <image src="../../static/homelist_select.png" mode="widthFix" style="width: 32upx;float:left;"></image> -->
					<text style="font-size: 22upx;color:#515151;font-family:PingFang SC;">房源:</text>
					<radio-group @change="checkhashouse" style="margin-left:20upx;">
						<label>
							<radio value="1" :checked="remove1"/>
							<text style="color:#515151;font-size:22upx;font-family:PingFang SC;">有房源</text>
						</label>
						<label style="margin-left:30upx;">
							<radio value="0" :checked="remove2"/>
							<text style="color:#515151;font-size:22upx;font-family:PingFang SC;">无房源</text>
						</label>
					</radio-group>
				</view>
			</view>
			
		</view>
		
		<view class="main" v-bind:class="type!=5? 'margintop':'' ">
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
					type: 2, //0无title，1有title
					renttype: 0,
					back: true,
				},
				type: 0,
				key: '',
				cards: [],
				loadingType: 0, //定义加载方式 0---contentdown  1---contentrefresh 2---contentnomore
				contentText: {
					contentdown: '上拉显示更多',
					contentrefresh: '正在加载...',
					contentnomore: '没有更多数据了'
				},
				page: 1,
				colorstyle: ['', 'chuzucolor', 'chushoucolor', 'qiuzucolor', 'qiushoucolor', 'zhaoshiyoucolor'],
				remove1:false,
				remove2:false,
				remove:[false,false,false,false,false],
				hasHouseResource:'',
				unitType:'',
				minPrice:'',
				maxPrice:'',
				minSquare:'',
				maxSquare:'',
				location:''
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
			console.log(options.type)
			console.log(options.key)
			this.config.renttype = options.type
			this.type = options.type
			if (options.key){
				this.location=options.key
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
			onKeyInput(){
				this.getnewList()
			},
			checkhashouse(e){
				if (e.detail.value == 1){
					this.remove1 = true
					this.hasHouseResource = true
				}
				else {
					this.remove2 = true
					this.hasHouseResource = false
				}
				this.getnewList()
			},
			checkunittype(e){
				this.unitType = e.detail.value
				this.remove[e.detail.value] = true
				this.getnewList()
			},
			removeBY(){
				this.remove1=false
				this.remove2=false
				this.remove=[false,false,false,false,false]
				this.hasHouseResource=''
				this.unitType=''
				this.minPrice=''
				this.maxPrice=''
				this.minSquare=''
				this.maxSquare=''
				this.location=''
				this.getnewList()
			},
			getnewList() {
				this.page = 1;
				this.loadingType = 2;
				uni.showNavigationBarLoading();
				this.$http
					.get('/api/home/getCards/', {
						type: this.type,
						page: this.page,
						hasHouseResource: this.hasHouseResource,
						unitType: this.unitType,
						minPrice: this.minPrice,
						maxPrice: this.maxPrice,
						minSquare: this.minSquare,
						maxSquare: this.maxSquare,
						location:this.location
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
					.get('/api/home/getCards/', {
						type: this.type,
						page: this.page,
						hasHouseResource: this.hasHouseResource,
						unitType: this.unitType,
						minPrice: this.minPrice,
						maxPrice: this.maxPrice,
						minSquare: this.minSquare,
						maxSquare: this.maxSquare,
						location:this.location
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
		}
	}
</script>

<style>
	.navigation{
		position:fixed;
		height:340upx;
		width:100%;
		background-color: white;
		font-size: 30upx;
		font-family: SFProDisplay-Bold;
		box-shadow: 0upx 4upx 4upx 2upx rgba(242, 242, 242, 1);
		z-index:10;
	}
	.fixedheight{
		height:290upx;
	}
	.weui-search-bar {
		background: #F8F8F8;
		position: relative;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		box-sizing: border-box;
		width: 684upx;
		background:transparent;
		border-radius: 10upx;
		margin:  20upx auto;
	}
	.weui-icon-search_in-box {
		position: absolute;
		left: 20upx;
		top: 15upx;
	}
			
	.weui-search-bar__form {
		position: relative;
		-webkit-box-flex: 1;
		-webkit-flex: auto;
		flex: auto;
		display: flex;
		flex-direction: row;
		border-radius: 10upx;
		background: #F8F8F8;
		opacity: 0.7;
	}
			
	.weui-search-bar__box {
		text-align: left;
		position: relative;
		padding-left: 30px;
		width: 100%;
		box-sizing: border-box;
		z-index: 1;
	}
			
	.weui-search-bar__input {
		/* height: 76.38upx; */
		height: 60upx;
		line-height: 60upx;
		font-size: 24upx;
		/* */
	}
			
	.weui-search-bar__cancel-btn {
		margin-left: 10px;
		line-height: 28px;
		color: #09BB07;
		white-space: nowrap;
	}
	
	.remove{
		width:128upx;
		height:34upx;
		line-height: 34upx;
		text-align: center;
		color:#969595;
		font-size:16upx;
		border: 2upx solid #CAC8C8;
		border-radius: 20upx;
		position:absolute;
		right:30upx;
		top:112upx;
	}
	
	.input_digit {
		float:left;
		margin-left:10upx;
		font-size: 22upx;
		width:150upx;
		height:40upx;
		min-height: 40upx;
		background:#FFFFFF;
		border:2upx solid #F2F2F2;
		border-radius:4upx;
		line-height: 40upx;
		padding-left:20upx;
	}
	
	/* 默认背景样式*/
	radio .wx-radio-input{
	  width: 20upx;
	  height: 20upx;
	}
	
	/* 选中后的 背景样式 （红色背景 无边框 可根据UI需求自己修改） */
	radio .wx-radio-input.wx-radio-input-checked{
	  width: 20upx; /* 选中后对勾大小，不要超过背景的尺寸 */
	  height: 20upx; /* 选中后对勾大小，不要超过背景的尺寸 */
	  border: none;
	  background: #FEA951;
	}
	/* 选中后的 对勾样式 （白色对勾 可根据UI需求自己修改） */
	radio .wx-radio-input.wx-radio-input-checked::before{
	  border-radius: 50%;/* 圆角 */
	  width: 20upx; /* 选中后对勾大小，不要超过背景的尺寸 */
	  height: 20upx; /* 选中后对勾大小，不要超过背景的尺寸 */
	  line-height: 20upx;
	  text-align: center;
	  font-size:15upx; /* 对勾大小 30rpx */
	  color:#FEA951;
	  border: 2upx solid #FEA951;
	  background: #FEA951;
	  transform:translate(-50%, -50%) scale(1);
	  -webkit-transform:translate(-50%, -50%) scale(1);
	}
	
	.alllist{
		width:100%;
		margin-top:340upx;
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
