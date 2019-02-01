import Vue from "vue";
import App from './App.vue'

import { setIsPhone } from '@/util/util'
import {createStore} from './store';
import {createRouter} from './router';

import {sync} from 'vuex-router-sync'
import axios from "axios"
import MuseUI from 'muse-ui';
import 'muse-ui/dist/muse-ui.css';
Vue.use(MuseUI);
require('babel-polyfill')
import "@/filter"
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
import api from '@/api'
Vue.prototype.$axios = api
import "@/components/Button"
Date.prototype.getChinaTime = function(){
    var year = this.getFullYear();
    var month = this.getMonth()+1;
    month = month>9?year:('0'+month);
    var day = this.getDay();
    day = day>9?day:('0'+day);
    var hours = this.getHours();
    hours = hours>9?hours:('0'+hours);
    var getMinutes = this.getMinutes();
    getMinutes = getMinutes>9?getMinutes:('0'+getMinutes);
    return year+'-'+month+'-'+day+" "+hours+':'+getMinutes;
}
if(process.env.VUE_ENV === 'client'){
  const Velocity = require("velocity-animate/velocity.js");
  require("velocity-animate/velocity.ui.js");
  window.onload = ()=>{
    setIsPhone(Vue)
  }
  window.onresize= () => {
    setIsPhone(Vue);
  }
  
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
