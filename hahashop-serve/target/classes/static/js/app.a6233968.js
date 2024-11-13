(function(){"use strict";var e={5974:function(e,t,n){n.d(t,{DY:function(){return u},az:function(){return o},ec:function(){return i},qC:function(){return a}});var r=n(5720);function o(e){return(0,r.A)({url:"/api/auth/login",data:e,method:"post"})}function a(e){return(0,r.A)({url:"/api/auth/info",params:{token:e},method:"get"})}function i(e){return(0,r.A)({url:"/api/auth/changePassword",data:e,method:"post"})}function u(e){return(0,r.A)({url:"/api/auth/register",data:e,method:"post"})}},5521:function(e,t,n){var r=n(6674),o=function(){var e=this,t=e._self._c;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},a=[],i=n(1656),u={},s=(0,i.A)(u,o,a,!1,null,null,null),d=s.exports,c=n(9325),h=n(6582),f=n(3603);const l=["/login","/","/register"];c.Ay.beforeEach(((e,t,n)=>{const r=(0,f.VY)();console.log(e.path),r?"/"===e.path?n():(console.log(h.A.getters.permmited),0===h.A.getters.permmited?h.A.dispatch("GetUserInfoAction",r).then((()=>{"/login"!==e.path?n(e.path):n("/dashboard")})).catch((e=>{console.log(e),(0,f.TK)(),n("/login")})):"/login"!==e.path?n():n("/dashboard")):l.indexOf(e.path)>=0?n():"/login"!==t.path?n("/login"):n(!1)}));var p=n(9143),m=n.n(p),g=n(8156);console.log({NODE_ENV:"production",VUE_APP_baseurl:"http://hahashop.natapp1.cc",VUE_APP_startPort:"8080",VUE_APP_tokenName:"X-Hahashop-Token",BASE_URL:""}),n.e(475).then(n.bind(n,1475)),r["default"].use(g.Ay),r["default"].use(m()),r["default"].config.productionTip=!1,new r["default"]({router:c.Ay,store:h.A,render:e=>e(d)}).$mount("#app")},9325:function(e,t,n){n.d(t,{EB:function(){return f},Mj:function(){return h},dg:function(){return c}});var r=n(6674),o=n(6178);r["default"].use(o.A);const a=[{path:"",name:"goodlist",component:()=>n.e(557).then(n.bind(n,1557)),hidden:!0},{path:"/login",name:"loginView",component:()=>n.e(687).then(n.bind(n,5687)),hidden:!0},{path:"/register",name:"RegisterView",component:()=>n.e(959).then(n.bind(n,959)),hidden:!0}],i=[{path:"/dashboard",name:"DashboardView",component:()=>n.e(67).then(n.bind(n,5067)),hidden:!1,meta:{title:"主页"},children:[{path:"/dashboard/good",hidden:!1,meta:{title:"商品管理"},component:{render:e=>e("router-view")},children:[{path:"/dashboard/good/manager",name:"GoodView",component:()=>n.e(365).then(n.bind(n,5365)),hidden:!1,meta:{title:"商品操作"}},{path:"/dashboard/good/history",name:"HistoryView",component:()=>n.e(541).then(n.bind(n,9541)),hidden:!1,meta:{title:"商品历史"}}]},{path:"/dashboard/changePassword",name:"ChangePasswordView",component:()=>n.e(581).then(n.bind(n,5581)),hidden:!1,meta:{title:"修改密码"}},{path:"/dashboard/user",name:"UserView",component:()=>n.e(235).then(n.bind(n,1235)),hiiden:!1,meta:{title:"用户信息管理"}},{path:"/dashboard*",redirect:"/dashboard/good/manager",hidden:!0}]}],u=[{path:"/dashboard",name:"DashboardView",component:()=>n.e(67).then(n.bind(n,5067)),hidden:!1,meta:{title:"主页"},children:[{path:"/dashboard/order",hidden:!1,meta:{title:"订单查询"},component:()=>n.e(106).then(n.bind(n,9725)),name:"buyerOrderView"},{path:"/dashboard/changePassword",name:"ChangePasswordView",component:()=>n.e(581).then(n.bind(n,5581)),hidden:!1,meta:{title:"修改密码"}},{path:"/dashboard*",redirect:"/dashboard/order",hidden:!0}]}];function s(){return new o.A({routes:a})}const d=s();function c(){const e=s();d.matcher=e.matcher}function h(){return i.forEach((e=>{d.addRoute(e)})),i}function f(){return u.forEach((e=>{d.addRoute(e)})),u}t.Ay=d},6582:function(e,t,n){n.d(t,{A:function(){return h}});var r=n(6674),o=n(3518),a=n(5974),i=n(3603),u=n(9325),s={state:{token:"",permitted:0,permittedroutes:[],user:{username:"",userPhone:"",userAddress:"",userId:"",userRealName:""}},mutations:{SET_TOKEN(e,t){e.token=t},ADD_PERMS(e,t){0===e.permitted&&(e.permitted=t,1===e.permitted?e.permittedroutes=(0,u.Mj)():2===e.permitted&&(e.permittedroutes=(0,u.EB)()),console.log(u.Ay))},SET_USER(e,t){e.user.username=t.username,e.user.userRealName=t.userRealName,e.user.userAddress=t.userAddress,e.user.userId=t.userId,e.user.userPhone=t.userPhone},REMOVE_TOKEN(e){e.token=""},REMOVE_PERMS(e){e.permitted=0,(0,u.dg)()}},actions:{LoginByUserName({commit:e},t){return new Promise(((n,r)=>{(0,a.az)(t).then((t=>{const r=t.data.data.token;e("SET_TOKEN",r),(0,i.lc)(r),n()})).catch((e=>{r(e)}))}))},GetUserInfoAction({commit:e},t){return new Promise(((n,r)=>{(0,a.qC)(t).then((r=>{const o=r.data.data;e("SET_USER",o),e("SET_TOKEN",t),e("ADD_PERMS",o.privilege),n()})).catch((e=>{r(e)}))}))},UserLogout({commit:e}){(0,i.TK)(),e("REMOVE_PERMS"),e("REMOVE_TOKEN")}}};const d={token:e=>e.user.token,permmited:e=>e.user.permitted,username:e=>e.user.user.username,permittedRoutes:e=>e.user.permittedroutes,user:e=>e.user.user};var c=d;r["default"].use(o.Ay);var h=new o.Ay.Store({state:{},getters:c,mutations:{},actions:{},modules:{user:s}})},3603:function(e,t,n){n.d(t,{TK:function(){return u},VY:function(){return i},lc:function(){return a}});const r="X-Hahashop-Token",o=r;function a(e){localStorage.setItem(o,e)}function i(){return localStorage.getItem(o)}function u(){localStorage.removeItem(o)}},5720:function(e,t,n){n(4114);var r=n(6582),o=n(4373),a=n(9143);const i="http://hahashop.natapp1.cc",u="X-Hahashop-Token",s=o.A.create({baseURL:i});s.interceptors.request.use((e=>(r.A.getters.token&&(e.headers[u]=r.A.getters.token),e))),s.interceptors.response.use((e=>{const t=e.data;return t.code&&400!==t.code?(201===t.code?(a.Message.error({message:"token验证失败"}),(void 0).$store.dispatch("UserLogout"),(void 0).$router.push("/login").catch((e=>{console.log(e)}))):301===t.code?a.Message.error("用户不存在"):302===t.code?a.Message.error("用户密码错误"):303===t.code?a.Message.error("你操作的对象找不到了呢~"):304===t.code?a.Message.error("该用户名已被占用"):305===t.code?a.Message.error("商品已经被抢完啦！"):306===t.code?a.Message.error("非法输入！"):1001===t.code?a.Message.error("服务器发生了未知错误"):a.Message.error("服务器返回未知错误码"),Promise.reject("err")):t.code?e:(a.Message.error("后台服务器返回了意料外的结果"),Promise.reject("err"))})),t.A=s}},t={};function n(r){var o=t[r];if(void 0!==o)return o.exports;var a=t[r]={id:r,loaded:!1,exports:{}};return e[r].call(a.exports,a,a.exports,n),a.loaded=!0,a.exports}n.m=e,function(){n.amdO={}}(),function(){var e=[];n.O=function(t,r,o,a){if(!r){var i=1/0;for(c=0;c<e.length;c++){r=e[c][0],o=e[c][1],a=e[c][2];for(var u=!0,s=0;s<r.length;s++)(!1&a||i>=a)&&Object.keys(n.O).every((function(e){return n.O[e](r[s])}))?r.splice(s--,1):(u=!1,a<i&&(i=a));if(u){e.splice(c--,1);var d=o();void 0!==d&&(t=d)}}return t}a=a||0;for(var c=e.length;c>0&&e[c-1][2]>a;c--)e[c]=e[c-1];e[c]=[r,o,a]}}(),function(){n.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return n.d(t,{a:t}),t}}(),function(){n.d=function(e,t){for(var r in t)n.o(t,r)&&!n.o(e,r)&&Object.defineProperty(e,r,{enumerable:!0,get:t[r]})}}(),function(){n.f={},n.e=function(e){return Promise.all(Object.keys(n.f).reduce((function(t,r){return n.f[r](e,t),t}),[]))}}(),function(){n.u=function(e){return"js/"+e+"."+{67:"84299914",106:"70858221",235:"3852b923",365:"ab63731a",475:"3f99af7e",541:"302eeca2",557:"95984c39",581:"e3ba63c6",687:"ca2a3c0b",959:"39c7eb67"}[e]+".js"}}(),function(){n.miniCssF=function(e){return"css/"+e+"."+{67:"eac43e5b",106:"69ac2c13",235:"ee7fc016",365:"1f17063c",475:"77489a8d",541:"aa11414c",557:"d9877989",581:"a10765e9",687:"a863fd54",959:"3e34fad5"}[e]+".css"}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){n.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){var e={},t="hahashop:";n.l=function(r,o,a,i){if(e[r])e[r].push(o);else{var u,s;if(void 0!==a)for(var d=document.getElementsByTagName("script"),c=0;c<d.length;c++){var h=d[c];if(h.getAttribute("src")==r||h.getAttribute("data-webpack")==t+a){u=h;break}}u||(s=!0,u=document.createElement("script"),u.charset="utf-8",u.timeout=120,n.nc&&u.setAttribute("nonce",n.nc),u.setAttribute("data-webpack",t+a),u.src=r),e[r]=[o];var f=function(t,n){u.onerror=u.onload=null,clearTimeout(l);var o=e[r];if(delete e[r],u.parentNode&&u.parentNode.removeChild(u),o&&o.forEach((function(e){return e(n)})),t)return t(n)},l=setTimeout(f.bind(null,void 0,{type:"timeout",target:u}),12e4);u.onerror=f.bind(null,u.onerror),u.onload=f.bind(null,u.onload),s&&document.head.appendChild(u)}}}(),function(){n.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){n.nmd=function(e){return e.paths=[],e.children||(e.children=[]),e}}(),function(){n.p=""}(),function(){if("undefined"!==typeof document){var e=function(e,t,r,o,a){var i=document.createElement("link");i.rel="stylesheet",i.type="text/css",n.nc&&(i.nonce=n.nc);var u=function(n){if(i.onerror=i.onload=null,"load"===n.type)o();else{var r=n&&n.type,u=n&&n.target&&n.target.href||t,s=new Error("Loading CSS chunk "+e+" failed.\n("+r+": "+u+")");s.name="ChunkLoadError",s.code="CSS_CHUNK_LOAD_FAILED",s.type=r,s.request=u,i.parentNode&&i.parentNode.removeChild(i),a(s)}};return i.onerror=i.onload=u,i.href=t,r?r.parentNode.insertBefore(i,r.nextSibling):document.head.appendChild(i),i},t=function(e,t){for(var n=document.getElementsByTagName("link"),r=0;r<n.length;r++){var o=n[r],a=o.getAttribute("data-href")||o.getAttribute("href");if("stylesheet"===o.rel&&(a===e||a===t))return o}var i=document.getElementsByTagName("style");for(r=0;r<i.length;r++){o=i[r],a=o.getAttribute("data-href");if(a===e||a===t)return o}},r=function(r){return new Promise((function(o,a){var i=n.miniCssF(r),u=n.p+i;if(t(i,u))return o();e(r,u,null,o,a)}))},o={524:0};n.f.miniCss=function(e,t){var n={67:1,106:1,235:1,365:1,475:1,541:1,557:1,581:1,687:1,959:1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=r(e).then((function(){o[e]=0}),(function(t){throw delete o[e],t})))}}}(),function(){var e={524:0};n.f.j=function(t,r){var o=n.o(e,t)?e[t]:void 0;if(0!==o)if(o)r.push(o[2]);else{var a=new Promise((function(n,r){o=e[t]=[n,r]}));r.push(o[2]=a);var i=n.p+n.u(t),u=new Error,s=function(r){if(n.o(e,t)&&(o=e[t],0!==o&&(e[t]=void 0),o)){var a=r&&("load"===r.type?"missing":r.type),i=r&&r.target&&r.target.src;u.message="Loading chunk "+t+" failed.\n("+a+": "+i+")",u.name="ChunkLoadError",u.type=a,u.request=i,o[1](u)}};n.l(i,s,"chunk-"+t,t)}},n.O.j=function(t){return 0===e[t]};var t=function(t,r){var o,a,i=r[0],u=r[1],s=r[2],d=0;if(i.some((function(t){return 0!==e[t]}))){for(o in u)n.o(u,o)&&(n.m[o]=u[o]);if(s)var c=s(n)}for(t&&t(r);d<i.length;d++)a=i[d],n.o(e,a)&&e[a]&&e[a][0](),e[a]=0;return n.O(c)},r=self["webpackChunkhahashop"]=self["webpackChunkhahashop"]||[];r.forEach(t.bind(null,0)),r.push=t.bind(null,r.push.bind(r))}();var r=n.O(void 0,[504],(function(){return n(5521)}));r=n.O(r)})();