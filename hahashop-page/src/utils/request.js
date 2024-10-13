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
                Message.error('商品不存在')
            } else if (data.code === 304) {
                Message.error('商品已经被冻结')
            } else if (data.code === 305) {
                Message.error('填入的信息不合法')
            } else if (data.code === 307) {
                Message.error('重复填入了信息')
            } else if (data.code === 308) {
                Message.error("商品未被冻结")
            } else if (data.code === 309) {
                Message.error('历史记录不存在')
            }
            else {
                Message.error("未知错误码")
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