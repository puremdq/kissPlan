<template>
    <div class="mavonEditorMy">
        <mavon-editor v-if="isWindow" v-model="value" @change="change" @fullScreen="fullScreen" :ishljs="true">></mavon-editor>
    </div>
</template>
<script>
var time = null;
import { createNamespacedHelpers } from 'vuex';
const { mapState, mapActions,mapMutations } = createNamespacedHelpers('writeArticle');
export default {
    name:'mavonEditorMy',
    data(){
        return {
            value:"",
            isWindow:false
        }
    },
    mounted(){
        this.isWindow = true;
    },
    methods:{
        ...mapMutations(['_setQPBoll']),
        change(value, render) {
            if(!time && value){
                time = window.setTimeout(()=>{
                    this.$message({
                        message: '本地保存',
                        showClose: true,
                        type: 'success'
                    });
                    clearTimeout(time);
                    time = null;
                },3000)
            }
        },
        fullScreen() {
            this._setQPBoll();
        }
    }
}
</script>
