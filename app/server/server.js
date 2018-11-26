const express = require('express');
const fs = require('fs')
const path = require('path')
var mongodb = require('mongodb');
var mongoose = require('mongoose')
var qs = require('qs')
var User = require('./module/user/model.js')
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser')
const session = require('express-session');
var mongoStore = require('connect-mongo')(session);
var mongoUrl = 'mongodb://localhost:27017/missu'
// import a from "./module/user/model.js"
// console.log(User);
console.log(11111111111111);
// console.log(mongoStore);
// mongoose.connect(mongoUrl);
// mongoose.connection.on('connected', function () {    
//     console.log('Mongoose connection open to ' );  
// });    

// /**
//  * 连接异常
//  */
// mongoose.connection.on('error',function (err) {    
//     console.log('Mongoose connection error: ' + err);  
// });    
 
// /**
//  * 连接断开
//  */
// mongoose.connection.on('disconnected', function () {    
//     console.log('Mongoose connection disconnected');  
// });
const { createBundleRenderer } = require('vue-server-renderer')

const isProd = process.env.NODE_ENV === 'production'
const resolve = file => path.resolve(__dirname, file)
const app = express()
app.set('trust proxy', function (ip) {
  console.log('ip:');
  console.log(ip);
  if (ip === '127.0.0.1' || ip === '123.123.123.123') return true; // trusted IPs
  else return false;
})
app.use('/public',express.static(path.join(__dirname, '../dist'),{
  maxAge:31536000
}))
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
// app.use(cookieParser())
// app.use(session({
//   secret:'ssr',
//   cookie: {maxAge: 1000 },
//   resave: true,
//   saveUninitialized: true,
//   store:new mongoStore({
//     url:mongoUrl,
//     collection:'sessions'
//   })
// }))
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
app.use('/api',function(req, res,next){
  console.log('req.session.user:');
  console.log(req.session.user);
  if(req.session.user){
    next();
  }else{
    res.json({
      code:'-2',
      result:[],
      ressage:'sesson user err'
    })
  }
 
})
app.get('/api/userLists/:id',(req, res) =>{
  console.log(req.params);
  // res.download('/index.html');
  User.fetch(function(err,users){
    if(err){
      res.json({
        code:'-1',
        result:[],
        message:'err'
      })
    }
    // res.sendStatus(200);
    res.status(200).json({
      code:'0',
      result:users,
      message:'succ'
    })
  })
})
app.get('/signout',(req, res) => {
  delete req.session.user;
  res.json({
    code:'0',
    message:'succ'
  })
})
app.post('/signup',function(req, res){
  var data = req.body;
  var dataObject = qs.parse(data);
 
  var user =new User({
    name:dataObject.name,
    password:dataObject.password
  })
  user.save((err,userRes) => {
    if(err){
      res.json({
        code:-1,
        result:err,
        message:'err'
      })
    }else{
      req.session.user = userRes;
      res.json({
        code:0,
        result:{
          name:userRes.name,
          createDate:userRes.meta.createAt
        },
        message:'succ'
      })
    
      
    }
  })
  
})
app.post('/signin',function(req, res){
    var data = req.body;
    var dataObject = qs.parse(data);
    User.findOne({name:dataObject.name},function(err,userName){
      if(err){
        res.json({
          code:'-1',
          result:[],
          message:'err'
        })
      }
      if(!userName){
        res.json({
          code:'-1',
          result:[],
          message:'err'
        })
      }else{
        userName.comparePassword(dataObject.password,function(err,isMatch){
          if(err){
            console.log(err);
          }
          if(isMatch){
            res.locals.userName = userName.name;
            req.session.user = userName;
            res.json({
              code:'0',
              result:{
                name:userName.name,
                createDate:userName.meta.createAt
              },
              message:'succ'
            })
          }else{
            res.json({
              code:'-1',
              result:[],
              message:'err'
            })
          }  
        })

      }
    })
  
})
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
        console.error(`error during render : ${req.url}`)
        console.error(err)
      }
    }
  
    const context = { 
      url: req.url ,
      title:'hello',
      cookies: req.cookies
    }
    renderer.renderToString(context, (err, html) => {
      if (err) {
        return errorHandler(err)
      }
      res.setHeader("Content-Type", "text/html")
      res.end(html)
     
    })
})
  
const port = process.env.PORT || 3000
app.listen(port, () => {
console.log(`server started at http://localhost:${port}`)
})
  