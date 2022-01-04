<template>
  <div id="login"  data-title="登录">
    <div class="rs-login-box rs-login-box-radius">
      <h1>登录</h1>
      <el-form ref="loginForm" :rules="rules" :model="loginForm">
        <el-form-item prop="account">
          <el-input placeholder="用户名" type="text" v-model="loginForm.username"></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input placeholder="密码" type="password" v-model="loginForm.password"></el-input>
        </el-form-item>

        <el-form-item prop="remember" class="rs-remember">
          <el-checkbox label="记住我" name="type"></el-checkbox>
          <router-link to="/" class="rs-reset-password">忘记密码？</router-link>
        </el-form-item>

        <el-form-item size="small" class="rs-login-button">
          <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
        </el-form-item>
      </el-form>

      <div class="rs-line"></div>

      <div class="rs-register">
        <span>Don't have an account?</span>
        <router-link to="/" >立即注册</router-link>
      </div>


    </div>
  </div>
</template>

<script>
import ApiManager from "../api";
import service from "../api/request";

export default {
  name: "LoginIn",
  data(){
    return{
      loginForm: {
        username: '',
        password: ''
      },
      rules:{
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {max: 15, message: '不能大于15个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {max: 20, message: '不能大于20个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods:{
    submitForm(loginForm){
      const _this = this;
      this.$refs[loginForm].validate(valid=>{
        if(valid){
          //提交逻辑
          _this.$store.dispatch('login',_this.loginForm).then(res=>{
            _this.$notify({
              title: '登录成功：',
              message: '欢迎 '+ res.nickname,
              duration: 1500
            });
            _this.$router.go(-1)
          })
        }else {
          console.log('error submit!!');
          return false;
        }
      })

    }
  }
}
</script>

<style scoped>
#login {
  min-width: 100%;
  min-height: 100%;
}

.rs-login-box {
  position: absolute;
  width: 300px;
  height: max-content;
  background-color: white;
  margin-top: 120px;
  margin-left: -180px;
  left: 50%;
  padding: 30px;
}

.rs-login-box-radius {
  border-radius: 10px;
  box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
}

.rs-login-box h1 {
  text-align: center;
  font-size: 24px;
  margin-bottom: 20px;
  vertical-align: middle;
}

.rs-login-button {
  text-align: center;
}

.rs-login-button button {
  width: 100%;
}

.rs-remember{
  margin-bottom:0px;
}

.rs-reset-password{
  float: right;
  text-decoration:none;
  color: #8590a6;
}

.rs-line{
  box-sizing: border-box;
  min-width: 0;
  border-top: 1px solid;
  border-color: #EBEBEB;
  margin-left: 24px;
  margin-right: 24px;
}

.rs-register{
  text-align: center;
}

.rs-register span{
  color: #a7abc3;
  line-height: 1.5rem;
  margin-top: 5px;
  font-size:.835rem ;
}
.rs-register a{
  color: #409EFF;
  font-size:.835rem ;
  margin-left:10px ;
}


</style>