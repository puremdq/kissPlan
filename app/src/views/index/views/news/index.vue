<template>
    <div class="news" >
        <div class="box mt20" ref="box">
            <mu-row gutter>
                <mu-col span="24"  sm="24" md="8" style="margin:0 auto;">
                    <h1 class="title">{{news.title}}</h1>
                    <newsAutor :data="news"></newsAutor>
                    <textPic :data="news.content"></textPic>
                    <zanShang class="mt20"></zanShang>
                    <mu-row gutter >
                        <mu-col span="12" sm="12" md="6">
                            <like class="phoneTAC mt20" :data="news" @clickLike="clickLike"></like>
                        </mu-col>
                        <mu-col span="12" sm="12" md="6">
                            <zhuanZai class="phoneTAC mt20 tar" :data="news"></zhuanZai>
                        </mu-col>
                    </mu-row>
                    <pingLun style="margin-top:40px;"></pingLun>
                    <comment style="padding:40px 0;" :data="comment"></comment>
                </mu-col>
            </mu-row>
        </div>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions } = createNamespacedHelpers('news');
import newsAutor from "./components/newsAutor.vue"
import textPic from "./components/textPic.vue"
import zanShang from "./components/zanShang.vue"
import like from "./components/like.vue"
import zhuanZai from "@/components/zhuanZai/zhuanZai.vue"
import pingLun from "@/components/pingLun/index.vue"
import comment from "@/components/comment/index.vue"
export default {
    name:'news',
    asyncData({store}){
        return Promise.all([
            store.dispatch('news/getNews',{
                id:store.state.route.params.id
            }),
            store.dispatch('news/getComment',{
                id:store.state.route.params.id,
                pageNo:'1'
            }),
        ]);
    },
    data(){
        return {
        }
    },
    computed:{
        ...mapState(['news','comment'])
    },
    components:{
        newsAutor,
        textPic,
        zanShang,
        like,
        zhuanZai,
        pingLun,
        comment
    },
    methods:{
        //点赞
        clickLike() {

        }
    }
}
</script>
<style lang="less" scoped>
    .news{
        .box{
            .title{
                word-break: break-word!important;
                word-break: break-all;
                margin: 20px 0 0;
                font-family: -apple-system,SF UI Display,Arial,PingFang SC,Hiragino Sans GB,Microsoft YaHei,WenQuanYi Micro Hei,sans-serif;
                font-size: 34px;
                font-weight: 700;
                line-height: 1.3;
            }
           
        }
    }
</style>
