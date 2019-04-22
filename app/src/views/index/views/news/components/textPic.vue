<template>
    <div class="textPic">
        <div data-note-content="" class="show-content mt20">
            <div class="show-content-free ql-editor markdown-body" v-html="data">
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name:'textPic',
    data(){
        return {}
    },
    props:['data']
}
</script>
<style lang="less">
    .textPic{
        img{
            max-width: 100%;
        }
        .show-content{
            color: #2f2f2f;
            word-break: break-word!important;
            word-break: break-all;
            font-size: 16px;
            font-weight: 400;
            line-height: 1.7;
            .show-content-free{
                color: #2f2f2f;
                word-break: break-word!important;
                word-break: break-all;
                font-size: 16px;
                font-weight: 400;
                line-height: 1.7;
            }
            .image-package{
                padding-bottom: 25px;
                width: 100%;
                margin-top:10px;
                text-align: center;
                .image-container{
                    position: relative;
                    background-color: #eee;
                    transition: background-color .1s linear;
                    margin: 0 auto;
                    .image-container-fill{
                        z-index: 1;
                    }
                    .image-view{
                        position: absolute;
                        top: 0;
                        left: 0;
                        width: 100%;
                        height: 100%;
                        overflow: hidden;
                        img{
                            max-width: 100%;
                            height: auto;
                            vertical-align: middle;
                            border: 0;
                            cursor: -webkit-zoom-in;
                            transition: all .25s ease-in-out;
                            display: block;
                            transition: all .15s linear;
                            -webkit-filter: blur(0);
                            filter: blur(0);
                            opacity: 1;
                        }
                    }
                }
            }
        }
    }
</style>
