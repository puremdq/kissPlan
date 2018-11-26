<template>
    <div class="search">
        <mu-button class="search_btn"  icon @click="getSearchContent">
           <mu-icon class="iconfont"  size="30" value=":icon-search"></mu-icon>
        </mu-button>
        <input class="search_input"  type="text" v-model="msg" placeholder="请输入搜索内容">
        
    </div>
</template>
<script>
export default {
    name:'search',
    data(){
        return {
            msg:''
        }
    },
    methods:{
        getSearchContent(){
            this.msg = ''
        }
    }
}
</script>
<style lang="less" scoped>
    .search{
        position: relative;
        .search_input{
            width:100%;
            height:30px;
            padding:0px 10px;
            padding-right:48px;
            border-radius: 15px;
            outline: none;
            border:1px solid #ddd;
            margin-top:9px;
        }
        .search_btn{
            position: absolute;
            right:0px;
            
        }
    }
</style>
