import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

export function createRouter(){
    return new VueRouter({
        mode:'history',
        routes:[
            {
                path:'/',
                name:'home',
                component:()=>import(/* webpackChunkName: "home" */'@/views/home/index.vue')
            },
        ]
    })
}