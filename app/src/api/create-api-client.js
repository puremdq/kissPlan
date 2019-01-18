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
        var user = JSON.parse(window.localStorage.getItem('user'));
        var kiss_plan_token = (user && user.kiss_plan_token);
        if(kiss_plan_token){
            if(config.method==="post"){
                if(!config.data){
                    config.data+="?kiss_plan_token="+kiss_plan_token;
                }else{
                    config.data+="&kiss_plan_token="+kiss_plan_token;
                }
            }else if(config.method==="get"){
                if(config.url.indexOf('?')!=-1){
                    config.url+="&kiss_plan_token="+kiss_plan_token
                }else{
                    config.url+="?kiss_plan_token="+kiss_plan_token
                }
            }
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
                Vue.prototype.$Message.error(response.msg);
            }
            return Promise.reject(response)
        }, 
        function (error) {
            if(hasLoading){
                hasLoading.close();
            }
            if(hasMessage){
                Vue.prototype.$Message.error(error.config);
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