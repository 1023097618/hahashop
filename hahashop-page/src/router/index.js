import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)



const constantRoutes = [
  { path: '', name: 'goodlist', component: () => import('@/views/layout/LayoutView.vue'), hidden: true },
  { path: '/login', name: 'loginView', component: () => import('@/views/login/LoginView.vue'), hidden: true },
  { path: '/register', name: 'RegisterView', component: () => import('@/views/login/RegisterView.vue'), hidden: true }
]

const sellerRoutes = [
  {
    path: '/dashboard', name: 'DashboardView', component: () => import('@/views/dashboard/DashboardView.vue'), hidden: false, meta:{title: '主页'}, children: [{
      path: '/dashboard/good',  hidden: false, meta:{title: '商品管理'},component: { render: (h) => h('router-view') },children:[
        {path:'/dashboard/good/manager',name:'GoodView',component: () => import('@/views/goods/GoodsView.vue'),hidden:false,meta:{title:'商品操作'}},
        {path:'/dashboard/good/history',name:'HistoryView',component:()=>import('@/views/history/HistoriesViews.vue'),hidden:false,meta:{title:'商品历史'}}
      ]
    },
    { path: '/dashboard/changePassword', name: 'ChangePasswordView', component: () => import('@/views/login/ChangePasswordView.vue'), hidden: false, meta:{title: '修改密码'} },
    { path: '/dashboard*', redirect: '/dashboard/good/manager', hidden: true }]
  }
]

const buyerRoutes=[
  {
    path: '/dashboard', name: 'DashboardView', component: () => import('@/views/dashboard/DashboardView.vue'), hidden: false, meta:{title: '主页'}, children: [
      {path:'/dashboard/order',hidden:false,meta:{title:'订单查询'},component:()=>import('@/views/buyer/buyerOrderView.vue'),name:'buyerOrderView'},
      { path: '/dashboard*', redirect: '/dashboard/order', hidden: true }
    ]
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

export function addSellerRoute(){
  sellerRoutes.forEach(route=>{
    router.addRoute(route)
  })
  return sellerRoutes
}

export function addBuyerRoute(){
  buyerRoutes.forEach(route=>{
    router.addRoute(route)
  })
  return buyerRoutes
}

export default router

