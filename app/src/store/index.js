import Vue from 'vue'
import Vuex from 'vuex'
import modules from './modules'
Vue.use(Vuex);

export function createStore(){
    return new Vuex.Store({
        state:{
            user:{},
        },
        mutations:{
            setUser (state,user) {
                state.user = user;
            }
        },
        modules
    })
}