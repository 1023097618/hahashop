"use strict";(self["webpackChunkhahashop"]=self["webpackChunkhahashop"]||[]).push([[312],{1312:function(t,e,s){s.r(e),s.d(e,{default:function(){return c}});var a=function(){var t=this,e=t._self._c;return e("el-container",[e("el-header",{staticStyle:{height:"60px"}},[e("el-row",{staticClass:"row-bg",staticStyle:{"align-items":"center",height:"100%"},attrs:{type:"flex",justify:"space-around"}},[e("el-col",{attrs:{span:6}}),e("el-col",{staticStyle:{"text-align":"center"},attrs:{span:8}},[e("h1",[t._v("hahashop")])]),e("el-col",{staticStyle:{"text-align":"right"},attrs:{span:8}})],1)],1),e("el-main",[e("div",{staticClass:"login-wrap"},[e("h3",{staticStyle:{"text-align":"center"}},[t._v("登录")]),e("el-row",{attrs:{type:"flex",justify:"center"}},[e("el-form",{attrs:{model:t.form,"label-position":"left","label-width":"80px"}},[e("el-form-item",{attrs:{label:"账号"}},[e("el-input",{model:{value:t.form.username,callback:function(e){t.$set(t.form,"username",e)},expression:"form.username"}})],1),e("el-form-item",{attrs:{label:"密码"}},[e("el-input",{model:{value:t.form.password,callback:function(e){t.$set(t.form,"password",e)},expression:"form.password"}})],1)],1)],1),e("el-row",{attrs:{type:"flex",justify:"center"}},[e("el-button",{staticStyle:{"margin-left":"0"},attrs:{type:"primary"},on:{click:function(e){return t.SendLogin()}}},[t._v("确 定")])],1)],1)])],1)},l=[],r=(s(4114),{name:"LoginView",data(){return{form:{username:"",password:""}}},methods:{SendLogin(){this.$store.dispatch("LoginByUserName",this.form).then((()=>{this.$router.push("/dashboard").catch((t=>{console.log(t)})),console.log("success")})).catch((t=>{console.log(t)}))}}}),o=r,n=s(1656),i=(0,n.A)(o,a,l,!1,null,"c3ded788",null),c=i.exports}}]);
//# sourceMappingURL=312.667e80d0.js.map