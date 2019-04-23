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
            },
            currentUser({commit}) {
                return api.instance({
                    method:'get',
                    url:'/u/currentUser',
                    hasLoading:true,
                })
                .then((res)=>{
                    if(res && res.status=='200'){
                        commit('setCurrentUser',res.data)
                    }else{
                        commit('setCurrentUser',null)
                    }
                    return res;
                })
            }
        },
        mutations:{
            setUser (state,user) {
                state.user = user;
            },
            setCurrentUser(state,user) {
                state.user = state.user|| {};
                state.user.user = user;
            },
            setKiss_plan_token(state,kiss_plan_token) {
                state.user = state.user|| {};
                state.user.kiss_plan_token = kiss_plan_token;
            }
        },
        modules
    })
}