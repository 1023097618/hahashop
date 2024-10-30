<template>
    <div id="userview">
        <el-skeleton :rows="3" animated :loading="isload" />
        <webErrorResult :error="weberror"></webErrorResult>
        <el-table :data="users" style="width: 100;" :height="tableConfig.height" v-if="!isload&&!weberror" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
            <el-table-column prop="userPhone" label="用户手机号" width="120" fixed>
            </el-table-column>
            <el-table-column prop="userRealName" label="用户真实姓名" width="150">
            </el-table-column>
            <el-table-column prop="userAddress" label="用户地址" width="120">
            </el-table-column>
            <el-table-column fixed="right" width="210" label="查看购买历史" >
                <template slot-scope="scope">
                    <div style="display: flex; justify-content: center;">
                        <el-button @click.native.prevent="ViewUserOrder(scope.row)" type="primary"
                            icon="el-icon-view" size="mini">
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :current-page="currentPage" :page-size="pageSize" layout="prev, pager, next"
            :total="totalUsers" @current-change="handlePageChange" v-if="totalUsers > pageSize">
        </el-pagination>
        <OrderView :visible.sync="OrderViewVisible" @updateUsers="GetUsers" ref="OrderView" />
    </div>
</template>

<script>
    import { getUsers } from '@/api/user/user.js'
    import webErrorResult from '@/components/webErrorResult.vue'
    import OrderView from './components/OrderView.vue'
    export default {
        name: 'HistoriesView',
        methods: {
            handlePageChange(page) {
                this.currentPage = page
                this.GetUsers()
            },
            ViewUserOrder(user) {
                this.$refs.OrderView.openDialog(user)
            },
            GetUsers() {
                this.isload = true
                this.weberror=false
                getUsers(
                    {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize
                    }
                ).then(res => {
                    this.users = res.data.data.users
                    this.totalUsers = res.data.data.totalUsers
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
                totalUsers: 0,
                tableConfig: {
                    height: 200
                },
                isload: true,
                weberror: false,
                OrderViewVisible:false
            }
        },
        created() {
            this.GetUsers()
            this.getHeight()
            window.addEventListener('resize', this.getHeight)
        },
        destroyed() {
            window.removeEventListener('resize', this.getHeight)
        },
        components: {
            webErrorResult,
            OrderView
        }
    }
</script>

<style>

  #userview .el-table__body .cell{
    height: 100px;
  }

  #userview .el-table__body .cell{
    height: 100px;
  }


</style>