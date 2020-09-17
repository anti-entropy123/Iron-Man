<template>
	<view class="map">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct"/>
		</view>
		<view style="height:100%;width:100%">
			<map v-if="area==0" id="map" longitude="117.312269" latitude="38.994228" scale="15" :markers="markers" @markertap="marktap" @callouttap="gotodetail" show-location style="width: 100%; height:100%;"></map>
			<map v-if="area==1" id="map" longitude="117.172880" latitude="39.108819" scale="16" :markers="markers" @markertap="marktap" @callouttap="gotodetail" show-location style="width: 100%; height:100%;"></map>
			<cover-view class="changebtn">
				<cover-view v-bind:class="area==0?'selectedbtn':'unselectedbtn'" @tap="changearea1">
					北洋园
				</cover-view>
				<cover-view v-bind:class="area==1?'selectedbtn':'unselectedbtn'" @tap="changearea2">
					卫津路
				</cover-view>
			</cover-view>
			<!-- <cover-view class="card" :class="cardState">
				<cover-view id="upbutton" :class="cardState" @tap="openclose">
					<cover-image src="../../static/up.png" mode="" class="upIcon"></cover-image>
				</cover-view>
				<cover-view style="background: #10518e;height:500upx;" scroll-top=""></cover-view>
			</cover-view> -->
		</view>
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				area:0,
				markers: [],
					
				config: {
					type:1,//0无title，1有title
					title: "地图模式",
					back:true,
				},
				cards:[]
			}
		},
		components: {
			navigation
		},
		onLoad(){
			this.getmarkers()
		},
		onShow(){
			this.getmarkers()
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			marktap(e){
				console.log(e.detail.markerId)
			},
			gotodetail(e){
				uni.navigateTo({
					url:'../order/detail?Id='+this.cards[e.detail.markerId].cardId +'&type='+this.cards[e.detail.markerId].type
				})
			},
			changearea1(){
				this.area = 0 ;
			},
			changearea2(){
				this.area = 1 ;
			},
			getmarkers(){
				this.$http.get('/api/home/getCardsWithCoordinates/').then(res => {
					console.log(res)
					this.cards = res.data.cards
					let cards = res.data.cards
					for(let i = 0;i<cards.length;i++){
						if (cards[i].coordinates[0]){
							if (cards[i].type==1){
								this.markers.push({
									iconPath: "../../static/markIcon2.png",
									id: i,
									latitude: cards[i].coordinates[0],
									longitude: cards[i].coordinates[1],
									width: 25,
									height: 25,
									callout:{
										content:("标题："+"出租 | "+cards[i].title).replace(/[^\x00-\xff]/g,"$&\x01").replace(/.{19}\x01?/g,"$&\n").replace(/\x01/g,"")+"\n"+("位置："+cards[i].location).replace(/[^\x00-\xff]/g,"$&\x01").replace(/.{19}\x01?/g,"$&\n").replace(/\x01/g,"")+"\n\n点击查看详情",
										borderRadius:10,
										display:'BYCLICK',
										padding:10,
									}
								})
							} else {
								this.markers.push({
									iconPath: "../../static/markIcon.png",
									id: i,
									latitude: cards[i].coordinates[0],
									longitude: cards[i].coordinates[1],
									width: 25,
									height: 25,
									callout:{
										content:("标题："+"出售 | "+cards[i].title).replace(/[^\x00-\xff]/g,"$&\x01").replace(/.{19}\x01?/g,"$&\n").replace(/\x01/g,"")+"\n"+("位置："+cards[i].location).replace(/[^\x00-\xff]/g,"$&\x01").replace(/.{19}\x01?/g,"$&\n").replace(/\x01/g,"")+"\n\n点击查看详情",
										borderRadius:10,
										display:'BYCLICK',
										padding:10,
									}
								})
							}
						}
					}
				}).catch(err => {
					console.log(err)
				})
			}
		}
	}
</script>

<style>
.map{
	height:100vh
}
.changebtn{
	position:absolute;
	top:200upx;
	left:50upx;
	width:200upx;
	height:50upx;
	border-radius: 25upx;
	background: #ffffff;
	display: flex;
	box-shadow: 0 5upx 5upx 2upx #e6e6e6;
}
.selectedbtn{
	width:50%;
	background: #10518e;
	color:#ffffff;
	font-size:24upx;
	line-height:50upx;
	text-align:center;
}
.unselectedbtn{
	width:50%;
	background: #ffffff;
	color:#000000;
	font-size:24upx;
	line-height:50upx;
	text-align:center;
}

</style>
