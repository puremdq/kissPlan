import api from '@/api'
export default {
    name:'home',
    namespaced:true,
    state:() => ({
        imgs : [],
        newItem:{},
    }),
    actions : {
        getCarousel({commit},n){
            return api.instance({
                method:'get',
                url:'/index/slideshow'
            })
            .then((res)=>{
                if(res.status==200){
                    if(res.data && res.data.slideshow){
                        commit('setCarousel',res.data.slideshow)
                    }else{
                        commit('setCarousel',[])
                    }
                }
                return res
            })
        },
        getNewItem({commit},data) {
            return api.instance({
                method:'get',
                url:'/article?pageNo=1&pageSize=10'
            })
            .then((res)=>{
               
                if(res.status==200){
                    if(res.data){
                        commit('setNewItem',res.data)
                    }else{
                        commit('setNewItem',[])
                    }
                }
                return res
            })
        }
    },
    mutations:{
        setCarousel: (state,data) => {
            state.imgs=data 
        },
        setNewItem:(state,data)=>{
            state.newItem = data;
        }
    }
}