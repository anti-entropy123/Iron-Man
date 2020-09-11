<template>
	<view class="map">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>
		<view style="height:100%;width:100%">
			<map v-if="area==0" id="map" longitude="117.312269" latitude="38.994228" scale="15" :markers="markers" @markertap="marktap"
			 show-location style="width: 100%; height:100%;"></map>
			<map v-if="area==1" id="map" longitude="117.172880" latitude="39.108819" scale="16" :markers="markers" @markertap="marktap"
			 show-location style="width: 100%; height:100%;"></map>
			<cover-view class="changebtn">
				<cover-view v-bind:class="area==0?'selectedbtn':'unselectedbtn'" @tap="changearea1">
					北洋园
				</cover-view>
				<cover-view v-bind:class="area==1?'selectedbtn':'unselectedbtn'" @tap="changearea2">
					卫津路
				</cover-view>
			</cover-view>
			<cover-view class="card" :class="cardState">
				<cover-view id="upbutton" :class="cardState" @tap="openclose">
					<cover-image src="../../static/up.png" mode="" class="upIcon"></cover-image>
				</cover-view>
				<cover-view style="background: #10518e;height:500upx;" scroll-top=""></cover-view>
			</cover-view>
		</view>
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				area: 0,
				cardState: "close-card",
				markers: [{
					iconPath: "../../static/markIcon.png",
					id: 0,
					latitude: 38.992508,
					longitude: 117.315668,
					width: 25,
					height: 25
				}],

				config: {
					type: 1, //0无title，1有title
					title: "地图模式",
					back: true,
				},
			}
		},
		components: {
			navigation
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			marktap(e) {
				console.log(e.markerId)
			},
			changearea1() {
				this.area = 0;
			},
			changearea2() {
				this.area = 1;
			},
			openclose() {
				this.cardState = this.cardState == 'close-card' ? 'open-card' : 'close-card'
			}
		}
	}
</script>

<style scoped>
	.map {
		height: 100vh
	}

	.changebtn {
		position: absolute;
		top: 200upx;
		left: 50upx;
		width: 200upx;
		height: 50upx;
		border-radius: 25upx;
		background: #ffffff;
		display: flex;
		box-shadow: 0 5upx 5upx 2upx #e6e6e6;
	}

	.selectedbtn {
		width: 50%;
		background: #10518e;
		color: #ffffff;
		font-size: 24upx;
		line-height: 50upx;
		text-align: center;
	}

	.unselectedbtn {
		width: 50%;
		background: #ffffff;
		color: #000000;
		font-size: 24upx;
		line-height: 50upx;
		text-align: center;
	}

	.card {
		background: #ffffff;
		position: absolute;
		bottom: 0;
		left: calc((100vw - 650upx)/2);
		width: 650upx;
		height: 70upx;
		overflow-y: scroll;
		box-shadow: 0 5upx 5upx 2upx #e6e6e6;
		transition: all 1s ease;
	}

	.card .open-card {
		height: 500upx;
	}

	.card .close-card {
		height: 70upx;
	}

	#upbutton {
		position: absolute;
		top: 0;
		width: 100%;
		height: 50upx;
		z-index: 5;
		background: #FFFFFF;
	}

	.upIcon {
		width: 50upx;
		height: 50upx;
		margin: auto;
		transition: all .4s ease;
	}

	#upbutton .open-card .upIcon {
		transform: rotate(180deg) scaleY(0.75);
	}

	#upbutton .close-card .upIcon {
		transform: rotate(0) scaleY(0.75);
	}
</style>
