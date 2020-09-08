import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/controllers/Home'
Vue.use(Router)

export default new Router({
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
  ]
})