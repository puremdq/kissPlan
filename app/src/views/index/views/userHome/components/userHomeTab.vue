<template>
    <div class="userHomeTab">
        <mu-tabs :value.sync="active1" inverse color="secondary" text-color="rgba(0, 0, 0, .54)"  >
            <mu-tab>文章</mu-tab>
            <mu-tab>动态</mu-tab>
        </mu-tabs>
        <div class="p20" v-if="active1 === 0">
            <!-- <contentItem :data="{}"></contentItem> -->
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
            active1: 0
        };
    },
    mounted(){
    },
    components:{
        contentItem
    }
}
</script>
<style lang="less">
    
</style>
