import Request from './request'

const http = new Request();
http.setConfig((config) => { /* 设置全局配置 */
	
	config.baseUrl = 'http://188.131.227.20:1314'
	
	//config.baseUrl = 'http://172.23.12.67:1314'
	
	return config
})
http.interceptor.request((config, cancel) => { /* 请求之前拦截器 */
	
	config.header = {
		...config.header,
		Authorization: 'Bearer ' + uni.getStorageSync('token'),
	}
	
	return config;
})
http.interceptor.response((response) => { /* 请求之后拦截器 */
	console.log(response);
	return response;
})
export {
	http
};
