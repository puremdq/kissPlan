import api from '@/api'
var Qs = require('qs')
export default {
    name:'login',
    namespaced:true,
    state:() => ({
        count : 1
    }),
    actions : {
        userEmilRepeat({commit},data) {
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
        _register({commit},data) {
            return api.instance({
                method:'post',
                url:'/u/register',
                data:Qs.stringify({
                    username:data.username,
                    password:data.password,
                    email:data.email,
                })
            })
        }
    },
    mutations:{
        m_setCont: (state,n) => { state.count=n }
    }
}