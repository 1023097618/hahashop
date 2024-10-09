module.exports = {
  publicPath: './', // 部署应用包时的基本 URL
  outputDir: 'dist', // 打包输出目录
  assetsDir: '', // 静态资源目录
  indexPath: 'index.html', // 指定生成的 index.html 的输出路径
  transpileDependencies: true,
  devServer:{
    port: process.env.VUE_APP_startPort,
    proxy:{
      '/':{
        target: process.env.VUE_APP_baseurl,//后端接口地址
        changeOrigin: true,//是否允许跨越
      }
    },
    webSocketServer: false
  },

}
