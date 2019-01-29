<template>
    <div class="zhuanZai">
        <mu-tooltip placement="top" content="分享到微信">
            <i class="icon iconfont icon-weixin"></i>
        </mu-tooltip>
        <mu-tooltip placement="top" content="分享到微博">
            <i class="icon iconfont icon-weibo"></i>
        </mu-tooltip>
        <mu-tooltip placement="top" content="分享到QQ">
            <i class="icon iconfont icon-QQ"></i>
        </mu-tooltip>
    </div>
</template>
<script>
export default {
    name:'zhuanZai',
    data(){
        return {

        }
    }
}
</script>
<style lang="less">
    .zhuanZai{
        
        .icon{
            font-size: 35px;
            margin-left:10px;
            cursor: pointer;
        }
        .icon-weixin{
            color:#00c800;
        }
        .icon-weibo{
            color:#ea5d5c;
        }
        .icon-QQ{
            color:#30a5dd;
        }
    }
</style>

