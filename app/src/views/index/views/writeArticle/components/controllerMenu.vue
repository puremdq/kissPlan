<template>
    <div class="controllerMenu">
        <ul>
            <li class="newsList">
                <mu-button fab  color="teal" @click="showNewsList">
                    列表
                </mu-button>
            </li>
            <!-- <li>
                <mu-button fab  color="teal" @click="addNews">
                    新增
                </mu-button>
            </li> -->
            <li>
                <mu-button fab  color="success" @click="save" :disabled="_writeArticleMenuType[0].content?false:true">
                    保存
                </mu-button>
            </li>
        </ul>
        <mu-drawer :open.sync="open" :docked="docked" :right="position === 'right'">
            <mu-list class="mu-list">
                <mu-list-item button v-for="(item,idx) in _writeArticleMenuType" :key="idx"  @click.native="checkList(idx)" :class="{active:hoverIdx==idx}">
                    <i class="el-icon-document"></i>
                    <mu-list-item-title>{{item.title}}</mu-list-item-title>
                    <i class="el-icon-edit" style="margin-left:15px;" @click="openSimpleDialog(idx)" v-if="hoverIdx===idx"></i>
                </mu-list-item>
            </mu-list>
            <div class="tac">
                <mu-button :color="$store.state.theme" class="mt10" @click="xiuGaiXiangQing">修改详情</mu-button>
            </div>
            <mu-dialog width="360" transition="slide-bottom" fullscreen :open.sync="openFullscreen">
                <mu-appbar  :color="$store.state.theme" :title="dialogTitle">
                    <mu-button slot="left" icon @click="closeFullscreenDialog">
                        <i class="el-icon-close"></i>
                    </mu-button>
                    <mu-button slot="right" flat  @click="okFullscreenDialog">
                        确定
                    </mu-button>
                </mu-appbar>
                <div style="padding: 24px;">
                    <mu-form :model="form" class="mu-demo-form" :label-position="labelPosition" label-width="100">
                        <mu-form-item prop="input" label="标题">
                        <mu-text-field v-model="form.input"></mu-text-field>
                        </mu-form-item>
                        <mu-form-item prop="select" label="文章类型">
                        <mu-select v-model="form.select">
                            <mu-option v-for="(option,index) in options" :key="index" :label="option" :value="option"></mu-option>
                        </mu-select>
                        </mu-form-item>
                       
                        <mu-form-item prop="switch" label="仅对自己可见">
                            <mu-switch v-model="form.switch"></mu-switch>
                        </mu-form-item>
                        
                        <mu-form-item prop="textarea" label="文章详情">
                            <mu-text-field multi-line :rows="3" :rows-max="6" v-model="form.textarea"></mu-text-field>
                        </mu-form-item>
                    </mu-form>
                </div>
            </mu-dialog>
        </mu-drawer>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('writeArticle');
export default {
    name:'controllerMenu',
    data(){
        return {
            docked: false,
            open: false,
            position: 'left',
            hoverIdx:0,
            openFullscreen:false,
            form:{},
            options:[],
            dialogTitle:'修改',
            labelPosition: 'top',
        }
    },
    computed:{
        ...mapState(['_writeArticleMenuType'])
    },
    methods:{
        ...mapActions(['articleSave']),
        ...mapMutations(['_addWriteArticleMenuType']),
        save() {
            if(!this._writeArticleMenuType[0].content){
                this.$message({
                    message: '文章不能为空',
                    showClose: true,
                    type: 'warning'
                });
                return
            }
            if(this._writeArticleMenuType[0].title==='默认菜单'){
                this.$message({
                    message: '请修改标题',
                    showClose: true,
                    type: 'warning'
                });
                return
            }
            this.articleSave(this.$store.state.user)
            .then((res)=>{
                if(res.status==200){
                    this.$message({
                        message: '文章发布成功，3s后将跳转至首页',
                        showClose: true,
                        type: 'success'
                    });
                    setTimeout(()=>{
                        this.$router.push('/home')
                    },3000)
                }
            })
        },
        addNews() {
            this.dialogTitle='新增';
            if(this.isPhone){
                this.openFullscreen = !this.openFullscreen;

            }else{
                this.$eventBus.emit('dialog',{
                    type:'add'
                })
            }
            // this._addWriteArticleMenuType()
        },
        showNewsList() {
            this.open = !this.open;
        },
        checkList(idx) {
            this.hoverIdx = idx;
        },
        openSimpleDialog() {

        },
        okFullscreenDialog() {

            
        },
        xiuGaiXiangQing() {
            this.dialogTitle='修改';
            this.openFullscreen = !this.openFullscreen;
        },
        closeFullscreenDialog () {
            this.openFullscreen = false;
        }
    }
}
</script>
<style lang="less">
    .controllerMenu{
        >ul{
            position: fixed;
            bottom:25px;
            right:25px;
            z-index: 10004;
            li{
                margin-top:20px;
                
            }
            .newsList{
                display: none;
            }
        }
        .active{
            transform: all .3s;
            background-color: rgba(206,206,206,.3)
        }
        .mu-list{
            height: calc(100vh - 56px);
        }
    }
    @media (max-width: 768px){
        .controllerMenu{
            .newsList{
                display: block !important;
                
            }
        }
    }
</style>