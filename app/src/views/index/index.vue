<template>
    <div class="index">
        <yx-header></yx-header>
        <router-view style="margin-top:71px;"></router-view>
    </div>
</template>
<script>
import header from '@/components/header/header.vue'
export default {
    name:'index',
    data(){
        return {

        }
    },
    components:{
        'yx-header':header,
    }
}
</script>
<style lang="less" scoped>

</style>
