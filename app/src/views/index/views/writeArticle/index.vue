<template>
    <div class="writeArticle p10">
        <el-container>
            <el-aside width="200px">
                <ledtController></ledtController>
            </el-aside>
            <el-container>
                <el-main class="main">
                    <mavonEditorMy v-if="editorType==='markdown编辑器'"></mavonEditorMy>
                    <quillEditor v-else></quillEditor>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('writeArticle');
import ledtController from "./components/ledtController.vue"
import mavonEditorMy from "./components/mavonEditorMy.vue"
import quillEditor from "./components/quillEditor.vue"
export default {
    name:'writeArticle',
    data(){
        return {

        }
    },
    computed:{
        ...mapState(['editorType'])
    },
    components:{
       ledtController ,
       mavonEditorMy,
       quillEditor
    },
    
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
