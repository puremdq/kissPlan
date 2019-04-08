import Vue from "vue"
import {formatDate} from "./formatDate.js"
import MoneyFormat from "./MoneyFormat.js"
Vue.filter('formatDate',formatDate);
Vue.filter('MoneyFormat',MoneyFormat);