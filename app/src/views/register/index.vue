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
                    <input  class="input" id="username" placeholder="请输入账号" v-model="validateForm.username" @input="usernameInput" type="text" autocomplete="off">
                
                        <i class="iconfont icon-gantanhao passwordErr" :class="[userErr?'icon-duigoutianchong- colorG':'icon-gantanhao colorR']"></i>
                    
                </div>
            </mu-tooltip>
            <mu-tooltip content="不能为空" placement="top">
                <div class="login_input_box">
                    <mu-icon class="toggle-icon iconfont icon" size="25" value=":icon-lock"></mu-icon>
                    <input autocomplete="new-password" class="input"  id="password" placeholder="密码长度为6-12之间，只能是字母、数字和下划线" v-model="validateForm.password" @input="passwordInput" type="password">
                    <i class="iconfont icon-gantanhao passwordErr" :class="[passwordErr?'icon-duigoutianchong- colorG':'icon-gantanhao colorR']"></i>
                </div>
           </mu-tooltip>
           <mu-tooltip content="不能为空" placement="top">
                <div class="login_input_box">
                    <mu-icon class="toggle-icon iconfont icon" size="25" value=":icon-lock"></mu-icon>
                    <input class="input"  id="email" placeholder="请输入邮箱（asc@qq.com）" v-model="validateForm.email" @input="emailInput" type="email" autocomplete="off">
                    <i class="iconfont icon-gantanhao passwordErr" :class="[emailErr?'icon-duigoutianchong- colorG':'icon-gantanhao colorR']"></i>
                </div>
           </mu-tooltip>
            <mu-row gutter class="btn_box">
                <mu-col span="24">
                    <mu-button color="success" class="login_button" :disabled="(userErr && passwordErr && emailErr)?false:true" @click="registerC">注册</mu-button>
                </mu-col>
            </mu-row >
        </div>
    </div>
</template>
<script>
import axios from 'axios'
var Qs = require('qs');
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('register');
export default {
    name:'login',
    data:function(){
        return {
            userRepeat:false,
            active:1,
            userMessage:'不能为空',

            userErr:false,
            passwordErr:false,
            emailErr:false,
            validateForm:{
                username:'',
                password:'',
                email:'',
            }
        }
    },
    computed:{
        ...mapState(['user'])
    },
    methods:{
        ...mapActions(['userEmilRepeat','_register']),
        registerC() {
            if(this.userErr && this.passwordErr && this.emailErr){
                this._register(this.validateForm)
                .then((res)=>{
                    if(res && res.status=='200'){
                        this.$message({
                            message: '注册成功',
                            showClose: true,
                            type: 'success'
                        });
                        setTimeout(()=>{
                            this.$router.push('/login')
                        },1000)
                    }
                })
            }else{
                this.$message({
                    message: '格式有误，或不能为空',
                    showClose: true,
                    type: 'warning'
                });
            }
        },
        goHome() {
            this.$router.push('/')
        },
        usernameInput() {
            var username = this.validateForm.username;
            if(username.trim()){
                this.userEmilRepeat({
                    username:username,
                    email:this.validateForm.email
                })
                .then((res)=>{
                    if(res && res.status=='200'){
                        this.userErr = true;
                        this.userRepeat = true;
                        this.userMessage = '不能为空';
                    }
                },(err)=>{
                    this.userMessage = err.msg;
                })
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
        },
        emailInput() {
            var email = this.validateForm.email; 
            var reg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
            if(new RegExp(reg).test(email)){
                
                this.userEmilRepeat({
                    username:this.validateForm.username,
                    email:this.validateForm.email
                })
                .then((res)=>{
                    if(res && res.status=='200'){
                        this.emailErr = true;
                    }
                },(err)=>{
                    this.emailErr = false;
                })
            }else{
                this.emailErr = false;
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

