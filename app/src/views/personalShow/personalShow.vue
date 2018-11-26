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
            <mu-row gutter>
                <mu-col span="12" sm="12" md="3">
                    <yx-banner-List :bannnerList="bannnerList" class="banner_left phone_banner_left"></yx-banner-List> 
                </mu-col>
                <mu-col span="12" sm="12" md="9">
                    <yx-content class="content_right"></yx-content>
                </mu-col>
               
            </mu-row>
        </mu-container>
        
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions } = createNamespacedHelpers('personalShow');
import axios from "axios"
import bannerList from './componts/bannerList.vue'
import content from './componts/content.vue'
import header from '@/components/header/header.vue'
import search from "@/components/search/search"
export default {
    name:'personalShow',
    data(){
        return {
            open:true
        }
    },
    components:{
        'yx-banner-List':bannerList,
        'yx-content':content,
        'yx-header':header,
        'yx-search':search
    },
    asyncData({store}){
        //store.registerModule('home',homeModule);
        return store.dispatch('personalShow/a_getCount',16,{root:true})
    },
    destroyed(){
        //this.$store.unregisterModule('home');
    },
    mounted(){
         axios({
            method: 'get',
            url: '/api/userLists/123',
           
        })
        .then((res)=>{
            console.log(res);
        })
    },
    computed:{
        ...mapState(['count','bannnerList'])
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

