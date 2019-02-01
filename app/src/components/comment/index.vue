<template>
    <div class="comment">
        <div>
            <mu-button style="float:right;" flat color="primary">按时间正序</mu-button>
            <mu-button style="float:right;" flat>按时间倒序</mu-button>
            <h2 class="comment-count">25条评论</h2>
            <div class="comment-box mt10">
                <div v-for="(item,idx) in data.comments" :key="'news-'+idx" class="item">
                    <div class="clearfix head">
                        <mu-avatar size="45" style="vertical-align: middle;margin-right:5px;cursor:pointer;float:left;">
                            <img :src="item.user && item.user.avatar">
                        </mu-avatar>
                        <div  style="margin-left:50px;">
                            <p class="nickname">{{item.user && item.user.nickname}}</p>
                            <p class="floor">{{item.floor}}楼 {{item.created_at | formatDate('yyyy-MM-dd hh:mm:ss')}}</p>
                        </div>
                    </div>
                    <div class="compiled_content" style="padding:10px 0;font-size:16px;word-break: break-word!important;line-height: 1.5;">
                        {{item.compiled_content}}
                    </div>
                    <div>
                        点赞
                    </div>
                    <div class="child-content mt20">
                        <div v-for="(child,index) in (item.children?item.children.slice(0,3):[])" :key="index" class="mt20 childItem">
                            <div class="child-content-text">
                                <span class="nickname">{{child.user && child.user.nickname}}</span>:
                                <span v-html="child.compiled_content"></span>
                            </div>
                            <div class="child-content-time">{{child.created_at | formatDate('yyyy-MM-dd hh:mm:ss')}}</div>
                        </div>
                    </div>
                </div>
            </div>
            <mu-flex justify-content="center">
                <mu-pagination raised circle :total="1000" :current.sync="current"></mu-pagination>
            </mu-flex>
        </div>
    </div>
</template>
<script>
export default {
    name:'comment',
    data(){
        return {
            current:1
        }
    },
    props:{
        data:{
            type:Object,
            default(){
                return {}
            }
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
    }
</style>

