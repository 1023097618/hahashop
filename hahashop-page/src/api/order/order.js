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
        url:'/order/list',
        method:'get',
        params:{
            goodId
        }
    })
}

export function sellGood(data){
    return request({
        url:'/order/sell',
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