import axios from 'axios'
import Vue from "vue"
var hasLoading,hasMessage;
export function createAPI ({client}){
    var instance = axios.create({
        baseURL: client.baseURL,
        timeout: client.timeout,
        withCredentials:true,
        hasMessage:client.hasMessage,
        hasLoading:client.hasLoading,
    });
    instance.interceptors.request.use(function (config) {
        if(config.kiss_plan_token){
            config.headers.kiss_plan_token = config.kiss_plan_token;
        }
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
                Vue.prototype.$message({
                    message: response.msg,
                    showClose: true,
                    type: 'warning'
                });
            }
            return Promise.reject(response)
        }, 
        function (error) {
            if(hasLoading){
                hasLoading.close();
            }
            Vue.prototype.$message({
                message: error.response&& error.response.data&& error.response.data.msg,
                showClose: true,
                type: 'warning'
            });
            return Promise.reject(error.response && error.response.data);
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