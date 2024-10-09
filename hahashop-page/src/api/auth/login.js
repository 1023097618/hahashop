import request from '@/utils/request'

export function Login(data){
    return request({
        url:'/auth/login',
        data,
        method:'post'
    })
}

export function GetUserInfo(token){
    return request({
        url:'/auth/info',
        params:{
            token:token
        },
        method:'get'
    })
}