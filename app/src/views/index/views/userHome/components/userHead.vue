<template>
    <div class="userHead">
        <div class="pic">
            <mu-avatar size="60" style="vertical-align: middle;margin-right:5px;cursor:pointer;"  ref="button">
                <img :src="data.avatars">
            </mu-avatar>
        </div>
        <div class="userHead_detail">
            <mu-row gutter>
                <mu-col span="6" sm="6" md="6">
                    <h1 class="userHead_detail_name">{{data.username}}</h1>
                    <div>
                        
                    </div>
                </mu-col>
                <mu-col span="6" sm="6" md="6">
                    <div class="btns tar" v-if="$store.state.user.user && $store.state.route.params.id!=$store.state.user.user.id">
                        <mu-button round color="success">发简信</mu-button>
                        <mu-button round color="success" >关注</mu-button>
                    </div>
                </mu-col>
            </mu-row>
        </div>
    </div>
</template>
<script>
export default {
    name:'userHead',
    data(){
        return {

        }
    },
    computed:{
        showBtns() {
            
        }
    },
    props:['data'],
    mounted() {
    }
}
</script>
<style lang="less">
    .userHead{
        border-bottom:1px solid #ddd;
        padding-bottom:10px;
        .pic{
            width:60px;
            height:60px;
            float: left;
            padding-top:20px;
        }
        .userHead_detail{
            margin-left:70px;
            padding:5px 20px;
            .userHead_detail_name{
            }
            .btns{
                margin-top:18px;
            }
        }
    }
</style>
