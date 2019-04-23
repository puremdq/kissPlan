import {createApp} from './app.js'
import Vue from "vue"
const {app,router,store} = createApp();
if (window.__INITIAL_STATE__) {
    store.replaceState(window.__INITIAL_STATE__)
}

router.beforeEach((to, from, next) => {
    store.dispatch('currentUser')
        .then((res)=>{
            if(to.meta.requiresAuth){
               
                if(res && res.status=='200'){
                    return next();
                }else{
                    Vue.prototype.$message({
                        message: '当前页面需要登陆呦！',
                        showClose: true,
                        type: 'warning'
                    });
                    return next({ path: '/login' });
                }
            }else{
                if(to.path=='/login' || to.path=='/register' ){
                    if(store.state.user.user){
                       return next({path:'/'})
                    } 
                }
                return next();
            }
        })
 
})
router.afterEach(()=>{
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
        var arr = [];
        activated.forEach((item)=>{
            if(item.asyncData){
                arr.push( item.asyncData({
                    store,
                    router:router.currentRoute
                }))
            }
            if(item.components){
                Object.keys(item.components).forEach((key)=>{
                    item.components[key]
                    if(item.components[key] && item.components[key].asyncData){
                        arr.push(item.components[key].asyncData({
                            store,
                            router:router.currentRoute
                        })) 
                    }
                })
            }
        })
        Promise.all(arr).then(()=>{
            next();
        }).catch(next)
    })
    app.$mount('#app')
})
