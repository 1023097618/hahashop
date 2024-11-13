import request from '@/utils/request'

export function getUsers(params){
    return request({
        url:'/api/user/list',
        params,
        method:'get'
    })
}

export function getUserOrder(params){
    return request({
        url:'/api/user/orderlist',
        params,
        method:'get'
    })
}