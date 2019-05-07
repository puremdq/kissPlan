<template>
    <div class="home">
        <div class="box mt20">
            <mu-row gutter>
                <mu-col span="12" sm="12" md="8">
                    <carousel></carousel>
                    <div class="contentItem-box">
                        <mu-load-more @refresh="refresh" :refreshing="refreshing" :loading="loading" @load="load">
                            <contentItem class="mt20 bb1 pb10" v-for="(item,idx) in (newItem)" :key="idx" :data="item"></contentItem>
                        </mu-load-more>
                        <div class="tac mb10" v-if="newItem.length >= newItemObj.total">到底了，没有更多</div>
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
import carousel from "./components/carousel.vue"
import contentItem from "@/components/contentItem/index.vue"
import Advertisement from "@/components/Advertisement/index.vue"
import hotEnter from "./components/hotEnter.vue"
import download from "./components/download.vue"
import author from "./components/author.vue"
export default {
    name:'home',
    asyncData({store}){
        return store.dispatch('home/getNewItem',{
            pageNo:1,
            pageSize:store.state.pageSize
        });
    },
    data(){
        return {
            pageNo:1,
            open:true,
            refreshing: false,
            loading: false,
        }
    },
    components:{
        carousel,
        contentItem,
        hotEnter,
        download,
        Advertisement,
        author
    },
    computed:{ 
        ...mapState(['newItem','newItemObj'])
        
    },
    methods:{
        load() {
            if(this.newItem.length < this.newItemObj.total && !this.loading){
                this.loading = true;
                this.pageNo = this.pageNo+1;
                this.$store.dispatch('home/getNewItem',{
                    pageNo:this.pageNo,
                    pageSize:this.pSize
                })
                .then(()=>{
                    this.loading = false;
                })
            }
        },
        refresh() {
            this.refreshing = true;
            this.pageNo = 1;
            this.$store.dispatch('home/getNewItem',{
                pageNo:1,
                pageSize:this.pSize
            })
            .then(()=>{
                this.refreshing = false;
            })
        }
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

