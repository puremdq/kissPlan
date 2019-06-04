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
                name:'主页',
                redirect:'/home',
                component:()=>import(/* webpackChunkName: "index" */'@/views/index/index.vue'),
                children:[
                    {
                        path:'/home',
                        name:'首页',
                        component:()=>import(/* webpackChunkName: "home" */'@/views/index/views/home/index.vue')
                    },
                    {
                        path:'/hotToday',
                        name:'今日热门',
                        component:()=>import(/* webpackChunkName: "hotToday" */'@/views/index/views/hotToday/index.vue')
                    },
                    {
                        path:'/sevenDayHot',
                        name:'七日热门',
                        component:()=>import(/* webpackChunkName: "sevenDayHot" */'@/views/index/views/sevenDayHot/index.vue')
                    },
                    {
                        path:'/ThirtyDayHot',
                        name:'30日热门',
                        component:()=>import(/* webpackChunkName: "ThirtyDayHot" */'@/views/index/views/ThirtyDayHot/index.vue')
                    },
                    {
                        path:'/preferredSerialization',
                        name:'优选连载',
                        component:()=>import(/* webpackChunkName: "preferredSerialization" */'@/views/index/views/preferredSerialization/index.vue')
                    },
                    {
                        path:'/topOneHundred',
                        name:'top100',
                        component:()=>import(/* webpackChunkName: "topOneHundred" */'@/views/index/views/topOneHundred/index.vue')
                    },
                    {
                        path:'/news/:id',
                        name:'文章详情',
                        component:()=>import(/* webpackChunkName: "news" */'@/views/index/views/news/index.vue')
                    },
                    {
                        path:'/userHome/:id',
                        name:'个人中心',
                        component:()=>import(/* webpackChunkName: "userHome" */'@/views/index/views/userHome/index.vue')
                    },
                    {
                        path:'/message',
                        name:'消息中心',
                        redirect:'/message/communication',
                        component:()=>import(/* webpackChunkName: "message" */'@/views/index/views/message/index.vue'),
                        children:[
                            {
                                path:'/message/communication',
                                name:'聊天列表',
                                component:()=>import(/* webpackChunkName: "communication" */'@/views/index/views/message/views/communication.vue'),
                            },
                            {
                                path:'/message/commun/:id',
                                name:'聊天',
                                component:()=>import(/* webpackChunkName: "communication" */'@/views/index/views/message/views/xiaoXi.vue'),
                            },
                        ]
                    },
                ]
            },
            {
                path:'/writeArticle',
                name:'写文章',
                meta:{
                    requiresAuth:true,
                },
                component:()=>import(/* webpackChunkName: "writeArticle" */'@/views/index/views/writeArticle/index.vue')
            },
            {
                path:'/login',
                name:'登录',
                component:()=>import(/* webpackChunkName: "login" */'@/views/login/index.vue')
            },
            {
                path:'/register',
                name:'注册',
                component:()=>import(/* webpackChunkName: "register" */'@/views/register/index.vue')
            }
        ]
    })
}