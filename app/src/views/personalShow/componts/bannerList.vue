<template>
    <mu-paper :z-depth="1" class="babberList_container" >
        <div @click="dropDownisPhone">
            <mu-icon class="toggle-icon iconfont pullRight" size="20" value=":icon-xiangxiajiantou"></mu-icon>
            <div class="babberList_title">展示列表</div> 
        </div>
        <transition 
            v-on:before-enter="beforeEnter"
            v-on:enter="baseEnter"
            v-on:leave="leave"
            v-on:beforeLeave="beforeLeave"
            v-bind:css="false"
        > 
        <ul class="babberList_items" v-if="show"> 
            
            <li v-for="(item)  in bannnerList" :key="item.id">
                <div @click="open==item.id?open=-1:open=item.id">
                    <mu-icon class="toggle-icon iconfont pullRight" size="20" :value="open==item.id?':icon-xiangshangjiantou':':icon-xiangxiajiantou'"></mu-icon>
                    <div class="item_title">{{item.name}}</div> 
                </div>
                <transition 
                    
                    v-on:before-enter="beforeEnter"
                    v-on:enter="enter"
                    v-on:leave="leave"
                    v-on:beforeLeave="beforeLeave"
                    v-bind:css="false"
                >
                    <ul class="babberList_items_list" v-if="open==item.id">
                        <li  @click="dropDownisPhone" class="babberList_items_list_item" v-for="li in item.list" :key="li.id">{{li.name}}</li>
                    </ul>
                </transition>
            </li>
        </ul>
        </transition>
    </mu-paper>
</template>
<script>
   import { setIsPhone } from '@/util/util'
   import Vue from 'vue'
    export default {
        name:'bannerList',
        data(){
            return {
                open:'1',
                show:true
            }
        },
        beforeMount(){
            setIsPhone(Vue)
            if(this.isPhone){
                this.show = false
            }
        },
        props:['bannnerList'],
        methods:{
            beforeEnter: function (el) {
                console.log(this.isPhone)
                el.style.height = 0+'px';
                el.style.overflow = 'hidden'
                },
            enter: function (el, done) {
                var maxHeight = el.childElementCount*35;
                Velocity(el, { height: maxHeight+'px' ,overflow:'hidden'}, { duration: 300 }, { complete: done })
                //Velocity(el, {  })
            },
            baseEnter:function(el, done){
                var maxHeight = window.innerHeight-70;
                Velocity(el, { height: maxHeight+'px' ,overflow:'hidden'}, { duration: 300 }, { complete: done })
                
            },
            beforeLeave(el){
                el.style.overflow = 'hidden'
            },
            leave: function (el, done) {
                Velocity(el, { height: '0px',overflow:'hidden' }, { duration: 300 },{ complete: done })
                
            },
            dropDownisPhone(){
                if(this.isPhone){
                    this.show = !this.show;
                }
            }
           
        }
    }
</script>
<style lang="less" scoped>
    .phonePosition{
        position: fixed;
        top:70px;
    }
    .babberList_container{
        padding:16px;
        .babberList_title{
            font-size: 16px;
            font-weight: 700;
            line-height:30px;
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
        }
        .babberList_items{
            >li{
                line-height:35px;
                >div{
                    padding:0 15px;
                    &:hover{
                        background-color: #ddd;
                    }
                    .item_title{
                        
                        overflow: hidden;
                        text-overflow:ellipsis;
                        white-space: nowrap;
                    }
                }
                .babberList_items_list{
                    li{
                        line-height:35px;
                        overflow: hidden;
                        text-overflow:ellipsis;
                        white-space: nowrap;
                        padding-left:25px;
                    }
                    li:hover{
                        background-color: #ddd;
                    }
                }
            }
            
        }
    }
</style>
