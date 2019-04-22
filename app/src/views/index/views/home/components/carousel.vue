<template>
    <div class="carousel">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide" v-for="(item,idx) in imgs" :key="idx">
                    <a :href="item.url">
                        <img :src="item.img" ref="imgs">
                    </a>
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
const Qs = require('qs');
import Swiper  from 'swiper'
import "swiper/dist/css/swiper.css"

export default {
    name:'carousel',
    asyncData({store}){
        return store.dispatch('home/getCarousel')
    },
    data:function(){
        return {
            // imgs:[],
            width:'0',
            
        }
    },
    computed:{
        imgs() {
            return this.$store.state.home.imgs
        }
    },
    mounted(){
       this.renderSwper()
       this.getIsPhone()
    //    this.getImages()
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
        },
        getImages() {
            this.$axios.instance({
                url:'/slideshow',
                method:'get',
                hasLoading:false,
            })
            .then((res)=>{
                if(res.status==200){
                    if(res.data && res.data.slideshow){
                        this.imgs = res.data.slideshow;
                    }else{
                        this.imgs = [];
                    }
                }
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
