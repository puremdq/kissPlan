import Vue from 'vue'
import Vuex from 'vuex'
import modules from './modules'
Vue.use(Vuex);

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
        modules
    })
}