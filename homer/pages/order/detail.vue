<template>
	<view class="detail">
		<!-- 顶部轮播图 -->
		<view v-if="(card.type==1||card.type==2)&&card.images">
			<view class="top" v-if="scrolltotitle" :style="{height:height}"></view>
			<view class="backicon" :style="{height:height}">
				<view @click="customConduct" class="navigation-bar-custom-capsule">
					<image class="custom-icon" src="../../static/back.png"></image>
				</view>
			</view>
			<uni-swiper-dot :info="card.images" :current="current" :mode="mode" :dots-styles="dotsStyles" field="content">
				<swiper class="swiper-box" @change="change" :autoplay="true" :circular="true">
					<swiper-item v-for="(item, index) in card.images" :key="index">
						<view class="swiper-item">
							<image class="image" :src="item" mode="aspectFill" :data-idx="index" @click="previewImgs" />
						</view>
					</swiper-item>
				</swiper>
			</uni-swiper-dot>
		</view>

		<view v-else>
			<view style="text-align: center;">
				<navigation :config="config" @customConduct="customConduct" />
			</view>
		</view>

		<view class="box">
			<view style="display: flex;align-items: center;margin-bottom: 10upx;width:calc(100vw - 60upx);">
				<image :src="owner.avatarUrl" mode="" style="width: 76upx;height: 76upx;border-radius: 50%;"></image>
				<view>
					<view style="padding-left: 24upx;font-size: 32upx;font-family: bold;color:rgba(102,102,102,1);overflow: hidden;text-overflow:ellipsis;white-space: nowrap;display: flex;align-items: center;">
						<view>{{ owner.name }}</view>
					</view>
					<view class="time">
						<text>发布时间{{ "："+card.postDate}}</text>
					</view>
				</view>
			</view>
			<view class="title">
				<view>{{ card.type|filters1 }}{{" | "+card.title}}</view>
			</view>
			<view class="position">
				<view>{{card.location}}</view>
				<image src="../../static/mapIcon.png" mode="widthFix" class="positionIcon"></image>
			</view>
			<view class="mainInfo">
				<view class="mainbox">
					<text class="mainnum">{{card.prices[0]}}元</text>
					<view class="maintitle">价格</view>
				</view>
				<view class="mainbox">
					<text class="mainnum">{{card.squares[0]}}㎡</text>
					<view class="maintitle">面积</view>
				</view>
			</view>
		</view>

		<view class="box">
			<view class="title">其他概况</view>
			<view class="qita">
				<view class="qitatitle">户型</view>
				<view class="qitacontent">{{card.unitType|filters2}}</view>
				<view style="clear: both;"></view>
			</view>
			<view class="qita" v-if="card.type==1">
				<view class="qitatitle">最大合租人数</view>
				<view class="qitacontent" style="width:100upx;">{{card.unionNum}}</view>
				<view style="clear: both;"></view>
			</view>
			<view class="qita">
				<view class="qitatitle">简介</view>
				<view class="qitacontent">{{card.introduce}}</view>
				<view style="clear: both;"></view>
			</view>

			<view class="qita" v-if="card.type==1||card.type==2">
				<view class="qitatitle">要求</view>
				<view class="qitacontent">{{card.requirement}}</view>
				<view style="clear: both;"></view>
			</view>
		</view>

		<view class="box" v-if="myinfo.id==card.ownerId&&card.status==0">
			<view class="title">申请列表</view>
			<view v-if="persons.length>0" class="applybox" v-for="(person,index) in persons" :key="index">
				<view style="display: flex;align-items: center;">
					<image class="avatarlist" :src="person.avatarUrl" />
					<view style="padding-left: 24upx;font-size: 32upx;font-family: bold;color:rgba(102,102,102,1);overflow: hidden;text-overflow:ellipsis;white-space: nowrap;display: flex;align-items: center;">
						<view>{{ person.name }}</view>
					</view>
				</view>
			</view>
			<view v-if="applyteams.length>0" class="applybox" v-for="(team,index) in applyteams" :key="index">
				<view v-for="(member,id) in team.members" :key="id">
					<image class="avatarlist" :src="member.avatarUrl" />
				</view>
				<view style="clear: both;"></view>
			</view>
		</view>

		<view class="box" v-if="myTeam">
			<view class="title">我的合租</view>
			<view class="applybox">
				<view v-for="(member,id) in myTeam.members" :key="id">
					<image class="avatarlist" :src="member.avatarUrl" />
				</view>
				<view style="clear: both;"></view>
			</view>
		</view>

		<view class="box" v-if="otherTeams.length>0">
			<view class="title">合租队列</view>
			<view class="applybox" v-for="(team,index) in otherTeams" :key="index">
				<view v-for="(member,id) in team.members" :key="id">
					<image class="avatarlist" :src="member.avatarUrl" />
				</view>
				<view style="clear: both;"></view>
			</view>
		</view>

		<view class="box">
			<view class="title">评论区</view>
		</view>
		<view style="height:100upx;width:100vw;background: #ffffff;"></view>

	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	import uniSwiperDot from '@/components/uni-swiper-dot/uni-swiper-dot.vue'
	export default {
		data() {
			return {
				config: {
					type: 1, //0无title，1有title
					title: "详情",
					back: true,
				},
				myinfo: {
					id: "123",
					avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/shuijiao.jpg",
					name: "bzl",
				},
				owner: {
					id: "123",
					avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/shuijiao.jpg",
					name: "bzl",
				},
				card: {
					type: 1,
					images: [
						"https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/shuijiao.jpg",
						"https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg",
						'https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/cbd.jpg'
					],
					title: "保证界面简洁明确，可操作性强不存在有异议的交互功能",
					introduce: "保证界面简洁明确，可操作性强不存在有异议的交互功能",
					prices: [
						1000, 2000
					],
					postDate: "2020-08-16",
					squares: [
						100, 150
					],
					requirement: "保证界面简洁明确，可操作性强不存在有异议的交互功能",
					unitType: 0,
					status: 0,
					unionNum: 2,
					location: "卫津路",
					ownerId: "123",
					hasHouseResourse: false
				},
				persons: [{
					userId: 233,
					avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg",
					name: "rlj",
					applyId: "133"
				}],
				applyteams: [{
					teamId: 333,
					members: [{
							userId: "122",
							avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
						},
						{
							userId: "124",
							avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
						}
					],
					applyId: ""
				}],
				myTeam: {
					teamId: "",
					maxTeamMem: 3,
					members: [{
							userId: "125",
							avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
						},
						{
							userId: "125",
							avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
						},
					]
				},
				otherTeams: [{
						teamId: "",
						maxTeamMem: 3,
						members: [{
								userId: "125",
								avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
							},
							{
								userId: "125",
								avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
							},
						]
					},
					{
						teamId: "",
						maxTeamMem: 3,
						members: [{
								userId: "125",
								avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
							},
							{
								userId: "125",
								avatarUrl: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
							},
						]
					}
				],
				scrolltotitle: false,
				scrollTop: 0,
				current: 0,
				mode: 'round',
				dotsStyles: {}
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
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
		},
		watch: {
			scrollTop(newVal, oldVal) {
				if (newVal > 240) {
					this.scrolltotitle = true;
				} else {
					this.scrolltotitle = false;
				}
			}
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
			filters2: function(arg) {
				if (arg == 0) {
					return '一室一厅';
				} else if (arg == 1) {
					return '两室一厅';
				} else if (arg == 2) {
					return '三室一厅';
				} else if (arg == 3) {
					return '三室两厅';
				} else if (arg == 4) {
					return '其他';
				}
			},
		},
		components: {
			navigation,
			uniSwiperDot
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			change(e) {
				this.current = e.detail.current
			},
			previewImgs(e) {
				puImage(e, this);
			},
		}
	}
	const puImage = async (e, _this) => {
		let cacheImg = [];
		for (let i = 0, len = _this.card.images.length; i < len; i++) {
			cacheImg.push(_this.card.images[i]);
		}
		uni.previewImage({
			current: cacheImg[e.currentTarget.dataset.idx],
			urls: cacheImg
		});
	};
</script>

<style scoped>
	.detail {
		width: 100vw;
		background: rgb(245, 245, 245);
	}

	.swiper-box {
		height: 550upx;
		width: 100vw;
	}

	.swiper-item {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		justify-content: center;
		align-items: center;
		background-color: #999;
		color: #fff;
	}

	.image {
		height: 550upx;
		width: 100vw;
	}

	.top {
		z-index: 9;
		position: fixed;
		left: 0;
		top: 0;
		width: 100vw;
		background: #10518e;
	}

	.backicon {
		z-index: 10;
		position: fixed;
		left: 0;
		top: 0;
	}

	.navigation-bar-custom-capsule {
		position: absolute;
		display: flex;
		align-items: center;
		justify-content: center;
		height: 32px;
		left: 10px;
		bottom: 6px;
		background-color: rgba(255, 255, 255, .5);
		border-radius: 16px;
		padding: 0 23upx;
		border: 0.5px solid #e6e6e6;
		box-sizing: border-box;
		overflow: hidden;
		z-index: 9;
	}

	.custom-icon {
		width: 45upx;
		height: 45upx;
	}

	.box {
		width: 100vw;
		margin-bottom: 15upx;
		background: #FFFFFF;
		padding: 30upx;
		box-sizing: border-box;
	}

	.time {
		padding: 10upx 0 0upx 24upx;
		font-size: 18upx;
		color: #363631;
	}

	.title {
		width: calc(100vw - 60upx);
		font-size: 40upx;
		font-weight: 500;
	}

	.position {
		font-size: 30upx;
		color: #707070;
		width: calc(100vw - 60upx);
		margin: 15upx 0;
		border-bottom: 2upx solid rgb(245, 245, 245);
		padding-bottom: 15upx;
		position: relative;
	}

	.positionIcon {
		position: absolute;
		right: 0;
		top: 0;
		width: 40upx;
		height: 40upx;
	}

	.mainInfo {
		width: calc(100vw - 60upx);
		display: flex;
		justify-content: space-around;
		align-items: center;
	}

	.mainbox {
		vertical-align: middle;
		text-align: center;
	}

	.mainnum {
		font-size: 38upx;
		color: #FF7900;
		font-weight: bold;
	}

	.maintitle {
		font-size: 30upx;
		color: #707070;
	}

	.qita {
		width: calc(100vw - 60upx);
		margin: 15upx 0;
	}

	.qitatitle {
		font-size: 30upx;
		color: #bfbfbf;
		margin-right: 20upx;
		float: left;
	}

	.qitacontent {
		width: 500upx;
		font-size: 30upx;
		color: #707070;
		margin-right: 20upx;
		float: left;
	}

	.applybox {
		border-top: 2upx solid rgb(245, 245, 245);
		margin: 15upx 0 0;
		padding-top: 15upx;
	}

	.avatarlist {
		width: 76upx;
		height: 76upx;
		border-radius: 50%;
		float: left;
		margin-left: 30upx;
	}
</style>
