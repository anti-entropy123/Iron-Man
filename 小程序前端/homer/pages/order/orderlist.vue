<template>
	<view class="orderlist">
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct"/>
		</view>
	</view>
</template>

<script>
	import navigation from '../../components/navigation/navigation';
	export default {
		data() {
			return {
				config: {
					type:2,//0无title，1有title
					renttype:0,
					back:true,
				},
				type:0,
				key:'',
			}
		},
		components: {
			navigation
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
			onLoad(options) {
				this.config.renttype=options.type
				this.type = options.type
			}
		}
	}
</script>

<style>
</style>
