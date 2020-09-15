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
				<view v-if="card.type!=5">{{ card.type|filters1 }}{{" | "+card.title}}</view>
				<view v-else>{{ card.type|filters1 }}{{" | "+(card.hasHouseResourse?"有房源":"无房源") +" | "+card.title}}</view>
			</view>
			<view class="position">
				<view style="width:calc(100vw - 200upx)">{{ ((card.type==1||card.type==2||(card.type==5&&card.hasHouseRes))?"":"预期位置：") + card.location }}</view>
				<image v-if="card.type==1||card.type==2" src="../../static/mapIcon.png" mode="widthFix" class="positionIcon" @tap="gotolocation"></image>
			</view>
			<view class="mainInfo">
				<view v-if="card.type==1||card.type==2" class="mainbox">
					<text class="mainnum">{{card.prices[0]}}元</text>
					<view class="maintitle">价格</view>
				</view>
				<view v-else class="mainbox">
					<text class="mainnum">{{card.prices[0]+"~"+card.prices[1]}}元</text>
					<view class="maintitle">价格</view>
				</view>
				<view v-if="card.type==1||card.type==2" class="mainbox">
					<text class="mainnum">{{card.squares[0]}}㎡</text>
					<view class="maintitle">面积</view>
				</view>
				<view v-else class="mainbox">
					<text class="mainnum">{{card.squares[0]+"~"+card.squares[1]}}㎡</text>
					<view class="maintitle">面积</view>
				</view>
			</view>
		</view>

		<view class="box">
			<view v-if="card.type==1||card.type==2" class="title">其他概况</view>
			<view v-else class="title">其他描述</view>
			<view class="qita" v-if="(card.type==3||card.type==4||card.type==5)&&card.images">
				<view v-for="(item, index) in card.images" :key="index">
					<image class="imagelist" :src="item" mode="aspectFill" :data-idx="index" @click="previewImgs" />
				</view>
				<view style="clear: both;"></view>
			</view>
			<view class="qita" v-if="card.type==1||card.type==2||(card.type==5&&card.hasHouseResourse)">
				<view class="qitatitle">户型</view>
				<view class="qitacontent">{{card.unitType|filters2}}</view>
				<view style="clear: both;"></view>
			</view>
			<view class="qita" v-else>
				<view class="qitatitle">预期户型</view>
				<view class="qitacontent">{{card.unitType|filters2}}</view>
				<view style="clear: both;"></view>
			</view>
			<view class="qita" v-if="card.type==1||card.type==5">
				<view class="qitatitle">最大合租人数</view>
				<view class="qitacontent" style="width:100upx;">{{card.unionNum}}</view>
				<view style="clear: both;"></view>
			</view>
			<view class="qita" v-if="card.type==3">
				<view class="qitatitle">人数</view>
				<view class="qitacontent" style="width:100upx;">{{card.unionNum}}</view>
				<view style="clear: both;"></view>
			</view>
			<view class="qita">
				<view class="qitatitle">简介</view>
				<view class="qitacontent">{{card.introduce}}</view>
				<view style="clear: both;"></view>
			</view>

			<view class="qita" v-if="card.type==1||card.type==5">
				<view class="qitatitle">要求</view>
				<view class="qitacontent">{{card.requirement}}</view>
				<view style="clear: both;"></view>
			</view>
		</view>

		<view class="box" v-if="userId==card.ownerId&&(persons.length>0||applyteams.length>0)&&!card.status">
			<view class="title">申请列表</view>
			<view v-if="persons.length>0" class="applybox" v-for="(person,index) in persons" :key="index">
				<view style="display: flex;align-items: center;width:400upx;float:left;">
					<image class="avatarlist" :src="person.avatarUrl" />
					<view style="padding-left: 24upx;font-size: 32upx;font-family: bold;color:rgba(102,102,102,1);overflow: hidden;text-overflow:ellipsis;white-space: nowrap;display: flex;align-items: center;">
						<view>{{ person.name }}</view>
					</view>
				</view>
				<view v-if="userId==card.ownerId" class="textbtn" @tap="processapply(person.applyId)">同意申请</view>
				<view style="clear: both;"></view>
			</view>
			<view v-if="applyteams.length>0" class="applybox" v-for="(team,index) in applyteams" :key="index">
				<view v-for="(member,id) in team.members" :key="id">
					<image class="avatarlist" :src="member.avatarUrl" />
				</view>
				<view v-if="userId==card.ownerId" class="textbtn" @tap="processapply(team.applyId)">同意申请</view>
				<view style="clear: both;"></view>
			</view>
		</view>

		<view class="box" v-if="card.type==1&&myTeam.teamId&&!card.status">
			<view class="title">我的合租</view>
			<view class="applybox">
				<view v-for="(member,id) in myTeam.members" :key="id">
					<image class="avatarlist" :src="member.avatarUrl" />
				</view>
				<view v-if="myTeam.members[0].userId==userId" class="textbtn" @tap="leaveteam(0)">解散队列</view>
				<view v-else class="textbtn" @tap="leaveteam(1)">退出队列</view>
				<view style="clear: both;"></view>
			</view>
		</view>

		<view class="box" v-if="card.type==1&&otherTeams.length>0&&!card.status">
			<view class="title">合租队列</view>
			<view class="applybox" v-for="(team,index) in otherTeams" :key="index">
				<view v-for="(member,id) in team.members" :key="id">
					<image class="avatarlist" :src="member.avatarUrl" />
				</view>
				<image v-if="!myTeam.teamId&&team.members.length<card.unionNum&&userId!=card.ownerId" src="../../static/join.png"
				 class="avatarlist" @tap="jointeam(team.teamId)"></image>
				<view v-if="team.members.length==card.unionNum" class="textbtn">队列已满</view>
				<view style="clear: both;"></view>
			</view>
		</view>

		<view class="box">
			<view class="title">评论区</view>
			<view v-for="(comment, index) in comments" :key="index" class="applybox">
				<view style="display: flex;align-items: center;padding-left: 20upx;position: relative;">
					<image :src="comment.avatarUrl" mode="" style="width: 50upx;height: 50upx;border-radius: 50%;"></image>
					<text style="padding-left: 20upx;color:#10518e">{{ comment.name }}</text>
					<text style="position: absolute;right: 20upx;color:rgba(119,119,119,1);font-size:25upx">{{ comment.createDate }}</text>
					<!-- <text style="position: absolute;right: 20upx;font-size:30upx" @tap="reply(comment)">回复</text> -->
				</view>
				<view style="padding: 10upx 0 0 90upx;width:550rpx;word-wrap: break-word;font-size:30upx;text-align: justify;">{{ comment.content }}</view>
				<view style="float:right;margin-right:20upx;display: flex;flex-direction: row;align-items: center;justify-content: center;" @tap="deletecomment(comment.commentId)" v-if="comment.userId==userId">
					<image src="../../static/deletecomment.png" style="width:30upx;" mode="widthFix"></image>
				</view>
				<view style="float:right;margin-right:20upx;display: flex;flex-direction: row;align-items: center;justify-content: center;" @tap="openreply(comment)">
					<text style="margin-right: 10upx;font-size:25upx;color:#808080">{{ comment.replyNum }}</text>
					<image src="../../static/comment.png" style="width:30upx;" mode="widthFix"></image>
				</view>
				<view style="clear: both;"></view>
			</view>
			<view style="border-top: 2upx solid rgb(245, 245, 245);width:100%;margin:15upx 0 0;">
				<uni-load-more :loadingType="loadingType" :contentText="contentText"></uni-load-more>
			</view>
		</view>
		<view style="height:100upx;width:100vw;background: #ffffff;"></view>

		<view class="btnbox">
			<image class="Tocomment" src="../../static/Tocomment.png" mode="widthFix" @tap="comment()"></image>
			<view v-if="card.status"></view>
			<view v-else-if="userId==card.ownerId">
				<view class="deleterent" @tap="deleterent()">删除帖子</view>
			</view>
			<view v-else>
				<view class="manyrent" v-if="card.type==1&&card.unionNum>1">
					<view class="mainbox" @tap="manyapply()" v-if="myTeam.teamId&&myTeam.members[0].userId==userId">
						<view>我要合租</view>
						<view style="font-size:20upx">提交合租申请</view>
					</view>
					<view class="mainbox" v-else-if="myTeam.teamId&&myTeam.members[0].userId!=userId">
						<view>等待合租</view>
					</view>
					<view class="mainbox" @tap="createteam()" v-else>
						<view>我要合租</view>
						<view style="font-size:20upx">新建合租队列</view>
					</view>
				</view>
				<view class="onerent" @tap="oneapply()">我要{{card.type|filters3}}</view>
			</view>
		</view>

		<!-- 评论回复框 -->
		<view class="prompt-box" v-if="visible">
			<view class="prompt">
				<view class="prompt-top">
					<view class="prompt-title" v-if="commenttype==0">{{ title[0] }}</view>
					<view class="prompt-title" v-else>{{ title[1] + ' ' + replycomment.name}}</view>
					<textarea class="textarea" value="" placeholder="请输入评论,最多不超过100字" :maxlength="100" v-model="value"
					 placeholder-class="plc" adjust-position fixed />
					</view>
				<slot></slot>
				<view class="prompt-buttons">
					<button class="prompt-cancle" @click="close">取消</button>
					<button class="prompt-confirm" @tap="handlecomment()">确定</button>
				</view>
			</view>
		</view>
		
		<!-- 回复 -->
		<uni-popup ref="popup" type="bottom">
			<view style="height: 800upx;width: 100%;background:rgba(255,255,255,1);box-sizing: border-box;">
				<view class="replytitle">
					<text style="font-size:30upx;line-height:70upx;">回复详情</text>
					<view @tap="closereply()" style="position: absolute;right: 14upx;top: 44upx;">
						<image src="../../static/deletehistory.png" mode="" style="width: 42upx;height: 42upx;"></image>
					</view>
				</view>
				<view class="applybox" style="margin-top:70upx;">
					<view style="display: flex;align-items: center;padding-left: 20upx;position: relative;">
						<image :src="replytocomment.avatarUrl" mode="" style="width: 50upx;height: 50upx;border-radius: 50%;"></image>
						<text style="padding-left: 20upx;color:#10518e">{{ replytocomment.name }}</text>
						<!-- <text style="position: absolute;right: 120upx;color:rgba(119,119,119,1);font-size:25upx">{{ replytocomment.createDate }}</text> -->
						<text style="position: absolute;right: 20upx;font-size:30upx" @tap="reply(replytocomment)">回复</text>
						<text style="position: absolute;right: 120upx;font-size:30upx;color:#D81E06" @tap="deletecomment(replytocomment.commentId)" v-if="replytocomment.userId==userId">删除</text>
					</view>
					<view style="padding: 10upx 0 0 90upx;width:550rpx;word-wrap: break-word;font-size:30upx;text-align: justify;">{{ replytocomment.content }}</view>
					<view style="float:right;margin-right:20upx;display: flex;flex-direction: row;align-items: center;justify-content: center;">
						<text style="color:rgba(119,119,119,1);font-size:25upx">{{ replytocomment.createDate }}</text>
					</view>
					<view style="clear: both;"></view>
				</view>
				
				<view v-for="(reply, index) in replylist" :key="index" class="applybox">
					<view style="display: flex;align-items: center;padding-left: 20upx;position: relative;">
						<text style="padding-left: 70upx;padding-right:10upx;color:#10518e">{{ reply.name }}</text>
						<text>回复</text>
						<text style="padding-left: 10upx;color:#10518e">{{ reply.replyToUserName }}</text>
						<text style="position: absolute;right: 20upx;font-size:30upx" @tap="reply(reply)">回复</text>
						<text style="position: absolute;right: 120upx;font-size:30upx;color:#D81E06" @tap="deletecomment(reply.replyId)" v-if="reply.userId==userId">删除</text>
					</view>
					<view style="padding: 10upx 0 0 90upx;width:550rpx;word-wrap: break-word;font-size:30upx;text-align: justify;">{{ reply.content }}</view>
					<view style="float:right;margin-right:20upx;display: flex;flex-direction: row;align-items: center;justify-content: center;">
						<text style="color:rgba(119,119,119,1);font-size:25upx">{{ reply.createDate }}</text>
					</view>
					<view style="clear: both;"></view>
				</view>
				<view class="applybox" style="display: flex;align-items: center;justify-content: space-around;">
					<view style="color:rgba(119,119,119,1);font-size:25upx;margin-bottom:30upx;">没有更多数据了</view>
				</view>
				
			</view>
			<view class="prompt-box" v-if="visible1" style="top:calc(800upx - 100vh)">
				<view class="prompt">
					<view class="prompt-top">
						<view class="prompt-title">{{ title[1] + ' ' + replycomment.name}}</view>
						<textarea class="textarea" value="" placeholder="请输入回复,最多不超过100字" :maxlength="100" v-model="value"
						 placeholder-class="plc" adjust-position fixed />
						</view>
					<slot></slot>
					<view class="prompt-buttons">
						<button class="prompt-cancle" @click="close">取消</button>
						<button class="prompt-confirm" @tap="handlecomment()">确定</button>
					</view>
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	import uniSwiperDot from '@/components/uni-swiper-dot/uni-swiper-dot.vue';
	import uniLoadMore from '../../components/uni-load-more/uni-load-more.vue';
	import uniPop from '@/components/uni-popup/uni-pop.vue';
	import uniPopup from '@/components/uni-popup/uni-popup.vue';
	export default {
		data() {
			return {
				config: {
					type: 2, //0无title，1有title，2类别title
					renttype: 5,
					back: true,
				},
				userId: '',
				owner: {},
				card: {},
				persons: [],
				applyteams: [],
				myTeam: {},
				otherTeams: [],
				comments: [],
				replycomment:{},
				replytocomment:{},
				replylist:[],
				commenttype:0,
				loadingType: 0, //定义加载方式 0---contentdown  1---contentrefresh 2---contentnomore
				contentText: {
					contentdown: '上拉显示更多',
					contentrefresh: '正在加载...',
					contentnomore: '没有更多数据了'
				},
				visible:false,
				visible1:false,
				value:'',
				title:['评论','回复'],
				scrolltotitle: false,
				scrollTop: 0,
				current: 0,
				mode: 'round',
				dotsStyles: {},
				commentpage: 1,
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
			filters3: function(arg) {
				if (arg == 1) {
					return '整租';
				} else if (arg == 2) {
					return '购房';
				} else if (arg == 3) {
					return '出租';
				} else if (arg == 4) {
					return '出售';
				} else if (arg == 5) {
					return '申请';
				}
			},
		},
		components: {
			navigation,
			uniSwiperDot,
			uniLoadMore,
			uniPopup,
			uniPop
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
			this.config.renttype = options.type;
			this.cardId = options.Id;
			this.getdetail()
		},
		onShow() {
			this.getdetail()
		},
		onReachBottom: function() {
			this.getmorecomments();
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
			openreply(e){
				this.$refs.popup.open();
				this.replytocomment = e
				this.getreply()
			},
			getreply(){
				let _this = this
				this.$http.get('/api/comment/getReplies/', {
					commentId: this.replytocomment.commentId
				}).then(res => {
					console.log(res)
					_this.replylist = res.data.replies
					
				}).catch(err => {
					console.log(err)
				})
			},
			closereply(){
				this.$refs.popup.close();
			},
			gotolocation(){
				uni.navigateTo({
					url:'../order/mapdetail?card='+this.cardId
				})
			},
			getdetail() {
				let _this = this
				this.$http.get('/api/detail/getCardDetail/', {
					cardId: this.cardId
				}).then(res => {
					console.log(res)
					_this.card = res.data
					_this.getowner()
					_this.getrentlist()
					if (_this.userId == _this.card.ownerId) {
						_this.getapply()
					}
					_this.getcomment()
				}).catch(err => {
					console.log(err)
				})
			},
			getowner() {
				let _this = this
				this.$http.get('/api/person/info/', {
					userId: this.card.ownerId
				}).then(res => {
					console.log(res)
					_this.owner.name = res.name
					_this.owner.avatarUrl = res.avatarUrl
				}).catch(err => {
					console.log(err)
				})
			},
			getapply() {
				let _this = this
				this.$http.get('/api/detail/owner/getApply/', {
					cardId: this.cardId
				}).then(res => {
					console.log(res)
					_this.persons = res.data.person
					_this.applyteams = res.data.teams
				}).catch(err => {
					console.log(err)
				})
			},
			getrentlist() {
				let _this = this
				this.$http.get('/api/union/getTeam/', {
					cardId: this.cardId
				}).then(res => {
					console.log(res)
					_this.myTeam = res.data.myTeam
					_this.otherTeams = res.data.otherTeams
				}).catch(err => {
					console.log(err)
				})
			},
			getcomment() {
				let _this = this
				this.commentpage = 1
				this.loadingType = 2;
				uni.showNavigationBarLoading();
				this.$http.get('/api/comment/getComments/', {
					cardId: this.cardId,
					pageNum: this.commentpage
				}).then(res => {
					console.log(res)
					_this.commentpage = 2
					_this.comments = res.data.comments
					if (res.data.comments.length < 10) {
						console.log('评论区1');
						this.loadingType = 2;
					} else {
						this.loadingType = 0;
					}
					uni.hideNavigationBarLoading(); //关闭加载动画
				}).catch(err => {
					console.log(err)
				})
			},
			getmorecomments(){
				if (this.loadingType !== 0) {
					//loadingType!=0;直接返回
					return false;
				}
				let _this = this
				this.loadingType = 1;
				uni.showNavigationBarLoading();
				this.$http.get('/api/comment/getComments/', {
					cardId: this.cardId,
					pageNum: this.commentpage
				}).then(res => {
					console.log(res)
					if (res.data.comments == null) {
						this.loadingType = 2;
						uni.hideNavigationBarLoading(); //关闭加载动画
						return;
					}
					else if (res.data.comments.length < 10) {
						console.log('评论区');
						this.loadingType = 2;
					} else {
						this.loadingType = 0;
					}
					_this.commentpage++
					_this.comments = _this.comments.concat(res.data.comments)
					uni.hideNavigationBarLoading(); //关闭加载动画
				}).catch(err => {
					console.log(err)
				})
			},
			oneapply() {
				let _this = this
				uni.showModal({
					title: '提示',
					content: '是否确认提出申请',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							_this.$http.post('/api/detail/orderApply/', {
								userId: _this.userId,
								cardId: _this.card.cardId
							}).then(res => {
								console.log(res)
								uni.showToast({
									title: '申请成功',
									icon: 'success'
								})
								_this.detail()
							}).catch(err => {
								console.log(err)
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			manyapply() {
				let _this = this
				uni.showModal({
					title: '提示',
					content: '是否确认提出合租申请',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							_this.$http.post('/api/detail/orderTeamApply/', {
								teamId: _this.myTeam.teamId,
								cardId: _this.card.cardId
							}).then(res => {
								console.log(res)
								uni.showToast({
									title: '申请成功',
									icon: 'success'
								})
								_this.detail()
							}).catch(err => {
								console.log(err)
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			deleterent(){
				let _this = this
				uni.showModal({
				    title: '提示',
				    content: '是否确定删除帖子',
				    success: function (res) {
				        if (res.confirm) {
				            console.log('用户点击确定');
							_this.$http.post('/api/adm/deleteCard/', {
								cardIds:[_this.cardId]
							}).then(res => {
								console.log(res)
								setTimeout(() => {
									uni.showToast({
										title: '删除成功',
										icon: 'success'
									});
								}, 100);
								uni.switchTab({
									url:'../home/home'
								})
							}).catch(err => {
								console.log(err)
							})
				        } else if (res.cancel) {
				            console.log('用户点击取消');
				        }
				    }
				});
			},
			processapply(e){
				let _this = this
				uni.showModal({
					title: '提示',
					content: '是否确认同意该申请',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							_this.$http.post('/api/detail/processApply/', {
								applyId:e
							}).then(res => {
								console.log(res)
								setTimeout(() => {
									uni.showToast({
										title: '操作成功',
										icon: 'success'
									});
								}, 100);
								uni.switchTab({
									url:'../home/home'
								})
							}).catch(err => {
								console.log(err)
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			createteam() {
				let _this = this
				uni.showModal({
					title: '提示',
					content: '是否确认创建合租队列',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							_this.$http.post('/api/union/createTeam/', {
								userId: _this.userId,
								cardId: _this.card.cardId,
								maxNum: _this.card.unionNum
							}).then(res => {
								console.log(res)
								uni.showToast({
									title: '新建成功',
									icon: 'success'
								})
								_this.getrentlist()
							}).catch(err => {
								console.log(err)
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			jointeam(e){
				let _this = this
				uni.showModal({
					title: '提示',
					content: '是否确认加入合租队列',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							_this.$http.post('/api/union/joinTeam/', {
								userId: _this.userId,
								teamId: e,
							}).then(res => {
								console.log(res)
								uni.showToast({
									title: '加入成功',
									icon: 'success'
								})
								_this.getrentlist()
							}).catch(err => {
								console.log(err)
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			leaveteam(e){
				let _this = this
				let content = e==0?'是否确认解散合租队列':'是否确认退出合租队列'
				let title = e==0?'解散成功':'退出成功'
				uni.showModal({
					title: '提示',
					content: content,
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							_this.$http.post('/api/union/leaveTeam/', {
								userId: _this.userId,
								teamId: _this.myTeam.teamId,
							}).then(res => {
								console.log(res)
								uni.showToast({
									title: title,
									icon: 'success'
								})
								_this.getrentlist()
							}).catch(err => {
								console.log(err)
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			comment(){
				this.visible = true;
				this.value = ''
				this.commenttype = 0
			},
			reply(e){
				this.visible1 = true;
				this.value = ''
				this.commenttype = 1
				this.replycomment = e
				console.log(e)
			},
			close(){
				this.visible = false;
				this.visible1 = false;
			},
			handlecomment(){
				let _this = this
				if (this.commenttype == 0){
					this.$http.post('/api/comment/addComment/', {
						userId: this.userId,
						cardId: this.card.cardId,
						content: this.value,
						replyToId: null
					}).then(res => {
						console.log(res)
						uni.showToast({
							title: '评论成功',
							icon: 'success'
						})
						_this.visible = false;
						_this.getcomment()
					}).catch(err => {
						console.log(err)
					})
				} else if (this.commenttype == 1){
					this.$http.post('/api/comment/addComment/', {
						userId: this.userId,
						cardId: this.card.cardId,
						content: this.value,
						replyToId: (this.replycomment.commentId?this.replycomment.commentId:this.replycomment.replyId)
					}).then(res => {
						console.log(res)
						uni.showToast({
							title: '回复成功',
							icon: 'success'
						})
						_this.visible1 = false;
						_this.getreply()
						_this.getcomment()
					}).catch(err => {
						console.log(err)
					})
				}
			},
			deletecomment(e){
				let _this = this
				uni.showModal({
					title: '提示',
					content: '是否删除评论',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							_this.$http.post('/api/comment/deleteComment/', {
								commentId: e,
							}).then(res => {
								console.log(res)
								uni.showToast({
									title: '删除成功',
									icon: 'success'
								})
								_this.visible = false;
								_this.visible1 = false;
								_this.closereply();
								_this.getcomment()
							}).catch(err => {
								console.log(err)
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			}
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

	.imagelist {
		width: 200upx;
		height: 160upx;
		margin-left: 10upx;
		margin-top: 10upx;
		float: left;
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

	.btnbox {
		height: 100upx;
		background: rgba(255, 255, 255, 1);
		box-shadow: 0px -1px 6px rgba(158, 158, 158, 0.19);
		position: fixed;
		bottom: 0;
		width: 100%;
		justify-content: flex-start;
		box-sizing: border-box;
	}

	.textbtn {
		float: right;
		font-size: 30upx;
		color: #707070;
		line-height: 76upx;
		margin-right: 20upx;
	}

	.Tocomment {
		width: 60upx;
		float: left;
		margin-left: 40upx;
		margin-top: 20upx;
	}

	.onerent {
		width: 250upx;
		float: right;
		height: 100upx;
		color: #FFFFFF;
		background: #DBAF66;
		font-size: 30upx;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}

	.manyrent {
		width: 250upx;
		float: right;
		height: 100upx;
		color: #EDCD9C;
		background: #606060;
		font-size: 30upx;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}
	
	.deleterent{
		width: 250upx;
		float: right;
		height: 100upx;
		color: #FFFFFF;
		background: #d81e06;
		font-size: 30upx;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}
	
	.prompt-box {
		position: fixed;
		left: 0;
		top: 0;
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;
		height: 100vh;
		background: rgba(0, 0, 0, 0.2);
		transition: opacity 0.2s linear;
		z-index:20;
	}
	
	.prompt {
		position: relative;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		align-items: center;
		width: 600upx;
		min-height: 300upx;
		background: white;
		border-radius: 20upx;
		overflow: hidden;
	}
	
	.prompt-top {
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 100%;
	}
	
	.prompt-title {
		margin: 20upx 0;
		color: #333;
		display:block;
		text-overflow:ellipsis;
		white-space:nowrap;
		overflow:hidden;
		max-width:266upx;
	}
	
	.prompt-input {
		width: 520upx;
		font-size: 28upx;
		border-radius: 8upx;
	}
	
	.prompt-buttons {
		display: flex;
		width: 100%;
		margin-top: 20upx;
	}
	
	button {
		border-radius: 0;
	}
	
	.prompt-cancle {
		background: #FFFFFF;
		width:50%;
		font-size:16px;
	}
	
	.prompt-confirm {
		color: #6ba1ff;
		background: #FFFFFF;
		width:50%;
		font-size:16px;
	}
	
	.textarea {
		position: relative;
		font-size: 22.22upx;
		box-sizing: border-box;
		padding:25.69upx 22.22upx 0;
		font-weight: bold;
		width: 500upx;
		height: 240upx;
		background: rgba(255, 255, 255, 1);
		border: 2upx solid rgba(245, 245, 245);
		border-radius: 20.83upx;
		color: rgba(147, 147, 147, 1);
		font-family: SourceHanSansCN-Regular;
	}
	
	.replytitle{
		height:70upx;
		width:calc(100% - 60rpx);
		padding:30rpx 0 0;
		position:fixed;
		top:0;
		background:rgba(255,255,255,1);
		border-bottom: 2upx solid rgb(245, 245, 245);
		display: flex;
		align-items: center;
		justify-content: space-around;
		z-index:10;
	}
</style>
