
import { createAPI } from 'create-api'

const isProd = process.env.NODE_ENV === 'production'

const proUrl = 'http://aojiaoo.com:8080' // 生产环境api地址
const devUrl = 'http://aojiaoo.com:8080' // 开发api地址

const FaceUrl = isProd ? proUrl : devUrl
var config = {
  baseURL: FaceUrl,
  client: {
    baseURL: isProd?FaceUrl:'/api',
    timeout: 10000,
    hasMessage:true,
    hasLoading:true,
  },
  server: {
    baseURL: FaceUrl,
    timeout: 10000
  }
}
const api = createAPI(config)

export default api
