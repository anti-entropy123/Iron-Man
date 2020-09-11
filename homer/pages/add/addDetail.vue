<template>
	<view>
		<view style="text-align: center;">
			<navigation :config="config" @customConduct="customConduct" />
		</view>
		<view>
			addDetail
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
					title: "",
					back: true
				},
			}
		},
		components: {
			navigation
		},
		onLoad(options) {
			this.config.title = options.type
			console.log(options.type)
		},
		methods: {
			customConduct() {
				uni.navigateBack({});
			},
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

	}
</script>

<style scoped>
</style>
