<template>
	<view class="add">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>

		<view class="flex">
			<view class="top_input">
				<input type="text" class="titleinput" :maxlength="textareaLength" v-model="title" placeholder="请输入标题..."
				 placeholder-class="plc" />
				<view class="info">{{ title.length }}/{{ textareaLength }}</view>
			</view>
		</view>

		<view class="flex">
			<view class="top_txt">
				<textarea v-if="type==1" @input="handtextarea1" class="textarea" value="" placeholder="请输入其他房屋介绍, 例如楼层、有无电梯、最短租期、装修情况、家具家电情况等"
				 :maxlength="textareaLength1" v-model="inputValue" />
				<textarea v-else-if="type==2" @input="handtextarea1" class="textarea" value="" placeholder="请输入其他房屋介绍, 例如产权、朝向、楼层、装修情况、有无电梯等" :maxlength="textareaLength1" v-model="inputValue" />
				<textarea v-else-if="type==3" @input="handtextarea1" class="textarea" value="" placeholder="请输入其他求租描述, 例如楼层、有无电梯、最短租期、装修情况、家具家电情况等" :maxlength="textareaLength1" v-model="inputValue" />
				<textarea v-else-if="type==4" @input="handtextarea1" class="textarea" value="" placeholder="请输入其他求购描述, 例如产权、朝向、楼层、装修情况、有无电梯等" :maxlength="textareaLength1" v-model="inputValue" />
				<textarea v-else-if="type==5" @input="handtextarea1" class="textarea" value="" placeholder="请输入对自己的介绍, 例如性格,爱好,习惯等" :maxlength="textareaLength1" v-model="inputValue" />
				<view class="info1">{{ introduce.length }}/{{ textareaLength1 }}</view>
			</view>
		</view>
		
		<view class="flex" v-if="type==1||type==5">
			<view class="top_txt">
				<textarea v-if="type==1" @input="handtextarea2" class="textarea" value="" placeholder="请输入对租客要求" :maxlength="textareaLength2" v-model="inputValue1" />
				<textarea v-else @input="handtextarea2" class="textarea" value="" placeholder="请输入对室友的要求, 例如性别, 作息, 习惯等" :maxlength="textareaLength2" v-model="inputValue1" />
				<view class="info1">{{ require.length }}/{{ textareaLength2 }}</view>
			</view>
		</view>
		
		<view class="flex" style="margin-top:0;">
			<view style="width:670upx;">
				<addimg
				@onUpImg="onImgUploaded"
				@onImgDel="onImgDeleted"
				></addimg>
			</view>
		</view>
		
		<view class="center" v-if="type==5">
			<view style="align-items: center;">
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">是否已有房源</text>
			</view>
			<radio-group @change="checkresource" style="float:right;margin-right:42.36upx;">
				<label>
					<radio value="1"  checked="true" />
					<text style="color:#707070;font-size:28upx;">有</text>
				</label>
				<label style="margin-left:100upx;">
					<radio value="0" />
					<text style="color:#707070;font-size:28upx;">无</text>
				</label>
			</radio-group>
			<view style="clear: both;"></view>
		</view>
		
		<view class="center">
			<view style="align-items: center;" v-if="type==1||type==2">
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">价格(单位:元)</text>
				<input type="digit" value="" placeholder="出个价吧" v-model="prices[0]" class="input_digit_long" />
			</view>
			<view style="align-items: center;" v-else>
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">{{(type==5&&hasHouseResource)?"":"预期"}}价格(单位:元)</text>
				<input type="digit" value="" placeholder="最高价格" v-model="prices[1]" class="input_digit"/>
				<view style="float:right;margin:0 10upx;"> ~ </view>
				<input type="digit" value="" placeholder="最低价格" v-model="prices[0]" class="input_digit" style="margin-right:0;"/>
			</view>
			<view style="clear: both;"></view>
		</view>
		
		<view class="center">
			<view style="align-items: center;" v-if="type==1||type==2">
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">面积(单位:㎡)</text>
				<input type="digit" value="" placeholder="面积大小" v-model="squares[0]" class="input_digit_long" />
			</view>
			<view style="align-items: center;" v-else>
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">{{(type==5&&hasHouseResource)?"":"预期"}}面积(单位:㎡)</text>
				<input type="digit" value="" placeholder="最大面积" v-model="squares[1]" class="input_digit"/>
				<view style="float:right;margin:0 10upx;"> ~ </view>
				<input type="digit" value="" placeholder="最小面积" v-model="squares[0]" class="input_digit" style="margin-right:0;"/>
			</view>
			<view style="clear: both;"></view>
		</view>
		
		<view class="center">
			<view style="align-items: center;">
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">{{(type==1||type==2||(type==5&&hasHouseResource))?"":"预期"}}户型</text>
				<input class="input_digit_long" placeholder="几室几厅" v-model="unitType" @tap="showpicker()" disabled=true></uni-combox>
				<view class="gpp-datePicker">
					<slot />
					<view class="g-dp-mask" :class="{'show':pickershow}" @click.stop="onCancel" @touchmove.stop.prevent catchtouchmove="true"></view>
					<view class="g-dp-content" :class="{'show':pickershow}" @touchmove.stop.prevent catchtouchmove="true">
						<view class="g-dp-ctt-head">
							<view class="g-dp-ctt-hd-btn" @click.stop="onCancel">取消</view>
							<view class="g-dp-ctt-hd-btn" style="color:#6ba1ff" @click.stop="onConfirm">确定</view>
						</view>
						<view class="g-dp-ctt-wrapper">
							<picker-view :indicator-style="indicatorStyle" :value="selectedValue" @change="wrapperChange">
								<picker-view-column>
									<view class="g-dp-ctt-wp-item" v-for="(item,index) in unitTypes" :key="index">{{item}}</view>
								</picker-view-column>
							</picker-view>
						</view>
					</view>
				</view>
			</view>
			<view style="clear: both;"></view>
		</view>
		
		<view class="center">
			<view style="align-items: center;" v-if="type==1||type==2">
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">位置</text>
				<input type="text" value="" placeholder="地理位置" v-model="location" class="input_digit_long" @tap="getlocation()" disabled=true/>
			</view>
			<view style="align-items: center;" v-else>
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">{{(type==5&&hasHouseResource)?"":"预期"}}位置</text>
				<input type="text" value="" placeholder="地理位置" v-model="location" class="input_digit_long" @click="showPCA" disabled=true/>
				<pcaPicker ref="pcaPicker" @confirm="getPCA"></pcaPicker>
			</view>
			<view style="clear: both;"></view>
		</view>
		
		
		
		<view class="center">
			<view style="align-items: center;" v-if="type==1||type==5">
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">最多合租人数</text>
				<input type="digit" value="" placeholder="1" v-model="unionNum" class="input_digit_long" />
			</view>
			<view style="align-items: center;" v-if="type==3">
				<text style="font-size: 28upx;color:#707070;font-family:PingFangSC-Regular;margin-left:48upx;line-height:54upx ;float:left">合租人数</text>
				<input type="digit" value="" placeholder="1" v-model="unionNum" class="input_digit_long" />
			</view>
			<view style="clear: both;"></view>
		</view>
		
		<view style="height:120upx;width:100vw;background: #ffffff;"></view>
		
		<view class="btnbox">
			<view class="onerent" @tap="handle">我要发布</view>
		</view>
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	import addimg from '../../components/addimg/add-img';
	import uniCombox from '../../components/uni-combox/uni-combox';
	import pcaPicker from '@/components/pcaPicker/pcaPicker.vue'
	export default {
		data() {
			return {
				config: {
					type: 2, //0无title，1有title
					renttype: 1,
					back: true
				},
				type:1,
				userId:'',
				title: '',
				introduce:'',
				require:'',
				inputValue: '',
				inputValue1: '',
				textareaLength: 20,
				textareaLength1: 100,
				textareaLength2: 100,
				images:[],
				prices:[],
				squares:[],
				location:'',
				longitude:0,
				latitude:0,
				hasHouseResource:true,
				unionNum:1,
				unitType:null,
				unitTypes:['一室一厅','两室一厅','三室一厅','三室两厅','其他'],
				unitNum:-1,
				pickershow:false,
				selectedValue:0,
				indicatorStyle: `height: ${uni.upx2px(88)}px;`,
				havecoordinates:0
			}
		},
		components:{
			navigation,
			addimg,
			uniCombox,
			pcaPicker
		},
		onLoad(options) {
			this.config.renttype = options.type;
			this.type = options.type;
		},
		onReady(){
			if (uni.getStorageSync('userId')) {
				this.userId = uni.getStorageSync('userId')
			} else{
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
		},
		onShow() {
			var pages = getCurrentPages();
			var currPage = pages[pages.length - 1]; //当前页面
			let res = currPage.data.userdata;
			console.log(res)
			if(res){
				this.location = res.address
				this.latitude = res.latitude
				this.longitude = res.longitude
				this.havecoordinates = 1
			}
		},
		watch:{
			title(){
				if(this.title.length>20){
					this.title=this.title.slice(0,20)
					console.log(this.title)
				}
			}
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			handtextarea1(event) {
				this.introduce = event.target.value;
				console.log(this.introduce);
			},
			handtextarea2(event) {
				this.require = event.target.value;
				console.log(this.require);
			},
			onImgUploaded(e){
				this.images.push(e);
				uni.showToast({
					title: '上传图片成功',
					icon: 'success'
				});
				console.log(this.images);
			},
			onImgDeleted(e) {
				this.images.splice(e,1)
				console.log(this.images);
			},
			showpicker(){
				this.pickershow = true;
			},
			wrapperChange(e){
				let detailValue = e.detail.value[0];
				console.log(e.detail)
				this.selectedValue = detailValue;
			},
			onCancel(){
			    this.pickershow = false;
			},
			onConfirm(){
				this.unitType = this.unitTypes[this.selectedValue]
				this.unitNum = this.selectedValue
				console.log(this.unitNum)
				this.pickershow = false;
			},
			checkresource(e){
				this.hasHouseResource = e.detail.value == 1?true:false;
				console.log(this.hasHouseResource)
			},
			getlocation(){
				uni.navigateTo({
					url:'../add/getmap'
				})
			},
			showPCA(e){
				this.$refs.pcaPicker.show();
			},
			getPCA(e) {
				console.log(e);
				this.location = e;
			},
			handle(){
				if (this.title==''||this.introduce==''||this.prices.length==0||this.squares.length==0||this.unitNum==-1||this.location==''){
					uni.showToast({
						title: '填写信息不完整',
						icon: 'none'
					});
					return;
				}
				let prices = [parseFloat(this.prices[0]),parseFloat(this.prices[1])]
				let squares = [parseFloat(this.squares[0]),parseFloat(this.squares[1])]
				let coordinates = []
				if (this.havecoordinates==1){
					coordinates = [this.latitude.toFixed(6),this.longitude.toFixed(6)]
				}
				let _this = this;
				uni.showModal({
					title:'提示',
					content:'是否确定发布',
					success:function(response){
						if (response.confirm) {
						    console.log('用户点击确定');
							_this.$http.post('/api/publish/',{
								userId:_this.userId,
								type:_this.type,
								title:_this.title,
								introduce:_this.introduce,
								location:_this.location,
								prices:prices,
								squares:squares,
								unitType:_this.unitNum,
								images:_this.images,
								require:_this.require,
								unionNum:_this.unionNum,
								hasHouseResource:_this.hasHouseResource,
								coordinates:coordinates
							}).then(res => {
								console.log(res)
								setTimeout(() => {
									uni.showToast({
										title: '发布成功',
										icon: 'success'
									});
								}, 100);
								uni.switchTab({
									url:'../home/home'
								})
							}).catch((e) => {});
						} else if (response.cancel) {
						    console.log('用户点击取消');
						}
					},
					fail:function(){}
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.add{
		width:100%;
	}
	.flex{
		display: flex;
		align-items: center;
		justify-content: center;
		margin-top:20upx;
	}
	.top_input {
		width: 670upx;
		display: flex;
		position: relative;
		font-size: 24upx;
		color: rgba(147, 147, 147, 1);
		font-weight: bold;
	}
	.top_txt {
		color: rgba(147, 147, 147, 1);
		font-weight: bold;
		font-size: 24upx;
		width: 670upx;
		position: relative;
	}
	.info {
		position: absolute;
		right: 22upx;
		top: 18upx;
	}
	.titleinput {
		box-sizing: border-box;
		padding-left: 18upx;
		width: 670upx;
		height: 68upx;
		background:#FCFCFC;
		/* border:2upx solid rgba(240,240,240,1); */
		opacity:1;
		border-radius:10upx;
		font-weight: bold;
	}
	.textarea {
		box-sizing: border-box;
		padding-left: 18upx; 
		padding-top: 14upx;
		width:670upx;
		height: 126upx;
		background:#FCFCFC;
		/* border:2upx solid rgba(240,240,240,1); */
		border-radius:10upx;
		color: rgba(147, 147, 147, 1);
	}
	.info1 {
		position: absolute;
		right: 20upx;
		bottom: 14upx;
		font-size: 24upx;
	}
	.imagelist{
		width:190upx;
		height:190upx;
		margin-left:25upx;
		margin-top:25upx;
		float:left;
		background-color:#F9F9F9;
		border-radius: 20upx;
	}
	
	.center {
		align-items: center;
		margin-top: 30upx;
		position:relative;
	}
	
	.input_digit {
		float:right;
		margin-right:42.36upx;
		font-size: 28upx;
		width:150upx;
		height:50upx;
		background:#FFFFFF;
		border:2upx solid #F2F2F2;
		border-radius:4upx;
		line-height: 50upx;
		padding-left:20upx;
	}
	
	.input_digit_long {
		float:right;
		margin-right:42.36upx;
		font-size: 28upx;
		width:250upx;
		height:50upx;
		background:#FFFFFF;
		border:2upx solid #F2F2F2;
		border-radius:4upx;
		line-height: 50upx;
		padding-left:20upx;
	}
	
	.img_upload_wrap {
		width: inherit;
		height: inherit;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
		
		.imgtext_addimg {
			text-align: center;
			display: flex;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			width: 100%;
			height: 100%;
			border-radius: 40upx;
			margin-top:10upx;
			
			.imgtext_addimg_icon {
				width: 170upx;
				height: 170upx;
				opacity: 0.7;
			}
		}
		
		.imgtext_curimg_wrap {
			text-align: center;
			display: inline-block;
			position: relative;
			// top: 5upx;
			
			.upload_progress_mask {
				width: 190upx;
				height: 190upx;
				position: absolute;
				left: 0;
				z-index: 10;
				background-color: #ababab;
				opacity: 0.8;
				border-radius: 20upx;
			}
			
			.imgtext_curimg {
				width: 190upx;
				height: 190upx;
				box-shadow:0px 6upx 12upx rgba(0,0,0,0.16);
				border-radius: 20upx;
			}
			
			.imgtext_del_button {
				position: absolute;
				right: 0;
				background-color: #e54d42;
				color: #ffffff;
				width: 40upx;
				height: 40upx;
				border-top-right-radius: 6upx;
			}
		}
	}
	
	.gpp-datePicker{
		position: relative;
		z-index: 999;
		.g-dp-mask{
			position: fixed;
			z-index: 1000;
			top: 0;
			right: 0;
			left: 0;
			bottom: 0;
			background: rgba(0, 0, 0, 0.6);
			visibility: hidden;
			opacity: 0;
			transition: all 0.3s ease;
		}
		.g-dp-mask.show{
			visibility: visible;
			opacity: 1;
		}
		.g-dp-content{
			position: fixed;
			z-index: 1001;
			bottom: 0;
			right: 0;
			width: 100%;
			transition: all 0.3s ease;
			transform: translateY(100%);
			.g-dp-ctt-head{
				height: 88upx;
				background-color: #FFFFFF;
				border-bottom: 1px solid #e5e5e5;
				padding: 0 40upx;
				display: flex;
				align-items: center;
				justify-content: space-between;
				.g-dp-ctt-hd-btn{
					color: #888;
					font-size: 17px;
				}
			}
			.g-dp-ctt-wrapper{
				height: 480upx;
				width: 100%;
				background-color: #FFFFFF;
				.g-dp-ctt-wp-item{
					text-align: center;
					width: 100%;
					height: 88upx;
					line-height: 88upx;
					text-overflow: ellipsis;
					white-space: nowrap;
					font-size: 30upx;
				}
			}
		}
		.g-dp-content.show{
			transform: translateY(0);
		}
		picker-view-column{
			height: 480rpx !important;
		}
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
		z-index:999;
	}
	
	.onerent{
		width:250upx;
		float:right;
		height:100upx;
		color:#FFFFFF;
		background: #DBAF66;
		font-size:30upx;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}
	
</style>
