export function parseCookie(cookies){
    let cookieStr = '';
    console.log(cookies);
    Object.keys(cookies).forEach((item) => {
        cookieStr += `${item}=${cookies[item]};`
    })
    return cookieStr;
}
