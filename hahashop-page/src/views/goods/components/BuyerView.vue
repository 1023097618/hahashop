<template>
  <el-dialog title="订单查看" :visible="this.visible" width="50%" :before-close="closeDialog">
    <h3 style="text-align: center;">{{good.goodName}}</h3>
    <el-table :data="orders" style="width: 100%" max-height="250" :row-class-name="confirmedHeightlight">
      <el-table-column prop="buyerPhone" label="买家手机" width="150">
      </el-table-column>
      <el-table-column prop="buyerAddress" label="买家地址" width="120">
      </el-table-column>
      <el-table-column prop="buyerDesc" label="买家备注" width="120">
      </el-table-column>
      <el-table-column prop="buyerName" label="买家姓名" width="120">
      </el-table-column>
      <el-table-column prop="buyerGoodsNum" label="买家购买商品数量" width="120">
      </el-table-column>
      <el-table-column width="210" label="确认状态">
        <template slot-scope="scope">
            <div v-if="scope.row.orderState===0">订单正在进行中</div>
            <div v-if="scope.row.orderState===1">订单被卖家取消</div>
            <div v-if="scope.row.orderState===2">订单完成</div>
        </template>
    </el-table-column>
      <el-table-column fixed="right" width="210">
        <template slot-scope="scope">
          <div style="display: flex; justify-content: center;">
            <el-button @click.native.prevent="ConfirmSellGood(scope.row.orderId)" type="primary" icon="el-icon-check"
              size="mini" v-if="scope.row.orderState===0">
            </el-button>
            <el-button @click.native.prevent="CancelSellGood(scope.row.orderId)" type="primary" icon="el-icon-close"
              size="mini" v-if="scope.row.orderState===0">
            </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :current-page="currentPage" :page-size="pageSize" layout="prev, pager, next" :total="totalOrders"
      @current-change="handlePageChange" v-if="totalOrders > pageSize">
    </el-pagination>
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
  import { getOrders, confirmSellGood, cancelSellGood } from '@/api/order/order.js'

  export default {
    name: 'BuyerView',
    data() {
      return {
        good: {
          goodId: 1,
          goodName: 'loading...',
          goodPrice: 'loading...',
          goodImage: '',

        },
        currentPage: 1,
        pageSize: 8,
        totalOrders: 0,
        orders: []
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
        this.GetOrders()
      },
      GetOrders() {
        getOrders({
          goodId: this.good.goodId
          , currentPage: this.currentPage,
          pageSize: this.pageSize
        }).then(res => {
          this.orders = res.data.data.orders
          this.totalOrders = res.data.data.totalOrders
        }).catch(err => {
          console.log(err)
        })
      }
      ,
      closeDialog() {
        this.$emit('update:visible', false)
      },
      sureDialog() {
        this.$emit('updateGoods')
        this.$emit('update:visible', false)
      },
      confirmedHeightlight({ row }) {
        if (row.isConfirmed) {
          return 'confirmed-row'
        }
        return ''
      },
      ConfirmSellGood(orderId) {
        const goodId = this.good.goodId
        confirmSellGood({ orderId, goodId }).then(
          res => {
            this.GetOrders()
            console.log(res)
          }
        ).catch(err => {
          console.log(err)
        })
      },
      CancelSellGood(orderId) {
        const goodId = this.good.goodId
        cancelSellGood({ orderId, goodId }).then(
          res => {
            this.GetOrders()
            console.log(res)
          }
        ).catch(err => {
          console.log(err)
        })
      },
      handlePageChange(page) {
            this.currentPage = page
            this.GetOrders()
        }
    }
  };
</script>