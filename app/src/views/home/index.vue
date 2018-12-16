<template>
    <div class="home">
        <yx-header></yx-header>
        <div class="box mt20">
            <mu-row gutter>
                <mu-col span="8" sm="12" md="8" style="height:270px;">
                    <carousel></carousel>
                </mu-col>
                <mu-col span="4" sm="12" md="4" style="height:270px;">2</mu-col>
            </mu-row>
        </div>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions } = createNamespacedHelpers('home');
import header from '@/components/header/header.vue'
import carousel from "./components/carousel.vue"
export default {
    name:'home',
    asyncData({store}){
        //store.registerModule('home',homeModule);
        return store.dispatch('home/a_getCount',16,{root:true})
    },
    data(){
        return {
            open:true
        }
    },
    components:{
        'yx-header':header,
        carousel
    },
    
    destroyed(){
        //this.$store.unregisterModule('home');
    },
    
    computed:{
        ...mapState(['count'])
    }
}
</script>
<style lang="less" scoped>
    .home{
        .header{
        }
        .body{
             margin-top:70px;
            .banner_left{
                margin-bottom:8px;
                
            }
            .phone_banner_left{
                position: fixed;
                top:60px;
                left:8px;
                right:8px;
                z-index: 1;
            }
           .content_right{
                margin-top:60px;
            }
        }
        
    }
    @media screen and (min-width: 768px){
        .home{

            .body{
               
                .banner_left{
                    min-height:100vh;
                    position: static;
                }
                .content_right{
                    margin-top:0px;
                }
            }
        }      
    }
</style>

