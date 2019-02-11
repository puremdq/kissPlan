
// const files = require.context('../views', true, /^\.\/((?!\/)[\s\S])+\/store\/index\.js$/)
// const modules = {}
// files.keys().forEach((key) => {
//     var arr = key.split('/')
//     modules[arr[1]] = require('@/views'+key.slice(1)).default
// })
import home from "../views/index/views/home/store"
import news from "../views/index/views/news/store"
import writeArticle from "../views/index/views/writeArticle/store"
var modules = {
    home,
    news,
    writeArticle
}
export default modules