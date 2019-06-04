const express = require('express');
const fs = require('fs')
const path = require('path')
const proxyMiddleware = require('http-proxy-middleware');//引入代理中间件
const { createBundleRenderer } = require('vue-server-renderer')

const isProd = process.env.NODE_ENV === 'production'
const resolve = file => path.resolve(__dirname, file)
const app = express()
app.set('trust proxy', function (ip) {
  if (ip === '127.0.0.1' || ip === '123.123.123.123') return true; // trusted IPs
  else return false;
})
app.use('/static',express.static(path.join(__dirname, '../dist'),{
  maxAge:31536000
}))

var proxyTable = {
  '/api': {
      target:  'http://106.12.205.37:8080',
      pathRewrite: {
          '^/api': '/'
      }
  },
}
var arr = [];
for(var key in proxyTable){
arr.push(
  proxyMiddleware([key], proxyTable[key]),
)
}
app.middleware = arr
app.use(arr);

app.locals.reload = true;
let renderer
function createRenderer(bundle, template) {
  return createBundleRenderer(bundle, {
    // runInNewContext: false, // 推荐
    template, // （可选）页面模板
    //clientManifest // （可选）客户端构建 manifest
  })
}
if (isProd) {
    // 生产环境使用本地打包文件来渲染
    const serverBundle = require('../dist/vue-ssr-server-bundle.json')
    const clientManifest = require('../dist/vue-ssr-client-manifest.json')
    const template = fs.readFileSync(resolve('../dist/index.html'), 'utf-8')
    renderer = createRenderer(serverBundle, template,template)
}else{
  // 开发环境使用webpack热更新服务
  require('./dev.server.build')(app, (bundle, template) => {
    renderer = createRenderer(bundle, template)
  })
 
}

app.get('*', (req, res) => {
    if (!renderer) {
      return res.end('waiting for compilation... refresh in a moment.')
    }
    const errorHandler = err => {
      res.setHeader("Content-Type", "text/plain;charset=utf-8")
      if (err && err.code === 404) {
        // 未找到页面
        res.status(404).end('404 - 页面没找到')
      } else {
        // 页面渲染错误
        res.status(500).end('500 - Internal Server Error')
      }
    }
    const context = { 
      url: req.url ,
      title:'KissPlan',
      cookies: req.headers['cookie']
    }
    renderer.renderToString(context, (err, html) => {
      if (err) {
        return errorHandler(err)
      }
      res.setHeader("Content-Type", "text/html")
      res.end(html)
     
    })
})
  
const port = /*process.env.PORT ||*/ 80
app.listen(port, () => {
  console.log(`server started at http://localhost:${port}`)
})
  