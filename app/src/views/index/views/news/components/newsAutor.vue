<template>
    <div class="newsAutor">
        <div class="mt20 newsTargetDetail">
            <mu-avatar size="45" style="vertical-align: middle;margin-right:5px;cursor:pointer;float:left;">
                <img :src="data.avatars"  @click="$router.push(`/userHome/${data.authorId}`)">
            </mu-avatar>
            <div style="margin-left:50px;height:50px;">
                <div style="padding:0 10px;">
                    <span class="name " @click="$router.push(`/userHome/${data.authorId}`)" style="cursor:pointer;">{{data.username}}</span>
                    <mu-tooltip content="更新达人">
                        <i class="iconfont icon-bi"></i>
                    </mu-tooltip>
                    
                </div>
                <div style="padding:0 10px;" class="new-t-t-y-p-x">
                    <span>{{data.updateDate}}</span>
                    <span>字数 {{data.content&& data.content.length | MoneyFormat(true)}}</span>
                    <span>阅读 {{(data.readNum || 0) | MoneyFormat(true)}}</span>
                    <span>评论 {{(data.commentNum || 0) | MoneyFormat(true)}}</span>
                    <span>喜欢 {{(data.likeNum || 0 )| MoneyFormat(true)}}</span>
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
    },
    props:['data'],
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
