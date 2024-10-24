import store from "@/store";
import axios from "axios";
import { Message } from "element-ui";
const baseurl = process.env.VUE_APP_baseurl
const tokenName = process.env.VUE_APP_tokenName
const service = axios.create(
    {
        baseURL: baseurl
    }
)
service.interceptors.request.use(
    config => {
        if (store.getters.token) {
            config.headers[tokenName] = store.getters.token
        }
        return config
    }
)

service.interceptors.response.use(
    config => {
        const data = config.data
        if (data.code && data.code !== 400) {
            if (data.code === 201) {
                Message.error({
                    message: 'token验证失败'
                })
                this.$store.dispatch('UserLogout')
                this.$router.push('/login').catch(
                  err=>{
                    console.log(err)
                  }
                )
            } else if (data.code === 301) {
                Message.error('用户不存在')
            } else if (data.code === 302) {
                Message.error('用户密码错误')
            } else if (data.code === 303) {
                Message.error('你操作的对象找不到了呢~')
            } else if (data.code === 304) {
                Message.error('该用户名已被占用')
            } else if (data.code === 305) {
                Message.error('商品已经被抢完啦！')
            }
            else if(data.code===1001){
                Message.error("服务器发生了未知错误")
            }else{
                Message.error("服务器返回未知错误码")
            }
            return Promise.reject('err')
        }
        if (!data.code) {
            Message.error('后台服务器返回了意料外的结果')
            return Promise.reject('err')
        }
        return config
    }
)
export default service