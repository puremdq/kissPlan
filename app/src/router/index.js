import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);
const personalShow = ()=>import(/* webpackChunkName: "personalShow" */'@/views/personalShow/personalShow')
const login = ()=>import(/* webpackChunkName: "login" */'@/views/login/login')
const design = ()=>import(/* webpackChunkName: "design" */'@/views/design/design')
export function createRouter(){
    return new VueRouter({
        mode:'history',
        routes:[
            {path:'/',redirect:'/personalShow'},
            {path:'/personalShow',name:'personalShow',component:personalShow,meta: { requiresAuth: true }},
            {path:'/login',name:'login',component:login},
            {path:'/design',name:'design',component:design},
        ]
    })
}