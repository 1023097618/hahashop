import request from '@/utils/request'

export function buyGoods(data){
    return request({
        url:'/order/buy',
        method:'post',
        data:data
    })
}

export function getOrders(goodId){
    return request({
        url:'/order/sellerlist',
        method:'get',
        params:{
            goodId
        }
    })
}

export function confirmSellGood(data){
    return request({
        url:'/order/confirmsell',
        method:'post',
        data:data
    })
}

export function cancelSellGood(data){
    return request({
        url:'/order/cancelsell',
        method:'post',
        data:data
    })
}

export function getBuyerOrders(params){
    return request({
        url:'/order/buyerlist',
        method:'get',
        params
    })
}