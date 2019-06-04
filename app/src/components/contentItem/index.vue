<template>
    <div class="contentItem">
        <a class="wrap-img" v-if="data.firstImg" :href="'/news/'+data.id" target="_blank">
            <img class="img-blur-done" :src="data.firstImg" alt="120">
        </a>
        <div class="content" :class="{hasImg:data.firstImg}">
            <a class="title"  :href="'/news/'+data.id">{{data.title}}</a>
            <p class="abstract"  v-html="data.preview">
            </p>
            <div class="meta">
                <a class="nickname mr10" target="_blank"  :href="'/news/'+data.id">{{data.username}}</a>
                <span class="mr10"><i class="iconfont icon-message"></i>{{data.commentNum}}</span>
                <span><i class="iconfont icon-shoucang_xiantiao"></i> {{data.likeNum}}</span>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name:'contentItem',
    data:function(){
        return {

        }
    },
    props:{
        data:{
            type:Object,
            default:()=>{
                return {}
            }
        },
        
    }
}
</script>
<style lang="less" scoped>
    .contentItem{
        position: relative;
        .wrap-img{
            position: absolute;
            top: 50%;
            margin-top: -60px;
            right: 0;
            width: 150px;
            height: 100px;
            img{
                width: 100%;
                height: 100%;
                border-radius: 4px;
                border: 1px solid #f0f0f0;
            }
        }
        .content{
            &.hasImg{
                padding-right: 165px;
            }
            .title{
                font-family: -apple-system,SF UI Display,Arial,PingFang SC,Hiragino Sans GB,Microsoft YaHei,WenQuanYi Micro Hei,sans-serif;
                color:#333;
                margin: -7px 0 4px;
                display: inherit;
                font-size: 18px;
                font-weight: 700;
                line-height: 1.5;
                &visited{
                    color: #969696;
                }
            }
            .abstract{
                margin: 0 0 8px;
                font-size: 13px;
                line-height: 24px;
                color: #999;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 2;
                overflow: hidden;
            }
            .meta{
                padding-right: 0!important;
                font-size: 12px;
                font-weight: 400;
                line-height: 20px;
                color: #999;
                a{
                    color: #999;

                }
            }
        }
    }
</style>


