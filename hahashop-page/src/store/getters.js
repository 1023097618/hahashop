const getters={
    token: state=>state.user.token,
    permmited: state=>state.user.permitted,
    username: state=>state.user.user.username,
    permittedRoutes: state=>state.user.permittedroutes,
    user:state=>state.user.user
}
export default getters