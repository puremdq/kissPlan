export default {
    methods:{
        goHome(){
            this.$router.push('/index')
        },
        go(path){
            if(!path){
                return;
            }
            this.$router.push(path)
        }
    }
}