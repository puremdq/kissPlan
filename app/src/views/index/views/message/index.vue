<template>
    <div class="message box mt20">
        <mu-row gutter>
            <mu-col span="4" class="listMenu" >
                <mu-list>
                    <mu-list-item button :class="{active:active=='聊天'}">
                        <mu-list-item-title ><i class="iconfont icon-xiaoxizhongxin mr10 baseColor"></i>聊天</mu-list-item-title>
                    </mu-list-item>
                   
                </mu-list>
            </mu-col>
            <mu-col span="8">
                <router-view ></router-view>
            </mu-col>
        </mu-row>   
    </div>
</template>
<script>
export default {
    name:'message',
    data(){
        return {
            active:'聊天',
        }
    }
}
</script>
<style lang="less">
    .message{
        .listMenu{
            .active{
                background-color: #ddd;
            }
        }
    }
</style>