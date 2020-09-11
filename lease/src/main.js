// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import './theme/index.css'
import qs from 'qs'
import axios from 'axios'

Vue.prototype.$http = axios
axios.defaults.withCredentials = true
axios.defaults.baseURL = 'http://188.131.227.20:8080' // 关键步骤–填写后台请求统一的地址
axios.defaults.headers.post['Content-Type'] = 'application/json'
// axios.defaults.headers.post['x-csrf-token'] = localStorage.getItem('token')

axios.interceptors.request.use((config) => {
  if (config.method === 'post') {
    config.data = qs.stringify(config.data)
  }
  return config
}, (error) => {
  return Promise.reject(error)
})
Vue.config.productionTip = false
// Vue.config.devtools = true


Vue.use(ElementUI, { size: 'small', zIndex: 3000 })
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})