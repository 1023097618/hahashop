import request from '@/utils/request'

export function buyGoods(data){
    return request({
        url:'/api/order/buy',
        method:'post',
        data:data
    })
}

export function getOrders(params){
    return request({
        url:'/api/order/sellerlist',
        method:'get',
        params
    })
}

export function confirmSellGood(data){
    return request({
        url:'/api/order/confirmsell',
        method:'post',
        data:data
    })
}

export function cancelSellGood(data){
    return request({
        url:'/api/order/cancelsell',
        method:'post',
        data:data
    })
}

export function getBuyerOrders(params){
    return request({
        url:'/api/order/buyerlist',
        method:'get',
        params
    })
}