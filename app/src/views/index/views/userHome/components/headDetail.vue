<template>
    <div class="guanZhu p20">
        <dl>
            <dt>关注</dt>
            <dd>{{data.followNum | MoneyFormat(true)}}</dd>
        </dl>
        <dl>
            <dt>粉丝</dt>
            <dd>{{data.fansNum | MoneyFormat(true)}}</dd>
        </dl>
        <dl>
            <dt>文章</dt>
            <dd>{{data.articleNum | MoneyFormat(true)}}</dd>
        </dl>
        <dl>
            <dt>字数</dt>
            <dd>{{data.wordsNum | MoneyFormat(true)}}</dd>
        </dl>
        <dl>
            <dt>收获喜欢</dt>
            <dd>{{data.beLikeNum | MoneyFormat(true)}}</dd>
        </dl>
    </div>
</template>
<script>
export default {
    name:'headDetail',
    data(){
        return {

        }
    },
    props:['data']
}
</script>
<style lang="less">
    .guanZhu{
        display: flex;
        dl{
            flex-grow:1;
            text-align: center;
        }
    }
</style>
