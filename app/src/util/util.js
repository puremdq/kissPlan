export function parseCookie(cookies){
    let cookieStr = '';
    console.log(cookies);
    Object.keys(cookies).forEach((item) => {
        cookieStr += `${item}=${cookies[item]};`
    })
    return cookieStr;
}
export function setIsPhone(Vue){
    if(window){
        var innweWidth = window.innerWidth;
        if(innerWidth<= 768){
          Vue.prototype.isPhone = true;
        }else{
          Vue.prototype.isPhone = false;
        }
    }
    
}