import api from '@/api'
const Qs = require('qs')
export default {
    name:'message',
    namespaced:true,
    state:{
        _messageList:[]
    },
    actions : {
        _getMessageList({commit},data) {
            return api.instance({
                method:'get',
                url:'/message/messageList',
                data:Qs.stringify(data)
            })
            .then((res)=>{
                if(res.status==200 && res.data){
                    commit('_setMessageList',res.data)
                }
                return res
            })
        }
    },
    mutations:{
        _setMessageList(state,data) {
            state._messageList = data;
        }
    }
}