<template>
<view class="mine-container">
	<view style="text-align: center;">
		<navigation :config="config"/>
	</view>
	<view class="user-box">
		<view class="avator-box">
			<image src="../../static/yjn.png" mode="aspectFit"></image>
		</view>
		<view class="user-right">
			<view class="user-id">
				闹钟你别闹
			</view>
			<view class="user-phone">
				<text>手机号: 18722648040</text>
			</view>
			<view class="user-edit" @tap="goEdit">编辑信息</view>
		</view>
	</view>
	<view class="title">订单</view>
	<view class="order">
		<view class="order-btn" @tap="goFinish">
			已完成订单
		</view>
		<view class="order-btn" @tap="goUnfinish">
			未完成订单
		</view>
	</view>
	<view class="title">消息队列</view>
  <block v-for="(msg,key,index) in msgList" :key="index">
	<view class="msg-list">
		<view class="msg-item" >
    {{key}},{{msg.msg}}
    </view>
	</view>
  </block>
</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				config: {
					type:1,//0无title，1有title
					title: "首页",
					back:false
				},
				 msgList: [{
				        type:'0',
				        msg:'你的card有人申请了!',
				        cardId:'1'
				      },{
				        type:'1',
				        msg:'你的队伍有人申请加入了!',
				        cardId:'1'
				      },{
				        type:'2',
				        msg:'你的入队申请通过了!',
				        cardId:'1'
				      },{
				        type:'3',
				        msg:'你的队伍有人退出了!',
				        cardId:'1'
				      },{
				        type:'4',
				        msg:'你的队伍解散了!',
				        cardId:'1'
				      },{
				        type:'5',
				        msg:'你的card有人申请了!',
				        cardId:'1'
				      }]
			}
		},
		methods: {
			 goEdit: function(){
			    wx.navigateTo({
			      url: '../my/editMine'　
			    })
			  },
			  goFinish: function(){
			    wx.navigateTo({
			      url: '../my/finish'　
			    })
			  },
			  goUnfinish: function(){
			    wx.navigateTo({
			      url: '../my/unfinish'　
			    })
			  },
		}
	}
</script>

<style scoped>
.mine-container {
  background-color: #F8F8F8;
}
 .user-box{
   height: 240rpx;
   padding-left: 20rpx;
   display: flex;
   align-items: center;
   margin: 10rpx 6rpx 10rpx 6rpx;
   border-radius: 10rpx;
   overflow: hidden;
   background-color: #fff;
   box-shadow: 6rpx 6rpx 6rpx #c0c0c0;
   position: relative;
 }
.avator-box {
  height: 160rpx;
  width: 160rpx;
  overflow: hidden;
}
.avator-box image {
  height: 140rpx;
  width: 140rpx;
}
.user-right {
  margin-left: 30rpx;
}
.user-id {
  font-weight: bold;
  font-size: large;
}
.user-phone {
  margin-top:20rpx;
  font-size: smaller;
}
.user-edit {
  font-size: small;
  position: absolute;
  bottom: 20rpx;
  right: 40rpx;
}
.order {
  display: flex;
  padding: 0 30rpx ;
  
}
.order-btn {
  flex:1;
  width: 200rpx;
  height: 120rpx;
  background-color: rgba(16, 81, 142, 0.25);
  border-radius: 10rpx;
  text-align: center;
  line-height: 120rpx;
}
.order-btn:first-child {
  margin-right: 20rpx;
}

.title {
  margin-top: 10rpx;
  margin-bottom: 10rpx;
  margin-left: 15rpx;
  font-size: larger;
  font-weight: bold;
}
.msg-list {
  display: flex;
  flex-direction: column;
}
.msg-item {
  height: 80rpx;
  line-height: 80rpx;
  padding-left: 20rpx;
  margin: 10rpx 15rpx 10rpx 15rpx;
  background-color:#fff;
  border-radius: 6rpx;
  box-shadow: 5rpx 5rpx 5rpx #c0c0c0;
}
</style>
