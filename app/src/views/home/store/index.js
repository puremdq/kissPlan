import api from '@/api'
export default {
    name:'home',
    namespaced:true,
    state:() => ({
        imgs : [],
    }),
    actions : {
        getCarousel({commit},n){
            return api.get('http://aojiaoo.com:8080/slideshow')
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
        }
    },
    mutations:{
        setCarousel: (state,data) => {
            state.imgs=data 
        }
    }
}