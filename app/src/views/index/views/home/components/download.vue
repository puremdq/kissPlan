<template>
    <div class="download p20 clearfix">
        <div class="download-bpdy" @mouseenter="mouseenter" @mouseleave="mouseleave">
            <img class="qrcode" src="//cdn2.jianshu.io/assets/web/download-index-side-qrcode-cb13fc9106a478795f8d10f9f632fccf.png" alt="">
            <div class="download-text p10">
                <h6>下载KissPlan 手机APP></h6>
                <p>随时随地发现和创作内容</p>
            </div>
        </div>
        <div v-if="qrcodeShow" class="download-qrcode">
            <img style="width:100%" src="//cdn2.jianshu.io/assets/web/download-index-side-qrcode-cb13fc9106a478795f8d10f9f632fccf.png" alt="">
            <span class="sqlt"></span>
        </div>
    </div>
</template>
<script>
export default {
    name:'download',
    data(){
        return {
            qrcodeShow:false
        }
    },
    methods:{
        mouseenter(){
            this.qrcodeShow = true;
        },
        mouseleave(){
            this.qrcodeShow = false;
        }
    }
}
</script>
<style lang="less" scoped>
    .download{
        border:0.5px solid #ddd;
        border-radius:5px;
        cursor: pointer;
        position: relative;
        .download-bpdy{
            .qrcode{
                width:60px;
                float: left;
            }
            .download-text{
                margin-left:60px;
                p{
                    color:#999;
                    font-size:12px;
                }
            }
        }
        .download-qrcode{
            position: absolute;
            width:150px;
            height:150px;
            border:1px solid #ddd;
            top:-160px;
            background-color: #fff;
            left:50%;
            transform: translateX(-50%);
            box-shadow:0 2px 1px #999;
            .sqlt{
                width: 0;
                height: 0;
                border-width: 10px;
                border-style: solid;
                position: absolute;
                left:50%;
                bottom:-20px;
                transform: translateX(-50%);
                border-color: #fff transparent transparent transparent;
            }
        }
    }
</style>

