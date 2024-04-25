import axios from "axios";
import qs from "qs";
import {message} from "antd";

const baseURL = `http://192.168.9.8:8080`;

// 创建自定义实例
const http = axios.create({
    baseURL, // 基础请求地址
    timeout: 10 * 1000, // 请求超时设置
    withCredentials: false, // 跨域请求是否需要携带 cookie
    transformRequest: [(data) => qs.stringify(data)]
});

// 请求拦截器
http.interceptors.request.use(
    (config) => {

        return config;
    },
    (error) => {
        return Promise.reject(error.data.error.message);
    },
);

// 响应拦截器
http.interceptors.response.use(
    (res) => {
        // 响应数据处理
        if (res.data.code !== 1) {
            message.error(res.data.message)
            return
        }
        return res.data;
    },
    (error) => {
        return Promise.reject(error);
    },
);

export default http;
