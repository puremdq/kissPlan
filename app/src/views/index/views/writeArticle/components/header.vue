<template>
    <div class="writeArticlemyHeader">
        <div class="header_container">
            <mu-row gutter>
                <mu-col span="12" sm="12" md="2" class="header_left">
                    <span class="logo baseColor" @click="goHome">KissPlan</span>
                    <span class="header_conterl" @click="click_phone_menu">
                        <span ref="header_conterl_span1"></span> 
                        <span ref="header_conterl_span2"></span> 
                        <span ref="header_conterl_span3"></span> 
                    </span>
                </mu-col>
            </mu-row>
        </div>
        <div class="phone_menu" :class="{active:phone_menu}" :style="{height:phone_menu_height}">
            <mu-list>
                <mu-list-item button @click="setEditorType('富文本编辑器')">
                    <mu-list-item-action>
                        <i class="iconfont icon-shouye" style="font-size:28px;margin-left:-5px;"></i>
                    </mu-list-item-action>
                    <mu-list-item-title>富文本编辑器</mu-list-item-title>
                </mu-list-item>
                <mu-list-item button  @click="setEditorType('markdown编辑器')">
                    <mu-list-item-action>
                        <i class="iconfont icon-shouye" style="font-size:28px;margin-left:-5px;"></i>
                    </mu-list-item-action>
                    <mu-list-item-title>markdown编辑器</mu-list-item-title>
                </mu-list-item>
            </mu-list>
        </div>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('writeArticle');
export default {
    name:'writeArticlemyHeader',
    data(){
        return {
            phone_menu_height:"0px",
            phone_menu:false,
            open: false,
            trigger: null
        }
    },
    computed:{
    },
    mounted(){
        this.$nextTick(()=>{
            this.trigger = (this.$refs.button && this.$refs.button.$el)
        })
       
    },
    methods:{
        ...mapMutations(['_setEditorType']),
        keypress(e) {
            var keycode = e.keyCode;
            //获取搜索框的值
            var searchContent = $(this).val();
            if (keycode == '13') {
                e.preventDefault();
                //请求搜索接口
                if (searchContent == '') {
                    alert('请输入检索内容！');
                } else {
                    alert(searchContent);
                }
            }
        },
        
        click_phone_menu() {
            this.phone_menu = !this.phone_menu;
            if(this.phone_menu){
                $("body").scrollTop(1);
                $('body').css('overflow','hidden')
                this.phone_menu_height = document.body.scrollHeight  -62+'px'
                $(this.$refs['header_conterl_span1']).css({
                    'transform': 'rotate(-45deg)',
                })
                $(this.$refs['header_conterl_span2']).css({
                    'visibility': 'hidden',
                })
                $(this.$refs['header_conterl_span3']).css({
                    'transform': 'rotate(45deg)',
                })
            }else{
                $('body').css('overflow','auto')
                this.phone_menu_height = '0px'
                $(this.$refs['header_conterl_span1']).css({
                    'transform': 'rotate(0deg)',
                })
                
                window.setTimeout(()=>{
                    $(this.$refs['header_conterl_span2']).css({
                        'visibility': 'initial',
                    })
                },300)
                $(this.$refs['header_conterl_span3']).css({
                    'transform': 'rotate(0deg)',
                })
            }
            
        },
        goHome() {
            this.$router.push('/index')
        },
        setEditorType(type) {
            this.click_phone_menu();
            this._setEditorType(type);
        }
        
    }
}
</script>
<style lang="less" >
    .writeArticlemyHeader{
        height:62px;
        
        background-color:#fff;
        font-size: 16px;
        .header_container{
            background-color:#fff;
            border-bottom:1px solid #ddd;
            border-top:none;
            padding:0 16px;
            position: fixed;
            // max-width: 1920px;
            margin:0 auto;
            top:0px;
            left:0px;
            right:0px;
            height:60px;
            z-index: 12001;
            // box-shadow: 0px 3px 3px #ddd;
            .header_left{
                .logo{
                    line-height:60px;
                    height:60px;
                    font-size:25px;
                    cursor: pointer;
                    color:#ea6f5a;
                    font-weight: bold;        
                }
                .header_conterl{
                    display: inline-block;
                    padding: 18px 10px 17px;
                    vertical-align: middle;
                    float: right;
                    span{
                        display: block;
                        width: 20px;
                        height: 2px;
                        margin-bottom: 5px;
                        position: relative;
                        background: #ea6f5a;
                        border-radius: 3px;
                        z-index: 1;
                        -webkit-transform-origin: 20px;
                        transform-origin: 20px;
                        transition: background .5s cubic-bezier(.77,.2,.05,1),opacity .55s ease,transform .5s cubic-bezier(.77,.2,.05,1),width .5s;
                    }
                }
            }
            
        }
        .phone_menu{
            position: fixed;
            left:0;
            right:0;
            top:60px;
            z-index: 10005;
            background-color: #fff;
            padding:0 10px;
            transition: height .3s;
            overflow: hidden;
            .active{
                border-bottom:0.5px solid #ddd;
            }
            .iconfont{
                color:#ea6f5a;
            }
        }
        @media (max-width: 891px){
            .mu-input {
                width: 190px;
            }
        }
        
    }
    @media (min-width: 768px){
        .writeArticlemyHeader{
            display: none;
            .header_container {
                display: none;
                .header_left{
                    .header_conterl{
                        display: none;
                    }
                }
            }
        }
    }
</style>
