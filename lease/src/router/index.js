import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/controllers/Home'
import Rent from '@/controllers/Rent'
import User from '@/controllers/User'
import Login from '@/controllers/Login'

Vue.use(Router)

export default new Router({
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },{
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/rent',
      name: 'Rent',
      component: Rent
    },{
      path: '/user',
      name: 'User',
      component: User
    }
  ]
})
