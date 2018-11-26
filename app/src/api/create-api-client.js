import qs from 'qs';
import axios from 'axios'

axios.interceptors.request.use(function (config) {
    return config;
  }, function (error) {
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    if (response.status >= 200 && response.status < 300) {
        return response
      }
      return Promise.reject(response)
  }, function (error) {
    return Promise.reject(error);
});
export function createAPI ({client}){
    // axios.defaults.timeout = client.timeout
    // axios.defaults.baseURL = client.baseURL
    // axios.defaults.withCredentials = false;
    return {
        get (url,params = {}){
            return new Promise((resolve,reject) => {
                axios({
                    url,
                    params,
                    headers: {'X-Requested-With': 'XMLHttpRequest'},
                    method:'get'
                }).then((res) => {
                    resolve(res)
                }).catch((err) => {
                    reject(err)
                })
            })
        },
        post(url, params = {}){
            return new Promise((resolve,reject) => {
                axios({
                    url,
                    params:qs.stringify(params),
                    headers: {
                        'X-Requested-With': 'XMLHttpRequest',
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    method:'post'
                }).then((res) => {
                    resolve(res)
                }).catch((err) => {
                    reject(err)
                })
            })
        }
    }
}