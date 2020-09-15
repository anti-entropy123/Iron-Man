<template>
	<view class="map">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct"/>
		</view>
		<view style="height:100%;width:100%">
			<map v-if="area==0&&showmap" id="map" longitude="117.312269" latitude="38.994228" scale="14" :markers="markers" @tap="marktap" show-location style="width: 100%; height:100%;"></map>
			<map v-if="area==1&&showmap" id="map" longitude="117.172880" latitude="39.108819" scale="15" :markers="markers" @tap="marktap" show-location style="width: 100%; height:100%;"></map>
			<cover-view class="changebtn">
				<cover-view v-bind:class="area==0?'selectedbtn':'unselectedbtn'" @tap="changearea1">
					北洋园
				</cover-view>
				<cover-view v-bind:class="area==1?'selectedbtn':'unselectedbtn'" @tap="changearea2">
					卫津路
				</cover-view>
			</cover-view>
		</view>
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				area:0,
				cardState:"close-card",
				markers: [],
				showmap:true,
				config: {
					type:1,//0无title，1有title
					title: "地图",
					back:true,
				},
				address: ''
			}
		},
		components: {
			navigation
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			marktap(e){
				console.log(e.detail.latitude);
				console.log(e.detail.longitude);
				let longitude = e.detail.longitude;
				let latitude = e.detail.latitude;
				let _this = this
				uni.request({
				      url: 'https://restapi.amap.com/v3/geocode/regeo',
				      data: {
				        key: '53846ae53aa06f53c1a754a376673811',
				        location: longitude + "," + latitude,
				        extensions: "base",
				        s: "rsx",
				        sdkversion: "sdkversion",
				        logversion: "logversion"
				 
				      },
				      success: function (res) {
						  // console.log(res)
				    //     console.log(res.data.regeocode.formatted_address);
						_this.address = res.data.regeocode.formatted_address;
				      },
				      fail: function (res) {
				        console.log('获取地理位置失败')
				      }
				    })
				uni.showModal({
					title:'提示',
					content:'是否确定房源位置: '+_this.address,
					success:function(){
						_this.markers[0]={
							  iconPath: "../../static/markIcon.png",
						      id: 0,
						      latitude: latitude,
						      longitude: longitude,
						      width: 25,
						      height: 25
						}
						var pages = getCurrentPages();
						var prevPage = pages[pages.length - 2];
						prevPage.setData({
						     userdata: {
						       'address':_this.address,
							   'longitude':longitude,
							   'latitude':latitude
						     }
						})
						// uni.setStorageSync('latitude', latitude)
						// uni.setStorageSync('longitude', longitude)
						// uni.setStorageSync('address', _this.address)
						_this.showmap=false;
						setTimeout(() => {
						_this.showmap = true;
						}, 100);
					},
					fail:function(){
						console.log(1)
					}
				})
			},
			changearea1(){
				this.area = 0 ;
			},
			changearea2(){
				this.area = 1 ;
			},
			
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
