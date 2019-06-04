import api from '@/api'
export default {
    name:'home',
    namespaced:true,
    state:() => ({
        imgs : [],
        newItem:[],
        newItemObj:{}
    }),
    actions : {
        getCarousel({commit},n){
            return api.instance({
                method:'get',
                url:'/index/slideshow',
                hasLoading:false,
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
                hasLoading:false,
                url:`/article?pageNo=${data.pageNo}&pageSize=${data.pageSize}`
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
            state.newItemObj = data;
            if(data.pageNo>1){
                state.newItem = state.newItem.concat(data.list)
            }else{
                state.newItem = data.list;
            }
           
        }
    }
}