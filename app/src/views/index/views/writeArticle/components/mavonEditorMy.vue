<template>
    <div class="mavonEditorMy">
        <mavon-editor v-if="isWindow" v-model="value" @change="change" :ishljs="true">></mavon-editor>
    </div>
</template>
<script>
var time = null;
export default {
    name:'mavonEditorMy',
    data(){
        return {
            value:"# 3\n![统计营业走势.png](0)",
            isWindow:false
        }
    },
    mounted(){
        this.isWindow = true;
    },
    methods:{
        change(value, render) {
            if(!time){
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
        }
    }
}
</script>
