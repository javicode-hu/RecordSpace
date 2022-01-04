import axios from 'axios'
import ElementUI from 'element-ui';
import store from "../store"


const service =axios.create({
    baseURL:'http://localhost:8008',
    // timeout: 10*1000
})

//request拦截器
service.interceptors.request.use(config => {

    if(store.state.token!=null){
        config.headers['authorization'] = store.state.token
    }

     return config
}, error => {
    Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(response => {
    // console.log(response)
    const res =response.data

    if(res.code===200){
        // console.log(res)
        return Promise.resolve(response)
    }else {
        console.log(res)
        // 弹窗异常信息
        ElementUI.Message.error(res.message)
        // 直接拒绝往下面返回结果信息
        return Promise.reject(res.message)
    }
}, error => {
    ElementUI.Message.warning("连接超时")
    Promise.reject(error.message)
})

export default service


/**
 * 封装get方法
 * @param url
 * @param data
 * @returns {Promise}
 */
export function get(url) {
    return new Promise((resolve, reject) => {
        service.get(url).then(response => {
                resolve(response.data)
            })
            .catch(err => {
                reject(err)
            })
    })
}

/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */
export function post (url, data ={}) {
    return new Promise((resolve, reject) => {
        service.post(url, data)
            .then(response => {
                resolve(response.data)
            }, err => {
                reject(err)
            })
    })
}