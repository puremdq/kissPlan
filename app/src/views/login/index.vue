<template>
    <div class="login-container">
        <div class="logo" @click="goHome">KissPlan</div>
        <div class="login_box">
            <mu-tabs :value.sync="active" inverse color="secondary" text-color="rgba(0, 0, 0, .54)"  center>
                <mu-tab>登陆</mu-tab>
                <mu-tab>注册</mu-tab>
            </mu-tabs>
            <div class="login_input_box mt20">
                <mu-icon class="toggle-icon iconfont " size="25" value=":icon-addressbook"></mu-icon>
                <input class="input" id="userName" placeholder="请输入账号" v-model="validateForm.userName" type="text"  autocomplete="off">
            </div>
            <div class="login_input_box">
                <mu-icon class="toggle-icon iconfont " size="25" value=":icon-lock"></mu-icon>
                <input class="input"  id="password" placeholder="请输入密码" v-model="validateForm.password" type="password"  autocomplete="off">
            </div>
            <div class="mb10">
                <mu-checkbox label="记住密码" v-model="validateForm.isAgree"></mu-checkbox>
            </div>
            <mu-row gutter class="btn_box">
                <mu-col span="24">
                    <mu-button color="success" class="login_button" @click="login">登陆</mu-button>
                </mu-col>
            </mu-row >
        </div>
    </div>
</template>
<script>

var Qs = require('qs');
import { mapMutations ,mapState} from 'vuex'
export default {
    name:'login',
    data:function(){
        return {
            active:0,
            validateForm:{
                userName:'pure',
                password:'luckluck',
                isAgree:false,
            }
        }
    },
    computed:{
        ...mapState(['user'])
    },
    methods:{
        ...mapMutations(['setUser']),
        login() {
            if(!this.validateForm.userName || !this.validateForm.password){
                this.$message({
                    message: '用户名密码不能为空',
                    showClose: true,
                    type: 'warning'
                });
                return false;
            }
            var data = {
                username:this.validateForm.userName,
                password:this.validateForm.password,
                is_need_token:1
            }
            this.$axios.instance({
                url:'/login',
                method:'post',
                data:Qs.stringify(data)
            })
            .then((res) => {
                if(res.status == 200){
                    this.$message({
                        message: '登陆成功',
                        showClose: true,
                        type: 'success'
                    });
                    this.setUser(res.data)
                    window.localStorage.setItem('user',JSON.stringify(res.data))
                    setTimeout(()=>{
                        this.$router.push('/index')
                        // location.href='/index'
                    },1300)
                }
            })
        },
        goHome() {
            this.$router.push('/index')
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
                .iconfont{
                    position: absolute;
                    top:2px;
                    left:8px;
                }
            }
            .input{
                width: 100%;
                height:40px;
                padding:5px 5px 5px 35px;
                margin-bottom:15px;
                outline: none;
                border-radius: 5px;
                border:1px solid #ddd;
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

