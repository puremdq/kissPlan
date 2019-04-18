<template>
    <div class="pingLun">
        <mu-avatar size="45" style="vertical-align: middle;margin-right:5px;cursor:pointer;float:left;">
            <img src="../../assets/images/head.jpg">
        </mu-avatar>
        <div class="pingLinInputBox" v-if="user && user.kiss_plan_token">
            <el-input
                type="textarea"
                :rows="5"
                placeholder="请输入内容"
                v-model="from.pingLun">
            </el-input>
            <div class="tar mt10">
                <mu-button color="success">发送</mu-button>
            </div>
        </div>
        <div v-else class="noLogin">
            <mu-button color="primary" @click="toLogin">登录</mu-button>
            后发表评论
        </div>
    </div>
</template>
<script>
import {mapState ,mapMutations} from "vuex";
import toLign from "@/mixins/toLign.js"
export default {
    name:'pingLun',
    mixins:[toLign],
    data(){
        return {
            from:{
                pingLun:'',
            },
            user:{},
        }
    },
    mounted(){
        this.isLogin()
    },
    methods:{
        isLogin() {
            var user = JSON.parse(window.localStorage.getItem('user'));
            this.user = user;
        },
    }
}
</script>
<style lang="less">
    .pingLun{
        .pingLinInputBox{
            margin-left:50px; 
        }
        .noLogin{
            text-align: center;
            height:100px;
            line-height: 100px;
            border:1px solid #ddd;
            border-radius:5px;
            margin-left:50px; 
        }
    }
</style>
