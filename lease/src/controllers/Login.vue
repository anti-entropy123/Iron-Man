<template>
    <div>
      <div class="container">
        <img src="/static/yjn.png" class ="img">
        <el-input
          class="input"
          placeholder="请输入账号"
          v-model="loginForm.inputUser"
          clearable>
        </el-input>
        <el-input
          placeholder="请输入密码"
          v-model="loginForm.inputPassword"
          show-password
          class="input">
        </el-input>
        <el-button
          class="login_button"
          type="primary"
          @click.native="Login"
          @keydown.enter.native="Login"
          round>登陆</el-button>
      </div>
    </div>
</template>

<script>
  export default {
    name: "Login",
    data () {
      return {
        loginForm: {
          inputUser:'',
          inputPassword:''
        },
      }
    },
    methods: {
      //登陆接口
      Login (){
        let _this = this;
        if(this.loginForm.inputUser === '' || this.loginForm.inputPassword===''){
          this.$message.warning('账号或密码不能为空')
        } else {
          this.$emit('myEvent')
          this.$router.push({
            path:'/user'
          })
          // this.$http.post ('/api/adm/login',{
          //   account: this.loginForm.inputUser,
          //   password: this.loginForm.password
          // }).then( res=>{
          //   console.log(res);
          //
          //   if (res.response && res.response.status!=200){
          //     let message = res.response.data.message
          //     _this.$message.error(message)
          //   } else {
          //     sessionStorage.setItem('token', res.data.token)
          //     _this.$message.success("登录成功，请手动刷新")
          //     let a = this.$route.params.id
          //     // window.location = a
          //     _this.$router.push(a)
          //   }
          // }).catch(err=>{
          //   _this.$message.error('系统出错')
          // })

        }

      },

    },
    computed : {

    }
  }
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column
}
.img {
  height: 80px;
  width: 80px;
  border-radius: 50%;
  overflow: hidden;
  margin-top: 90px;
}
.input{
  padding-top: 10px;
  width: 30%
}
.login_button {
  margin-top: 25px;
  width: 20%;
  background-color: #3c3f41;
  opacity: 50%;
}

</style>
