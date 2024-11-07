(function(){"use strict";var e={5974:function(e,t,n){n.d(t,{DY:function(){return u},az:function(){return o},ec:function(){return i},qC:function(){return a}});var r=n(5720);function o(e){return(0,r.A)({url:"/auth/login",data:e,method:"post"})}function a(e){return(0,r.A)({url:"/auth/info",params:{token:e},method:"get"})}function i(e){return(0,r.A)({url:"/auth/changePassword",data:e,method:"post"})}function u(e){return(0,r.A)({url:"/auth/register",data:e,method:"post"})}},5521:function(e,t,n){var r=n(6674),o=function(){var e=this,t=e._self._c;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},a=[],i=n(1656),u={},d=(0,i.A)(u,o,a,!1,null,null,null),s=d.exports,c=n(9325),h=n(6582),f=n(3603);const l=["/login","/","/register"];c.Ay.beforeEach(((e,t,n)=>{const r=(0,f.VY)();console.log(e.path),r?"/"===e.path?n():(console.log(h.A.getters.permmited),0===h.A.getters.permmited?h.A.dispatch("GetUserInfoAction",r).then((()=>{"/login"!==e.path?n(e.path):n("/dashboard")})).catch((e=>{console.log(e),(0,f.TK)(),n("/login")})):"/login"!==e.path?n():n("/dashboard")):l.indexOf(e.path)>=0?n():"/login"!==t.path?n("/login"):n(!1)}));var m=n(9143),p=n.n(m),g=n(5771),b=n.n(g),v=n(8156);r["default"].use(v.Ay),r["default"].use(b()),r["default"].use(p()),r["default"].config.productionTip=!1,new r["default"]({router:c.Ay,store:h.A,render:e=>e(s)}).$mount("#app")},9325:function(e,t,n){n.d(t,{EB:function(){return f},Mj:function(){return h},dg:function(){return c}});var r=n(6674),o=n(6178);r["default"].use(o.A);const a=[{path:"",name:"goodlist",component:()=>n.e(959).then(n.bind(n,3959)),hidden:!0},{path:"/login",name:"loginView",component:()=>n.e(687).then(n.bind(n,5687)),hidden:!0},{path:"/register",name:"RegisterView",component:()=>n.e(340).then(n.bind(n,959)),hidden:!0}],i=[{path:"/dashboard",name:"DashboardView",component:()=>n.e(67).then(n.bind(n,5067)),hidden:!1,meta:{title:"主页"},children:[{path:"/dashboard/good",hidden:!1,meta:{title:"商品管理"},component:{render:e=>e("router-view")},children:[{path:"/dashboard/good/manager",name:"GoodView",component:()=>n.e(41).then(n.bind(n,3041)),hidden:!1,meta:{title:"商品操作"}},{path:"/dashboard/good/history",name:"HistoryView",component:()=>n.e(541).then(n.bind(n,9541)),hidden:!1,meta:{title:"商品历史"}}]},{path:"/dashboard/changePassword",name:"ChangePasswordView",component:()=>n.e(581).then(n.bind(n,5581)),hidden:!1,meta:{title:"修改密码"}},{path:"/dashboard/user",name:"UserView",component:()=>n.e(235).then(n.bind(n,1235)),hiiden:!1,meta:{title:"用户信息管理"}},{path:"/dashboard*",redirect:"/dashboard/good/manager",hidden:!0}]}],u=[{path:"/dashboard",name:"DashboardView",component:()=>n.e(67).then(n.bind(n,5067)),hidden:!1,meta:{title:"主页"},children:[{path:"/dashboard/order",hidden:!1,meta:{title:"订单查询"},component:()=>n.e(106).then(n.bind(n,9725)),name:"buyerOrderView"},{path:"/dashboard/changePassword",name:"ChangePasswordView",component:()=>n.e(581).then(n.bind(n,5581)),hidden:!1,meta:{title:"修改密码"}},{path:"/dashboard*",redirect:"/dashboard/order",hidden:!0}]}];function d(){return new o.A({routes:a})}const s=d();function c(){const e=d();s.matcher=e.matcher}function h(){return i.forEach((e=>{s.addRoute(e)})),i}function f(){return u.forEach((e=>{s.addRoute(e)})),u}t.Ay=s},6582:function(e,t,n){n.d(t,{A:function(){return h}});var r=n(6674),o=n(3518),a=n(5974),i=n(3603),u=n(9325),d={state:{token:"",permitted:0,permittedroutes:[],user:{username:"",userPhone:"",userAddress:"",userId:"",userRealName:""}},mutations:{SET_TOKEN(e,t){e.token=t},ADD_PERMS(e,t){0===e.permitted&&(e.permitted=t,1===e.permitted?e.permittedroutes=(0,u.Mj)():2===e.permitted&&(e.permittedroutes=(0,u.EB)()),console.log(u.Ay))},SET_USER(e,t){e.user.username=t.username,e.user.userRealName=t.userRealName,e.user.userAddress=t.userAddress,e.user.userId=t.userId,e.user.userPhone=t.userPhone},REMOVE_TOKEN(e){e.token=""},REMOVE_PERMS(e){e.permitted=0,(0,u.dg)()}},actions:{LoginByUserName({commit:e},t){return new Promise(((n,r)=>{(0,a.az)(t).then((t=>{const r=t.data.data.token;e("SET_TOKEN",r),(0,i.lc)(r),n()})).catch((e=>{r(e)}))}))},GetUserInfoAction({commit:e},t){return new Promise(((n,r)=>{(0,a.qC)(t).then((r=>{const o=r.data.data;e("SET_USER",o),e("SET_TOKEN",t),e("ADD_PERMS",o.privilege),n()})).catch((e=>{r(e)}))}))},UserLogout({commit:e}){(0,i.TK)(),e("REMOVE_PERMS"),e("REMOVE_TOKEN")}}};const s={token:e=>e.user.token,permmited:e=>e.user.permitted,username:e=>e.user.user.username,permittedRoutes:e=>e.user.permittedroutes,user:e=>e.user.user};var c=s;r["default"].use(o.Ay);var h=new o.Ay.Store({state:{},getters:c,mutations:{},actions:{},modules:{user:d}})},3603:function(e,t,n){n.d(t,{TK:function(){return u},VY:function(){return i},lc:function(){return a}});const r="X-Hahashop-Token",o=r;function a(e){localStorage.setItem(o,e)}function i(){return localStorage.getItem(o)}function u(){localStorage.removeItem(o)}},5720:function(e,t,n){n(4114);var r=n(6582),o=n(4373),a=n(9143);const i="http://localhost:8081",u="X-Hahashop-Token",d=o.A.create({baseURL:i});d.interceptors.request.use((e=>(r.A.getters.token&&(e.headers[u]=r.A.getters.token),e))),d.interceptors.response.use((e=>{const t=e.data;return t.code&&400!==t.code?(201===t.code?(a.Message.error({message:"token验证失败"}),(void 0).$store.dispatch("UserLogout"),(void 0).$router.push("/login").catch((e=>{console.log(e)}))):301===t.code?a.Message.error("用户不存在"):302===t.code?a.Message.error("用户密码错误"):303===t.code?a.Message.error("你操作的对象找不到了呢~"):304===t.code?a.Message.error("该用户名已被占用"):305===t.code?a.Message.error("商品已经被抢完啦！"):306===t.code?a.Message.error("非法输入！"):1001===t.code?a.Message.error("服务器发生了未知错误"):a.Message.error("服务器返回未知错误码"),Promise.reject("err")):t.code?e:(a.Message.error("后台服务器返回了意料外的结果"),Promise.reject("err"))})),t.A=d}},t={};function n(r){var o=t[r];if(void 0!==o)return o.exports;var a=t[r]={id:r,loaded:!1,exports:{}};return e[r].call(a.exports,a,a.exports,n),a.loaded=!0,a.exports}n.m=e,function(){n.amdO={}}(),function(){var e=[];n.O=function(t,r,o,a){if(!r){var i=1/0;for(c=0;c<e.length;c++){r=e[c][0],o=e[c][1],a=e[c][2];for(var u=!0,d=0;d<r.length;d++)(!1&a||i>=a)&&Object.keys(n.O).every((function(e){return n.O[e](r[d])}))?r.splice(d--,1):(u=!1,a<i&&(i=a));if(u){e.splice(c--,1);var s=o();void 0!==s&&(t=s)}}return t}a=a||0;for(var c=e.length;c>0&&e[c-1][2]>a;c--)e[c]=e[c-1];e[c]=[r,o,a]}}(),function(){n.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return n.d(t,{a:t}),t}}(),function(){n.d=function(e,t){for(var r in t)n.o(t,r)&&!n.o(e,r)&&Object.defineProperty(e,r,{enumerable:!0,get:t[r]})}}(),function(){n.f={},n.e=function(e){return Promise.all(Object.keys(n.f).reduce((function(t,r){return n.f[r](e,t),t}),[]))}}(),function(){n.u=function(e){return"js/"+e+"."+{41:"7a3ad2fd",67:"131c9751",106:"dcbc74c9",235:"813f0d5e",340:"e4656276",541:"98a81de6",581:"301b0ddb",687:"82fe41bd",959:"d4a9e513"}[e]+".js"}}(),function(){n.miniCssF=function(e){return"css/"+e+"."+{41:"2cebb018",67:"eac43e5b",106:"69ac2c13",235:"ee7fc016",340:"3e34fad5",541:"aa11414c",581:"a10765e9",687:"a863fd54",959:"8ddbafb2"}[e]+".css"}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){n.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){var e={},t="hahashop:";n.l=function(r,o,a,i){if(e[r])e[r].push(o);else{var u,d;if(void 0!==a)for(var s=document.getElementsByTagName("script"),c=0;c<s.length;c++){var h=s[c];if(h.getAttribute("src")==r||h.getAttribute("data-webpack")==t+a){u=h;break}}u||(d=!0,u=document.createElement("script"),u.charset="utf-8",u.timeout=120,n.nc&&u.setAttribute("nonce",n.nc),u.setAttribute("data-webpack",t+a),u.src=r),e[r]=[o];var f=function(t,n){u.onerror=u.onload=null,clearTimeout(l);var o=e[r];if(delete e[r],u.parentNode&&u.parentNode.removeChild(u),o&&o.forEach((function(e){return e(n)})),t)return t(n)},l=setTimeout(f.bind(null,void 0,{type:"timeout",target:u}),12e4);u.onerror=f.bind(null,u.onerror),u.onload=f.bind(null,u.onload),d&&document.head.appendChild(u)}}}(),function(){n.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){n.nmd=function(e){return e.paths=[],e.children||(e.children=[]),e}}(),function(){n.p=""}(),function(){if("undefined"!==typeof document){var e=function(e,t,r,o,a){var i=document.createElement("link");i.rel="stylesheet",i.type="text/css",n.nc&&(i.nonce=n.nc);var u=function(n){if(i.onerror=i.onload=null,"load"===n.type)o();else{var r=n&&n.type,u=n&&n.target&&n.target.href||t,d=new Error("Loading CSS chunk "+e+" failed.\n("+r+": "+u+")");d.name="ChunkLoadError",d.code="CSS_CHUNK_LOAD_FAILED",d.type=r,d.request=u,i.parentNode&&i.parentNode.removeChild(i),a(d)}};return i.onerror=i.onload=u,i.href=t,r?r.parentNode.insertBefore(i,r.nextSibling):document.head.appendChild(i),i},t=function(e,t){for(var n=document.getElementsByTagName("link"),r=0;r<n.length;r++){var o=n[r],a=o.getAttribute("data-href")||o.getAttribute("href");if("stylesheet"===o.rel&&(a===e||a===t))return o}var i=document.getElementsByTagName("style");for(r=0;r<i.length;r++){o=i[r],a=o.getAttribute("data-href");if(a===e||a===t)return o}},r=function(r){return new Promise((function(o,a){var i=n.miniCssF(r),u=n.p+i;if(t(i,u))return o();e(r,u,null,o,a)}))},o={524:0};n.f.miniCss=function(e,t){var n={41:1,67:1,106:1,235:1,340:1,541:1,581:1,687:1,959:1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=r(e).then((function(){o[e]=0}),(function(t){throw delete o[e],t})))}}}(),function(){var e={524:0};n.f.j=function(t,r){var o=n.o(e,t)?e[t]:void 0;if(0!==o)if(o)r.push(o[2]);else{var a=new Promise((function(n,r){o=e[t]=[n,r]}));r.push(o[2]=a);var i=n.p+n.u(t),u=new Error,d=function(r){if(n.o(e,t)&&(o=e[t],0!==o&&(e[t]=void 0),o)){var a=r&&("load"===r.type?"missing":r.type),i=r&&r.target&&r.target.src;u.message="Loading chunk "+t+" failed.\n("+a+": "+i+")",u.name="ChunkLoadError",u.type=a,u.request=i,o[1](u)}};n.l(i,d,"chunk-"+t,t)}},n.O.j=function(t){return 0===e[t]};var t=function(t,r){var o,a,i=r[0],u=r[1],d=r[2],s=0;if(i.some((function(t){return 0!==e[t]}))){for(o in u)n.o(u,o)&&(n.m[o]=u[o]);if(d)var c=d(n)}for(t&&t(r);s<i.length;s++)a=i[s],n.o(e,a)&&e[a]&&e[a][0](),e[a]=0;return n.O(c)},r=self["webpackChunkhahashop"]=self["webpackChunkhahashop"]||[];r.forEach(t.bind(null,0)),r.push=t.bind(null,r.push.bind(r))}();var r=n.O(void 0,[504],(function(){return n(5521)}));r=n.O(r)})();
//# sourceMappingURL=app.78191c3c.js.map