<template>
  <div>
    <el-menu :default-active="this.$route.path" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
      :router="true">
      <template  v-for="route in filteredRoutes">
        <el-submenu :index="route.path" :key="route.path" v-if="route.children && route.children.length">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>{{route.meta.title}}</span>
          </template>
          <MenuItem :routes="route.children"></MenuItem>
        </el-submenu>
        <el-menu-item v-else :key="route.path" :index="route.path">
          <i class="el-icon-menu"></i>
          <span slot="title">{{route.meta.title}}</span>
        </el-menu-item>
      </template>
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
    data() {
      return {
        routes: []
      }
    },
    computed: {
      filteredRoutes() {
        const dashboardRoute=this.routes.filter(route=>route.path==='/dashboard')
        const filterRoutes = (routes) => {
          const filteredRoute = routes.filter(route => !route.hidden)
          const mappedRoute=filteredRoute.map(route =>
            {return {
              ...route,
              children: route.children ? filterRoutes(route.children) : []
            }}
            )
          return mappedRoute
        }
        let result= filterRoutes(dashboardRoute)
        result = result[0].children
        return result
      }
    },
    created() {
      this.routes = this.$store.getters.permittedRoutes
    },
    components:{
      MenuItem:{
        props: ['routes'],
      template: `
        <div>
          <template v-for="route in routes">
            <el-submenu v-if="route.children && route.children.length" :key="route.path" :index="route.path">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>{{ route.meta.title }}</span>
              </template>
              <MenuItem :routes="route.children"></MenuItem>
            </el-submenu>
            <el-menu-item v-else :key="route.path" :index="route.path">
              <i class="el-icon-menu"></i>
              <span>{{ route.meta.title }}</span>
            </el-menu-item>
          </template>
        </div>
      `
      }
    }
  }
</script>