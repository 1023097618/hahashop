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
import 'element-ui/lib/theme-chalk/index.css'
//伪造后端请求(调试用)
// import './mock'


//富文本编辑器
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

//防止xss攻击
import VueDOMPurifyHTML from 'vue-dompurify-html'
Vue.use(VueDOMPurifyHTML)

Vue.use(VueQuillEditor, /* { default global options } */)

Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
