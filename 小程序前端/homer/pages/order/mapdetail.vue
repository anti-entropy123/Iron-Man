<template>
	<view class="map">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct"/>
		</view>
		<view style="height:100%;width:100%">
			<map id="map" :longitude=longitude :latitude=latitude scale="16" :markers="markers" @tap="marktap" show-location style="width: 100%; height:100%;"></map>
		</view>
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				markers: [],
				config: {
					type:1,//0无title，1有title
					title: "地图",
					back:true,
				},
				card:{},
				longitude:117.312269,
				latitude:38.994228,
			}
		},
		components: {
			navigation
		},
		onLoad(options) {
			this.cardId = options.card;
			this.getdetail()
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			marktap(e){
				
			},
			changearea1(){
				this.area = 0 ;
			},
			changearea2(){
				this.area = 1 ;
			},
			getdetail() {
				let _this = this
				this.$http.get('/api/detail/getCardDetail/', {
					cardId: this.cardId
				}).then(res => {
					console.log(res)
					_this.card = res.data
					_this.pushmarkers()
				}).catch(err => {
					console.log(err)
				})
			},
			pushmarkers(){
				this.latitude = this.card.coordinates[0]
				this.longitude = this.card.coordinates[1]
				if (this.card.type==1){
					this.markers[0]={
						iconPath: "../../static/markIcon2.png",
						id: 0,
						latitude: this.card.coordinates[0],
						longitude: this.card.coordinates[1],
						width: 30,
						height: 30,
						callout:{
							content:this.card.location.replace(/[^\x00-\xff]/g,"$&\x01").replace(/.{19}\x01?/g,"$&\n").replace(/\x01/g,""),
							borderRadius:10,
							display:'BYCLICK',
							padding:10,
						}
					}
				} else {
					this.markers[0]={
						iconPath: "../../static/markIcon.png",
						id: 0,
						latitude: this.card.coordinates[0],
						longitude: this.card.coordinates[1],
						width: 30,
						height: 30,
						callout:{
							content:this.card.location.replace(/[^\x00-\xff]/g,"$&\x01").replace(/.{19}\x01?/g,"$&\n").replace(/\x01/g,""),
							borderRadius:10,
							display:'BYCLICK',
							padding:10,
						}
					}
				}
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

