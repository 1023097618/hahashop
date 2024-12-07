import Vue from 'vue'
import App from './App.vue'
//路由
import router from './router'
//全局存储数据
import store from './store'
//路由导航(权限控制)
import './permission'
//element-ui
import ElementUI from 'element-ui'
// 仅在非生产环境中引入 Element UI 的 CSS
import('element-ui/lib/theme-chalk/index.css');
// if (process.env.NODE_ENV !== 'production') {
//   import('element-ui/lib/theme-chalk/index.css');
// }
//伪造后端请求(调试用)
// import './mock'



//防止xss攻击
import VueDOMPurifyHTML from 'vue-dompurify-html'
Vue.use(VueDOMPurifyHTML)



Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
