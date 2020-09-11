<template>
	<view class="home">
		<view class="content">
			<navigation :config="config" @customConduct="customConduct" />
		</view>

		<view class="sticky-box">
			<!-- 滑动区域 -->
			<scroll-view class="scroll-view_H" scroll-x="true" scroll-with-animation :scroll-left="tabsScrollLeft" @scroll="scroll">
				<view class="tab" id="tab_list">
					<view id="tab_item" :class="{ 'active': listActive == index,'tab__item':true}" v-for="(item, index) in listArr"
					 :key="index" @click="clickSort(index)">
						{{item}}
					</view>
				</view>
				<!-- tabs下划线 -->
				<view class="tab__line" :style="{background: lineColor, width: lineStyle.width, transform: lineStyle.transform,transitionDuration: lineStyle.transitionDuration}">
				</view>
			</scroll-view>
		</view>

		<view class="weui-search-bar">
			<view class="weui-search-bar__form">
				<icon class="weui-icon-search_in-box" type="search" size="14"></icon>
				<view class="weui-search-bar__box">
					<input type="text" @confirm="handlesearch()" class="weui-search-bar__input" placeholder="请在此处输入您想搜索的地区..." v-model="key" />
				</view>
			</view>
		</view>


		<view class="history">
			<text class="historyTitle">搜索历史</text>
			<view style="clear: both;"></view>
			<view v-for="(history, index) in historys" :key="index">
				<view style="position:relative">
					<view class="historysearch" @tap="tapsearch(history)">
						<text>{{history}}</text>
					</view>
					<image src="../../static/deletehistory.png" mode="widthFix" class="historydelete" @tap="deletehistory(index)"></image>
				</view>
			</view>
			<view style="clear: both;"></view>
		</view>

	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				config: {
					type: 1, //0无title，1有title
					title: "搜索",
					back: true,
				},
				historys: [],
				key: '',
				listArr: ['出租', '出售', '求租', '求售', '找室友'],
				listActive: 0, // 当前选中项
				tabsScrollLeft: 0, // tabs当前偏移量
				scrollLeft: 0,
				lineStyle: {}, // 下划线位置--动态甲酸
				duration: 0.2, // 下划线动画时长
			}
		},
		components: {
			navigation
		},
		onShow() {
			this.key = '';
			this.gethistory();
		},
		watch: {
			listActive(newVal) { // 监听当前选中项
				this.setTabList()
			}
		},
		mounted() {
			this.setTabList()
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			handlesearch() {
				if (this.key != '') {
					this.historys.push(this.key)
					uni.setStorageSync('historyword', JSON.stringify(this.historys));
					// uni.navigateTo({
					// 	url:'../order/homelist?key='+this.key
					// })
				}
			},
			tapsearch(key) {
				this.historys.push(key)
				uni.setStorageSync('historyword', JSON.stringify(this.historys));
				// uni.navigateTo({
				// 	url:'../order/homelist?key='+key
				// })
			},
			gethistory() {
				if (uni.getStorageSync('historyword')) {
					this.historys = JSON.parse(uni.getStorageSync('historyword'));
				}
			},
			deletehistory(e) {
				this.historys.splice(e, 1);
				uni.setStorageSync('historyword', JSON.stringify(this.historys));
			},
			clickSort(index) {
				this.listActive = index
			},
			// scroll-view滑动事件
			scroll(e) {
				this.scrollLeft = e.detail.scrollLeft;
			},
			setTabList() {
				this.$nextTick(() => {
					this.setLine()
					this.scrollIntoView()
				})
			},
			// 计算tabs位置
			scrollIntoView() { // item滚动
				let lineLeft = 0;
				this.getElementData('#tab_list', (data) => {
					let list = data[0]
					this.getElementData(`#tab_item`, (data) => {
						let el = data[this.listActive]
						lineLeft = el.width / 2 + (-list.left) + el.left - list.width / 2 - this.scrollLeft
						this.tabsScrollLeft = this.scrollLeft + lineLeft
					})
				})
			},
			//  计算下划线位置
			setLine() {
				let lineWidth = 0,
					lineLeft = 0
				this.getElementData(`#tab_item`, (data) => {
					let el = data[this.listActive]
					lineWidth = el.width / 2
					lineLeft = el.width / 2 + (-data[0].left) + el.left
					this.lineStyle = {
						width: `${lineWidth}px`,
						transform: `translateX(${lineLeft}px) translateX(-50%)`,
						transitionDuration: `${this.duration}s`
					};
				})
			},
			getElementData(el, callback) {
				uni.createSelectorQuery().in(this).selectAll(el).boundingClientRect().exec((data) => {
					callback(data[0]);
				});
			},
		}
	}
</script>

<style scoped>
	.home {
		height: 100vh;
	}

	.content {
		text-align: center;
	}

	.weui-search-bar {
		position: relative;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		box-sizing: border-box;
		width: 550upx;
		background: transparent;
		border-radius: 10upx;
		margin: 20upx 20upx 20upx 20upx;
	}

	.weui-icon-search_in-box {
		position: absolute;
		left: 10px;
		top: 8px;
	}

	.weui-search-bar__form {
		position: relative;
		-webkit-box-flex: 1;
		-webkit-flex: auto;
		flex: auto;
		display: flex;
		flex-direction: row;
		border-radius: 10upx;
		background: #F4F5FA;
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
		height: 30px;
		line-height: 30px;
		font-size: 24upx;
		/* */
	}

	.weui-search-bar__cancel-btn {
		margin-left: 10px;
		line-height: 28px;
		color: #09BB07;
		white-space: nowrap;
	}

	.history {
		margin: 20upx 34upx 20upx 34upx;
	}

	.historyTitle {
		margin-left: 8upx;
		font-size: 24upx;
		color: #969595;
	}

	.historysearch {
		padding: 0 5upx 5upx 5upx;
		margin-top: 20upx;
		border-bottom: 1px solid #F8F8F8;
	}

	.historysearch text {
		font-size: 28upx;
		color: #969595;
	}

	.historydelete {
		position: absolute;
		right: 16upx;
		bottom: 10upx;
		width: 24upx;
	}

	.sticky-box {
		background: #FFFFFF;
		display: flex;
		width: 720upx;
		z-index: 99;
		flex-direction: row;
		padding: 14upx 15upx 0 15upx;
	}

	.tab {
		position: relative;
		display: flex;
		font-size: 24upx;
		font-family: PingFang SC;
		white-space: nowrap;
	}

	.tab__item {
		flex: 1;
		padding: 0 20upx;
		text-align: center;
		height: 60upx;
		line-height: 60upx;
		color: #969595;
	}

	.tab__item.active {
		font-weight: bold;
		font-size: 32upx;
		color: #10518e;
	}

	.tab__line {
		display: block;
		height: 6upx;
		position: absolute;
		bottom: 0;
		left: 0;
		z-index: 1;
		border-radius: 3upx;
		position: relative;
		background: #10518e;
	}

	.scroll-view_H {
		/* 文本不会换行，文本会在在同一行上继续，直到遇到 <br> 标签为止。 */
		white-space: nowrap;
		width: 100%;
	}

	::-webkit-scrollbar {

		width: 0;

		height: 0;

		color: transparent;

	}
</style>
