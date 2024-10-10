const getters={
    token: state=>state.user.token,
    permmited: state=>state.user.permitted,
    username: state=>state.user.username,
    permittedRoutes: state=>state.user.permittedroutes
}
export default getters