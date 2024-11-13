const isProd = process.env.NODE_ENV === 'production'; // 是否生产环境

// // 配置 externals，告诉 Webpack 不要打包这些库
// let externals = {
//   vue: 'Vue',
//   'vue-router': 'VueRouter',
//   axios: 'axios',
//   'element-ui': 'ELEMENT',
//   vuex: 'Vuex',
//   'dompurify': 'DOMPurify',
//   quill: 'Quill'
// };

// 配置 CDN 链接 (去bootcdn上查)
// let cdn = {
//   css: [
//     // Element UI 的 CSS CDN
//     'https://cdn.bootcdn.net/ajax/libs/element-ui/2.15.14/theme-chalk/index.min.css',
//   ],
//   js: [
//     // Vue 的 CDN
//     'https://cdn.bootcdn.net/ajax/libs/vue/2.6.14/vue.min.js',
//     // Vue Router 的 CDN
//     'https://cdn.bootcdn.net/ajax/libs/vue-router/3.5.1/vue-router.min.js',
//     // Axios 的 CDN
//     'https://cdn.bootcdn.net/ajax/libs/axios/1.7.7/axios.min.js',
//     // Element UI 的 JS CDN
//     'https://cdn.bootcdn.net/ajax/libs/element-ui/2.15.14/index.min.js',
//     //VueX 的 CDN
//     'https://cdn.bootcdn.net/ajax/libs/vuex/3.6.2/vuex.min.js',
//     // DOMPurify 的 CDN
//     'https://cdn.bootcdn.net/ajax/libs/dompurify/3.1.6/purify.min.js',
//     // Quill 的 JS CDN
//     'https://cdn.bootcdn.net/ajax/libs/quill/2.0.2/quill.min.js'
//   ],
// };

// 字节跳动的，这样加载速度快些
// let cdn = {
//   css: [
//     // Element UI 的 CSS CDN
//     'https://cdn.staticfile.net/element-ui/2.15.14/theme-chalk/index.min.css'
//   ],
//   js: [
//     // Vue 的 CDN
//     'https://cdn.staticfile.net/vue/2.6.14/vue.min.js',
//     // Vue Router 的 CDN
//     'https://cdn.staticfile.net/vue-router/3.5.1/vue-router.min.js',
//     // Axios 的 CDN
//     'https://cdn.staticfile.net/axios/1.6.5/axios.min.js',
//     // Element UI 的 JS CDN
//     'https://cdn.staticfile.net/element-ui/2.15.14/index.min.js',
//     //VueX 的 CDN
//     'https://cdn.staticfile.net/vuex/3.6.2/vuex.min.js',
//     // DOMPurify 的 CDN
//     'https://cdn.staticfile.net/dompurify/3.0.8/purify.min.js',
//     // Quill 的 JS CDN
//     'https://cdn.staticfile.net/quill/2.0.0-dev.4/quill.min.js'
//   ],
// };

let cdn={ css: [], js: [] }

let externals={}
// 如果不是生产环境，清空 externals 和 cdn
cdn = isProd ? cdn : { css: [], js: [] };
externals = isProd ? externals : {};


module.exports = {
  productionSourceMap: false, //优化打包后的体积大小
  publicPath: './', // 部署应用包时的基本 URL
  outputDir: 'dist', // 打包输出目录
  assetsDir: '', // 静态资源目录
  indexPath: 'index.html', // 指定生成的 index.html 的输出路径
  transpileDependencies: true,
  runtimeCompiler: true,  //动态template有用
  devServer:{
    port: process.env.VUE_APP_startPort,
    proxy:{
      '/':{
        target: process.env.VUE_APP_baseurl,//后端接口地址
        changeOrigin: true,//是否允许跨越
      }
    },
    webSocketServer: false,
    historyApiFallback: true, //内网穿透
    allowedHosts: "all", //内网穿透
  },
  configureWebpack: {
    externals: externals, // 配置 externals
  },
  chainWebpack: (config) => {
    // 配置 HTML 插件，注入 CDN
    config.plugin('html').tap((args) => {
      args[0].cdn = cdn;
      return args;
    });
  },
}
