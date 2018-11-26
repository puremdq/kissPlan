import Vue from 'vue'
import Vuex from 'vuex'
import modules from './modules'
Vue.use(Vuex);

export function createStore(){
    return new Vuex.Store({
        state:{
            user:null,
            unreadMessage:[
                {
                    id:1,
                    title:'消息通知',
                    context:'Vue 实现了一套内容分发的 API，这套 API 基于当前的 Web Components 规范草案，将 <slot> 元素作为承载分发内容的出口。',
                    createDate:1530964874836,
                },
                {
                    id:2,
                    title:'消息通知',
                    context:'Vue 实现了一套内容分发的 API，这套 API 基于当前的 Web Components 规范草案，将 <slot> 元素作为承载分发内容的出口。',
                    createDate:1530964874836,
                },
                {
                    id:3,
                    title:'消息通知',
                    context:'Vue 实现了一套内容分发的 API，这套 API 基于当前的 Web Components 规范草案，将 <slot> 元素作为承载分发内容的出口。',
                    createDate:1530964874836,
                },
                {
                    id:4,
                    title:'消息通知',
                    context:'Vue 实现了一套内容分发的 API，这套 API 基于当前的 Web Components 规范草案，将 <slot> 元素作为承载分发内容的出口。',
                    createDate:1530964874836,
                },
                {
                    id:5,
                    title:'消息通知',
                    context:'Vue 实现了一套内容分发的 API，这套 API 基于当前的 Web Components 规范草案，将 <slot> 元素作为承载分发内容的出口。',
                    createDate:1530964874836,
                },
                {
                    id:6,
                    title:'消息通知',
                    context:'Vue 实现了一套内容分发的 API，这套 API 基于当前的 Web Components 规范草案，将 <slot> 元素作为承载分发内容的出口。',
                    createDate:1530964874836,
                },
                {
                    id:7,
                    title:'消息通知',
                    context:'Vue 实现了一套内容分发的 API，这套 API 基于当前的 Web Components 规范草案，将 <slot> 元素作为承载分发内容的出口。',
                    createDate:1530964874836,
                },
                {
                    id:8,
                    title:'消息通知',
                    context:'Vue 实现了一套内容分发的 API，这套 API 基于当前的 Web Components 规范草案，将 <slot> 元素作为承载分发内容的出口。',
                    createDate:1530964874836,
                },
                {
                    id:9,
                    title:'消息通知',
                    context:'Vue 实现了一套内容分发的 API，这套 API 基于当前的 Web Components 规范草案，将 <slot> 元素作为承载分发内容的出口。',
                    createDate:1530964874836,
                }
            ]


        },
        mutations:{
            setUser (state,user) {
                state.user = user;
            }
        },
        modules
    })
}