<template>
    <div class="header">
        <mu-container class="header_container">
            <mu-row gutter>
                <mu-col span="12" sm="12" md="2" class="header_left">
                    <span class="logo baseColor" @click="goHome">KissPlan</span>
                    <span class="header_conterl" @click="click_phone_menu">
                        <span ref="header_conterl_span1"></span> 
                        <span ref="header_conterl_span2"></span> 
                        <span ref="header_conterl_span3"></span> 
                    </span>
                </mu-col>
                <mu-col span="12" sm="12" md="6" class="phone_hide">
                    <span class="baseColor h60 ptr10 pointer"  @click="goHome">首页</span>
                    <span class="h60 ptr10 pointer" @click="downloadApp">下载App</span>
                    <mu-text-field v-model="search" placeholder="搜索" style="margin-left:20px;margin-bottom: 0px;" >
                        <i class="iconfont icon-search pointer" style="font-size:25px;" @click="doSearch"></i>
                    </mu-text-field>
                </mu-col>
                <mu-col span="12" sm="12" md="4" class="tar phone_hide">
                    <span  class="el-dropdown-link"  v-if="!$store.state.user || !$store.state.user.user">
                        <mu-button flat color="primary" class="h60" @click="signIn">登录</mu-button>    
                    </span>
                    <el-dropdown  @command="handleCommand"  v-if="$store.state.user && $store.state.user.user">
                        
                        <span  class="el-dropdown-link">
                            <mu-avatar size="36" style="vertical-align: middle;margin-right:5px;cursor:pointer;"  ref="button" @click="open = !open">
                                <img :src="$store.state.user.user.avatars">
                            </mu-avatar>
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="个人中心">个人中心</el-dropdown-item>
                            <el-dropdown-item command="消息中心">消息中心</el-dropdown-item>
                            <el-dropdown-item command="设置">设置</el-dropdown-item>
                            <el-dropdown-item command="退出登录" @click="signOut">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                    
                    <!-- <mu-popover placement="bottom"  :open.sync="open" :trigger="trigger">
                        <mu-list>
                            <mu-list-item button>
                            <mu-list-item-title>个人中心</mu-list-item-title>
                            </mu-list-item>
                            <mu-list-item button>
                            <mu-list-item-title>设置</mu-list-item-title>
                            </mu-list-item>
                            <mu-list-item button  @click="signOut">
                            <mu-list-item-title>退出登录</mu-list-item-title>
                            </mu-list-item>
                        </mu-list>
                    </mu-popover> -->
                    <mu-button large color="#ea6f5a" style="margin-top:5px;" @click="writeArticle">
                        <mu-icon left value=""></mu-icon>
                        写文章
                    </mu-button>
                </mu-col>
            </mu-row>
        </mu-container>
        <div class="phone_menu" :class="{active:phone_menu}" :style="{height:phone_menu_height}">
            <mu-list>
                <mu-list-item button  @click="()=>{this.goHome();this.click_phone_menu()}">
                    <mu-list-item-action>
                        <i class="iconfont icon-shouye" style="font-size:28px;margin-left:-5px;"></i>
                    </mu-list-item-action>
                    <mu-list-item-title>首页</mu-list-item-title>
                </mu-list-item>
                <mu-list-item button  @click="()=>{this.click_phone_menu();this.downloadApp()}">
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
                <mu-list-item button @click="writeArticle">
                    <mu-list-item-action class="tac">
                        <i class="iconfont icon-bi"></i>
                    </mu-list-item-action>
                    <mu-list-item-title>写文章</mu-list-item-title>
                </mu-list-item>
                <mu-list-item button  @click="signIn"  v-if="!$store.state.user || !$store.state.user.user">
                    <mu-list-item-action class="tac">
                        <i class="iconfont icon-huodongxiangqu"></i>
                    </mu-list-item-action>
                    <mu-list-item-title >登录</mu-list-item-title>
                </mu-list-item>
                <div v-else>
                    <mu-list-item button @click="goUserHome">
                        <mu-list-item-action class="tac">
                            <i class="iconfont icon-addressbook"></i>
                        </mu-list-item-action>
                        <mu-list-item-title >个人中心</mu-list-item-title>
                    </mu-list-item>
                    <mu-list-item button >
                        <mu-list-item-action class="tac">
                            <i class="iconfont icon-addressbook"></i>
                        </mu-list-item-action>
                        <mu-list-item-title >消息中心</mu-list-item-title>
                    </mu-list-item>
                    <mu-list-item button >
                        <mu-list-item-action class="tac">
                            <i class="iconfont icon-setup"></i>
                        </mu-list-item-action>
                        <mu-list-item-title >设置</mu-list-item-title>
                    </mu-list-item>
                    <mu-list-item button  @click="signOut" >
                        <mu-list-item-action class="tac">
                            <i class="iconfont icon-tuichu"></i>
                        </mu-list-item-action>
                        <mu-list-item-title >退出登录</mu-list-item-title>
                    </mu-list-item>
                    
                </div>
            </mu-list>
        </div>
    </div>
</template>
<script>
import {mapState ,mapMutations} from "vuex"
export default {
    name:'myHeader',
    data(){
        return {
            phone_menu_height:"0px",
            phone_menu:false,
            search:'',
            open: false,
            trigger: null
        }
    },
    computed:{
        ...mapState(['user'])
    },
    mounted(){
        this.$nextTick(()=>{
            this.trigger = (this.$refs.button && this.$refs.button.$el)
        })
       
    },
    methods:{
        ...mapMutations(['setUser']),
        handleCommand(command) {
            if(command=='退出登录'){
                this.signOut();
            }else if(command=='个人中心'){
                var id = this.$store.state.user && this.$store.state.user.user.id
                window.location.href = `/userHome/${id}`
            }else if(command=='消息中心'){
                window.location.href = `/message`
            }
            
        },
        doSearch() {
            this.$message({
                message: '该功能暂未开放',
                showClose: true,
                type: 'warning'
            });
        },
        downloadApp() {
            this.$message({
                message: '该功能暂未开放',
                showClose: true,
                type: 'warning'
            });
        },
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
        //登录
        signIn() { 
            this.$router.push('/login')
        },
        signOut() {
            debugger
            this.$store.dispatch('logout')
            .then((res)=>{
                window.localStorage.clear();
                this.$router.push('/login')
            })
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
        writeArticle() {
            this.$router.push('/writeArticle')
        },
        goUserHome() {
            var id = this.$store.state.user && this.$store.state.user.user.id
            this.$router.push(`/userHome/${id}`)
        }
    }
}
</script>
<style lang="less" >
    .header{
        height:62px;
        position: fixed;
        top:0px;
        left:0px;
        right:0px;
        height:61px;
        z-index: 10000;
        border-bottom:1px solid #ddd;
        background-color:#fff;
        font-size: 16px;
        margin-bottom:61px;
        .header_container{
            background-color:#fff;
            
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
            position: fixed;
            left:0;
            right:0;
            top:60px;
            z-index: 10000;
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
