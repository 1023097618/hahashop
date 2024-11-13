import request from '@/utils/request'

export function getGoods(query){
    return request({
        url:'/api/good/list',
        params:query,
        method:'get'
    })
}

export function getGoodsDetail(id){
    return request({
        url:'/api/good/detail',
        method:'get',
        params:{id:id}
    })
}

export function updateGoods(data){
    return request({
        url:'/api/good/update',
        data:data,
        method:'update'
    })
}

export function deleteGood(goodId){
    return request({
        url:'/api/good/delete',
        data:{
            goodId
        },
        method:'delete'
    })
}

export function addGoods(data){
    return request({
        url:'/api/good/add',
        data:data,
        method:'post'
    })
}

export function getCategory(){
    return request({
        url:'/api/good/category',
        method:'get'
    })
}