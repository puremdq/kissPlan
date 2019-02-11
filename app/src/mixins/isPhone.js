export default {
    data(){
        return {
            isPhone:true
        }
    },
    mounted() {
        var that = this;
        if($('body').width()>768){
            that.isPhone = false;
        } 
        window.onresize = function(){
            var width = parseFloat($('body').width());
            if(width>=768){
                that.isPhone = false;
            }else{
                that.isPhone = true;
            }
        }
    },
}