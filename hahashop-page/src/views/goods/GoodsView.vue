<template>
  <div id="goods">
    <webErrorResult :error="weberror"></webErrorResult>
    <el-skeleton :rows="3" animated :loading="isload" />
    <el-table :data="goods" style="width: 100;" :height="tableConfig.height" :row-class-name="tableRowClassName"
      v-if="!isload && !weberror">
      <el-table-column label="商品图片" width="120" fixed>
        <template slot-scope="scope">
          <img :src="scope.row.goodImage"
            style="width: 100px; height: 100px; object-fit: cover; object-position: center;">
        </template>
      </el-table-column>
      <el-table-column prop="goodName" label="商品名称" width="150">
      </el-table-column>
      <el-table-column prop="goodPrice" label="商品价格" width="120">
      </el-table-column>
      <el-table-column prop="buyerNum" label="购买者数量" width="120">
      </el-table-column>
      <el-table-column label="订单查看" width="120">
        <template slot-scope="scope">
          <el-button @click.native.prevent="ViewBuyers(scope.row)" type="primary" icon="el-icon-view" size="mini">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column fixed="right" width="210">
        <template slot="header">
          <div style="display: flex; justify-content: center;" v-if="totalProducts<=0">
            <el-button size="mini" type="primary" icon="el-icon-plus" @click="AddGoods()"></el-button>
          </div>
        </template>
        <template slot-scope="scope">
          <div style="display: flex; justify-content: center;">
            <el-button @click.native.prevent="EditGoods(scope.row)" type="primary" icon="el-icon-edit" size="mini">
            </el-button>
            <el-button @click.native.prevent="DeleteGood(scope.row.goodId)" type="danger" icon="el-icon-delete"
              size="mini" v-if="scope.row.goodState==0">
            </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :current-page="currentPage" :page-size="pageSize" layout="prev, pager, next" :total="totalProducts"
      @current-change="handlePageChange" v-if="totalProducts > pageSize">
    </el-pagination>
    <GoodsEdit :visible.sync="GoodsEditVisible" @updateGoods="GetGoods" ref="GoodsEdit" />
    <GoodsAdd :visible.sync="GoodsAddVisible" @updateGoods="GetGoods" ref="GoodsAdd" />
    <BuyerView :visible.sync="BuyerViewVisible" @updateGoods="GetGoods" ref="BuyerView" />
  </div>
</template>

<script>
  import { getGoods, deleteGood } from '@/api/shop/goods.js'
  import GoodsEdit from './components/GoodsEdit.vue'
  import GoodsAdd from './components/GoodsAdd.vue'
  import BuyerView from './components/BuyerView.vue'
  import webErrorResult from '@/components/webErrorResult.vue'
  export default {
    name: 'GoodsView',
    methods: {
      handlePageChange(page) {
        this.currentPage = page
        this.GetGoods()
      },
      DeleteGood(id) {
        deleteGood(id).then(
          res => {
            this.GetGoods()
            console.log(res)
          }
        ).catch(err => {
          console.log(err)
        })
      },
      GetGoods() {
        this.isload = true
        getGoods(
          {
            pageNum: this.currentPage,
            pageSize: this.pageSize
          }
        ).then(res => {
          this.goods = res.data.data.goods
          this.totalProducts = res.data.data.totalGoods
          this.isload = false,
          this.weberror = false
        }).catch(err => {
          this.isload = false,
          this.weberror = true
          console.log(err)
        })
      },
      EditGoods(good) {
        this.$refs.GoodsEdit.openDialog(good)
      },
      AddGoods() {
        this.$refs.GoodsAdd.openDialog()
      },
      ViewBuyers(good) {
        this.$refs.BuyerView.openDialog(good)
      },
      //自适应表格高度  8+60+32
      getHeight() {
        this.tableConfig.height = window.innerHeight - 100
      },
      tableRowClassName({ row }) {
        if (row.goodState == 1) {
          return 'warning-row'
        }
        return ''
      }
    },
    components: {
      GoodsEdit,
      GoodsAdd,
      BuyerView,
      webErrorResult
    }
    ,
    data() {
      return {
        goods: [],
        GoodsEditVisible: false,
        GoodsAddVisible: false,
        BuyerViewVisible: false,
        currentPage: 1,
        pageSize: 8,
        totalProducts: 0,
        tableConfig: {
          height: 200
        },
        isload: true,
        weberror: false
      }
    },
    created() {
      this.GetGoods()
      this.getHeight()
      window.addEventListener('resize', this.getHeight)
    },
    destroyed() {
      window.removeEventListener('resize', this.getHeight)
    }
  }
</script>

<style>
  #goods .el-table__body .cell {
    height: 100px;
  }

  #goods .warning-row {
    background-color: rgb(253, 253, 206);;
  }
</style>