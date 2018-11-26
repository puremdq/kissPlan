
const files = require.context('../views', true, /^\.\/((?!\/)[\s\S])+\/store\/index\.js$/)
const modules = {}
files.keys().forEach((key) => {
    var arr = key.split('/')
    modules[arr[1]] = require('@/views'+key.slice(1)).default
})

export default modules