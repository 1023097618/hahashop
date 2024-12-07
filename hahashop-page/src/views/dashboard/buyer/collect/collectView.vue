<template>
    <div id="cart">
        <el-skeleton :rows="3" animated :loading="isload" />
        <webErrorResult :error="weberror"></webErrorResult>
        <el-table :data="goods" style="width: 100;" :height="tableConfig.height" v-if="!isload&&!weberror">
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
            <el-table-column fixed="right" width="210">
                <template slot-scope="scope">
                    <div style="display: flex; justify-content: center;">
                        <el-button @click.native.prevent="DeleteCollectGood(scope.row.goodId)" type="danger"
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
    import { getCollectGoods,deleteCollectGood } from '@/api/collect/collect.js'
    import webErrorResult from '@/components/webErrorResult.vue'
    export default {
        name: 'HistoriesView',
        methods: {
            handlePageChange(page) {
                this.currentPage = page
                this.GetCollectGoods()
            },
            DeleteCollectGood(goodId) {
                deleteCollectGood({goodId}).then(
                    res => {
                        this.GetCollectGoods()
                        console.log(res)
                    }
                ).catch(err => {
                    console.log(err)
                })
            },
            GetCollectGoods() {
                this.isload = true
                this.weberror=false
                getCollectGoods(
                    {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize
                    }
                ).then(res => {
                    this.goods = res.data.data.goods
                    this.totalHistories = res.data.data.totalHistories
                    this.isload = false
                    this.weberror = false
                }).catch(err => {
                    this.isload = false
                    this.weberror = true
                    console.log(err)
                })
            },
            //自适应表格高度  8+60+32
            getHeight() {
                this.tableConfig.height = window.innerHeight - 100
            },
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
                weberror: false
            }
        },
        created() {
            this.GetCollectGoods()
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

  #history .el-table__body .cell{
    height: 100px;
  }
</style>