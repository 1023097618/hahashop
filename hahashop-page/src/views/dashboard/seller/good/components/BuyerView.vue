<template>
  <el-dialog title="订单查看" :visible="this.visible" width="50%" :before-close="closeDialog" :close-on-click-modal="false">
    <h3 style="text-align: center;">{{good.goodName}}</h3>
    <el-table :data="orders" style="width: 100%" max-height="250" :row-class-name="confirmedHeightlight">
      <el-table-column prop="buyerPhone" label="买家手机" width="150">
      </el-table-column>
      <el-table-column prop="buyerAddress" label="买家地址" width="120">
      </el-table-column>
      <el-table-column prop="buyerDesc" label="买家备注" width="120">
      </el-table-column>
      <el-table-column prop="buyerRealName" label="买家姓名" width="120">
      </el-table-column>
      <el-table-column prop="buyerGoodsNum" label="买家购买商品数量" width="120">
      </el-table-column>
      <el-table-column prop="orderPrice" label="支付价格" width="120">
      </el-table-column>
      <el-table-column width="210" label="确认状态">
        <template slot-scope="scope">
          <div>{{ getStateTextFromMap(scope.row.orderState) }}</div>
        </template>
    </el-table-column>
      <el-table-column fixed="right" width="210">
        <template slot-scope="scope">
          <div style="display: flex; justify-content: center;">
            <el-button @click.native.prevent="ChangeState(scope.row,true)" type="primary" icon="el-icon-check"
              size="mini" v-if="scope.row.orderState===0 || scope.row.orderState===4 || scope.row.orderState===5">
            </el-button>
            <el-button @click.native.prevent="ChangeState(scope.row,false)" type="primary" icon="el-icon-close"
              size="mini" v-if="scope.row.orderState===0 || scope.row.orderState===4 || scope.row.orderState===5 || scope.row.orderState===6">
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
  import { getOrders, changeState, cancelSellGood } from '@/api/order/order.js'
  import { ORDER_STATES,getOrderStateText } from '@/utils/common';
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
      ChangeState(order,okbtn) {
        let stateToChange = -1
        const goodId=this.good.goodId
        const orderId=order.orderId
        const orderState=order.orderState
        if(okbtn){
          if(orderState === ORDER_STATES.CUSTOMER_ORDER){
            stateToChange = ORDER_STATES.SELLER_CONFIRMED
          }
          if(orderState === ORDER_STATES.SELLER_CONFIRMED){
            stateToChange = ORDER_STATES.STOCK_READY
          }
          if(orderState === ORDER_STATES.STOCK_READY){
            stateToChange = ORDER_STATES.SHIPPING_STARTED
          }
        }else{
          if(orderState===ORDER_STATES.CUSTOMER_ORDER || 
             orderState===ORDER_STATES.SELLER_CONFIRMED || 
             orderState===ORDER_STATES.STOCK_READY || 
             orderState===ORDER_STATES.SHIPPING_STARTED){
            stateToChange = ORDER_STATES.SELLER_CANCELLED
          }
        }
        changeState({ orderId,orderState:stateToChange,goodId}).then(
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
      },
      getStateTextFromMap(orderState) {
        return getOrderStateText(orderState);
      }
    }
  };
</script>