export function parseCookie(cookies){
    let cookieStr = '';
    Object.keys(cookies).forEach((item) => {
        cookieStr += `${item}=${cookies[item]};`
    })
    return cookieStr;
}
