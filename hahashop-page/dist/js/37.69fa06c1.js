"use strict";(self["webpackChunkhahashop"]=self["webpackChunkhahashop"]||[]).push([[37],{4959:function(e,t,r){r.d(t,{A:function(){return d}});var a=function(){var e=this,t=e._self._c;return t("div",[e.error?t("el-result",{attrs:{title:"404",subTitle:"抱歉，请求错误"}},[t("template",{slot:"icon"},[t("el-image",{attrs:{src:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"}})],1)],2):e._e()],1)},s=[],i={name:"webErrorResult",props:{error:{type:Boolean,required:!0}}},l=i,o=r(1656),n=(0,o.A)(l,a,s,!1,null,null,null),d=n.exports},2037:function(e,t,r){r.r(t),r.d(t,{default:function(){return v}});var a=function(){var e=this,t=e._self._c;return t("div",{attrs:{id:"userview"}},[t("el-skeleton",{attrs:{rows:3,animated:"",loading:e.isload}}),t("webErrorResult",{attrs:{error:e.weberror}}),e.isload||e.weberror?e._e():t("el-table",{staticStyle:{width:"100"},attrs:{data:e.users,height:e.tableConfig.height,"header-cell-style":{"text-align":"center"},"cell-style":{"text-align":"center"}}},[t("el-table-column",{attrs:{prop:"userPhone",label:"用户手机号",width:"120",fixed:""}}),t("el-table-column",{attrs:{prop:"userRealName",label:"用户真实姓名",width:"150"}}),t("el-table-column",{attrs:{prop:"userAddress",label:"用户地址",width:"120"}}),t("el-table-column",{attrs:{fixed:"right",width:"210",label:"查看购买历史"},scopedSlots:e._u([{key:"default",fn:function(r){return[t("div",{staticStyle:{display:"flex","justify-content":"center"}},[t("el-button",{attrs:{type:"primary",icon:"el-icon-view",size:"mini"},nativeOn:{click:function(t){return t.preventDefault(),e.ViewUserOrder(r.row)}}})],1)]}}],null,!1,2672054242)})],1),e.totalUsers>e.pageSize?t("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next",total:e.totalUsers},on:{"current-change":e.handlePageChange}}):e._e(),t("OrderView",{ref:"OrderView",attrs:{visible:e.OrderViewVisible},on:{"update:visible":function(t){e.OrderViewVisible=t},updateUsers:e.GetUsers}})],1)},s=[],i=r(5720);function l(e){return(0,i.A)({url:"/user/list",params:e,method:"get"})}function o(e){return(0,i.A)({url:"/user/orderlist",params:e,method:"get"})}var n=r(4959),d=function(){var e=this,t=e._self._c;return t("el-dialog",{attrs:{title:"订单查看",visible:this.visible,width:"50%","before-close":e.closeDialog}},[t("h3",{staticStyle:{"text-align":"center"}},[e._v("用户姓名："+e._s(e.user.userRealName))]),t("el-table",{staticStyle:{width:"100%"},attrs:{data:e.orders,"max-height":"250","row-class-name":e.confirmedHeightlight}},[t("el-table-column",{attrs:{prop:"buyerPhone",label:"购买者姓名",width:"150"}}),t("el-table-column",{attrs:{prop:"buyerAddress",label:"购买者手机号",width:"120"}}),t("el-table-column",{attrs:{prop:"buyerGoodsNum",label:"购买商品数量",width:"120"}}),t("el-table-column",{attrs:{prop:"goodImage",label:"商品图片",width:"120"}}),t("el-table-column",{attrs:{prop:"goodName",label:"商品名称",width:"120"}}),t("el-table-column",{attrs:{prop:"goodPrice",label:"商品价格",width:"120"}}),t("el-table-column",{attrs:{prop:"buyerDesc",label:"购买者备注",width:"120"}}),t("el-table-column",{attrs:{width:"210",label:"确认状态"},scopedSlots:e._u([{key:"default",fn:function(r){return[0===r.row.orderState?t("div",[e._v("订单正在进行中")]):e._e(),1===r.row.orderState?t("div",[e._v("订单被卖家取消")]):e._e(),2===r.row.orderState?t("div",[e._v("订单完成")]):e._e()]}}])})],1),e.totalOrders>e.pageSize?t("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next",total:e.totalOrders},on:{"current-change":e.handlePageChange}}):e._e(),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){return e.closeDialog()}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.sureDialog()}}},[e._v("确 定")])],1)],1)},u=[],h={name:"BuyerView",data(){return{user:{},orders:[],currentPage:1,pageSize:8,totalOrders:0}},props:{visible:{type:Boolean,required:!0}},methods:{openDialog(e){Object.assign(this.user,e),this.$emit("update:visible",!0),this.GetUserOrder()},GetUserOrder(){o({userId:this.user.userId,pageNum:this.currentPage,pageSize:this.pageSize}).then((e=>{this.orders=e.data.data.orders,this.totalOrders=e.data.data.totalOrders})).catch((e=>{console.log(e)}))},closeDialog(){this.$emit("update:visible",!1)},sureDialog(){this.$emit("updateGoods"),this.$emit("update:visible",!1)},confirmedHeightlight({row:e}){return e.isConfirmed?"confirmed-row":""},handlePageChange(e){this.currentPage=e,this.GetUserOrder()}}},c=h,g=r(1656),p=(0,g.A)(c,d,u,!1,null,null,null),b=p.exports,w={name:"HistoriesView",methods:{handlePageChange(e){this.currentPage=e,this.GetUsers()},ViewUserOrder(e){this.$refs.OrderView.openDialog(e)},GetUsers(){this.isload=!0,l({pageNum:this.currentPage,pageSize:this.pageSize}).then((e=>{this.users=e.data.data.users,this.totalUsers=e.data.data.totalUsers,this.isload=!1,this.weberror=!1})).catch((e=>{this.isload=!1,this.weberror=!0,console.log(e)}))},getHeight(){this.tableConfig.height=window.innerHeight-100}},data(){return{histories:[],currentPage:1,pageSize:8,totalUsers:0,tableConfig:{height:200},isload:!0,weberror:!1,OrderViewVisible:!1}},created(){this.GetUsers(),this.getHeight(),window.addEventListener("resize",this.getHeight)},destroyed(){window.removeEventListener("resize",this.getHeight)},components:{webErrorResult:n.A,OrderView:b}},m=w,f=(0,g.A)(m,a,s,!1,null,null,null),v=f.exports}}]);
//# sourceMappingURL=37.69fa06c1.js.map