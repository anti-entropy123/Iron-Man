<template>
    <div>
      <div class="container">
        <div class="mbry">
          <img src="static/mbry.png">
        </div>
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
          this.$http.post(`/api/adm/login`,{
            account:this.loginForm.inputUser,
            password:this.loginForm.inputPassword
          }).then(res =>{
            console.log(res);
            if (res.status!=200 || res.data.result==0){
              let message = res.data.message
              _this.$message.error(message)
            } else {
              sessionStorage.setItem('token', res.data.token)
              sessionStorage.setItem('username', '尤嘉宁');
              sessionStorage.setItem('userImage', '/static/yjn.png')
              _this.$message.success("登录成功")
              this.$emit('myEvent')
              this.$router.push({
                path:'/user'
              })
            }
          }).catch(err=>{
            _this.$message.error('系统出错')
          })
        }

      },
    },
    computed : {
    }
  }
</script>

<style scoped>
.container {
  padding-top: 120px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column
}
.mbry {
  height: 70px;
  width: 300px;
}
.mbry img{
  height: 70px;
  width: 300px;
}
.input{
  padding-top: 10px;
  width: 30%
}
.login_button {
  margin-top: 25px;
  width: 20%;
  background-color: #3c3f41;
  opacity: 0.5;
}

</style>
