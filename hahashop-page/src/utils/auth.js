const tokenName =process.env.VUE_APP_tokenName
const Token_key=tokenName

export function SetCookie(value){
    localStorage.setItem(Token_key,value)
}

export function GetCookie(){
    return localStorage.getItem(Token_key)
}

export function RemoveCookie(){
    
    localStorage.removeItem(Token_key)
}