<template>
    <div class="ThirtyDayHot">
        <div class="box mt20">
            <div class="hot-item p10 mb10" style="" >
                <h1 class="tac">30日热门</h1>
            </div>
            <mu-row gutter>
                <mu-col span="12" sm="12" md="8">
                    
                    <div class="contentItem-box">
                        <mu-load-more @refresh="refresh" :refreshing="refreshing" :loading="loading" @load="load">
                            <contentItem class="mt20 bb1 pb10" v-for="(item,idx) in newItem" :key="idx" :data="item"></contentItem>
                        </mu-load-more>
                    </div>
                </mu-col>
                <mu-col class="phone_hide" span="12" sm="12" md="4">
                    <author ></author>
                </mu-col>
            </mu-row>
        </div>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions } = createNamespacedHelpers('home');
import contentItem from "@/components/contentItem/index.vue"
import author from "@/components/author/index.vue"
export default {
    name:'ThirtyDayHot',
    asyncData({store}){
        return store.dispatch('home/getNewItem',{
            pageNo:1,
            pageSize:10
        });
    },
    data(){
        return {
            open:true,
            arr:10,
            refreshing: false,
            loading: false,
        }
    },
    components:{
        contentItem,
        author
    },
    computed:{ 
        ...mapState(['newItem'])
        
    },
    methods:{
        load() {

        },
        refresh() {}
    }
}
</script>
<style lang="less" scoped>
    .ThirtyDayHot{
        .contentItem-box{
            border-top:1px solid #ddd;
            padding-top:10px;
            margin-top: 30px;
        }   
        .hot-item{
            height:85px; 
            line-height:85px;
            color:#fff;
            background-color:#f69581;
            border-radius:5px;
            font-weight: bold;
            padding:0 20px;
            
        } 
        
    }
    
</style>

