<template>
    <div class="home">
        <div class="header">
            <yx-header  @click="open = !open">
                <template slot="header">
                    <yx-search ></yx-search>  
                </template>
                <template slot="footer">
                    <yx-search ></yx-search>  
                </template>  
            </yx-header>
        </div>
        <mu-container fluid class="body">
           
        </mu-container>
        
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions } = createNamespacedHelpers('home');
import header from '@/components/header/header.vue'
import search from "@/components/search/search"
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
        'yx-search':search
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

