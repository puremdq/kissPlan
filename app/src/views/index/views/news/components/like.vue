<template>
    <div class="like">
        <div class="like-box":class="{active:data.isCurrentUserLiked}">
            <i class="iconfont icon-shoucang_xiantiao" style="font-size:25px;vertical-align: middle;"  @click="clickHandle" ></i>
            <span  @click="clickHandle" >喜欢</span>
            <span>|</span>
            <span>{{data.likeNum | MoneyFormat(true)}}</span>
        </div>
    </div>
</template>
<script>
export default {
    name:'like',
    data(){
        return {

        }
    },
    props:['data'],
    methods:{
        clickHandle() {
            this.$emit('clickLike')
        }
    }
}
</script>
<style lang="less">
    .like{
        .like-box{
            border:1px solid #999;
            border-radius:25px;
            height:50px;
            line-height:50px;
            text-align: center;
            width:200px;
            color:#999;
            cursor: pointer;
            display: inline-block;
            &.active{
                border:1px solid #ea6f5a;
                color:#ea6f5a;
            }
        }
        
    }
</style>

