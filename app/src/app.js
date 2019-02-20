import Vue from "vue";
import App from './App.vue'

import {createStore} from './store';
import {createRouter} from './router';
import {sync} from 'vuex-router-sync'
require('babel-polyfill')

import "@/lib"
import "@/filter"
import "@/mixins"

import api from '@/api'
Vue.prototype.$axios = api

import eventBus from '@/eventBus'
Vue.prototype.$eventBus = new eventBus()


if(process.env.VUE_ENV === 'client'){
  const Velocity = require("velocity-animate/velocity.js");
  require("velocity-animate/velocity.ui.js");
  
}
export function createApp () {
  
  // 同步路由状态(route state)到 store
  // 创建应用程序实例，将 router 和 store 注入
  const router = createRouter();
  const store = createStore();
  sync(store,router);
  
  const app = new Vue({
    router,
    store,
    render: h => h(App)
  })

  // 暴露 app, router 和 store。
  return {app, router,store}
}
