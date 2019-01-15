<template>
    <div class="login-container">
        <div class="logo" @click="goHome">KissPlan</div>
        <div class="login_box">
            <mu-tabs :value.sync="active" inverse color="secondary" text-color="rgba(0, 0, 0, .54)"  center>
                <mu-tab>登陆</mu-tab>
                <mu-tab>注册</mu-tab>
            </mu-tabs>
            <mu-tooltip :content="userMessage" placement="top" :open="userRepeat">
                <div class="login_input_box mt20">
                    <mu-icon class="toggle-icon iconfont icon" size="25" value=":icon-addressbook"></mu-icon>
                    <input class="input" id="userName" placeholder="请输入账号" v-model="validateForm.userName" @input="userNameInput" type="text" autocomplete="off">
                
                        <i class="iconfont icon-gantanhao passwordErr" :class="[userErr?'icon-duigoutianchong- colorG':'icon-gantanhao colorR']"></i>
                    
                </div>
            </mu-tooltip>
            <mu-tooltip content="不能为空" placement="top">
                <div class="login_input_box">
                    <mu-icon class="toggle-icon iconfont icon" size="25" value=":icon-lock"></mu-icon>
                    <input class="input"  id="password" placeholder="密码长度为6-12之间，只能是字母、数字和下划线" v-model="validateForm.password" @input="passwordInput" type="password" autocomplete="off">
                    <i class="iconfont icon-gantanhao passwordErr" :class="[passwordErr?'icon-duigoutianchong- colorG':'icon-gantanhao colorR']"></i>
                </div>
           </mu-tooltip>
            <mu-row gutter class="btn_box">
                <mu-col span="24">
                    <mu-button color="success" class="login_button" :disabled="(userErr && passwordErr)?false:true" @click="register">注册</mu-button>
                </mu-col>
            </mu-row >
        </div>
    </div>
</template>
<script>
import axios from 'axios'
var Qs = require('qs');
import { mapMutations ,mapState} from 'vuex'
export default {
    name:'login',
    data:function(){
        return {
            userRepeat:false,
            active:1,
            userErr:false,
            userMessage:'不能为空',
            passwordErr:false,
            validateForm:{
                userName:'',
                password:'',
            }
        }
    },
    computed:{
        ...mapState(['user'])
    },
    methods:{
        register() {

        },
        goHome() {
            this.$router.push('/')
        },
        userNameInput() {
            var userName = this.validateForm.userName;
            this.userErr = false;
            this.userRepeat = false;
            if(userName.trim()){
                this.userMessage = '用户名重复';
            }else{
                this.userMessage = '不能为空';
            }
        },
        passwordInput() {
            var password = this.validateForm.password; 
            var reg = /^[\w]{6,12}$/;
            if(new RegExp(reg).test(password)){
                this.passwordErr = true;
            }else{
                this.passwordErr = false;
            }
        }
    },
    watch:{
        active(newValue) {
            if(newValue==0){
                this.$router.push('/login')
            }else{
                this.$router.push('/register')
            }
        }
    }
}
</script>
<style lang="less" scoped>
    .login-container{
        .logo{
            position: fixed;
            top:5%;
            left:5%;
            line-height: 60px;
            height: 60px;
            font-size: 25px;
            cursor: pointer;
            color: #ea6f5a;
            font-weight: bold;
        }
        .login_box{
            background-color: #fff;
            padding:10px 30px 30px 30px;
            border-radius: 5px;
            box-shadow: 0px 3px 13px #666;
            width:350px;
            position: fixed;
            top:50%;
            left:50%;
            transform: translate(-50%,-50%);
            .login_title{
                text-align: center;
                font-weight: bold;
                padding:20px;
                color:#333;
                font-size: 20px;
            }
            .login_input_box{
                position: relative;
                
                .icon{
                    position: absolute;
                    top:2px;
                    left:8px;
                }
                .passwordErr{
                    position: absolute;
                    right:15px;
                    top:8px;
                   
                }
                .input{
                    width: 100%;
                    height:40px;
                    padding:5px 5px 5px 35px;
                    margin-bottom:15px;
                    outline: none;
                    border-radius: 5px;
                    border:1px solid #ddd;
                    padding-right:40px;
                    box-sizing: border-box;
                }
            }
            .btn_box{
                margin-left: 0px;
                .login_button{
                    width:100%;
                }
            }
            
        }
        @media (max-width: 768px){
            .login_box{
                width:100vw;
                border-radius: 0px;
                position: absolute;
                top:0;
                left:0;
                right:0;
                bottom:0;
                transform: translate(0%,0%);
                box-shadow: none;
            }
        }
    }
</style>

