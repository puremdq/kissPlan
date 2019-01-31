import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

export function createRouter(){
    return new VueRouter({
        mode:'history',
        routes:[
            {
                path:'/',
                name:'main',
                redirect:'/index',
            },
            {
                path:'/index',
                name:'index',
                redirect:'/home',
                component:()=>import(/* webpackChunkName: "index" */'@/views/index/index.vue'),
                children:[
                    {
                        path:'/home',
                        name:'home',
                        component:()=>import(/* webpackChunkName: "home" */'@/views/index/views/home/index.vue')
                    },
                    {
                        path:'/news/:id',
                        name:'news',
                        component:()=>import(/* webpackChunkName: "news" */'@/views/index/views/news/index.vue')
                    },
                ]
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