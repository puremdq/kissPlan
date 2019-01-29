<template>
    <div class="like">
        <div class="like-box">
            <i class="iconfont icon-shoucang_xiantiao" style="font-size:25px;vertical-align: middle;"></i>
            <span>喜欢</span>
            <span>|</span>
            <span>46</span>
        </div>
    </div>
</template>
<script>
export default {
    name:'like',
    data(){
        return {

        }
    }
}
</script>
<style lang="less">
    .like{
        .like-box{
            border:1px solid #ea6f5a;
            border-radius:25px;
            height:50px;
            line-height:50px;
            text-align: center;
            width:200px;
            color:#ea6f5a;
            cursor: pointer;
            display: inline-block;
        }
        
    }
</style>

