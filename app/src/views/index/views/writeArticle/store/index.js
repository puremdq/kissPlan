import api from '@/api'
const Qs = require('qs')
export default {
    name:'writeArticle',
    namespaced:true,
    state:{
        _editorType:'富文本编辑器',
        _mavonEditorQP:false,//markdown编辑器全屏
        _writeArticleMenuType:[
            {
                title:'默认菜单',
                content:'',
                articleType:'0',
                preview:'',
                mdContent:'',
                active:true,
            },
        ]
    },
    actions : {
        articleSave({commit,state},config) {
            var data = state._writeArticleMenuType[0];
            return api.instance({
                method:'post',
                url:'/article/save',
                data:Qs.stringify(data)
            })
            .then((res)=>{
                if(res.status==200){
                   
                }
                return res
            })
        }
    },
    mutations:{
        //修改文章
        _setNewsItem(state,data) {
            for(var key in data){
                state._writeArticleMenuType[data.idx][key] = data[key]
            }
        },
        //设置文章选中
        _checkNewsItem(state,idx) {
            var arr = state._writeArticleMenuType;
            if(arr && arr.length>0){
                arr.forEach((item)=>{
                    item.active = false;
                })
            }
            arr[idx].active = true;
        },
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
        },
        _writeText(state,data) {
            state._writeArticleMenuType.forEach((item)=>{
                if(item.active){
                    item.content = data.content=='<p><br></p>'?'':data.content;
                    item.mdContent =  data.mdContent;
                    item.articleType =  data.articleType;
                }
            })
        }
    }
}