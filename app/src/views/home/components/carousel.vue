<template>
    <div class="carousel">
        <mu-carousel>
            <mu-carousel-item v-for="(item,idx) in imgs" :key="idx">
                <img :src="item">
            </mu-carousel-item>
        </mu-carousel>
    </div>
</template>
<script>
import banner1 from '@/assets/images/banner1.jpg';
import banner2 from '@/assets/images/banner2.jpg';
import banner3 from '@/assets/images/banner3.jpg';
export default {
    name:'carousel',
    data:function(){
        return {
            imgs:[
                banner1,banner2,banner3
            ]
        }
    }
}
</script>
<style lang="less">
.carousel{
    .mu-carousel{
        height:270px;
    }
    img{
        width: 100%;
        background-color: hsla(0,0%,71%,.2);
    }
}
</style>
