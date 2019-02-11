<template>
    <div class="leftController p10">
        <el-button type="danger" style="width:100%;" round @click="goBack">回首页</el-button>
        <asideMenu class="mt20 Menu"></asideMenu>
        <div class="leftFooter tac">
            <el-dropdown @command="dropdownHandle">
                <el-button type="primary">
                    {{_editorType}}<i class="el-icon-arrow-down el-icon--right"></i>
                </el-button>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="富文本编辑器">富文本编辑器</el-dropdown-item>
                    <el-dropdown-item command="markdown编辑器">markdown编辑器</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>
<script>
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('writeArticle');
import asideMenu from "./asideMenu.vue"
export default {
    name:'leftController',
    data(){
        return {
        }
    },
    computed:{
        ...mapState(['_editorType'])
    },
    components:{
        asideMenu
    },
    methods:{
        ...mapMutations(['_setEditorType']),
        goBack() {
            this.$router.push('/index')
        },
        dropdownHandle(command){
            this._setEditorType(command);
        }
    }
}
</script>
<style lang="less">
    .leftController{
        border-right: solid 1px #e6e6e6; 
        height:calc(100vh - 20px);
        .Menu{
            height:calc(100vh - 160px);
            margin-bottom:20px;
            overflow-y:auto;
            .el-menu{
                border-right: solid 0px #e6e6e6; 
            }
        }
        .leftFooter{
            width:180px;
            
        }
    }
</style>