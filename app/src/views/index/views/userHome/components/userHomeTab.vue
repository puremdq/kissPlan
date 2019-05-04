<template>
    <div class="userHomeTab">
        <mu-tabs :value.sync="active1" inverse color="secondary" text-color="rgba(0, 0, 0, .54)"  >
            <mu-tab>文章</mu-tab>
            <mu-tab>动态</mu-tab>
        </mu-tabs>
        <div class="p20" v-if="active1 === 0">
            <mu-load-more @refresh="refresh" :refreshing="refreshing" :loading="loading" @load="load">
                <contentItem class="mt20 bb1 pb10" v-for="(item,idx) in (newList && newList.list)" :key="idx" :data="item"></contentItem>
            </mu-load-more>
        </div>
        <div class="p20" v-if="active1 === 1">
           
        </div>
        
    </div>
</template>
<script>
import contentItem from "@/components/contentItem/index.vue"
export default {
    name:'userHomeTab',
    data () {
        return {
            active1: 0,
            refreshing: false,
            loading: false,
        };
    },
    props:['newList'],
    mounted(){
    },
    components:{
        contentItem
    },
    methods:{
        load() {
        },
        refresh() {}
    }
}
</script>
<style lang="less">
    
</style>
