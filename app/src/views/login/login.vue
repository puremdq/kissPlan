<template>
    <div class="login-container">
        <mu-row >
            <mu-col span="12" sm="12" md="6">
                
            </mu-col>
            <mu-col class="login_box" span="12" sm="12" md="6">
                <div class="from">
                    <div class="login_title">登陆</div>
                    <div class="login_input_box">
                        <mu-icon class="toggle-icon iconfont " size="25" value=":icon-addressbook"></mu-icon>
                        <input class="input" id="userName" v-model="userName" type="text">
                    </div>
                    <div class="login_input_box">
                        <mu-icon class="toggle-icon iconfont " size="25" value=":icon-lock"></mu-icon>
                        <input class="input"  id="password" v-model="password" type="password">
                    </div>
                    <mu-row gutter class="btn_box">
                        <mu-col span="12" sm="12" md="6">
                            <mu-button color="success" class="login_button" @click="login">登陆</mu-button>
                        </mu-col>
                        <mu-col span="12" sm="12" md="6">
                            <mu-button color="info" class="login_button" @click="register">注册</mu-button>
                        </mu-col>
                    </mu-row >
                </div>
            </mu-col>
        </mu-row>
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
            userName:'',
            password:''
        }
    },
    computed:{
        ...mapState(['user'])
    },
    methods:{
        ...mapMutations(['setUser']),
        login(){
            var _this = this;
            axios({
                method: 'post',
                url: '/signin',
                data: Qs.stringify({
                    name: _this.userName,
                    password: _this.password
                })
            })
            .then((res)=>{
                console.log(res);
                var data = res.data;
                
                if(data.code==='0'){
                    this.setUser(data.result);
                    window.localStorage.setItem('user',JSON.stringify(data.result))
                    _this.$router.push('/personalShow')
                }else{
                    alert('err')
                }
            })
        },
        register(){
            var _this = this;
            axios({
                method: 'post',
                url: '/signup',
                data: Qs.stringify({
                    name: _this.userName,
                    password: _this.password
                })
            })
            .then((res)=>{
                var data = res.data;
                
                if(data.code=='0'){
                    this.setUser(data.result);
                    window.localStorage.setItem('user',JSON.stringify(data.result))
                    _this.$router.push('/personalShow')
                }else{
                    alert('err')
                }
            })
        }
    }
}
</script>
<style lang="less" scoped>
    .login-container{
        background-image: url(../../assets/images/bg.jpg);
        background-repeat:no-repeat;
        background-size: 100% 100%;
        .login_box{
            position: relative;
            height:100vh;
            .from{
                background-color: #fff;
                padding:10px 30px 30px 30px;
                border-radius: 5px;
                box-shadow: 0px 3px 13px #666;
                width:50%;
                position: absolute;
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
                    max-width:300px;
                    padding:5px 5px 5px 35px;
                    margin-bottom:15px;
                    outline: none;
                    border-radius: 5px;
                    border:1px solid #ddd;
                }
                .btn_box{
                    max-width:300px;
                    margin-left: 0px;
                    .login_button{
                        width:100%;
                    }
                }
                
            }
        }
       
    }
</style>

