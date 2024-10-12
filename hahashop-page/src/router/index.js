import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)



export const constantRoutes = [
  { path: '', name: 'goodlist', component: () => import('@/views/layout/LayoutView.vue'), hidden: true },
  { path: '/login', name: 'loginView', component: () => import('@/views/login/LoginView.vue'), hidden: true }
]

export const asyncRoutes = [
  {
    path: '/dashboard', name: 'DashboardView', component: () => import('@/views/dashboard/DashboardView.vue'), hidden: false, meta:{title: '主页'}, children: [{
      path: '/dashboard/good',  hidden: false, meta:{title: '商品管理'},component: { render: (h) => h('router-view') },children:[
        {path:'/dashboard/good/manager',name:'GoodView',component: () => import('@/views/goods/GoodsView.vue'),hidden:false,meta:{title:'商品操作'}},
        {path:'/dashboard/good/history',name:'HistoryView',component:()=>import('@/views/history/HistoriesViews.vue'),hidden:false,meta:{title:'商品历史'}}
      ]
    },
    { path: '/dashboard/changePassword', name: 'ChangePasswordView', component: () => import('@/views/login/ChangePasswordView.vue'), hidden: false, meta:{title: '修改密码'} },
    { path: '/dashboard*', redirect: '/dashboard/good', hidden: true }]
  }
]

function createRoute() {
  return new Router({
    routes: constantRoutes
  })
}
const router = createRoute()
export function resetRouter() {
  const newrouter = createRoute()
  router.match = newrouter.match
}

export default router

