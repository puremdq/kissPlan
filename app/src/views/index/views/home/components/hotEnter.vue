<template>
    <div class="hotEnter">
        <div class="hot-item p10 mb10" v-for="(item) in hot" :key="item.label" :style="{color:item.color,backgroundColor:item.bgColor}" @click="goToRoute(item)">{{item.label}}</div>
    </div>
</template>
<script>
export default {
    name:'hotEnter',
    data(){
        return {
            hot:[
                {
                    path:'hotToday',
                    label:'今日热门 >',
                    bgColor:'#febb50',
                    color:'#fff',
                },
                {
                    path:'sevenDayHot',
                    label:'7日热门 >',
                    bgColor:'#f69581',
                    color:'#fff',
                },
                {
                    path:'ThirtyDayHot',
                    label:'30日热门 >',
                    bgColor:'#f4e0bd',
                    color:'#bf913d',
                },
                {
                    path:'preferredSerialization',
                    label:'优选连载 >',
                    bgColor:'#c1e4de',
                    color:'#548f88',
                },
                {
                    path:'topOneHundred',
                    label:'top100 >',
                    bgColor:'#b7d6ec',
                    color:'#4e8aab',
                },
            ]
        }
    },
    methods:{
        goToRoute(item) {
            this.$router.push(item.path);
        }
    }
}
</script>
<style lang="less" scoped>
    .hotEnter{
        .hot-item{
            height:45px; 
            cursor: pointer;
            border-radius:5px;
            &:hover{
                box-shadow:0 2px 1px #999;
            }
        }  
    }
</style>

