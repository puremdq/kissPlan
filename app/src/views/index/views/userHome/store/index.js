import api from '@/api'
var Qs = require('qs');
export default {
    name:'userHome',
    namespaced:true,
    state:() => ({
        _userDetail:{}
    }),
    actions : {
        _getUserHome({commit},data){
            return api.instance({
                method:'get',
                url:`/u/${data.id}`
            })
            .then((res)=>{
                if(res.status==200){
                    if(res.data){
                        commit('_setUserDetail',res.data)
                    }else{
                        commit('_setUserDetail',{})
                    }
                }
                return res
            })
        },
        
    },
    mutations:{
        _setUserDetail(state,data) {
            state._userDetail = data;
        }
    }
}