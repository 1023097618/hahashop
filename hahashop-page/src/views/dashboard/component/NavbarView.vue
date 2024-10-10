<template>
  <div class="navbar" style="display: flex; justify-content: space-between;">
    <el-page-header @back="goBack" content="详情页面" style="line-height: 60px;">
    </el-page-header>
    <el-dropdown placement="bottom-start" @command="handleCommand">
      <span class="el-dropdown-link" style="font-size: 30px;">
        {{username}}<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="logout">登出</el-dropdown-item>
        <el-dropdown-item command="changePassowrd">修改密码</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
  export default {
    name: 'AppMain',
    data() {
      return {
        username: 'zhangsan'
      }
    },
    computed: {
      key() {
        return this.$route.fullPath
      }
    },
    methods: {
      goBack() {
        this.$router.push('/')
      },
      handleCommand(command){
        if(command==='logout'){
          this.$store.dispatch('UserLogout')
          this.$router.push('/login').catch(
            err=>{
              console.log(err)
            }
          )
        }else if(command==='changePassowrd'){
          this.$router.push('/dashboard/changePassword').catch(err=>{
            console.log(err)
          })
        }
      }
    },
    created(){
      this.username=this.$store.getters.username
    }
  }
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-dropdown-link{
    height: 100%;
    display: inline-block;
    min-width: 100px;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>