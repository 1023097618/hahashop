<template>
  <el-dialog title="订单查看" :visible="this.visible" width="50%" :before-close="closeDialog">
    <h3 style="text-align: center;">{{good.goodName}}</h3>
    <el-table :data="buyers" style="width: 100%" max-height="250" :row-class-name="confirmedHeightlight">
      <el-table-column prop="buyerPhone" label="买家手机" width="150">
      </el-table-column>
      <el-table-column prop="buyerAddress" label="买家地址" width="120">
      </el-table-column>
      <el-table-column prop="buyerDesc" label="买家备注" width="120">
      </el-table-column>
      <el-table-column prop="buyerName" label="买家姓名" width="120">
      </el-table-column>
      <el-table-column fixed="right" width="210">
        <template slot-scope="scope">
          <div style="display: flex; justify-content: center;">
            <el-button @click.native.prevent="SellGood(scope.row.orderId)" type="primary" icon="el-icon-check" size="mini"
              v-if="scope.row.isConfirmed==false && good.goodState==0">
            </el-button>
            <el-button @click.native.prevent="CancelSellGood(scope.row.orderId)" type="primary" icon="el-icon-close" size="mini"
            v-if="scope.row.isConfirmed==true && good.goodState==1">
          </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog()">取 消</el-button>
      <el-button type="primary" @click="sureDialog()">确 定</el-button>
    </span>
  </el-dialog>
</template>

<style>
  .el-table .confirmed-row {
    background: rgb(245, 255, 201);
  }
</style>

<script>
  import { getOrders,sellGood,cancelSellGood } from '@/api/order/order.js'

  export default {
    name: 'BuyerView',
    data() {
      return {
        good: {
          goodId: 1,
          goodName: '比亚迪跑车',
          goodPrice: '100 ￥',
          goodImage: '',
          goodState: 0
        },
        buyers: [{
          buyerPhone: '110',
          buyerAddress: '商专',
          buyerDesc: '必须选我',
          buyerName: 'zhangsan',
          orderId:1,
          isConfirmed: true
        }]
      };
    },

    props: {
      visible: {
        type: Boolean,
        required: true
      }
    },
    methods: {
      openDialog(good) {
        Object.assign(this.good, good)
        this.$emit('update:visible', true)
        getOrders(this.good.goodId).then(res => {
          this.buyers = res.data.data.orders
          this.good.goodState = res.data.data.goodState
        }).catch(err => {
          console.log(err)
        })
      },
      closeDialog() {
        this.$emit('update:visible', false)
      },
      sureDialog() {
        this.$emit('update:visible', false)
      },
      confirmedHeightlight({ row }) {
        if (row.isConfirmed) {
          return 'confirmed-row'
        }
        return ''
      },
      SellGood(orderId){
        const goodId=this.good.goodId
        sellGood({orderId,goodId}).then(
          res=>{
            console.log(res)
          }
        ).catch(err=>{
          console.log(err)
        })
      },
      CancelSellGood(orderId){
        const goodId=this.good.goodId
        cancelSellGood({orderId,goodId}).then(
          res=>{
            console.log(res)
          }
        ).catch(err=>{
          console.log(err)
        })
      }
    }
  };
</script>