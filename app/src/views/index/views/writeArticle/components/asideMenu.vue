<template>
    <div class="asideMenu">
        <el-menu default-active="1" unique-opened class="el-menu-vertical-demo">
            <el-menu-item :index="idx+1+''" v-for="(item,idx) in _writeArticleMenuType" :key="idx" @mouseenter.native="mouseenter(idx)" @mouseleave.native="mouseleave">
                <i class="el-icon-document"></i>
                <span slot="title">
                    {{item.name}}
                    <i class="el-icon-edit" style="margin-left:15px;" @click="openSimpleDialog(idx)" v-if="hoverIdx===idx"></i>
                </span>
            </el-menu-item>
            
        </el-menu>
        <mu-dialog :title="dialogTitle" width="760" :open.sync="openSimple">
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="文章标题">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="文章类型">
                    <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="仅自己可见">
                    <el-switch v-model="form.delivery"></el-switch>
                </el-form-item>
                <el-form-item label="文章描述">
                    <el-input type="textarea" v-model="form.desc"></el-input>
                </el-form-item>
            
            </el-form>
            <mu-button slot="actions" flat color="default" @click="closeSimpleDialog">取消</mu-button>
            <mu-button slot="actions" flat color="primary" @click="closeSimpleDialog">确定</mu-button>
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
            openSimple:false,
            form:{},
            dialogTitle:'修改文章详情'
        }
    },
    computed:{
        ...mapState(['_writeArticleMenuType']),
        
    },
    methods:{
        mouseenter(idx) {
            this.hoverIdx = idx;
        },
        mouseleave() {
            this.hoverIdx = -1;
        },
        openSimpleDialog () {
            this.openSimple = true;
        },
        closeSimpleDialog () {
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
