<template>
	<div id="app" >
	  <router-view class="view"></router-view>
	</div>
</template>

<script>
export default {
	name: 'App',
	mounted(){
		var user = JSON.parse(localStorage.getItem('user'));
		this.$store.commit('setUser',user)
		if(user){
			if(this.$route.path==='/login'){
				this.$router.push('/')
			}
		}
	}
}
</script>

<style>
		@import './assets/reset.css';
		@import './assets/main.css';
		@import './assets/fonts/iconfont.css';
		#app{
		
		}
</style>
