import {createApp} from './app.js'
const isDev = process.env.NODE_ENV !== 'production'

export default context => {
    return new Promise((resolve,reject) =>{
        const { app, router,store } = createApp()
        router.push(context.url)

        // 等到 router 将可能的异步组件和钩子函数解析完
        router.onReady(() => {
            const matchedComponents = router.getMatchedComponents()
            // 匹配不到的路由，执行 reject 函数，并返回 404
            if (!matchedComponents.length) {
                return reject({ code: 404 })
            }
            var arr = [];
            
            matchedComponents.forEach((item)=>{
                if(item.asyncData){
                    arr.push( item.asyncData({
                        store,
                        router:router.currentRoute
                    }))
                }
                if(item.components){
                    Object.keys(item.components).forEach((key)=>{
                        item.components[key]
                        if(item.components[key] && item.components[key].asyncData){
                            arr.push(item.components[key].asyncData({
                                store,
                                router:router.currentRoute
                            })) 
                        }
                    })
                }
            })
            Promise.all(arr).then((data)=>{
                context.state = store.state
                resolve(app)
            }).catch(reject)
            // Promise 应该 resolve 应用程序实例，以便它可以渲染
            //resolve(app)
        }, reject)
    })
}