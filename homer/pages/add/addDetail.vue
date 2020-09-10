<template>
	<view>
		addDetail
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		onLoad: function(options) {
			console.log(options)
			var types = new Array("出租", "租房", "出售", "买房", "找室友")
			const a = parseInt(JSON.parse(options.num));
			console.log(app.globalData.publishTitle = types[a]);
			this.title = app.globalData.publishTitle
			console.log(this.title)
		},

	}
</script>

<style scoped>
</style>
