<template>
    <div class="news" >
        <div class="box mt20" ref="box">
            <mu-row gutter>
                <mu-col  sm="24" md="8" :offset="offset">
                    <h1 class="title">李白临终前最后一首诗，道尽多少英雄情，令人惋惜</h1>
                </mu-col>
            </mu-row>
        </div>
    </div>
</template>
<script>
export default {
    name:'news',
    data(){
        return {
            offset:2
        }
    },
    mounted(){
        if($(this.$refs['box']).width()<=768){
            this.offset = 0;
        }
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
