import api from '@/api'
var Qs = require('qs');
export default {
    name:'userHome',
    namespaced:true,
    state:() => ({
        _userDetail:{},
        _newsList:[]
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
        _getMyNewsList({commit},data) {
            return api.instance({
                method:'get',
                url:`/article?pageNo=${data.pageNo}&pageSize=${data.pageSize}&authorId=${data.userId}`
            })
            .then((res)=>{
                if(res.status==200){
                    if(res.data){
                        commit('_set_newsList',res.data)
                    }else{
                        commit('_set_newsList',{})
                    }
                }
                return res
            })
        }
    },
    mutations:{
        _setUserDetail(state,data) {
            state._userDetail = data;
        },
        _set_newsList(state,data) {
            state._newsList = data;
        }
    }
}