<template>
    <div class="newsAutor">
        <div class="mt20 newsTargetDetail">
            <mu-avatar size="45" style="vertical-align: middle;margin-right:5px;cursor:pointer;float:left;">
                <img src="../../../../../assets/images/head.jpg">
            </mu-avatar>
            <div style="margin-left:50px;height:50px;">
                <div style="padding:0 10px;">
                    <span class="name ">屠夫小胖17001276309</span>
                    <mu-tooltip content="更新达人">
                        <i class="iconfont icon-bi"></i>
                    </mu-tooltip>
                    
                </div>
                <div style="padding:0 10px;" class="new-t-t-y-p-x">
                    <span>2018.12.31 07:29</span>
                    <span>字数 208</span>
                    <span>阅读 2829</span>
                    <span>评论 22</span>
                    <span>喜欢 101</span>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name:'newsAutor',
    data(){
        return {}
    }
}
</script>
<style lang="less">
    .newsAutor{
        .newsTargetDetail{
            .name{
                font-size: 16px;
                margin-right:10px;
            }
            .new-t-t-y-p-x{
                span{
                    font-size: 12px;
                    color:#aaa;
                    margin-right:5px; 
                }
            }
        }
    }
</style>
