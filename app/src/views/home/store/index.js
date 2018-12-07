import api from '@/api'
export default {
    name:'home',
    namespaced:true,
    state:() => ({
        count : 1,
    }),
    actions : {
        a_getCount({commit},n){
            console.log(api);
            api.get('https://cdn.bootcss.com/echarts/4.1.0.rc2/echarts-en.common.js')
                .then((res) => {
                    commit('m_setCont',n)
                })
          
        }
    },
    mutations:{
        m_setCont: (state,n) => { state.count=n }
    }
}