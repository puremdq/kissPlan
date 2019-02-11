import api from '@/api'
export default {
    name:'writeArticle',
    namespaced:true,
    state:{
        _editorType:'markdown编辑器',
        _mavonEditorQP:false,//markdown编辑器全屏
        _writeArticleMenuType:[
            {
                name:'默认菜单',
            }
        ]
    },
    actions : {
        
    },
    mutations:{
        _addWriteArticleMenuType(state,type) {
            state._writeArticleMenuType.push({
                name:'新建文章',
            })
        },
        _setEditorType(state,type){
            state._editorType = type;
        },
        _setQPBoll(state,type) {
            state._mavonEditorQP = !state._mavonEditorQP;
        }
    }
}