import api from '@/api'
export default {
    name:'personalShow',
    namespaced:true,
    state:() => ({
        count : 1,
        bannnerList:[
            {
                id:1,
                name:'css3',
                list:[
                    {
                        id:1,
                        name:'css3动画',
                    },
                    {
                        id:2,
                        name:'css3旋转',
                    },
                    {
                        id:3,
                        name:'css3位移',
                    }
                ]
            },
            {
                id:2,
                name:'css3',
                list:[
                    {
                        id:1,
                        name:'css3动画实打实大苏打实打实打算水水水水水水水水水水水水水水水水水水水水水',
                    },
                    {
                        id:2,
                        name:'css3旋转',
                    },
                    {
                        id:3,
                        name:'css3位移',
                    },
                    {
                        id:4,
                        name:'css3位移',
                    },
                    {
                        id:5,
                        name:'css3位移',
                    }
                ]
            },
        ]
    }),
    actions : {
        a_setCount: ({commit},n)=>{
            commit('m_setCont',n)
            
        },
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