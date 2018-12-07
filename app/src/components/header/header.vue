<template>
    <div class="header">
        <mu-row class="header_container">
            <mu-col span="12" sm="12" md="1" class="header_left">
                <mu-row>
                    <span class="logo">KissPlan</span>
                    <div class="container"></div>
                </mu-row>
                
            </mu-col>
            <mu-col span="12" sm="12" md="6" class="header_right">
               
            </mu-col>
        </mu-row>
       
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
        // var strUser = window.localStorage.getItem('user');
        // if(strUser){
        //     this.setUser(JSON.parse(strUser));
        // }else{
        //     this.$router.push('/login')
        // }
    },
    mounted(){
        var message_noRead_box = document.getElementById('message_noRead_box');
        message_noRead_box.style.height=(window.innerHeight-120)+'px';
        
    },
    methods:{
        ...mapMutations(['setUser']),
       
        
    }
}
</script>
<style lang="less" >
    .header{
        height:62px;
        border-bottom:1px solid #ddd;
        background-color:#fff;
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
                
            }
        
        }
        
    }
    
</style>
