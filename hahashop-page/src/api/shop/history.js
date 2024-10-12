import request from '@/utils/request'

export function getHistories(query){
    return request({
        url:'/history/list',
        params:query,
        method:'get'
    })
}

export function deleteHistory(historyId){
    return request({
        url:'/history/delete',
        data:{
            historyId
        },
        method:'delete'
    })
}