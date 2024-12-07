<template>
  <el-container>
    <el-header style="height: 60px;">
      <el-row type="flex" class="row-bg" justify="space-around" style="align-items: center; height: 100%;">
        <el-col :span="6">
          <el-page-header @back="goBack" content="详情页面" style="line-height: 60px;">
          </el-page-header>
        </el-col>
        <el-col :span="8" style="text-align: center;">
          <h1>hahashop</h1>
        </el-col>
        <el-col :span="8" style="text-align: right;">
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <div class="login-wrap">
        <h3 style="text-align: center;">登录</h3>
        <el-row type="flex" justify="center">
          <el-form :model="form" label-position="left" label-width="80px">
            <el-form-item label="账号">
              <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="form.password"></el-input>
            </el-form-item>
          </el-form>
        </el-row>
        <el-row type="flex" justify="center">
          <el-button type="primary" class="loginbutton" @click="SendLogin()" style="margin-left: 0;">确 定</el-button>
        </el-row>
        <el-row type="flex" justify="center">
          <el-button type="primary" class="registerbutton" @click="ToRegister()">注册</el-button>
        </el-row>
      </div>
    </el-main>
  </el-container>

</template>

<script>
  export default {
    name: "LoginView",
    data() {
      return {
        form: {
          username: "",
          password: ""
        }
      };
    },
    methods: {
      SendLogin() {
        this.$store.dispatch('LoginByUserName', this.form).then(() => {
          this.$router.push('/dashboard').catch(err => {
            console.log(err)
          })
          console.log('success')
        }).catch(err => {
          console.log(err)
        })
      },
      goBack() {
        this.$router.push('/')
      },
      ToRegister(){
        this.$router.push('/register')
      }
    }
  }
</script>
<style scoped>
  .login-wrap {
    background: #ffffff;
    background-size: cover;
    width: 400px;
    height: 350px;
    margin: 100px auto;
    margin-bottom: 0px;
    overflow: hidden;
    padding-top: 10px;
    line-height: 40px;
  }

  #password {
    margin-bottom: 5px;
  }

  h3 {
    color: #0babeab8;
    font-size: 24px;
  }

  hr {
    background-color: #444;
    margin: 20px auto;
  }

  a {
    text-decoration: none;
    color: #aaa;
    font-size: 15px;
  }

  a:hover {
    color: coral;
  }

  .el-button.loginbutton{
    width: 80%;

  }

  .el-button.registerbutton{
    width: 20%;
    margin-top: 30px;
  }

  /* 头部、尾部布局 */
  .el-header,
  .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 100px;
    height: 100px;
    /* 确保高度足够 */
    padding: 0 20px;
    /* 添加一些内边距 */
    box-sizing: border-box;
    /* 确保内边距不会影响总高度 */
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body>.el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>