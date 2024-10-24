import { Login,GetUserInfo } from "@/api/auth/login";
import {SetCookie,RemoveCookie} from "@/utils/auth";
import {addSellerRoute,addBuyerRoute} from "@/router";
import router,{resetRouter} from "@/router";
export default {
    state:{
        token:'',
        //0代表没有授权  1代表卖家  2代表买家
        permitted:0,
        permittedroutes:[],
        user:{
            username:'',
            userPhone:'',
            userAddress:'',
            userId:'',
            userRealName:'',
        }
    },
    mutations:{
        SET_TOKEN(state,token){
            state.token=token
        },
        ADD_PERMS(state,priviliage){
            if(state.permitted===0){
            state.permitted=priviliage
            if(state.permitted===1){
            state.permittedroutes=addSellerRoute()
            }else if(state.permitted===2){
                state.permittedroutes=addBuyerRoute()
            }
            console.log(router)
        }
        },
        SET_USER(state,user){
            state.user.username=user.username
            state.user.userRealName=user.userRealName
            state.user.userAddress=user.userAddress
            state.user.userId=user.userId
            state.user.userPhone=user.userPhone
        },
        REMOVE_TOKEN(state){
            state.token=''
        },
        REMOVE_PERMS(state){
            state.permitted=0
            resetRouter()
        }
    },
    actions:{
        LoginByUserName({commit},data){
            return new Promise((resolve,reject)=>{
                Login(data).then(res=>{
                    const token=res.data.data.token
                    commit('SET_TOKEN',token)
                    SetCookie(token)
                    resolve()
                }).catch(err=>{
                    reject(err)
                })
            })
        },
        GetUserInfoAction({commit},token){
            return new Promise((resolve,reject)=>{
                GetUserInfo(token).then(
                    res=>{
                        const user=res.data.data
                        commit('SET_USER',user)
                        commit('SET_TOKEN',token)
                        commit('ADD_PERMS',user.priviliage)
                        resolve()
                    }).catch(
                        err=>{
                            reject(err)
                        }
                    )
            })
        },
        UserLogout({commit}){
            RemoveCookie()
            commit('REMOVE_PERMS')
            commit('REMOVE_TOKEN')
        }
    }
}