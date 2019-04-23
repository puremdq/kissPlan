<template>
    <div class="asideMenu">
        <el-menu default-active="1" unique-opened class="el-menu-vertical-demo">
            <el-menu-item :index="idx+1+''" v-for="(item,idx) in _writeArticleMenuType" :key="idx" @mouseenter.native="mouseenter(idx)" @mouseleave.native="mouseleave" @click.native="clickNative(idx)">
                <i class="el-icon-document"></i>
                <span slot="title">
                    {{item.title}}
                    <i class="el-icon-edit" style="margin-left:15px;" @click="openSimpleDialog(idx)" v-if="hoverIdx===idx"></i>
                </span>
            </el-menu-item>
            
        </el-menu>
        <mu-dialog :title="dialogTitle" width="760" :open.sync="openSimple">
            <el-form ref="form" :rules="rules" :model="form" label-width="100px">
                <el-form-item label="文章标题" prop="title">
                    <el-input v-model="form.title"></el-input>
                </el-form-item>
                <!-- <el-form-item label="文章类型">
                    <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="仅自己可见">
                    <el-switch v-model="form.delivery"></el-switch>
                </el-form-item> -->
                <el-form-item label="文章描述">
                    <el-input type="textarea" v-model="form.preview"></el-input>
                </el-form-item>
            
            </el-form>
            <mu-button slot="actions" flat color="default" @click="closeSimpleDialog">取消</mu-button>
            <mu-button slot="actions" flat color="primary" @click="OkSimpleDialog">确定</mu-button>
        </mu-dialog>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('writeArticle');
export default {
    name:'asideMenu',
    data(){
        return {
            hoverIdx:-1,
            activeIdx:-1,
            openSimple:false,
            form:{
                title:'',
                preview:'',
            },
            dialogTitle:'修改文章详情',
            nowData:{},
            rules: {
                title:[
                    { required: true, message: '请输入文章标题', trigger: 'blur' },
                    { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
                ]
            }
        }
    },
    computed:{
        ...mapState(['_writeArticleMenuType']),
        
    },
    mounted(){
        var that = this;
        this.$eventBus.off('dialog')
        this.$eventBus.on('dialog',(data)=>{
            if(data.type==='add'){
                that.dialogTitle = '添加文章';
                this.form = {};
                that.openSimple = !that.openSimple;
            }
        })
    },
    methods:{
        ...mapMutations(['_checkNewsItem','_setNewsItem']),
        clickNative(idx) {
            this._checkNewsItem(idx);
        },
        mouseenter(idx) {
            this.hoverIdx = idx;
        },
        mouseleave() {
            this.hoverIdx = -1;
        },
        openSimpleDialog (idx) {
            var that = this;
            this.activeIdx = idx;
            this.dialogTitle = '修改文章详情';
            this.openSimple = true;
            if(this._writeArticleMenuType && this._writeArticleMenuType.length){
                this._writeArticleMenuType.forEach((item)=>{
                    if(item.active){
                        that.nowData = item;
                    }
                })
            }
            this.form = {
                title:that.nowData.title,
                preview:that.nowData.preview,
            }
        },
        OkSimpleDialog() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    var data = {
                        idx:this.activeIdx,
                    };
                    $.extend(true,data,this.form)
                    this._setNewsItem(data)
                    this.openSimple = false;
                } else {
                    return false;
                }
            });
            
        },
        closeSimpleDialog () {
            this.$refs['form'].resetFields();
            this.openSimple = false;
        },
        //设置新闻详情
        setNewsDetail() {
            this.openSimple = true;
        }
    }
}
</script>
<style lang="less">
    .asideMenu{

    }
</style>
