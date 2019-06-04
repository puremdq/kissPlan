import api from '@/api'
export default {
    name:'login',
    namespaced:true,
    state:() => ({
        count : 1
    }),
    actions : {
       
        a_getCount({commit},data){
            api.post('https://http://aojiaoo.com:8080',data)
                .then((res) => {
                })
        }
    },
    mutations:{
        m_setCont: (state,n) => { state.count=n }
    }
}