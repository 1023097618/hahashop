<template>
  <div class="login-wrap">
    <h3 style="text-align: center;">修改密码</h3>
    <el-row type="flex" justify="center">
      <el-form :model="form" label-position="left" label-width="80px">
        <el-form-item label="旧密码">
          <el-input v-model="form.oldPassword"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="form.newPassword"></el-input>
        </el-form-item>
      </el-form>
      
    </el-row>
    <el-row type="flex" justify="center">
      <el-button type="primary" @click="PasswordChanege()" style="margin-left: 0;">确 定</el-button>
    </el-row>
  </div>

</template>

<script>
  import {changePassword}  from '@/api/auth/login.js'
export default {
  
  name: "ChangePasswordView",
  data(){
    return {
      form:{
        oldPassword:'',
        newPassword:''
      }
    }
  },
  methods:{
    PasswordChanege(){
      changePassword(this.form).then(res=>{
        console.log(res)
      }).catch(err=>{
        console.log(err)
      })
    }
  }
}
</script>
<style scoped>
    .login-wrap {
    background: #ffffff;
    background-size: cover;
    width: 400px;
    height: 300px;
    margin: 100px auto;
    overflow: hidden;
    padding-top: 10px;
    line-height: 40px;
  }
  </style>