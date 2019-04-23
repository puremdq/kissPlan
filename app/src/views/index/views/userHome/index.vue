<template>
    <div class="userHome box mt20">
        <mu-row gutter>
            <mu-col span="12" sm="12" md="8">
                <userHead :data="_userDetail"></userHead>
                <headDetail :data="_userDetail"></headDetail>
                <userHomeTab class="mt10"></userHomeTab>
            </mu-col>
            <mu-col class="phone_hide" span="12" sm="12" md="4">
                
            </mu-col>
        </mu-row>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions } = createNamespacedHelpers('userHome');
import userHead from "./components/userHead.vue"
import headDetail from "./components/headDetail.vue"
import userHomeTab from "./components/userHomeTab.vue"
export default {
    name:'userHome',
    data(){
        return {

        }
    },
    computed:{
        ...mapState(['_userDetail'])
    },
    components:{
        userHead,
        headDetail,
        userHomeTab
    },
    mounted(){
        this.$store.dispatch('userHome/_getUserHome',{
            id:this.$route.params.id
        });
    }
}
</script>
<style lang="less">
.userHome{
    
}
</style>
