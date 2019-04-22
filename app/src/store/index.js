import Vue from 'vue'
import Vuex from 'vuex'
import modules from './modules'
import api from '@/api'
var Qs = require('qs')
Vue.use(Vuex);

export function createStore(){
    return new Vuex.Store({
        state:{
            user:{},
            theme:'#ea6f5a'

        },
        actions:{
            getUser({commit},data) {
                return api.instance({
                    method:'post',
                    url:'/u/check',
                    hasLoading:false,
                    data:Qs.stringify({
                        username:data.username,
                        email:data.email,
                    })
                })
            }
        },
        mutations:{
            setUser (state,user) {
                state.user = user;
            }
        },
        modules
    })
}