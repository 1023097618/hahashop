<template>
    <div id="history">
        <el-skeleton :rows="3" animated :loading="isload" />
        <webErrorResult :error="weberror"></webErrorResult>
        <el-table :data="histories" style="width: 100;" :height="tableConfig.height" v-if="!isload&&!weberror">
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
            <el-table-column prop="goodDesc" label="商品描述" width="120">
            </el-table-column>
            <el-table-column prop="operationTime" label="操作时间" width="120">
            </el-table-column>
            <el-table-column fixed="right" width="210">
                <template slot-scope="scope">
                    <div style="display: flex; justify-content: center;">
                        <el-button @click.native.prevent="DeleteHistories(scope.row.historyId)" type="danger"
                            icon="el-icon-delete" size="mini">
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :current-page="currentPage" :page-size="pageSize" layout="prev, pager, next"
            :total="totalHistories" @current-change="handlePageChange" v-if="totalHistories > pageSize">
        </el-pagination>
    </div>
</template>

<script>
    import { getHistories, deleteHistory } from '@/api/shop/history.js'
    import webErrorResult from '@/components/webErrorResult.vue'
    export default {
        name: 'HistoriesView',
        methods: {
            handlePageChange(page) {
                this.currentPage = page
                this.GetHistories()
            },
            DeleteHistories(id) {
                deleteHistory(id).then(
                    res => {
                        this.GetHistories()
                        console.log(res)
                    }
                ).catch(err => {
                    console.log(err)
                })
            },
            GetHistories() {
                this.isload = true
                this.weberror=false
                getHistories(
                    {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize
                    }
                ).then(res => {
                    this.histories = res.data.data.histories
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
                histories: [],
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
            this.GetHistories()
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