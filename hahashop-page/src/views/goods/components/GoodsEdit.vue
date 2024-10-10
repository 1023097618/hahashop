
<template>
    <el-dialog
    title="修改商品"
    :visible="this.visible"
    width="30%"
    :before-close="closeDialog">
    <el-form :model="good" label-position="right" label-width="80px">
      <el-form-item label="商品名称">
        <el-input v-model="good.goodName"></el-input>
      </el-form-item>
      <el-form-item label="商品价格">
        <el-input v-model="good.goodPrice"></el-input>
      </el-form-item>
      <el-form-item label="商品图片">
        <el-input v-model="good.goodImage"></el-input>
      </el-form-item>
      <el-form-item label="商品备注">
        <el-input type="textarea" v-model="good.goodDesc"></el-input>
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
    import{updateGoods} from '@/api/shop/goods.js'
    export default {
      name:'GoodsEdit',
      data() {
        return {
            good:{
            goodName: '',
            goodPrice: '',
            goodImage: '',
            goodDesc: ''
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
          openDialog(good){
            Object.assign(this.good, good);
            this.$emit('update:visible',true)
            getGoodsDetail(this.good.goodId)
            .then(res=>{
              this.good.goodDesc = res.data.data.goodDesc
            }).catch(err=>{
              console.log(err)
            })
          },
          closeDialog(){
              this.$emit('update:visible',false)
          },
          sureDialog(){
              this.$emit('update:visible',false)
              updateGoods({
                goodName:this.good.goodName,
                goodPrice:this.good.goodPrice,
                goodImage:this.good.goodImage,
                goodDesc:this.good.goodDesc,
                goodId:this.good.goodId
              }).then(res=>{
                console.log(res)
              }).catch(err=>{
                console.log(err)
              })
          }
      }
    };
  </script>