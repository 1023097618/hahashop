import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)



export const constantRoutes = [
  {path:'',name:'goodlist',component:()=>import('@/views/layout/LayoutView.vue'),hidden:true},
   {path:'/login',name:'loginView',component:()=>import('@/views/login/LoginView.vue'),hidden:true}
]

export const asyncRoutes=[
   {path:'/dashboard',name:'DashboardView',component:()=>import('@/views/dashboard/DashboardView.vue'),hidden:false,children:[{
    path:'/dashboard/good',name:'GoodView',component:()=>import('@/views/goods/GoodsView.vue'),hidden:false},
    {path:'/dashboard/changePassword',name:'ChangePasswordView',component:()=>import('@/views/login/ChangePasswordView.vue'),hidden:false},
    {path:'/dashboard*',redirect:'/dashboard/good',hidden:true}]
  }
]

function createRoute(){
  return new Router({
    routes:constantRoutes
  })
}
const router=createRoute()
export function resetRouter(){
  const newrouter=createRoute()
  router.match=newrouter.match
}

export default router

