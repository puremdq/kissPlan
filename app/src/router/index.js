import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

export function createRouter(){
    return new VueRouter({
        mode:'history',
        routes:[
            {
                path:'/',
                name:'首页',
                redirect:'/home'
            },
            {
                path:'/home',
                name:'home',
                component:()=>import(/* webpackChunkName: "home" */'@/views/home/index.vue')
            },
            {
                path:'/login',
                name:'login',
                component:()=>import(/* webpackChunkName: "login" */'@/views/login/index.vue')
            },
            {
                path:'/register',
                name:'register',
                component:()=>import(/* webpackChunkName: "register" */'@/views/register/index.vue')
            }
        ]
    })
}