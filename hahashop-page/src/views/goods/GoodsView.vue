<template>
  <div>
  <el-table :data="goods" style="width: 100%" max-height="250">
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
        <div style="display: flex; justify-content: center;">
        <el-button size="mini" type="primary" icon="el-icon-plus"
          @click="AddGoods()"></el-button>
        </div>
      </template>
      <template slot-scope="scope">
        <div style="display: flex; justify-content: center;">
          <el-button @click.native.prevent="EditGoods(scope.row)" type="primary" icon="el-icon-edit" size="mini">
          </el-button>
        <el-button @click.native.prevent="DeleteGoods(scope.row.goodId)" type="danger" icon="el-icon-delete" size="mini">
        </el-button>
      </div>
      </template>
    </el-table-column>
  </el-table>
  <GoodsEdit :visible.sync="GoodsEditVisible" ref="GoodsEdit"/>
  <GoodsAdd :visible.sync="GoodsAddVisible" ref="GoodsAdd"/>
  <BuyerView :visible.sync="BuyerViewVisible" ref="BuyerView"/>
</div>
</template>

<script>
  import { getGoods,deleteGood } from '@/api/shop/goods.js'
  import GoodsEdit from './components/GoodsEdit.vue'
  import GoodsAdd from './components/GoodsAdd.vue'
  import BuyerView from './components/BuyerView.vue'
  export default {
    name:'GoodsView',
    methods: {
      deleteGood(goodId) {
        console.log(goodId)
      },
      GetGoods() {
        getGoods(
          {
            pageNum: this.currentPage,
            pageSize: this.pageSize
          }
        ).then(res => {
          this.goods = res.data.data.goods
        }).catch(err => {
          console.log(err)
        })
      },
      EditGoods(good){
        this.$refs.GoodsEdit.openDialog(good)
      },
      DeleteGoods(id){
        deleteGood(id).then(
          res=>{
            console.log(res)
          }
        ).catch(err=>{
          console.log(err)
        })
      },
      AddGoods(){
        this.$refs.GoodsAdd.openDialog()
      },
      ViewBuyers(good){
        this.$refs.BuyerView.openDialog(good)
      }
    },
    components:{
      GoodsEdit,
      GoodsAdd,
      BuyerView
    }
    ,
    data() {
      return {
        goods: [{
          "goodName": "比亚迪跑车",
          "goodPrice": "100 ￥",
          "goodId": 10086,
          "goodImage": "https://img.alicdn.com/imgextra/i2/O1CN01kcwuQk1LzVafnz3rv_!!6000000001370-0-tps-480-672.jpg",
          "buyerNum": 10
        }],
        GoodsEditVisible:false,
        GoodsAddVisible:false,
        BuyerViewVisible:false,
        currentPage:1,
        pageSize:8
      }
    },
    created() {
      this.GetGoods()
    }
  }
</script>

<style scoped>
  .el-table .el-table__cell {
    padding: 10px;
  }
</style>