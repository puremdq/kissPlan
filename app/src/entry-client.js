import {createApp} from './app.js'
import axios from 'axios'
const {app,router,store} = createApp();
if (window.__INITIAL_STATE__) {
    store.replaceState(window.__INITIAL_STATE__)
}
// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    if(response.data.code === '-2'){
        window.localStorage.removeItem('user')
        app.$router.push('login');
    }
    if (response.status >= 200 && response.status < 300) {
        return response
    }
    return Promise.reject(response)
}, function (error) {
    return Promise.reject(error);
});
router.beforeEach((to, from, next) => {
    var hasUser = JSON.parse(window.localStorage.getItem('user'))
    if(to.meta.requiresAuth){
        
        if(hasUser){
            return next();
        }else{
            return next({ path: '/login' });
        }
    }else{
        if(to.path=='/login' || to.path=='/register' ){
            if(hasUser){
               return next({path:'/'})
            } 
        }
        return next();
    }
  // if (to.matched.some(record => record.meta.requiresAuth)) {
  //   // this route requires auth, check if logged in
  //   // if not, redirect to login page.
  //   if (!auth.loggedIn()) {
  //     next({
  //       path: '/login',
  //       query: { redirect: to.fullPath }
  //     })
  //   } else {
  //     next()
  //   }
  // } else {
  //   next() // 确保一定要调用 next()
  // }
})
router.onReady(() => {
    router.beforeResolve((to,from,next) => {
        const matched = router.getMatchedComponents(to)
        const prevMatched = router.getMatchedComponents(from)
        let diffed = false
        const activated = matched.filter((c, i) => {
          return diffed || (diffed = (prevMatched[i] !== c))
        })
        if(!activated.length){
            return next()
        }
        Promise.all(activated.map(c => {
            if(c.asyncData){
                c.asyncData({store,route:to})
            }
        })).then(()=>{
            next();
        }).catch(next)
    })
    app.$mount('#app')
})