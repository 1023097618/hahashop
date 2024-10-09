import { Login,GetUserInfo } from "@/api/auth/login";
import {SetCookie} from "@/utils/auth";
import {asyncRoutes} from "@/router";
import router,{resetRouter} from "@/router";
export default {
    state:{
        token:'',
        username:'',
        permitted:false
    },
    mutations:{
        SET_TOKEN(state,token){
            state.token=token
        },
        ADD_PERMS(state){
            if(!state.permitted){
            console.log(asyncRoutes)
            state.permitted=true
            asyncRoutes.forEach(route=>{
                router.addRoute(route)
            })
            console.log(router)
        }
        },
        SET_USERNAME(state,username){
            state.username=username
        },
        REMOVE_TOKEN(state){
            state.token=''
        },
        REMOVE_PERMS(state){
            state.permitted=false
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
                    commit('ADD_PERMS')
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
                        const username=res.data.data.username
                        commit('SET_USERNAME',username)
                        commit('SET_TOKEN',token)
                        SetCookie(token)
                        commit('ADD_PERMS')
                        resolve()
                    }).catch(
                        err=>{
                            reject(err)
                        }
                    )
            })
        }
    }
}