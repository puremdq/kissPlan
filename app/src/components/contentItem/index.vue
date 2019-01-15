<template>
    <div class="contentItem">
        <a class="wrap-img" v-if="img" href="/p/58161c28e611" target="_blank">
            <img class="  img-blur-done" src="https://upload-images.jianshu.io/upload_images/14625074-1f90f36c8128d0c8.png?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/240" alt="120">
        </a>
        <div class="content" :class="{hasImg:img}">
            <a class="title" target="_blank" href="/p/29559a644e68">30岁了，我为什么要转行？</a>
            <p class="abstract">
                先简单的自我介绍一下我自己。 我2011年大学韩语专业毕业，在深圳一家外贸公司上班，负责韩国客户的贸易业务。这是我的第一份全职工作，一做就做了5...
            </p>
            <div class="meta">
                <a class="nickname mr10" target="_blank" href="/u/362fd55c5949">小小荔枝妹</a>
                <span class="mr10"><i class="iconfont icon-message"></i> 9</span>
                <span><i class="iconfont icon-shoucang_xiantiao"></i> 1</span>
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
        img:{
            type:String
        }
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


