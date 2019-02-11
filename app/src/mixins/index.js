import Vue from "vue"
import isWindow from "./isWindow.js"
import isPhone from "./isPhone.js"
Vue.mixin(isWindow)
Vue.mixin(isPhone)