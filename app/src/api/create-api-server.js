import qs from 'qs';
import axios from 'axios'
import { parseCookie } from '@/util/util'

const SSR = global.__VUE_SSR_CONTEXT__

export function createAPI({ server }) {
  
  let api
  axios.defaults.timeout = server.timeout
  axios.defaults.baseURL = server.baseurl
  axios.defaults.withCredentials = true

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
              'Cookie': SSR.cookies//'_ga=GA1.1.178608337.1537086079; kissPlan-sid=e5aa65a6-4852-4ad6-8fb1-3d4f28763ec9'//parseCookie(SSR.cookies)
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
              'Cookie':  parseCookie(SSR.cookies || {})
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
