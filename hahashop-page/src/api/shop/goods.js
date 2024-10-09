import request from '@/utils/request'

export function getGoods(query){
    return request({
        url:'/good/list',
        params:query,
        method:'get'
    })
}

export function getGoodsDetail(id){
    return request({
        url:'/good/detail',
        method:'get',
        params:{id:id}
    })
}

export function updateGoods(data){
    return request({
        url:'/good/update',
        data:data,
        method:'update'
    })
}

export function deleteGood(goodId){
    return request({
        url:'/good/delete',
        data:{
            goodId
        },
        method:'delete'
    })
}

export function addGoods(data){
    return request({
        url:'/good/add',
        data:data,
        method:'post'
    })
}