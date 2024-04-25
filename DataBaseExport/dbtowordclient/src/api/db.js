import http from '../utils/http'

/**
 * 连接测试
 * @returns {*}
 */
export const connectApi=(data)=> {
    return http.post('/db/connect',data)
}

/**
 * 获取数据库表格
 * @returns {*}
 */
export const listDbTableApi=(data)=> {
    return http.post('/db/listDbTable',data)
}

/**
 * 导出
 * @returns {*}
 */
export const exportDbTableApi=(data)=> {
    return http.post('/db/exportDbTable',data)
}
