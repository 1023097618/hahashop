<template>
    <div id="cart">
        <el-skeleton :rows="3" animated :loading="isload" />
        <webErrorResult :error="weberror"></webErrorResult>
        <el-table :data="goods" style="width: 100;" :height="tableConfig.height" v-if="!isload&&!weberror"
            @selection-change="selection">
            <el-table-column type="selection">
            </el-table-column>
            <el-table-column prop="goodId" label="商品id" width="120" fixed>
            </el-table-column>
            <el-table-column label="商品图片" width="120">
                <template slot-scope="scope">
                    <img :src="scope.row.goodImage"
                        style="width: 100px; height: 100px; object-fit: cover; object-position: center;">
                </template>
            </el-table-column>
            <el-table-column prop="goodName" label="商品名称" width="150">
            </el-table-column>
            <el-table-column prop="goodPrice" label="商品价格" width="120">
            </el-table-column>
            <el-table-column prop="goodNum" label="商品剩余数量" width="120">
            </el-table-column>
            <el-table-column fixed="right" width="210" label="购物数量">
                <template slot-scope="scope">
                    <div style="display: flex; justify-content: center;">
                        <el-input v-model.number="scope.row.cartGoodNum" @change="ChangeCartGoodNum(scope.row)"
                            type="number"></el-input>
                    </div>
                </template>
            </el-table-column>
            <el-table-column fixed="right" width="210">
                <template slot="header">
                    <div style="display: flex; justify-content: center;">
                        <el-button size="mini" type="primary" icon="el-icon-shopping-cart-2"
                            @click="BuyGoods()" :disabled="selectionGoods.length===0"></el-button>
                        <el-button size="mini" type="primary" icon="el-icon-star-on"
                            @click="CollectGoods()" :disabled="selectionGoods.length===0"></el-button>
                    </div>
                </template>
                <template slot-scope="scope">
                    <div style="display: flex; justify-content: center;">
                        <el-button @click.native.prevent="DeleteCartGood(scope.row.goodId)" type="danger"
                            icon="el-icon-delete" size="mini">
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :current-page="currentPage" :page-size="pageSize" layout="prev, pager, next" background
            :total="totalHistories" @current-change="handlePageChange" v-if="totalHistories > pageSize">
        </el-pagination>
    </div>
</template>

<script>
    import { getCartGoods, deleteCartGood, changeCartGoodNum } from '@/api/cart/cart.js'
    import { buyGoods } from '@/api/order/order.js'
    import webErrorResult from '@/components/webErrorResult.vue'
    import { Message } from "element-ui";
    import {addCollectGood} from '@/api/collect/collect.js'
    export default {
        name: 'HistoriesView',
        methods: {
            handlePageChange(page) {
                this.currentPage = page
                this.GetCartGoods()
            },
            DeleteCartGood(goodId) {
                deleteCartGood({ goodId }).then(
                    res => {
                        this.GetCartGoods()
                        console.log(res)
                    }
                ).catch(err => {
                    console.log(err)
                })
            },
            GetCartGoods() {
                this.isload = true
                this.weberror = false
                getCartGoods(
                    {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize
                    }
                ).then(res => {
                    this.goods = res.data.data.goods
                    this.isload = false
                    this.weberror = false
                }).catch(err => {
                    this.isload = false
                    this.weberror = true
                    console.log(err)
                })
            },
            //自适应表格高度  8(body边距)+60(navheader边距)+32(底部分页边距)
            getHeight() {
                this.tableConfig.height = window.innerHeight - 100
            },
            ChangeCartGoodNum(e) {
                const goodId = e.goodId
                const cartGoodNum = e.cartGoodNum
                changeCartGoodNum({ goodId, cartGoodNum }).then(result => {
                    if (result.data.code !== 400) {
                        Message.error('更改数量失败')
                        this.GetCartGoods()
                    }
                }).catch(err => {
                    console.log(err)
                })
            },
            selection(e) {
                this.selectionGoods = e
            },
            BuyGoods() {
                const user = this.$store.getters.user
                this.selectionGoods.forEach(item => {
                    buyGoods({
                        buyerRealName: user.userRealName,
                        buyerPhone: user.userPhone,
                        buyerAddress: user.userAddress,
                        buyerDesc: '',
                        goodId: item.goodId,
                        buyerGoodsNum: item.cartGoodNum
                    }).then(() => {
                        Message.success(`您的商品${item.goodName}购买成功`)
                        deleteCartGood({ goodId:item.goodId }).then(
                            () => {
                                this.goods=this.goods.filter(ite=>ite.goodId!=item.goodId)
                            }
                        ).catch(err => {
                            console.log(err)
                        })
                    }).catch(err => {
                        console.log(err)
                        Message.error(`您的商品${item.goodName}没有购买成功`)
                    })
                })
                this.selectionGoods = []
            },
            CollectGoods(){
                this.selectionGoods.forEach(item => {
                    addCollectGood({goodId:item.goodId}).then(()=>{
                        Message.success(`您的商品${item.goodName}收藏成功`)
                    }).catch(err=>{
                        console.log(err)
                        Message.error(`您的商品${item.goodName}没有收藏成功`)
                    })
                    
                })
                this.selectionGoods = []
            }
        }
        ,
        data() {
            return {
                goods: [],
                currentPage: 1,
                pageSize: 8,
                totalHistories: 0,
                tableConfig: {
                    height: 200
                },
                isload: true,
                weberror: false,
                selectionGoods: []
            }
        },
        created() {
            this.GetCartGoods()
            this.getHeight()
            window.addEventListener('resize', this.getHeight)
        },
        destroyed() {
            window.removeEventListener('resize', this.getHeight)
        },
        components: {
            webErrorResult
        }
    }
</script>

<style>
    #history .el-table__body .cell {
        height: 100px;
    }
</style>