<template>
    <div id="buyerOrder">
        <el-table :data="orders" style="width: 100%" :row-class-name="tableRowClassName"
            :height="tableConfig.height">
            <el-table-column label="商品图片" width="120" fixed>
                <template slot-scope="scope">
                    <img :src="scope.row.goodImage"
                        style="width: 100px; height: 100px; object-fit: cover; object-position: center;">
                </template>
            </el-table-column>
            <el-table-column width="210" label="确认状态">
                <template slot-scope="scope">
                    <div>{{ getStateTextFromMap(scope.row.orderState) }}</div>
                </template>
            </el-table-column>
            <el-table-column prop="goodName" label="商品名称" width="150">
            </el-table-column>
            <el-table-column prop="orderPrice" label="支付价格" width="120">
            </el-table-column>
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

            <el-table-column fixed="right" width="210">
                <template slot-scope="scope">
                  <div style="display: flex; justify-content: center;">
                    <el-button @click.native.prevent="ChangeState(scope.row,true)" type="primary" icon="el-icon-check"
                      size="mini" v-if="scope.row.orderState===6">
                    </el-button>
                    <el-button @click.native.prevent="ChangeState(scope.row,false)" type="primary" icon="el-icon-close"
                      size="mini" v-if="scope.row.orderState===0 || scope.row.orderState===4 || scope.row.orderState===5">
                    </el-button>
                  </div>
                </template>
              </el-table-column>
        </el-table>
        <el-pagination :current-page="currentPage" :page-size="pageSize" layout="prev, pager, next" :total="totalOrders" background
            @current-change="handlePageChange" v-if="totalOrders > pageSize">
        </el-pagination>
    </div>
</template>

<style>
    .el-table .confirmed-row {
        background: rgb(245, 255, 201);
    }
</style>

<script>
    import { getBuyerOrders,changeState } from '@/api/order/order.js'
    import { ORDER_STATES,getOrderStateText} from '@/utils/common.js'

    export default {
        name: 'buyerOrderView',
        data() {
            return {
                orders: [],
                currentPage: 1,
                pageSize: 8,
                totalOrders: 0,
                tableConfig: {
                    height: 200
                },
                ORDER_STATES
            };
        },
        methods: {
            GetOrders() {
                const token = this.$store.getters.token
                const params = {
                    token,
                    pageNum: this.currentPage,
                    pageSize: this.pageSize
                }
                getBuyerOrders(params).then(res => {
                    this.orders = res.data.data.orders
                    this.totalOrders = res.data.data.totalOrders
                }).catch(err => {
                    console.log(err)
                })
            },
            //自适应表格高度  8(body边距)+60(navheader边距)+32(底部分页边距)
            getHeight() {
                this.tableConfig.height = window.innerHeight - 100
            },
            tableRowClassName({ row }) {
                if (row.isConfirmed == true) {
                    return 'warning-row'
                }
                return ''
            },
            ChangeState(order, okbtn) {
                let stateToChange = -1
                const goodId = this.orders.goodId
                const orderId = order.orderId
                const orderState = order.orderState
                if (okbtn) {
                    if (orderState === ORDER_STATES.SHIPPING_STARTED) {
                        stateToChange = ORDER_STATES.ORDER_COMPLETED
                    }
                } else {
                    if (orderState === ORDER_STATES.CUSTOMER_ORDER || 
                        orderState === ORDER_STATES.SELLER_CONFIRMED || 
                        orderState === ORDER_STATES.STOCK_READY) {
                        stateToChange = ORDER_STATES.BUYER_CANCELLED
                    }
                }
                changeState({ orderId, orderState: stateToChange, goodId }).then(
                    res => {
                        this.GetOrders()
                        console.log(res)
                    }
                ).catch(err => {
                    console.log(err)
                })
            },
            getStateTextFromMap(orderState) {
                return getOrderStateText(orderState);
            }
        },
        created() {
            this.getHeight()
            window.addEventListener('resize', this.getHeight)
            this.GetOrders()
        },
        destroyed() {
            window.removeEventListener('resize', this.getHeight)
        }
    };
</script>
<style>
    #buyerOrder .warning-row {
        background-color: rgb(253, 253, 206);
    }
</style>