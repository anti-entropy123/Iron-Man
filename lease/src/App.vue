<template>
  <div id="app">
    <el-row >
      <el-col  :span="4" >
        <div class="left-side">
          <div class="administrator">
            <div class="img">
              <img style="height: 100%;width: 100%" :src="adm.url">
            </div>
            <div style="text-align: center;margin-top: 8px">管理员:{{adm.name}}</div>
          </div>
          <div class="line"></div>
          <div class="menu">
            <router-link to="/rent" class="menu-item">租赁</router-link>
            <router-link to="/user" class="menu-item">用户</router-link>
          </div>
        </div>
      </el-col>
      <el-col :span="20" >
        <div class="container" >
          <div class="headline ">
            <div >北洋房屋租售信息管理系统</div>
            <div class="login_btn" @click="goLogin" >{{login_status}}</div>
          </div>
        </div>
        <router-view @myEvent="handle"></router-view>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      adm:{
        name:'yjn',
        url:'/static/yjn.png'
      },
      login_status: '点击登陆'
    }
  },
  methods: {
    goLogin () {
      this.login_status=''
      this.$router.push('/login')
    },
    handle(){
      this.login_status='登出'
    }
  },
  computed : {

  },
  mounted() {

  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
.menu-item {
  color: #fff;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
}
#app {
  background-color: #f0f0f0;
}
.line {
  height: 1px;
  border-top: 1px solid #c7c7c7;
  opacity:0.5;
  margin:10px;
}
.left-side{
  min-height: 100vh;
  background-color: #2e2e2e;
  box-shadow: 2px 2px 2px #c0c0c0;
  color: #f0f0f0;
}
.img {
  height: 80px;
  width: 80px;
  border-radius: 50%;
  overflow: hidden;
  margin-top: 90px;
}
.administrator{
  height: 200px;
  display: flex;
  align-items: center;
  flex-direction: column;
}
.menu {
  display: flex;
  align-items: center;
  flex-direction: column;
  line-height: 35px
}
.shadow {
  box-shadow: 5px 5px 5px #c0c0c0;
  border-radius: 10px;
}
.headline{
  height: 80px;
  background-color: #fdfdfd;
  opacity: 70%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "Adobe 楷体 Std R";
  font-size: x-large;
}
  .login_btn {
    font-size:14px;
    position: absolute;
    top:20px;right:15px;
    cursor: pointer;
    color: #41bcfe
  }
</style>
