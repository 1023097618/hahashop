
<template>
  <el-dialog
  title="买家信息填写"
  :visible="this.visible"
  width="30%"
  :before-close="closeDialog">
  <div style="text-align: center;">商品名:{{product.goodName}}</div>
  <div style="text-align: center;">商品价格:{{product.goodPrice}}</div>
  <div style="text-align: center;">商品描述:{{product.goodDesc}}</div>
  <el-form :model="form" label-position="right" label-width="80px">
    <el-form-item label="姓名">
      <el-input v-model="form.buyerName"></el-input>
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
          buyerName: '',
          buyerPhone: '',
          buyerAddress: '',
          buyerDesc: ''
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
        openDialog(product){
          Object.assign(this.product, product);
          this.$emit('update:visible',true)
          getGoodsDetail(this.product.goodId)
          .then(res=>{
            this.product.goodDesc = res.data.data.goodDesc
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
        }
    }
  };
</script>