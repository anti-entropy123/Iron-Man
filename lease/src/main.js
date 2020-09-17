// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

import ElementUI from 'element-ui'
import './theme/index.css'
import qs from 'qs'
import axios from 'axios'
import router from './router'
Vue.prototype.$http = axios
axios.defaults.withCredentials = true
axios.defaults.baseURL = 'https://tjuyjn.top:1314'
Vue.config.productionTip = false
// 关键步骤–填写后台请求统一的地址
// axios.defaults.headers.post['Content-Type'] = 'application/json'
// axios.defaults.headers.post['Authorization'] = localStorage.getItem('token')
// axios.defaults.headers.post['Access-Control-Allow-Origin'] = "*"

axios.interceptors.request.use((config) => {
  const USER_TOKEN = sessionStorage.getItem('token')
  if(USER_TOKEN && !config.url.includes('login')){
    config.headers.Authorization = "Bearer "+ USER_TOKEN
  }
   config.headers['Access-Control-Allow-Origin'] = '*'
    config.headers['Access-Control-Allow-Credentials'] = 'true'
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
