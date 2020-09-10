<template>
	<view class="navigation-bar" :style="{height:height}">
		<view class="navigation-fixed">
			<view class="navigation-content" :style="{height:height}">
				<view v-if="config.back==1" @click="conduct_" class="navigation-bar-custom-capsule" >
					<image class="custom-icon" src="../../static/back.png" ></image>
				</view>
				<view v-if="config.type==1" class="navigation-bar-title" :style="'margin-top:'+marginTop">
					<text style="font-weight:bold;color:#ffffff">{{config.title}}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			};
		},
		props: {
			config: {
				type: Object,
				default () {
					return {
						type: 0,
						title: "",
						back: false,
					}
				}
			}
		},
		computed: {
			height() {
				const {
					platform,
					statusBarHeight
				} = uni.getSystemInfoSync()
				let height = statusBarHeight + 4 //ios 24px
				if (platform.toLowerCase() == "android") {
					height += 4 //android 28px
				}
				return height + 38 + "px"
			},
			marginTop() {
				const {
					platform,
					statusBarHeight
				} = uni.getSystemInfoSync()
				let height = statusBarHeight + 4
				if (platform.toLowerCase() == "android") {
					height += 4
				}
				return height + "px"
			},
		},
		methods:{
			conduct_(){
				this.$emit("customConduct")	
			},
		}
	}
</script>

<style>
	.navigation-bar {
		width: 100%;
		box-sizing: border-box;
	}

	.navigation-fixed {
		position: fixed;
		width: 100%;
		top: 0;
		z-index: 999;
		background: #10518e;
		box-sizing: border-box;
	}

	.navigation-content {
		display: flex;
		justify-content: center;
	}

	.navigation-bar-title {
		width: 280upx;
		line-height: 32px;
		height: 32px;
		color: #fff;
		font-size: 36upx;
		white-space: nowrap;
		text-overflow: ellipsis;
		overflow: hidden;
	}

	.navigation-bar-custom-capsule {
		position: absolute;
		display: flex;
		align-items: center;
		justify-content: center;
		height: 28px;
		left: 10px;
		bottom:6px;
		background-color: rgba(255, 255, 255, .5);
		border-radius: 16px;
		padding: 0 23upx;
		// border:0.5px solid rgba(255,255,255,.3);
		box-sizing: border-box;
		overflow: hidden;
		z-index: 9;
	}

	.custom-icon {
		width: 45upx;
		height: 45upx;
	}
</style>
