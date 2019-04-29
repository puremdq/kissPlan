<template>
    <div class="communication">
        <header class="title">全部聊天</header>
        <mu-list textline="three-line">
            
            <mu-list-item avatar  button v-for="(item,idx) in _messageList" :key="idx" @click="go()">
                <mu-list-item-action>
                    <mu-avatar  style="margin-top:5px;">
                        <img :src="item.toAvatars">
                    </mu-avatar>
                </mu-list-item-action>
                <mu-list-item-content>
                    <mu-list-item-title>
                        {{item.toUsername}}
                        <mu-menu cover style="float:right">
                            <span><span style="font-size:12px;margin-right:5px;color:#999;">{{item.updateDate}}</span> <i class="iconfont icon-xiangxiajiantou"></i></span>
                            <mu-list slot="content">
                                <mu-list-item button>
                                <mu-list-item-title>该功能未完善</mu-list-item-title>
                                </mu-list-item>
                            </mu-list>
                        </mu-menu>
                    </mu-list-item-title>
                    <mu-list-item-sub-title>
                        {{item.content}}
                    </mu-list-item-sub-title>
                </mu-list-item-content>
            </mu-list-item>
         </mu-list>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('message');
export default {
    name:'communication',
    data(){
        return {}
    },
    computed:{
        ...mapState(['_messageList'])
    },
    mounted(){
        this._getMessageList();
    },
    methods:{
        ...mapActions(['_getMessageList']),

    }
}
</script>
<style lang="less">
    .communication{
        .title{
            font-size: 14px;
            font-weight: bold;
            padding:10px 5px;
            border-bottom:1px solid #ddd;
        }
    }
</style>
