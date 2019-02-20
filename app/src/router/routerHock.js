import {router} from "../entry-client"
import 'muse-ui-progress/dist/muse-ui-progress.css';
import Vue from 'vue';
import NProgress from 'muse-ui-progress';
Vue.use(NProgress);
router.beforeEach((to, from, next) => {
    NProgress.start();
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
 
})
router.afterEach(()=>{
    NProgress.done();
})