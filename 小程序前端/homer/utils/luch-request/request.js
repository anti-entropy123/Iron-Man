export default class Request {
	config = {
		baseUrl: '',
		header: {
			'Content-Type': 'application/json'
		},
		method: 'POST',
		dataType: 'json',
		responseType: 'text',
		success() {},
		fail() {},
		complete() {}
	}

	static posUrl(url) { /* 判断url是否为绝对路径 */
		return /(http|https):\/\/([\w.]+\/?)\S*/.test(url)
	}

	interceptor = {
		request: (f) => {
			if (f) {
				this.requestBeforeFun = f
			}
		},
		response: (f) => {
			if (f) {
				this.requestComFun = f
			}
		}
	}

	static requestBeforeFun(config) {
		return config
	}

	static requestComFun(response) {
		return response
	}

	setConfig(f) {
		this.config = f(this.config)
	}

	request(options = {}) {
		options.baseUrl = options.baseUrl || this.config.baseUrl
		options.dataType = options.dataType || this.config.dataType
		options.url = Request.posUrl(options.url) ? options.url : (options.baseUrl + options.url)
		options.data = options.data || {}
		options.header = options.header || this.config.header
		options.method = options.method || this.config.method
		return new Promise((resolve, reject) => {
			let next = true
			let _config = null
			options.complete = (response) => {
				console.log(response)
				let statusCode = response.statusCode
				let result = response.data.result
				response.config = _config
				response = this.requestComFun(response)
				if (statusCode === 200 && result === 1) { // 成功
					resolve(response.data)
				} else if (response.data.message) {
					uni.showToast({
						title: response.data.message,
						icon: "none"
					})
					reject(response)
				} else {
					uni.showToast({
						title: response.data.error,
						icon: "none"
					})
					reject(response)
				}
			}
			let cancel = (t = 'handle cancel') => {
				let err = {
					errMsg: t,
					config: afC
				}
				reject(err)
				next = false
			}
			let afC = { ...this.config,
				...options
			}
			_config = { ...afC,
				...this.requestBeforeFun(afC, cancel)
			}
			if (!next) return
			uni.request(_config)
		})
	}

	get(url, data, options = {}) {
		options.url = url
		options.data = data
		options.method = 'GET'
		return this.request(options)
	}

	post(url, data, options = {}) {
		options.url = url
		options.data = data
		options.method = 'POST'
		return this.request(options)
	}
}
