<template>
    <div class="xiaoXi">
        
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('message');
export default {
    name:'xiaoXi',
    data(){
        return {}
    },
    computed:{
        ...mapState(['_messageList'])
    },
    mounted(){
        this._getMessageList();
    },
    methods:{
        ...mapActions(['_getMessageList']),

    }
}
</script>
<style lang="less">
    .xiaoXi{
       
    }
</style>
