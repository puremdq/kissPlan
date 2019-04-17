import api from '@/api'
export default {
    name:'writeArticle',
    namespaced:true,
    state:{
        _editorType:'markdown编辑器',
        _mavonEditorQP:false,//markdown编辑器全屏
        _writeArticleMenuType:[
            {
                title:'默认菜单',
                content:'',
                active:true,
            },
            // {
            //     title:'默认菜单',
            //     content:'',
            //     active:false,
            // }
        ]
    },
    actions : {
        articleSave({commit},data) {
            return api.instance({
                method:'post',
                url:'/index/slideshow',
                data:data
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
        }
    }
}