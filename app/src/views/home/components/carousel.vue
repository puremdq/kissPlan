<template>
    <div class="carousel">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide" v-for="(item,idx) in imgs" :key="idx">
                    <img :src="item" ref="imgs">
                </div>
            </div>
            <!-- 如果需要分页器 -->
            <div class="swiper-pagination"></div>
            
            <!-- 如果需要导航按钮 -->
            <div v-show="width>768" class="swiper-button-prev" style="outline: none;"></div>
            <div v-show="width>768" class="swiper-button-next" style="outline: none;"></div>
            
           
        </div>
    </div>
</template>
<script>
import Swiper  from 'swiper'
import "swiper/dist/css/swiper.css"
import banner1 from '@/assets/images/banner1.jpg';
import banner2 from '@/assets/images/banner2.jpg';
import banner3 from '@/assets/images/banner3.jpg';
export default {
    name:'carousel',
    data:function(){
        return {
            imgs:[
                banner1,banner2,banner3
            ],
            width:'0',
            
        }
    },
    mounted(){
       this.renderSwper()
       this.getIsPhone()
    },
    methods:{
        getIsPhone() {
            this.width = document.body.clientWidth;
            window.onresize = ()=>{
                this.width = document.body.clientWidth;
            }
        },
        renderSwper() {
            var mySwiper = new Swiper ('.swiper-container', {
                direction: 'horizontal', // 垂直切换选项
                loop: true, // 循环模式选项
                autoplay:true,
                // 如果需要分页器
                pagination: {
                    el: '.swiper-pagination',
                },
                
                // 如果需要前进后退按钮
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
                
            })     
        }
    }
}
</script>
<style lang="less">
.carousel{
    .mu-carousel{
        // height:270px;
    }
    img{
        width: 100%;
        background-color: hsla(0,0%,71%,.2);
    }
}
</style>
