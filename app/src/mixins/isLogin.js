export default {
    data(){
        return {
            isLogin:false
        }
    },
    mounted() {
    },
    methods:{
        getIsLogin() {
            if(this.$store && this.$store.state.user&& this.$store.state.user.user){
                this.isLogin = true;
            }else{
                this.isLogin = false;
            }
        },
        isLoginPromiss() {
            new Promise(()=>{
                this.$confirm('登陆后才能进行次操作呦！', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // this.$message({
                    //     type: 'success',
                    //     message: '删除成功!'
                    // });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });          
                });
            })
        }
    },
    watch:{
        $route(){
            this.getIsLogin();
        }
    }
}