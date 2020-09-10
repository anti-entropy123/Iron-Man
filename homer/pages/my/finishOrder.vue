<template>
	<view>
		<view style="text-align: center;">
			<navigation :config="config"/>
		</view>
		finishOrder
	</view>
</template>

<script>
	export default  {
		data() {
			return{
				config: {
					type:1,//0无title，1有title
					title: '已完成订单',
					back:true
				},
			}
		}
	}
</script>

<style scoped>
</style>
