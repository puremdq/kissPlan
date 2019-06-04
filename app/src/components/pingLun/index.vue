<template>
    <div class="pingLun">
        <mu-avatar  v-if="!hide_head && $store.state.user.user" size="45" style="vertical-align: middle;margin-right:5px;cursor:pointer;float:left;">
            <img :src="$store.state.user.user && $store.state.user.user.avatars">
        </mu-avatar>
        <div class="pingLinInputBox" v-if="$store.state.user.user">
            <el-input
                type="textarea"
                :rows="5"
                placeholder="请输入内容,最多200字"
                @change="change"
                v-model="from.pingLun">
            </el-input>
            <div class="tar mt10">
                <mu-button @click="cancel" v-if="!hide_cancel">取消</mu-button>
                <mu-button color="success" @click="success" :disabled="from.pingLun && from.pingLun.length<=200?false:true">发送</mu-button>
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
                pingLun:this.value,
            },
        }
    },
    props:['hide_head','value','hide_cancel'],
    mounted(){
    },
    methods:{
        change(){
            this.$emit('input',this.from.value)
        },
        cancel() {
            this.$emit('cancel')
        },
        success() {
            this.$emit('ok',this.from)
        },
        setValue(value) {
            this.from.pingLun = value;
        }
    },
   
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
