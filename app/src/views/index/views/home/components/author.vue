<template>
    <div class="author">
        <mu-list>
            <mu-sub-header>作者</mu-sub-header>
            <mu-list-item avatar v-for="(item,idx) in 5" :key="idx">
                <mu-list-item-action avatar>
                    <mu-avatar>
                        <img :src="head" alt="">
                    </mu-avatar>
                </mu-list-item-action>
                <mu-list-item-content>
                    <mu-list-item-title>Mike Li</mu-list-item-title>
                </mu-list-item-content>
                <mu-list-item-action>
                    <div><i class="iconfont icon-shoucang"></i><span>关注</span></div>
                </mu-list-item-action>
            </mu-list-item>
        </mu-list>
        <div class="look-all p10">查看全部 ></div>
    </div>
</template>
<script>
var head = require('@/assets/images/head.jpg')
export default {
    name:'author',
    data(){
        return {
            head
        }
    }
}
</script>
<style lang="less" scoped>
    .author{
        .look-all{
            text-align: center;
            border:1px solid #ddd;
            cursor: pointer;
            border-radius:5px;
            &:hover{
                box-shadow: 0 2px 1px #aaa;
            }
        }
    }
</style>

