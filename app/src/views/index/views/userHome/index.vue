<template>
    <div class="userHome box mt20">
        <mu-row gutter>
            <mu-col span="12" sm="12" md="8">
                <userHead :data="_userDetail"></userHead>
                <headDetail :data="_userDetail"></headDetail>
                <userHomeTab class="mt10" :newList="_newsList"></userHomeTab>
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
        ...mapState(['_userDetail','_newsList'])
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
        this._getMyNewsList({
            pageNo:'1',
            pageSize:10,
            userId:this.$route.params.id
        })
    },
    methods:{
        ...mapActions(['_getMyNewsList','_getUserHome'])
    }
}
</script>
<style lang="less">
.userHome{
    
}
</style>
