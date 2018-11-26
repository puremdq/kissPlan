<template>
    <div class="header">
        <mu-row class="header_container">
            <mu-col span="12" sm="12" md="6" class="header_left">
                <mu-row>
                    <mu-col span="10" class="header_left_welcome">
                        <mu-button  icon  @click="goMyConfig">
                            <mu-icon size="36" value=":icon-mine_fill" class="iconfont"></mu-icon>
                        </mu-button>
                    
                        <div class="welcome">
                            {{user?'昵称：'+user.name:''}}
                        </div>
                    </mu-col>
                    <mu-col span="2"  class="header_left_menu">
                        <mu-button class="margin" icon @click="clickphone_menu">
                            <mu-icon value=":icon-manage_fill" size="32" class="iconfont"></mu-icon>
                        </mu-button>
                    </mu-col>
                </mu-row>
                <transition
                    v-on:before-enter="beforeEnter"
                    v-on:enter="enter"
                    v-on:leave="leave"
                    v-on:beforeLeave="beforeLeave"
                    v-bind:css="false"
                >
                    <div class="phone_menu" v-if="phone_menu">
                        <mu-list>
                            <mu-list-item class="phone_menu_list" button :ripple="true">
                                <mu-list-item-title>
                                    <header>
                                        <slot name="header" ></slot>
                                        
                                    </header>
                                </mu-list-item-title>
                            
                            </mu-list-item>
                            <mu-list-item class="phone_menu_list"  button :ripple="true">
                                <mu-list-item-title>个人中心</mu-list-item-title>
                            
                            </mu-list-item>
                            <mu-list-item class="phone_menu_list"  button :ripple="true">
                                <mu-list-item-title>未读消息<span class="corlorRed">（{{unreadMessage.length}}）</span></mu-list-item-title>
                            
                            </mu-list-item>
                            <mu-list-item  class="phone_menu_list" button :ripple="true">
                                <mu-list-item-title>设置</mu-list-item-title>
                            
                            </mu-list-item>
                            <mu-list-item class="phone_menu_list"  button :ripple="true">
                                <mu-list-item-title>退出</mu-list-item-title>
                            
                            </mu-list-item>
                        </mu-list>
                    </div>
                </transition>  
            </mu-col>
            <mu-col span="12" sm="12" md="6" class="header_right">
                <footer>
                    <slot name="footer"></slot>
                    
                </footer>
                <mu-button class="margin"  icon  @click="goMyConfig">
                    <mu-icon size="30" value=":icon-mine_fill" class="iconfont"></mu-icon>
                </mu-button>
                <mu-badge  class="margin header_right_message" badge-class="badge_class" :content="unreadMessage.length+''" circle color="secondary" >
                    <mu-button icon @click="open = !open">
                        <mu-icon icon value=":icon-mail" class="iconfont" size="30"></mu-icon>
                    </mu-button>
                </mu-badge>  
                <mu-button class="margin" icon >
                    <mu-icon value=":icon-setup"  class="iconfont" size="30"></mu-icon>
                </mu-button>
                <mu-button color="error" small @click="signout">退出</mu-button>
            </mu-col>
        </mu-row>
        
        <mu-drawer class="message_noRead" :open.sync="open" :docked="false" :right="true" >
            <div class="message_title_box">
                <span class="message_title">消息中心</span>
                <span class="message_close">
                    <mu-icon class="iconfont" @click="open=!open"   value=":icon-guanbi"></mu-icon>
                </span>
            </div>
            <ul id="message_noRead_box" class="message_noRead_box">
                <li class="message_noRead_item clearfix" v-for="item in unreadMessage" :key="item.id">
                    <p class="message_noRead_title">
                        <mu-icon icon value=":icon-remind" color="red" class="iconfont" size="24"></mu-icon>
                        <span>{{item.title}}</span>                     
                    </p>
                    <p class="message_noRead_context">
                        {{item.context.slice(0,25)}}...
                    </p>
                    <p class="message_noRead_date">
                        {{new Date(item.createDate).getChinaTime()}}
                    </p>
                </li>
            </ul>
            <div class="message_noRead_footer">
                <a href="#">进入消息中心</a>
            </div>
        </mu-drawer>
    </div>
