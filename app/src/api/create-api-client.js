import axios from 'axios'
import Vue from "vue"
var hasLoading,hasMessage;
export function createAPI ({client}){
    var instance = axios.create({
        baseURL: client.baseURL,
        timeout: client.timeout,
        withCredentials:false,
        hasMessage:client.hasMessage,
        hasLoading:client.hasLoading,
    });
    instance.interceptors.request.use(function (config) {
        if(config.hasLoading){
            hasLoading = Vue.prototype.$loading({
                lock: true,
                text: 'Loading',
                spinner: 'el-icon-loading',
                background: 'rgba(0, 0, 0, 0.7)'
            });
        }
        if(config.hasMessage){
            hasMessage = config.hasMessage
        }
        return config;
      }, function (error) {
        return Promise.reject(error);
    });
    
    // 添加响应拦截器
    instance.interceptors.response.use(
        function (response) {
            if(hasLoading){
                hasLoading.close();
            }
            if (response.status >= 200 && response.status < 300) {
                return response.data
            }
            if(hasMessage){
                Vue.prototype.$Message.error(response.msg);
            }
            return Promise.reject(response)
        }, 
        function (error) {
            if(hasLoading){
                hasLoading.close();
            }
            if(hasMessage){
                Vue.prototype.$Message.error(error);
            }
            return Promise.reject(error);
        }
    );
    return {
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