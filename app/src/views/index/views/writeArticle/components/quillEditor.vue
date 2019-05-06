<template>
    <div class="wangEditor">
        <div id="mYwangEditor"></div>
    </div>
</template>
<script>
// import wangeditor from "wangeditor" 不支持服务端渲染
var wangeditor
import {config} from "@/api"
export default {
    name:'wangEditor',
    data(){
        return {
            editor:null,
            uploadUrl:config.client.baseURL,
        }
    },
    props:['value'],
    mounted(){
        wangeditor = require('wangeditor')
        this.create_info();
    },
    methods:{
        create_info(){
            var that = this;
            this.editor = new wangeditor('#mYwangEditor');
            this.editor.customConfig.uploadImgServer = this.uploadUrl+'/upload';
            this.editor.customConfig.uploadFileName = 'file'
            this.editor.customConfig.onchange = function (html) {
                that.$emit('input',{
                    content:html,
                    mdContent:'',
                    articleType:'0'
                })
            }
            this.editor.customConfig.customAlert = function (info) {
                that.$Message.warning(info);
            }
            this.editor.customConfig.uploadImgHooks = {
                before: function (xhr, editor, files) {
                    // 图片上传之前触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件
                    
                    // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
                    // return {
                    //     prevent: true,
                    //     msg: '放弃上传'
                    // }
                },
                success: function (xhr, editor, result) {
                    // 图片上传并返回结果，图片插入成功之后触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                },
                fail: function (xhr, editor, result) {
                    // 图片上传并返回结果，但图片插入错误时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                },
                error: function (xhr, editor) {
                    // 图片上传出错时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
                },
                timeout: function (xhr, editor) {
                    // 图片上传超时时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
                },

                // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
                // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
                customInsert: function (insertImg, result, editor) {
                    // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                    // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
                    if(result.status=='200' && result.data ){
                        var url = result.data
                        insertImg(url)
                    }else{
                        that.$Message.warning('图片上传失败');
                    }
                    

                }
            }
            this.editor.create()
            this.editor.txt.html(this.value.content)
        }
    },
    watch:{
        value(newValue,oldValue) {
            this.editor.txt.html(this.value.content)
        }
    }
}
</script>
<style lang="less">
    #mYwangEditor{
        
        .w-e-text-container{
            height:80vh !important;
        }
        .w-e-toolbar{
            flex-wrap:wrap;
        }
    }
</style>