</template>
<script>
import {mapState ,mapMutations} from "vuex"
import axios from "axios"
export default {
    name:'myHeader',
    data(){
        return {
            open:false,
            phone_menu:false,
            
        }
    },
    computed:{
        ...mapState(['unreadMessage','user'])
    },
    beforeMount(){
        var strUser = window.localStorage.getItem('user');
        if(strUser){
            this.setUser(JSON.parse(strUser));
        }else{
            this.$router.push('/login')
        }
    },
    mounted(){
        var message_noRead_box = document.getElementById('message_noRead_box');
        message_noRead_box.style.height=(window.innerHeight-120)+'px';
        
    },
    methods:{
        ...mapMutations(['setUser']),
        goMyConfig(){
            alert(1);
        },
        goToMessage(){
            alert(1);
        },
        clickphone_menu(){
            this.phone_menu = !this.phone_menu;
        },
        beforeEnter: function (el) {
            el.style.height = 0+'px';
            el.style.overflow = 'hidden'
        },
        enter: function (el, done) {
            var maxHeight = window.innerHeight-60;
            Velocity(el, { height: maxHeight+'px' ,overflow:'hidden'}, { duration: 300 }, { complete: done })
            
        },
        beforeLeave(el){
            el.style.overflow = 'hidden'
        },
        leave: function (el, done) {
            Velocity(el, { height: '0px',overflow:'hidden' }, { duration: 300 },{ complete: done })
            
        },
        signout(){
            var _this = this;
            axios({
                method: 'get',
                url: '/signout',
                
            })
            .then((res)=>{
                if(res.data.code=='0'){
                    this.setUser(null);
                    window.localStorage.removeItem('user');
                    _this.$router.push('/login')
                }
            })
        }
    }
}
</script>
<style lang="less" >
    .header{
        .header_container{
            border:1px solid #ddd;
            border-top:none;
            padding:0 16px;
            background-color:#fff;
            position: fixed;
            top:0px;
            left:0px;
            right:0px;
            height:60px;
            z-index: 2;
            box-shadow: 0px 3px 3px #ddd;
            .header_left{
                .phone_menu{
                    background-color: #fff; 
                    position: fixed;
                    top:60px;
                    left:0px;
                    right:0px; 
                    .phone_menu_list{
                        border-bottom:1px solid #eee;
                    }
                    .mu-item{
                        min-height: 48px;
                        .mu-item-title{
                            height:48px;
                            line-height: 48px;
                        }
                    }  
                } 
                .header_left_welcome{
                    height:60px;
                    display: flex;
                    justify-content:flex-start;
                    align-items:center ;
                    .welcome{
                        margin-left:10px;           
                    }
                }
                .header_left_menu{
                    display: flex;
                    justify-content:flex-end;
                    align-items:center ;
                    visibility: visible;
                }
            }
            .header_right{
                display:none;
                justify-content:flex-end;
                align-items:center;
                .margin{
                    margin-top:10px;
                    margin-left:10px;
                }
                .header_right_message{
                    .badge_class{
                        position: absolute;
                        top: -5px;
                        right: -5px;
                    }
                }
            }
            
        
        }
        @media screen and (min-width: 768px){
            .header_container{
                .header_right{
                    display:flex;
                }
                .header_left_menu{
                    visibility: hidden !important;
                }
            }
            .phone_menu{
                display:none;
            }  
        }
        .message_noRead{
            
            .message_title_box{
                padding:20px 20px 20px 20px;
                box-shadow: 0 0 5px #ddd;
                .message_title{
                    font-size: 18px;
                    font-weight: bold;    
                }
                .message_close{
                    float:right;
                    transition:  .3s linear;
                    cursor: pointer;
                }
                .message_close:hover{
                    transform:rotate(90deg);
                }
            }
            .message_noRead_box{
                overflow: auto;
                .message_noRead_item{
                    border-bottom:1px solid #ddd;
                    padding:10px;
                    cursor: pointer;
                    .message_noRead_title{
                    
                    }
                    .message_noRead_context{
                        font-size: 12px;
                        padding-left:25px;
                    }
                    .message_noRead_date{
                        font-size: 12px;
                        float:right;                    
                    }
                }
                .message_noRead_item:hover{
                    background-color: #eee;
                }
            }
            .message_noRead_footer{
                text-align: center;
                padding:10px 0;
            }
        }
    }
    
</style>
