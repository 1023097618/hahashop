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
        <el-main :style="{ height: height + 'px' }">
          <AppMain />
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
    background-color: #E9EEF3;
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
    name:'DashboardView',
    data() {
      return {
        height: 0
      }
    },
    methods: {
      //自适应表格高度  8(body边距)+60(navheader边距)
      getHeight() {
        this.height = window.innerHeight - 68
      }
    },
    created() {
      this.getHeight()
      window.addEventListener('resize', this.getHeight)
    },
    destroyed() {
      window.removeEventListener('resize', this.getHeight)
    },
    
};
</script>
