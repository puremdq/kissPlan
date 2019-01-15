<template>
    <div class="home">
        <yx-header></yx-header>
        <div class="box mt20">
            <mu-row gutter>
                <mu-col span="12" sm="12" md="8">
                    <carousel></carousel>
                    <div class="contentItem-box">
                        <contentItem class="mt20 bb1 pb10" v-for="(item,idx) in arr" :key="idx"></contentItem>
                    </div>
                </mu-col>
                <mu-col class="phone_hide" span="12" sm="12" md="4">
                    <hotEnter></hotEnter>
                    <download class="mt20"></download>
                    <Advertisement class="mt20"></Advertisement>
                    <author  class="mt20"></author>
                </mu-col>
            </mu-row>
        </div>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions } = createNamespacedHelpers('home');
import header from '@/components/header/header.vue'
import carousel from "./components/carousel.vue"
import contentItem from "@/components/contentItem/index.vue"
import Advertisement from "@/components/Advertisement/index.vue"
import hotEnter from "./components/hotEnter.vue"
import download from "./components/download.vue"
import author from "./components/author.vue"
export default {
    name:'home',
    asyncData({store}){
        //store.registerModule('home',homeModule);
        return store.dispatch('home/a_getCount',16,{root:true})
    },
    data(){
        return {
            open:true,
            arr:10
        }
    },
    components:{
        'yx-header':header,
        carousel,
        contentItem,
        hotEnter,
        download,
        Advertisement,
        author
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
        .contentItem-box{
            border-top:1px solid #ddd;
            padding-top:10px;
            margin-top: 30px;
        }   
        
        
    }
    
</style>

