import Vue from "vue"
import isWindow from "./isWindow.js"
import isPhone from "./isPhone.js"
import goHome from "./goHome.js"
import isLogin from "./isLogin.js"
Vue.mixin(goHome)
Vue.mixin(isWindow)
Vue.mixin(isPhone)
Vue.mixin(isLogin)