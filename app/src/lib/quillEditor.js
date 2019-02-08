import Vue from "vue"
if (process.env.VUE_ENV==='client') {
    const VueQuillEditor = require('vue-quill-editor/dist/ssr')
    require('quill/dist/quill.core.css') 
    require('quill/dist/quill.snow.css') 
    require('quill/dist/quill.bubble.css') 
    Vue.use(VueQuillEditor, /* { default global options } */)
}