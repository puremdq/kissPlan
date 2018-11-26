import qs from 'qs';
import axios from 'axios'
import { parseCookie } from '@/util/util'

const SSR = global.__VUE_SSR_CONTEXT__

export function createAPI({ server }) {
 
  let api
  axios.defaults.timeout = server.timeout
  axios.defaults.baseURL = server.baseurl
  axios.defaults.withCredentials = false

  if (process.__API__) {
    api = process.__API__
  } else {
    api = {
      get(url, params = {}) {
        return new Promise((resolve, reject) => {
          axios({
            url,
            params,
            headers: {
              'X-Requested-With': 'XMLHttpRequest',
              'Cookie': parseCookie(SSR.cookies)
            },
            method: 'get'
          }).then(res => {
            resolve(res.data)
          }).catch(error => {
            reject(error)
          })
        })
      },
      post(url, params = {}) {
        return new Promise((resolve, reject) => {
          axios({
            url,
            data: qs.stringify(params),
            method: 'post',
            headers: {
              'X-Requested-With': 'XMLHttpRequest',
              'Content-Type': 'application/x-www-form-urlencoded',
              'Cookie': parseCookie(SSR.cookies)
            }
          }).then(res => {
            resolve(res.data)
          }).catch(error => {
            reject(error)
          })
        })
      }
    }
  }
  return api
}
