<template>
  <el-dialog title="买家信息填写" :visible="this.visible" width="70%" top="3vh" :before-close="closeDialog"
    :close-on-click-modal="false">
    <div style="overflow-y: auto; height: 70vh">
      <div style="text-align: center;">商品名:{{product.goodName}}</div>
      <div style="text-align: center;">商品价格:{{product.goodPrice}}</div>
      <!-- <div style="text-align: center;">商品描述：<div class="rich-text-content" v-html="product.goodDesc"></div></div> -->
      <!-- 这样更安全，相信我，我们的hahashop是世界上最安全的网站！ -->
      <div style="text-align: center;">商品描述：<div class="rich-text-content" v-dompurify-html="product.goodDesc"></div>
      </div>
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
          <el-input-number v-model="form.buyerGoodsNum" @change="handleChange" :min="1" :max="10"
            label="描述文字"></el-input-number>
        </el-form-item>
      </el-form>
    </div>
    <span slot="footer" class="dialog-footer" style="display: flex; justify-content: space-between;">
      <div>
        <div style="height: 10px; display: flex; justify-content:flex-end;">
          <i class="sm_btn" :class="{'el-icon-star-on':product.isCollect===1,'el-icon-star-off':product.isCollect===0}" @click.stop="handleCollect" ></i>
          <i class="sm_btn" :class="{'el-icon-shopping-cart-2':product.isCart===0,'el-icon-shopping-cart-full':product.isCart===1}"  @click.stop="handleCart" style="margin-left: 30px;"></i>
        </div>
      </div>
      <div>
        <el-button @click="closeDialog()">取 消</el-button>
        <el-button type="primary" @click="sureDialog()">确 定</el-button>
      </div>

    </span>
  </el-dialog>
</template>

<script>
  import { getGoodsDetail } from '@/api/shop/goods.js'
  import { buyGoods } from '@/api/order/order.js'
  import { addCartGood,deleteCartGood } from '@/api/cart/cart.js'
  import {addCollectGood,deleteCollectGood} from '@/api/collect/collect.js'
  export default {
    data() {
      return {
        form: {
          buyerRealName: '',
          buyerPhone: '',
          buyerAddress: '',
          buyerDesc: '',
          buyerGoodsNum: 1
        },
        product: {
          goodDesc: '',
          goodPrice: '',
          goodName: '',
          goodImage: '',
          isCollect:0,
          isCart:0
        }
      };
    },

    props: {
      visible: {
        type: Boolean,
        required: true
      }
    },
    methods: {
      openDialog(product, user) {
        Object.assign(this.product, product);
        this.form.buyerRealName = user.userRealName
        this.form.buyerPhone = user.userPhone
        this.form.buyerAddress = user.userAddress
        this.form.buyerDesc=''
        this.form.buyerGoodsNum=1
        this.$emit('update:visible', true)
        getGoodsDetail(this.product.goodId)
          .then(res => {
            this.product.goodDesc = res.data.data.goodDesc
            this.product.isCollect=res.data.data.isCollect
            this.product.isCart=res.data.data.isCart
          }).catch(err => {
            console.log(err)
          })
      },
      closeDialog() {
        this.$emit('update:visible', false)
      },
      sureDialog() {
        this.$emit('update:visible', false)
        buyGoods({ ...this.form, goodId: this.product.goodId }).then(res => {
          console.log(res)
        }).catch(err => {
          console.log(err)
        })
      },
      handleChange(value) {
        this.form.buyerGoodsNum = value
      },
      handleCart(){
        if(this.product.isCart===1){
          deleteCartGood({goodId:this.product.goodId}).then(result=>{
            if(result.data.code===400){
              this.product.isCart = 0
            }
          }).catch(err=>{
            console.log(err)
          })
        }else if(this.product.isCart===0){
          addCartGood({goodId:this.product.goodId}).then(result=>{
            if(result.data.code===400){
              this.product.isCart = 1
            }
          }).catch(err=>{
            console.log(err)
          })
        }
      },
      handleCollect(){
        if(this.product.isCollect===1){
          deleteCollectGood({goodId:this.product.goodId}).then(result=>{
            if(result.data.code===400){
              this.product.isCollect = 0
            }
          }).catch(err=>{
            console.log(err)
          })
        }else if(this.product.isCollect===0){
          addCollectGood({goodId:this.product.goodId}).then(result=>{
            if(result.data.code===400){
              this.product.isCollect = 1
            }
          }).catch(err=>{
            console.log(err)
          })
        }
      }
    }
  };
</script>

<style scoped>
  .el-dialog__wrapper{
    overflow: hidden;
  }
  .rich-text-content img {
  max-width: 100%;
  height: auto; /* 保持图片的纵横比 */
  display: block; /* 防止图片底部出现空白间隙 */
  margin: 0 auto; /* 如果需要居中图片，可以加上这行 */
}

.sm_btn{
    width: 7px;
    height: 7px;
    cursor: pointer;
    margin-left: 40px;
  }
</style>