
<template>
  <el-dialog
  title="买家信息填写"
  :visible="this.visible"
  width="30%"
  :before-close="closeDialog"
  :close-on-click-modal="false">
  <div style="text-align: center;">商品名:{{product.goodName}}</div>
  <div style="text-align: center;">商品价格:{{product.goodPrice}}</div>
  <!-- <div style="text-align: center;">商品描述：<div class="rich-text-content" v-html="product.goodDesc"></div></div> -->
  <!-- 这样更安全，相信我，我们的hahashop是世界上最安全的网站！ -->
  <div style="text-align: center;">商品描述：<div class="rich-text-content" v-dompurify-html="product.goodDesc"></div></div>
  <el-form :model="form" label-position="right" label-width="80px">
    <el-form-item label="姓名">
      <el-input v-model="form.buyerRealName"></el-input>
    </el-form-item>
    <el-form-item label="手机号">
      <el-input v-model="form.buyerPhone"></el-input>
    </el-form-item>
    <el-form-item label="交易地点">
      <el-input v-model="form.buyerAddress"></el-input>
    </el-form-item>
    <el-form-item label="备注">
      <el-input type="textarea" v-model="form.buyerDesc"></el-input>
    </el-form-item>
    <el-form-item label="购买数量">
      <el-input-number v-model="form.buyerGoodsNum" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
    </el-form-item>
    
  </el-form>
  <span slot="footer" class="dialog-footer">
    <el-button @click="closeDialog()">取 消</el-button>
    <el-button type="primary" @click="sureDialog()">确 定</el-button>
  </span>
</el-dialog>
</template>

<script>
  import {getGoodsDetail} from '@/api/shop/goods.js'
  import{buyGoods} from '@/api/order/order.js'
  export default {
    data() {
      return {
        form:{
          buyerRealName: '',
          buyerPhone: '',
          buyerAddress: '',
          buyerDesc: '',
          buyerGoodsNum:1
        },
        product:{
          goodDesc:'',
          goodPrice:'',
          goodName:'',
          goodImage:''
        }
      };
    },
    
    props:{
        visible:{
            type:Boolean,
            required:true
        }
    },
    methods: {
        openDialog(product,user){
          Object.assign(this.product, product);
          this.form.buyerRealName=user.userRealName
          this.form.buyerPhone=user.userPhone
          this.form.buyerAddress=user.userAddress
          this.$emit('update:visible',true)
          getGoodsDetail(this.product.goodId)
          .then(res=>{

            this.product.goodDesc = res.data.data.goodDesc
            console.log(this.product.goodDesc)
          }).catch(err=>{
            console.log(err)
          })
        },
        closeDialog(){
            this.$emit('update:visible',false)
        },
        sureDialog(){
            this.$emit('update:visible',false)
            buyGoods({...this.form,goodId:this.product.goodId}).then(res=>{
              console.log(res)
            }).catch(err=>{
              console.log(err)
            })
        },
        handleChange(value) {
         this.form.buyerGoodsNum=value
      }
    }
  };
</script>