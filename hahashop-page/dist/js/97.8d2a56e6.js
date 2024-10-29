"use strict";(self["webpackChunkhahashop"]=self["webpackChunkhahashop"]||[]).push([[97],{4959:function(e,t,o){o.d(t,{A:function(){return n}});var i=function(){var e=this,t=e._self._c;return t("div",[e.error?t("el-result",{attrs:{title:"404",subTitle:"抱歉，请求错误"}},[t("template",{slot:"icon"},[t("el-image",{attrs:{src:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"}})],1)],2):e._e()],1)},l=[],r={name:"webErrorResult",props:{error:{type:Boolean,required:!0}}},a=r,s=o(1656),d=(0,s.A)(a,i,l,!1,null,null,null),n=d.exports},6097:function(e,t,o){o.r(t),o.d(t,{default:function(){return L}});var i=function(){var e=this,t=e._self._c;return t("div",{attrs:{id:"goods"}},[t("webErrorResult",{attrs:{error:e.weberror}}),t("el-skeleton",{attrs:{rows:3,animated:"",loading:e.isload}}),e.isload||e.weberror?e._e():t("el-table",{key:e.updatekey,staticStyle:{width:"100"},attrs:{data:e.goods,height:e.tableConfig.height,"row-class-name":e.tableRowClassName}},[t("el-table-column",{attrs:{label:"商品图片",width:"120",fixed:""},scopedSlots:e._u([{key:"default",fn:function(e){return[t("img",{staticStyle:{width:"100px",height:"100px","object-fit":"cover","object-position":"center"},attrs:{src:e.row.goodImage[0]}})]}}],null,!1,3493785268)}),t("el-table-column",{attrs:{prop:"goodName",label:"商品名称",width:"150"}}),t("el-table-column",{attrs:{prop:"goodPrice",label:"商品价格",width:"120"}}),t("el-table-column",{attrs:{prop:"goodNum",label:"剩余库存",width:"120"}}),t("el-table-column",{attrs:{prop:"buyerNum",label:"购买者数量",width:"120"}}),t("el-table-column",{attrs:{prop:"categoryName",label:"商品种类",width:"120"}}),t("el-table-column",{attrs:{label:"订单查看",width:"120"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("el-button",{attrs:{type:"primary",icon:"el-icon-view",size:"mini"},nativeOn:{click:function(t){return t.preventDefault(),e.ViewBuyers(o.row)}}})]}}],null,!1,1796306559)}),t("el-table-column",{attrs:{fixed:"right",width:"210"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("div",{staticStyle:{display:"flex","justify-content":"center"}},[t("el-button",{attrs:{type:"primary",icon:"el-icon-edit",size:"mini"},nativeOn:{click:function(t){return t.preventDefault(),e.EditGoods(o.row)}}}),t("el-button",{attrs:{type:"danger",icon:"el-icon-delete",size:"mini"},nativeOn:{click:function(t){return t.preventDefault(),e.DeleteGood(o.row.goodId)}}})],1)]}}],null,!1,1203914040)},[t("template",{slot:"header"},[t("div",{staticStyle:{display:"flex","justify-content":"center"}},[t("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-plus"},on:{click:function(t){return e.AddGoods()}}})],1)])],2)],1),e.totalProducts>e.pageSize?t("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next",total:e.totalProducts},on:{"current-change":e.handlePageChange}}):e._e(),t("GoodsEdit",{ref:"GoodsEdit",attrs:{visible:e.GoodsEditVisible},on:{"update:visible":function(t){e.GoodsEditVisible=t},updateGoods:e.GetGoods}}),t("GoodsAdd",{ref:"GoodsAdd",attrs:{visible:e.GoodsAddVisible},on:{"update:visible":function(t){e.GoodsAddVisible=t},updateGoods:e.GetGoods}}),t("BuyerView",{ref:"BuyerView",attrs:{visible:e.BuyerViewVisible},on:{"update:visible":function(t){e.BuyerViewVisible=t},updateGoods:e.GetGoods}})],1)},l=[],r=o(6551),a=function(){var e=this,t=e._self._c;return t("el-dialog",{staticStyle:{overflow:"hidden"},attrs:{title:"修改商品",visible:this.visible,width:"30%","before-close":e.closeDialog,center:"",top:"3vh"}},[t("div",{staticStyle:{"overflow-y":"auto",height:"70vh"}},[t("el-form",{attrs:{model:e.good,"label-position":"right","label-width":"80px"}},[t("el-form-item",{attrs:{label:"商品名称"}},[t("el-input",{model:{value:e.good.goodName,callback:function(t){e.$set(e.good,"goodName",t)},expression:"good.goodName"}})],1),t("el-form-item",{attrs:{label:"商品价格"}},[t("el-input",{model:{value:e.good.goodPrice,callback:function(t){e.$set(e.good,"goodPrice",t)},expression:"good.goodPrice"}})],1),t("el-form-item",{attrs:{label:"商品图片"}},[t("UploadPicture",{attrs:{urls:e.good.goodImage},on:{"update:urls":function(t){return e.$set(e.good,"goodImage",t)}}})],1),t("el-form-item",{attrs:{label:"剩余库存"}},[t("el-input",{model:{value:e.good.goodNum,callback:function(t){e.$set(e.good,"goodNum",t)},expression:"good.goodNum"}})],1),t("el-form-item",{attrs:{label:"商品种类"}},[t("el-cascader",{attrs:{options:e.options,props:{expandTrigger:"hover",checkStrictly:!0},clearable:""},on:{change:e.handleChange},model:{value:e.good.categoryId,callback:function(t){e.$set(e.good,"categoryId",t)},expression:"good.categoryId"}})],1),t("quill-editor",{ref:"myQuillEditor",attrs:{options:e.editorOption},model:{value:e.good.goodDesc,callback:function(t){e.$set(e.good,"goodDesc",t)},expression:"good.goodDesc"}})],1)],1),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){return e.closeDialog()}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.sureDialog()}}},[e._v("确 定")])],1)])},s=[],d=function(){var e=this,t=e._self._c;return t("div",[t("el-upload",{ref:"upload",attrs:{action:"#","list-type":"picture-card","http-request":e.uploadFile,accept:".jpg,.jpeg,.png","before-upload":e.beforeUpload,"file-list":e.fileList,"show-file-list":!0},scopedSlots:e._u([{key:"file",fn:function({file:o}){return t("div",{},[t("img",{staticClass:"el-upload-list__item-thumbnail",attrs:{src:o.url,alt:""}}),t("span",{staticClass:"el-upload-list__item-actions"},[t("span",{staticClass:"el-upload-list__item-preview",on:{click:function(t){return e.handlePictureCardPreview(o)}}},[t("i",{staticClass:"el-icon-zoom-in"})]),e.disabled?e._e():t("span",{staticClass:"el-upload-list__item-delete",on:{click:function(t){return e.handleDownload(o)}}},[t("i",{staticClass:"el-icon-download"})]),e.disabled?e._e():t("span",{staticClass:"el-upload-list__item-delete",on:{click:function(t){return e.handleRemove(o)}}},[t("i",{staticClass:"el-icon-delete"})])])])}}])},[t("i",{staticClass:"el-icon-plus",attrs:{slot:"default"},slot:"default"})]),t("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1)},n=[],u=(o(4114),o(5720));function c(e){return(0,u.A)({url:"/storage/upload",headers:{"Content-Type":"multipart/form-data"},data:e,method:"post"})}var g={props:{urls:{type:Array,required:!0}},data(){return{dialogImageUrl:"",dialogVisible:!1,disabled:!1,fileList:[]}},methods:{handleRemove(e){this.fileList=this.fileList.filter((t=>t.url!==e.url)),this.$emit("update:urls",this.fileList.map((e=>e.url)))},handlePictureCardPreview(e){this.dialogImageUrl=e.url,this.dialogVisible=!0},handleDownload(e){console.log(e)},uploadFile(e){let t=new FormData;t.append("file",e.file),c(t).then((t=>{const o=t.data.data.url;this.fileList.push({name:e.file.name,url:o}),this.$emit("update:urls",this.fileList.map((e=>e.url)))}))},beforeUpload(e){console.log(e)}},created(){this.fileList=this.urls.map((e=>({name:e.split("/").pop(),url:e})))}},h=g,p=o(1656),m=(0,p.A)(h,d,n,!1,null,null,null),f=m.exports,b={name:"GoodsEdit",data(){return{good:{goodName:"",goodPrice:"",goodImage:"",goodDesc:"",categoryId:void 0},options:[],editorOption:{}}},components:{UploadPicture:f},props:{visible:{type:Boolean,required:!0}},methods:{openDialog(e,t){Object.assign(this.good,e),this.options=t,this.$emit("update:visible",!0),(0,r._P)(this.good.goodId).then((e=>{this.good.goodDesc=e.data.data.goodDesc})).catch((e=>{console.log(e)}))},closeDialog(){this.$emit("update:visible",!1)},sureDialog(){this.$emit("update:visible",!1),(0,r.iJ)({goodName:this.good.goodName,goodPrice:this.good.goodPrice,goodImage:this.good.goodImage,goodDesc:this.good.goodDesc,goodId:this.good.goodId,categoryId:this.good.categoryId}).then((e=>{this.$emit("updateGoods"),console.log(e)})).catch((e=>{console.log(e)}))},handleChange(e){console.log(e),Array.isArray(e)&&e.length>0?this.good.categoryId=e[e.length-1]:this.good.categoryId=void 0}}},y=b,v=(0,p.A)(y,a,s,!1,null,null,null),w=v.exports,G=function(){var e=this,t=e._self._c;return t("el-dialog",{staticStyle:{overflow:"hidden"},attrs:{title:"增加商品",visible:this.visible,width:"90%","before-close":e.closeDialog,center:"",top:"3vh"}},[t("div",{staticStyle:{"overflow-y":"auto",height:"70vh"}},[t("el-form",{attrs:{model:e.good,"label-position":"right","label-width":"80px"}},[t("el-form-item",{attrs:{label:"商品名称"}},[t("el-input",{model:{value:e.good.goodName,callback:function(t){e.$set(e.good,"goodName",t)},expression:"good.goodName"}})],1),t("el-form-item",{attrs:{label:"商品价格"}},[t("el-input",{model:{value:e.good.goodPrice,callback:function(t){e.$set(e.good,"goodPrice",t)},expression:"good.goodPrice"}})],1),t("el-form-item",{attrs:{label:"商品图片"}},[t("UploadPicture",{attrs:{urls:e.good.goodImage},on:{"update:urls":function(t){return e.$set(e.good,"goodImage",t)}}})],1),t("el-form-item",{attrs:{label:"剩余库存"}},[t("el-input",{model:{value:e.good.goodNum,callback:function(t){e.$set(e.good,"goodNum",t)},expression:"good.goodNum"}})],1),t("el-form-item",{attrs:{label:"商品种类"}},[t("el-cascader",{attrs:{options:e.options,props:{expandTrigger:"hover",checkStrictly:!0},clearable:""},on:{change:e.handleChange},model:{value:e.good.categoryId,callback:function(t){e.$set(e.good,"categoryId",t)},expression:"good.categoryId"}})],1),t("quill-editor",{ref:"myQuillEditor",attrs:{options:e.editorOption},model:{value:e.good.goodDesc,callback:function(t){e.$set(e.good,"goodDesc",t)},expression:"good.goodDesc"}})],1)],1),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){return e.closeDialog()}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.sureDialog()}}},[e._v("确 定")])],1)])},I=[],_={name:"GoodsAdd",data(){return{good:{goodName:"",goodPrice:"",goodImage:[],goodDesc:"",categoryId:void 0},options:[],editorOption:{}}},props:{visible:{type:Boolean,required:!0}},components:{UploadPicture:f},methods:{openDialog(e){this.options=e,this.$emit("update:visible",!0)},closeDialog(){this.$emit("update:visible",!1)},sureDialog(){this.$emit("update:visible",!1),(0,r.OZ)({goodName:this.good.goodName,goodPrice:this.good.goodPrice,goodImage:this.good.goodImage,goodDesc:this.good.goodDesc,categoryId:this.good.categoryId}).then((e=>{this.$emit("updateGoods"),console.log(e)})).catch((e=>{console.log(e)}))},handleChange(e){Array.isArray(e)&&e.length>0?this.good.categoryId=e[e.length-1]:this.good.categoryId=void 0}}},D=_,k=(0,p.A)(D,G,I,!1,null,null,null),P=k.exports,S=function(){var e=this,t=e._self._c;return t("el-dialog",{attrs:{title:"订单查看",visible:this.visible,width:"50%","before-close":e.closeDialog}},[t("h3",{staticStyle:{"text-align":"center"}},[e._v(e._s(e.good.goodName))]),t("el-table",{staticStyle:{width:"100%"},attrs:{data:e.orders,"max-height":"250","row-class-name":e.confirmedHeightlight}},[t("el-table-column",{attrs:{prop:"buyerPhone",label:"买家手机",width:"150"}}),t("el-table-column",{attrs:{prop:"buyerAddress",label:"买家地址",width:"120"}}),t("el-table-column",{attrs:{prop:"buyerDesc",label:"买家备注",width:"120"}}),t("el-table-column",{attrs:{prop:"buyerName",label:"买家姓名",width:"120"}}),t("el-table-column",{attrs:{prop:"buyerGoodsNum",label:"买家购买商品数量",width:"120"}}),t("el-table-column",{attrs:{width:"210",label:"确认状态"},scopedSlots:e._u([{key:"default",fn:function(o){return[0===o.row.orderState?t("div",[e._v("订单正在进行中")]):e._e(),1===o.row.orderState?t("div",[e._v("订单被卖家取消")]):e._e(),2===o.row.orderState?t("div",[e._v("订单完成")]):e._e()]}}])}),t("el-table-column",{attrs:{fixed:"right",width:"210"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("div",{staticStyle:{display:"flex","justify-content":"center"}},[0===o.row.orderState?t("el-button",{attrs:{type:"primary",icon:"el-icon-check",size:"mini"},nativeOn:{click:function(t){return t.preventDefault(),e.ConfirmSellGood(o.row.orderId)}}}):e._e(),0===o.row.orderState?t("el-button",{attrs:{type:"primary",icon:"el-icon-close",size:"mini"},nativeOn:{click:function(t){return t.preventDefault(),e.CancelSellGood(o.row.orderId)}}}):e._e()],1)]}}])})],1),e.totalOrders>e.pageSize?t("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next",total:e.totalOrders},on:{"current-change":e.handlePageChange}}):e._e(),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){return e.closeDialog()}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.sureDialog()}}},[e._v("确 定")])],1)],1)},A=[],C=o(79),x={name:"BuyerView",data(){return{good:{goodId:1,goodName:"loading...",goodPrice:"loading...",goodImage:""},currentPage:1,pageSize:8,totalOrders:0,orders:[]}},props:{visible:{type:Boolean,required:!0}},methods:{openDialog(e){Object.assign(this.good,e),this.$emit("update:visible",!0),this.GetOrders()},GetOrders(){(0,C.WF)({goodId:this.good.goodId,currentPage:this.currentPage,pageSize:this.pageSize}).then((e=>{this.orders=e.data.data.orders,this.totalOrders=e.data.data.totalOrders})).catch((e=>{console.log(e)}))},closeDialog(){this.$emit("update:visible",!1)},sureDialog(){this.$emit("updateGoods"),this.$emit("update:visible",!1)},confirmedHeightlight({row:e}){return e.isConfirmed?"confirmed-row":""},ConfirmSellGood(e){const t=this.good.goodId;(0,C.iD)({orderId:e,goodId:t}).then((e=>{this.GetOrders(),console.log(e)})).catch((e=>{console.log(e)}))},CancelSellGood(e){const t=this.good.goodId;(0,C.Ii)({orderId:e,goodId:t}).then((e=>{this.GetOrders(),console.log(e)})).catch((e=>{console.log(e)}))},handlePageChange(e){this.currentPage=e,this.GetOrders()}}},N=x,$=(0,p.A)(N,S,A,!1,null,null,null),V=$.exports,O=o(4959),z={name:"GoodsView",methods:{handlePageChange(e){this.currentPage=e,this.GetGoods()},DeleteGood(e){(0,r.JH)(e).then((e=>{this.GetGoods(),console.log(e)})).catch((e=>{console.log(e)}))},GetGoods(){this.isload=!0,(0,r.HI)({pageNum:this.currentPage,pageSize:this.pageSize}).then((e=>{this.goods=e.data.data.goods,this.totalProducts=e.data.data.totalGoods,this.isload=!1,this.weberror=!1,this.Getcategoty(),this.updatekey=!this.updatekey})).catch((e=>{this.isload=!1,this.weberror=!0,console.log(e)}))},EditGoods(e){this.$refs.GoodsEdit.openDialog(e,this.category)},AddGoods(){this.$refs.GoodsAdd.openDialog(this.category)},ViewBuyers(e){this.$refs.BuyerView.openDialog(e)},getHeight(){this.tableConfig.height=window.innerHeight-100},tableRowClassName({row:e}){return 0==e.goodNum?"warning-row":""},Getcategoty(){(0,r.p7)().then((e=>{this.category=e.data.data.categoryList,this.goods.map((e=>(e.categoryName=this.findLabelsByValues(this.category,e.categoryId),e))),this.updatekey=!this.updatekey}))},findLabelsByValues(e,t,o=[]){for(let i of e){if(i.value===t)return[...o,i.label].join("/");if(i.children){const e=this.findLabelsByValues(i.children,t,[...o,i.label]);if(e)return e}}return""}},components:{GoodsEdit:w,GoodsAdd:P,BuyerView:V,webErrorResult:O.A},data(){return{goods:[],GoodsEditVisible:!1,GoodsAddVisible:!1,BuyerViewVisible:!1,currentPage:1,pageSize:8,totalProducts:0,tableConfig:{height:200},isload:!0,weberror:!1,category:[],updatekey:!1}},created(){this.GetGoods(),this.getHeight(),window.addEventListener("resize",this.getHeight)},destroyed(){window.removeEventListener("resize",this.getHeight)}},B=z,E=(0,p.A)(B,i,l,!1,null,null,null),L=E.exports},79:function(e,t,o){o.d(t,{Dm:function(){return d},HW:function(){return l},Ii:function(){return s},WF:function(){return r},iD:function(){return a}});var i=o(5720);function l(e){return(0,i.A)({url:"/order/buy",method:"post",data:e})}function r(e){return(0,i.A)({url:"/order/sellerlist",method:"get",params:e})}function a(e){return(0,i.A)({url:"/order/confirmsell",method:"post",data:e})}function s(e){return(0,i.A)({url:"/order/cancelsell",method:"post",data:e})}function d(e){return(0,i.A)({url:"/order/buyerlist",method:"get",params:e})}},6551:function(e,t,o){o.d(t,{HI:function(){return l},JH:function(){return s},OZ:function(){return d},_P:function(){return r},iJ:function(){return a},p7:function(){return n}});var i=o(5720);function l(e){return(0,i.A)({url:"/good/list",params:e,method:"get"})}function r(e){return(0,i.A)({url:"/good/detail",method:"get",params:{id:e}})}function a(e){return(0,i.A)({url:"/good/update",data:e,method:"update"})}function s(e){return(0,i.A)({url:"/good/delete",data:{goodId:e},method:"delete"})}function d(e){return(0,i.A)({url:"/good/add",data:e,method:"post"})}function n(){return(0,i.A)({url:"/good/category",method:"get"})}}}]);
//# sourceMappingURL=97.8d2a56e6.js.map