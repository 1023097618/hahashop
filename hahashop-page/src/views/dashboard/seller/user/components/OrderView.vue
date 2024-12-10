<template>
    <el-dialog title="订单查看" :visible="this.visible" width="50%" :before-close="closeDialog" :close-on-click-modal="false">
      <h3 style="text-align: center;">用户姓名：{{user.userRealName}}</h3>
      <el-table :data="orders" style="width: 100%" max-height="250" :row-class-name="confirmedHeightlight">
        <el-table-column label="商品图片" width="120" fixed>
          <template slot-scope="scope">
            <img :src="scope.row.goodImage"
              style="width: 100px; height: 100px; object-fit: cover; object-position: center;">
          </template>
        </el-table-column>
        <el-table-column prop="buyerRealName" label="购买者姓名" width="150">
        </el-table-column>
        <el-table-column prop="buyerPhone" label="购买者手机号" width="150">
        </el-table-column>
        <el-table-column prop="buyerAddress" label="购买者地址" width="120">
        </el-table-column>
        <el-table-column prop="buyerGoodsNum" label="购买商品数量" width="120">
        </el-table-column>
        <el-table-column prop="goodName" label="商品名称" width="120">
        </el-table-column>
        <el-table-column prop="orderPrice" label="支付价格" width="120">
        </el-table-column>
        <el-table-column prop="buyerDesc" label="购买者备注" width="120">
        </el-table-column>
        <el-table-column width="210" label="确认状态">
          <template slot-scope="scope">
            <div>{{getStateTextFromMap(scope.row.orderState)}}</div>
          </template>
      </el-table-column>
      
      </el-table>
      <el-pagination :current-page="currentPage" :page-size="pageSize" layout="prev, pager, next" background
      :total="totalOrders" @current-change="handlePageChange" v-if="totalOrders > pageSize">
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
    import { getUserOrder } from '@/api/user/user.js'
    import {getOrderStateText} from '@/utils/common.js'
    export default {
      name: 'BuyerView',
      data() {
        return {
          user:{},
          orders: [],
          currentPage: 1,
          pageSize: 8,
          totalOrders: 0
        };
      },
  
      props: {
        visible: {
          type: Boolean,
          required: true
        }
      },
      methods: {
        openDialog(user) {
          Object.assign(this.user,user)
          this.$emit('update:visible', true)
          this.GetUserOrder()
        },
        GetUserOrder(){
          getUserOrder(
            {userId:this.user.userId,
              pageNum:this.currentPage,
              pageSize:this.pageSize
            }).then(res => {
            this.orders = res.data.data.orders,
            this.totalOrders=res.data.data.totalOrders
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
        handlePageChange(page) {
            this.currentPage = page
            this.GetUserOrder()
        },
        getStateTextFromMap(orderState) {
          return getOrderStateText(orderState);
        }
      }
    };
  </script>