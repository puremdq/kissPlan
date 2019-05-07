<template>
    <div class="news" >
        <div class="box mt20" ref="box">
            <mu-row gutter>
                <mu-col span="24"  sm="24" md="10" style="margin:0 auto;">
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
                    <pingLun ref="pingLun" style="margin-top:40px;" :hide_cancel="true" @ok="getContext" v-model="value"></pingLun>
                    <comment style="padding:40px 0;" ref="comment" :data="comment" :pageNo="pageNo" @sortType="getSortType" @pageChange="pageChange" @comment="commentText"></comment>
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
        var id = store.state.route.params.id;
        if(!id){
            id= store.state.route.from.params.id;
        }
        return Promise.all([
            store.dispatch('news/getNews',{
                id:id
            }),
            
            store.dispatch('news/getComment',{
                id:id,
                pageNo:1
            }),
        ]);
    },
    data(){
        return {
            value:'',
            pageNo:1,
            sortType:'asc',
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
        ...mapActions(['articleLike','_articleReply','getComment','_articleLike']),
        //评论
        commentText(data) {
            this.$store.dispatch('news/_articleReply',{
                articleId:this.$route.params.id,
                content:data.content,
                pid:data.pid
           })
           .then((res)=>{
                if(res && res.status=='200'){
                    this.$refs['comment'].cancel();
                    this.$message({
                        message: '评论成功',
                        showClose: true,
                        type: 'success'
                    });
                    this.$store.dispatch('news/getComment',{
                        id:this.$route.params.id,
                        pageNo:this.pageNo,
                        sortType:this.sortType
                    })
                }
           })
        },
        pageChange(pageNo) {
            this.pageNo = Number(pageNo);
            this.getComment({
                id:this.$route.params.id,
                pageNo:this.pageNo,
                sortType:this.sortType
            })
        },
        getSortType(sortType) {
            this.sortType = sortType;
            this.pageNo =1;
            this.getComment({
                id:this.$route.params.id,
                pageNo:this.pageNo,
                sortType:sortType
            })
        },
        //点赞
        clickLike() {
            this._articleLike({
                id:this.$route.params.id,
                isCancel:this.news.isCurrentUserLiked?1:0
            })
            .then((res)=>{
                if(res && res.status=='200'){
                    this.$store.dispatch('news/getNews',{
                        id:this.$route.params.id,
                    })
                }
            })
        },
        getContext(data){
            this._articleReply({
                articleId:this.$route.params.id,
                content:data.pingLun
            })
            .then((res)=>{
                if(res && res.status=='200'){
                    this.$refs['pingLun'].setValue('');
                    this.$message({
                        message: '评论成功',
                        showClose: true,
                        type: 'success'
                    });
                    this.getComment({
                        id:this.$route.params.id,
                        pageNo:this.pageNo,
                        sortType:this.sortType
                    })
                }
            })
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
