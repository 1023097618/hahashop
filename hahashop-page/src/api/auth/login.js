import request from '@/utils/request'

export function Login(data){
    return request({
        url:'/api/auth/login',
        data,
        method:'post'
    })
}

export function GetUserInfo(token){
    return request({
        url:'/api/auth/info',
        params:{
            token:token
        },
        method:'get'
    })
}

export function changePassword(data){
    return request({
        url:'/api/auth/changePassword',
        data,
        method:'post'
    })
}

export function registerUser(data){
    return request({
        url:'/api/auth/register',
        data,
        method:'post'
    })
}