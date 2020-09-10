<template>
	<view>
		<view style="text-align: center;">
			<navigation :config="config"/>
		</view>
		unfinishOrder
	</view>
</template>

<script>
	export default  {
		data() {
			return{
				config: {
					type:1,//0无title，1有title
					title: '未完成订单',
					back:true
				},
			}
		}
	}
</script>

<style scoped>
</style>
