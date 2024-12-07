import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)



const constantRoutes = [
  { path: '', name: 'indexView', component: () => import('@/views/index/indexView.vue'), hidden: true },
  { path: '/login', name: 'loginView', component: () => import('@/views/login/LoginView.vue'), hidden: true },
  { path: '/register', name: 'RegisterView', component: () => import('@/views/login/RegisterView.vue'), hidden: true }
]

const sellerRoutes = [
  {
    path: '/dashboard', name: 'DashboardView', component: () => import('@/views/dashboard/DashboardView.vue'), hidden: false, meta:{title: '主页'}, children: [{
      path: '/dashboard/seller/good',  hidden: false, meta:{title: '商品管理'},component: { render: (h) => h('router-view') },children:[
        {path:'/dashboard/seller/good/manager',name:'GoodView',component: () => import('@/views/dashboard/seller/good/GoodsView.vue'),hidden:false,meta:{title:'商品操作'}},
        {path:'/dashboard/seller/good/history',name:'HistoryView',component:()=>import('@/views/dashboard/seller/history/HistoriesViews.vue'),hidden:false,meta:{title:'商品历史'}}
      ]
    },
    {path:'/dashboard/seller/user',name:'UserView',component:()=>import('@/views/dashboard/seller/user/UserView.vue'),hiiden:false,meta:{title:'用户信息管理'}},
    { path: '/dashboard/changePassword', name: 'ChangePasswordView', component: () => import('@/views/login/ChangePasswordView.vue'), hidden: false, meta:{title: '修改密码'} },
    { path: '/dashboard*', redirect: '/dashboard/seller/good/manager', hidden: true }]
  }
]

const buyerRoutes=[
  {
    path: '/dashboard', name: 'DashboardView', component: () => import('@/views/dashboard/DashboardView.vue'), hidden: false, meta:{title: '主页'}, children: [
      {path:'/dashboard/buyer/buyerOrder',hidden:false,meta:{title:'订单查询'},component:()=>import('@/views/dashboard/buyer/buyerOrder/buyerOrderView.vue'),name:'buyerOrderView'},
      { path: '/dashboard/buyer/cart', name: 'CartView', component: () => import('@/views/dashboard/buyer/cart/cartView.vue'), hidden: false, meta:{title: '购物车'} },
      { path: '/dashboard/buyer/collect', name: 'CollectView', component: () => import('@/views/dashboard/buyer/collect/collectView.vue'), hidden: false, meta:{title: '我的收藏'} },
      { path: '/dashboard/changePassword', name: 'ChangePasswordView', component: () => import('@/views/login/ChangePasswordView.vue'), hidden: false, meta:{title: '修改密码'} },
      { path: '/dashboard*', redirect: '/dashboard/buyer/buyerOrder', hidden: true }
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
  router.matcher = newrouter.matcher
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

