<template>
    <div class="writeArticle p10">
        <my-header v-if="!_mavonEditorQP"></my-header>
        <el-container>
            <el-aside width="200px" class="phone_hide">
                <ledtController></ledtController>
            </el-aside>
            <el-container>
                <el-main class="main">
                    <mavonEditorMy v-if="_editorType==='markdown编辑器'"  v-model="data"></mavonEditorMy>
                    <quillEditor v-else v-model="data"></quillEditor>
                </el-main>
            </el-container>
            <controllerMenu></controllerMenu>
        </el-container>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('writeArticle');
import ledtController from "./components/ledtController.vue"
import mavonEditorMy from "./components/mavonEditorMy.vue"
import quillEditor from "./components/quillEditor.vue"
import header from "./components/header.vue"
import controllerMenu from "./components/controllerMenu.vue"
export default {
    name:'writeArticle',
    data(){
        return {
            data:{
                content:'',
                mdContent:'',
                articleType:'',
            }
        }
    },
    computed:{
        ...mapState(['_editorType','_mavonEditorQP','_writeArticleMenuType'])
    },
    components:{
       ledtController ,
       mavonEditorMy,
       quillEditor,
       "my-header":header,
       controllerMenu
    },
    methods:{
       ...mapMutations(['_writeText'])
    },
    watch:{
        data(newValue,oldValue) {
            this._writeText(newValue)
        }
    }
}
</script>
<style lang="less">
    .writeArticle{
        .main{
            height:calc(100vh - 20px);
            overflow: auto;
        }
        
    }
    
</style>
