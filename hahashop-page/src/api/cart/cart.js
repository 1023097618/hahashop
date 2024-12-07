import request from '@/utils/request'

export function getCartGoods(params){
    return request({
        url:'/cart/list',
        method:'get',
        params
    })
}

export function deleteCartGood(data){
    return request({
        url:'/cart/delete',
        method:'post',
        data
    })
}
export function changeGoodNum(data){
    return request({
        url:'/cart/changenum',
        method:'post',
        data
    })
}

export function addCartGood(data){
    return request({
        url:'/cart/add',
        method:'post',
        data
    })
}