import qs from 'qs';
import axios from 'axios'
import { parseCookie } from '@/util/util'

const SSR = global.__VUE_SSR_CONTEXT__

export function createAPI({ server }) {
  
  var  api
  // axios.defaults.timeout = server.timeout
  // axios.defaults.baseURL = server.baseURL
  // axios.defaults.withCredentials = true
  var instance = axios.create({
    baseURL: server.baseURL,
    timeout: server.timeout,
    withCredentials:true,
  
  });
  instance.interceptors.request.use(function (config) {
    SSR.cookies && (config.headers.Cookie = SSR.cookies);
      return config;
  }, function (error) {
      return Promise.reject(error);
  });
   // 添加响应拦截器
   instance.interceptors.response.use(
     function (response) {
        if (response.status >= 200 && response.status < 300) {
            return response.data
        }
        
        return Promise.reject(response)
    }, 
    function (error) {
        return Promise.reject(error);
    }
  );
  if (process.__API__) {
    api = process.__API__
  } else {
    api = {
      instance (cfg){
          return new Promise((resolve,reject) => {
              instance(cfg).then((res) => {
                  resolve(res)
              }).catch((err) => {
                  reject(err)
              })
          })
      }
      
    }
    
  }
  return api
}
