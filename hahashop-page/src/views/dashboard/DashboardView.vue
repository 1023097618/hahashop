<template>
  <div class="appwraper">
    <el-container>
      <el-header>
        <NavbarView/>
      </el-header>
      <el-container>
        <el-aside width="200px" class="sidebar-container">
          <SidebarView />
        </el-aside>
        <el-main>
          <AppMain/>
        </el-main>
      </el-container>
    </el-container>
    
    <div class="main-container">
      

    </div>
  </div>
</template>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }

  .el-main{
    margin-left: 200px;
    padding: 0px;
  }

  .sidebar-container{
    position:fixed;
    /* 8px */
    left: 8px;
    /* 60px + 8px */
    top: 68px;
    bottom: 0;
  }
</style>
<script>
import SidebarView from './component/SidebarView.vue'
import NavbarView from './component/NavbarView.vue'
import AppMain from './component/AppMain.vue'
export default {
  components:{
      SidebarView,
      NavbarView,
      AppMain
  },
    name:'DashboardView'
    
};
</script>
