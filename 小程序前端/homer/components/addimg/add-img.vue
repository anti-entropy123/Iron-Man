<template>
	<view>
		<view class="img_upload_wrap" v-if="imgsrcs.length>0" v-for="(imgsrc,index) in imgsrcs" :key="index">

			<view class="imgtext_curimg_wrap">
				<!-- <view v-if="upload_progress[index] < 100" :style="{clip: 'rect(' + parseInt(2.5*upload_progress[index]) + 'rpx 250rpx 250rpx 0)'}"
				 class="upload_progress_mask"></view>
				<view v-if="upload_progress[index] == 100" @click="deleteImg(index)" class="imgtext_del_button">
					<image src="../../static/deleteimg.png" style="width:40upx;height:40upx;"></image>
				</view> -->
				<view @click="deleteImg(index)" class="imgtext_del_button">
					<image src="../../static/deleteimg.png" style="width:40upx;height:40upx;"></image>
				</view>
				<image class="imgtext_curimg" :src="imgsrc" @click="previewImg" mode="aspectFill" :data-idx="index"></image>
			</view>
		</view>
		
		<view class="img_upload_wrap" v-if="imgsrcs.length<9">
			<view @click="chooseImage" class="imgtext_addimg">
				<image class="imgtext_addimg_icon" src="../../static/addimage.png" mode="aspectFit"></image>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				imgsrcs: [],
				upload_progress: [0, 0, 0, 0, 0, 0, 0, 0, 0],
			};
		},
		name: 'add-img',
		created() {},
		mounted() {},
		methods: {
			chooseImage(count) {
				uni.chooseImage({
					count: 1,
					success: (res) => {
						console.log(res)
						let url = res.tempFilePaths[0]
						this.imgsrcs.push(url)
						this.uploadimage(url)
					},
					fail: (err) => {
						console.log(err)
					}
				});
			},
			uploadimage(url) {
				let id = this.imgsrcs.length;
				let upload_task = uni.uploadFile({
					url: 'http://188.131.227.20:1314/api/publish/image',
					filePath: url,
					name: 'image',
					header: {
						Authorization: 'Bearer ' + uni.getStorageSync('token')
						//Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiJ5b3VqaWFuaW5nIn0.agJw2OXrczMkLLD-JhqLb0EsUpEVkZ3NzigN4oEQJp68fEzRNQJEVrXRDB03xsG1EH6XTvBYImR3e1IzKbJUeA"
					},
					success: (res) => {
						console.log(res)
						console.log(JSON.parse(res.data))
						let data = JSON.parse(res.data)
						if (data.result == 1) {
							// this.upload_progress[id] = 100
							this.$emit('onUpImg', data.url);
						} else {
							uni.showToast({
								title: data.message,
								icon: 'none'
							})
						}

					}
				})
				// this.upload_progress[id] = 0
				upload_task.onProgressUpdate((res) => {
					// progress==100后还需要等待服务器返回url才算上传完成
					// 所以直接取progress的80%的值，到服务器返回结果后再直接赋值为100
					// this.upload_progress[id] = res.progress * 0.8
				});
			},
			deleteImg(index) {
				this.$emit('onImgDel', index);
				this.imgsrcs.splice(index, 1);
			},
			// previewImg() {
			// 	uni.previewImage({
			// 		current: this.imgsrc,
			// 		urls: [this.imgsrc]
			// 	});
			// }
			previewImg(e) {
				puImage(e, this);
			},
		}
	};
	const puImage = async (e, _this) => {
		let cacheImg = [];
		for (let i = 0, len = _this.imgsrcs.length; i < len; i++) {
			cacheImg.push(_this.imgsrcs[i]);
		}
		uni.previewImage({
			current: cacheImg[e.currentTarget.dataset.idx],
			urls: cacheImg
		});
	};
</script>

<style scoped lang="scss">
	.img_upload_wrap {
		width: 190upx;
		height: 190upx;
		margin-left: 25upx;
		margin-top: 25upx;
		float: left;
		background-color: #F9F9F9;
		border-radius: 20upx;
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
				box-shadow: 0px 6upx 12upx rgba(0, 0, 0, 0.16);
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
</style>
