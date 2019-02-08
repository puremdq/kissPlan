import api from '@/api'
export default {
    name:'writeArticle',
    namespaced:true,
    state:{
        editorType:'markdown编辑器'
    },
    actions : {
        
    },
    mutations:{
        setEditorType(state,type){
            state.editorType = type;
        }
    }
}