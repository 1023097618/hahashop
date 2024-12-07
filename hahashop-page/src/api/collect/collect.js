import request from '@/utils/request'

export function getCollectGoods(params){
    return request({
        url:'/collect/list',
        method:'get',
        params
    })
}

export function deleteCollectGood(data){
    return request({
        url:'/collect/delete',
        method:'post',
        data
    })
}

export function addCollectGood(data){
    return request({
        url:'/collect/add',
        method:'post',
        data
    })
}