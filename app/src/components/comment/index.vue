<template>
    <div class="comment">
        <div>
            <mu-button style="float:right;" flat :color="active=='asc'?'primary':''" @click="setActive('asc')">按时间正序</mu-button>
            <mu-button style="float:right;" flat :color="active=='desc'?'primary':''" @click="setActive('desc')">按时间倒序</mu-button>
            <h2 class="comment-count">{{data.total}}条评论</h2>
            <div class="comment-box mt10">
                <div v-for="(item,idx) in data.list" :key="'news-'+idx" class="item">
                    <div class="clearfix head">
                        <mu-avatar size="45" style="vertical-align: middle;margin-right:5px;cursor:pointer;float:left;">
                            <img :src="item.avatars">
                        </mu-avatar>
                        <div  style="margin-left:50px;">
                            <p class="nickname">{{item.username}}</p>
                            <p class="floor">{{idx+1}}楼 {{item.updateDate | formatDate('yyyy-MM-dd hh:mm:ss')}}</p>
                        </div>
                    </div>
                    <div class="compiled_content" style="padding:10px 0;font-size:16px;word-break: break-word!important;line-height: 1.5;">
                        {{item.content}}
                    </div>
                    <div @click="comment(idx,null,item,item)">
                        <mu-icon size="25" value=":icon-message" class="iconfont" style="vertical-align: middle;cursor:pointer;"></mu-icon>
                        <span style="cursor:pointer;">回复</span>
                    </div>
                    <pingLun  ref="pingLun" v-model="content"  @cancel="cancel" :hide_head="true" @ok="ok" v-if="parentIdx==idx && !childIdx && childIdx!==0"></pingLun>
                    <div class="child-content mt20">
                        <div v-for="(child,index) in (item.childCommentList?item.childCommentList.slice(0,3):[])" :key="index" class="mt20 childItem">
                            <div class="child-content-text">
                                <span class="nickname">{{child.username}}</span>:
                                <span v-html="child.content"></span>
                            </div>
                             <div @click="comment(idx,index,child,item)">
                                <mu-icon size="20" value=":icon-message" class="iconfont" style="vertical-align: middle;cursor:pointer;"></mu-icon>
                                <span style="cursor:pointer;">回复</span>
                            </div>
                            <div class="child-content-time">{{child.updateDate | formatDate('yyyy-MM-dd hh:mm:ss')}}</div>
                            <pingLun ref="pingLun" v-model="content" @cancel="cancel" :hide_head="true" @ok="ok" v-if="parentIdx==idx && childIdx==index"></pingLun>
                        </div>
                    </div>
                </div>
            </div>
            <mu-flex justify-content="center" v-if="data.total">
                <mu-pagination raised circle :total="data.total" :current.sync="current" @change="change"></mu-pagination>
            </mu-flex>
            <div v-else class=" noComment">
                暂无评论,您将成为第一个沙发呦！
            </div>
        </div>
    </div>
</template>
<script>
import pingLun from "@/components/pingLun/index.vue"
export default {
    name:'comment',
    data(){
        return {
            active:'asc',
            current:1,
            parentIdx:null,
            childIdx:null,
            content:'',
            pid:undefined,
        }
    },
    props:{
        data:{
            type:Object,
            default(){
                return {}
            }
        }
    },
    components:{
        pingLun
    },
    methods:{
        change() {
            this.$emit('pageChange',this.current)
        },
        setActive(text) {
            this.active = text;
            this.$emit('sortType',text)
        },
        ok(data) {
           this.$store.dispatch('news/_articleReply',{
                articleId:this.$route.params.id,
                content:data.pingLun,
                pid:this.pid
           })
           .then((res)=>{
                if(res && res.status=='200'){
                    this.$refs['pingLun'][0].setValue('')
                    this.cancel();
                    this.$message({
                        message: '评论成功',
                        showClose: true,
                        type: 'success'
                    });
                    this.$store.dispatch('news/getComment',{
                        id:this.$route.params.id,
                        pageNo:'1'
                    })
                }
           })
        },
        comment(parentIdx,childIdx,item,Pitem) {
            this.parentIdx = parentIdx;
            this.childIdx = childIdx;
            this.pid = Pitem.id;
            this.content = `@${item.username}  `;
        },
        cancel() {
            this.parentIdx = null;
            this.childIdx = null;
        }
    }
}
</script>
<style lang="less">
    .comment{
        .comment-count{
            font-szie:16px;
            font-weight: bold;
        }
        .comment-box{
            .item{
                border-bottom:1px solid #ddd;
                padding-bottom:30px;
                margin-bottom:30px;
                .head{
                    .floor{
                        font-size: 12px;
                        color:#999;
                    }
                }
                .child-content{
                    border-left:2px solid #ddd;
                    padding-left:45px;
                    .childItem{
                        border-bottom:2px dotted #ddd;
                        padding-bottom:20px;
                        .child-content-text{
                            .nickname{
                                color:#ff4081;
                                margin-right:10px;
                            }
                        }
                        .child-content-time{
                            font-size: 12px;
                            color:#999;
                        }
                    }
                }
            }
        }
        .mu-pagination-item.mu-button.is-current{
            background-color: #ea6f5a;
        }
        .noComment{
            font-size:18px;
            color:#aaa;
        }
    }
</style>

