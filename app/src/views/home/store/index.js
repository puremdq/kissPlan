import api from '@/api'
export default {
    name:'home',
    namespaced:true,
    state:() => ({
        count : 1,
    }),
    actions : {
        a_getCount({commit},n){
           
        }
    },
    mutations:{
        m_setCont: (state,n) => { state.count=n }
    }
}