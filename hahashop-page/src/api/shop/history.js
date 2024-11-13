import request from '@/utils/request'

export function getHistories(query){
    return request({
        url:'/api/history/list',
        params:query,
        method:'get'
    })
}

export function deleteHistory(historyId){
    return request({
        url:'/api/history/delete',
        data:{
            historyId
        },
        method:'delete'
    })
}