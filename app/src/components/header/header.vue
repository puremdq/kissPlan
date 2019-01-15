<template>
    <div class="header">
        <mu-container class="header_container">
            <mu-row gutter>
                <mu-col span="12" sm="12" md="2" class="header_left">
                    <span class="logo baseColor">KissPlan</span>
                    <span class="header_conterl" @click="click_phone_menu">
                        <span ref="header_conterl_span1"></span> 
                        <span ref="header_conterl_span2"></span> 
                        <span ref="header_conterl_span3"></span> 
                    </span>
                </mu-col>
                <mu-col span="12" sm="12" md="6" class="phone_hide">
                    <span class="baseColor h60 ptr10 pointer">首页</span>
                    <span class="h60 ptr10 pointer">下载App</span>
                    <mu-text-field v-model="search" placeholder="搜索" style="margin-left:20px;margin-bottom: 0px;" >
                        <i class="iconfont icon-search pointer" style="font-size:25px;"></i>
                    </mu-text-field>
                </mu-col>
                <mu-col span="12" sm="12" md="4" class="tar phone_hide">
                    <mu-button flat color="primary" class="h60">登录</mu-button>    
                    <mu-button large color="#ea6f5a">
                        <mu-icon left value=""></mu-icon>
                        写文章
                    </mu-button>
                </mu-col>
            </mu-row>
        </mu-container>
        <div class="phone_menu" :class="{active:phone_menu}" :style="{height:phone_menu_height}">
            <mu-list>
                <mu-list-item button >
                    <mu-list-item-action>
                        <i class="iconfont icon-shouye" style="font-size:28px;margin-left:-5px;"></i>
                    </mu-list-item-action>
                    <mu-list-item-title>首页</mu-list-item-title>
                </mu-list-item>
                <mu-list-item button >
                    <mu-list-item-action>
                        <i class="iconfont icon-erweima"></i>
                    </mu-list-item-action>
                    <mu-list-item-title>下载App</mu-list-item-title>
                </mu-list-item>
                <mu-list-item button  :ripple="false">
                    <mu-list-item-action>
                        <i class="iconfont icon-sousuo_xiantiao" style="font-size:28px;margin-left:-5px;"></i>
                    </mu-list-item-action>
                    <mu-list-item-title style="height:45px;">
                        <mu-text-field @keypress="keypress" v-model="search" type="search" placeholder="请输入内容"></mu-text-field>
                        <!-- <mu-button color="primary">搜索</mu-button> -->
                    </mu-list-item-title>
                </mu-list-item>
                <mu-list-item button >
                    <mu-list-item-action class="tac">
                        <i class="iconfont icon-bi"></i>
                    </mu-list-item-action>
                    <mu-list-item-title>写文章</mu-list-item-title>
                </mu-list-item>
                <mu-list-item button >
                    <mu-list-item-action class="tac">
                        <i class="iconfont icon-bi"></i>
                    </mu-list-item-action>
                    <mu-list-item-title>登录</mu-list-item-title>
                </mu-list-item>
            </mu-list>
        </div>
    </div>
</template>
<script>
import {mapState ,mapMutations} from "vuex"
import axios from "axios"
export default {
    name:'myHeader',
    data(){
        return {
            phone_menu_height:"0px",
            phone_menu:false,
            search:''
        }
    },
    computed:{
        ...mapState(['unreadMessage','user'])
    },
    mounted(){
        
    },
    methods:{
        ...mapMutations(['setUser']),
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
            
        }
        
    }
}
</script>
<style lang="less" >
    .header{
        height:62px;
        border-bottom:1px solid #ddd;
        background-color:#fff;
        font-size: 16px;
        .header_container{
            border-top:none;
            padding:0 16px;
            position: fixed;
            max-width: 1920px;
            margin:0 auto;
            top:0px;
            left:0px;
            right:0px;
            height:60px;
            z-index: 2;
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
            position: absolute;
            left:0;
            right:0;
            top:62px;
            z-index: 1000;
            background-color: #fff;
            padding:0 10px;
            transition: height .3s;
            overflow: hidden;
            .active{
                border-bottom:0.5px solid #ddd;
            }
        }
        @media (max-width: 891px){
            .mu-input {
                width: 190px;
            }
        }
        @media (min-width: 768px){
            .header_container {
                .header_left{
                    .header_conterl{
                        display: none;
                    }
                }
            }
        }
    }
    
</style>
