<template>
    <div class="hotToday">
        <div class="box mt20">
            <mu-row gutter>
                <mu-col span="12" sm="12" md="8">
                    <div class="contentItem-box">
                        <mu-load-more @refresh="refresh" :refreshing="refreshing" :loading="loading" @load="load">
                            <contentItem class="mt20 bb1 pb10" v-for="(item,idx) in (newItem && newItem.list)" :key="idx" :data="item"></contentItem>
                        </mu-load-more>
                    </div>
                </mu-col>
                <mu-col class="phone_hide" span="12" sm="12" md="4">
                    <author  class="mt20"></author>
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
    name:'hotToday',
    asyncData({store}){
        return store.dispatch('home/getNewItem');
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
    .hotToday{
        .contentItem-box{
            border-top:1px solid #ddd;
            padding-top:10px;
            margin-top: 30px;
        }   
        
        
    }
    
</style>

