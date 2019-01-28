<template>
    <div class="news" >
        <div class="box mt20" ref="box">
            <mu-row gutter>
                <mu-col span="24"  sm="24" md="8" style="margin:0 auto;">
                    <h1 class="title">李白临终前最后一首诗，道尽多少英雄情，令人惋惜</h1>
                    <newsAutor></newsAutor>
                    <textPic></textPic>
                    <zanShang class="mt20"></zanShang>
                </mu-col>
            </mu-row>
        </div>
    </div>
</template>
<script>
import newsAutor from "./components/newsAutor.vue"
import textPic from "./components/textPic.vue"
import zanShang from "./components/zanShang.vue"
export default {
    name:'news',
    data(){
        return {
        }
    },
    components:{
        newsAutor,
        textPic,
        zanShang
    },
    mounted(){
        
    }
}
</script>
<style lang="less" scoped>
    .news{
        .box{
            .title{
                word-break: break-word!important;
                word-break: break-all;
                margin: 20px 0 0;
                font-family: -apple-system,SF UI Display,Arial,PingFang SC,Hiragino Sans GB,Microsoft YaHei,WenQuanYi Micro Hei,sans-serif;
                font-size: 34px;
                font-weight: 700;
                line-height: 1.3;
            }
           
        }
    }
</style>
