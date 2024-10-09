import store from "@/store";
import axios from "axios";
const baseurl =process.env.VUE_APP_baseurl
const tokenName =process.env.VUE_APP_tokenName
const service = axios.create(
    {
        baseURL:baseurl
    }
)
service.interceptors.request.use(
    config=>{
        console.log(store)
        console.log(store.getters.token)
        if(store.getters.token){
            config.headers[tokenName]=store.getters.token
        }
        return config
    }
)
export default service