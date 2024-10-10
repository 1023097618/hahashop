<template>
  <div>
    <el-menu :default-active="this.$route.path" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
      :router="true">
      <!-- <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>导航一</span>
        </template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="1-1">选项1</el-menu-item>
          <el-menu-item index="1-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="1-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="1-4">
          <template slot="title">选项4</template>
          <el-menu-item index="1-4-1">选项1</el-menu-item>
        </el-submenu>
      </el-submenu> -->
      <el-submenu index="/dashboard" v-for="route in filteredRoutes" :key="route.path">
        <template slot="title" >
          <i class="el-icon-location"></i>
          <span>{{route.name}}</span>
        </template>
        <el-menu-item v-for="child in route.filteredChildren" :key="child.path" :index="child.path">
          <i class="el-icon-menu"></i>
          <span slot="title">{{child.name}}</span>
        </el-menu-item>
      </el-submenu>
      <!-- <el-menu-item index="3" disabled>
        <i class="el-icon-document"></i>
        <span slot="title">导航三</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-setting"></i>
        <span slot="title">导航四</span>
      </el-menu-item> -->
    </el-menu>
  </div>
</template>
<script>
  export default {
    name: 'SidebarView',
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    data(){
      return{
        routes:[]
      }
    },
    computed: {
    filteredRoutes() {
      return this.routes
        .filter(route => !route.hidden)
        .map(route => ({
          ...route,
          filteredChildren: route.children.filter(child => !child.hidden)
        }));
    }
  },
    created() {
      this.routes=this.$store.getters.permittedRoutes
    }
  }
</script>