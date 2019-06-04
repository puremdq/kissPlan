import Vue from 'vue'
import Vuex from 'vuex'
import modules from './modules'
Vue.use(Vuex);
import api from '@/api'
var Qs = require('qs')
export function createStore(){
    return new Vuex.Store({
        state:{
            user:{},
            theme:'#ea6f5a'

        },
        mutations:{
            setUser (state,user) {
                state.user = user;
            }
        },
        actions:{
            logout({commit},data) {
                debugger
                return api.instance({
                    method:'get',
                    url:'/logout',
                })
            }
        },
        modules
    })
}