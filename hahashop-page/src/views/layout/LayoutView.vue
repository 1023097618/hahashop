<template>
  <div id="layoutview">
    <el-container>
      <el-header style="height: 60px;">
        <el-row type="flex" class="row-bg" justify="space-around" style="align-items: center; height: 100%;">
          <el-col :span="6"></el-col>
          <el-col :span="8" style="text-align: center;">
            <h1>hahashop</h1>
          </el-col>
          <el-col :span="8" style="text-align: right;">
            <el-button type="primary" @click="login()">登录</el-button>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-row :gutter="20" v-for="(row,rowindex) in productRows" :key="rowindex">
          <el-col :span="6" v-for="good in row" :key="good.goodId">
            <ProductCard :product="good" @click.native="buy(good)" />
          </el-col>
        </el-row>
      </el-main>
    </el-container>
    <PurchaseDialog :visible.sync="dialogVisible" ref="purchaseDialog"/>
    
  </div>
</template>

<script>
import ProductCard from '@/components/ProductCard';
import PurchaseDialog from '@/components/PurchaseDialog'
import {getGoods} from '@/api/shop/goods.js'
// getGoods

export default {
  components: {
    ProductCard,
    PurchaseDialog
  },
  data() {
    return {
      products: [],
      totalProducts: 0,
      currentPage: 1,
      pageSize: 8,
      dialogVisible:false
    };
  },
  methods: {
    fetchProducts() {
      getGoods({
          pageNum: this.currentPage,
          pageSize: this.pageSize
      }).then(response => {
        console.log(response)
        this.products = response.data.data.goods;
        this.totalProducts = response.data.data.totalgoods;
      }).catch(error => {
        console.error('Error fetching products:', error);
      });
    }
    ,
    handlePageChange(page) {
      this.currentPage = page;
      this.fetchProducts();
    },
    login() {
      this.$router.push('/login')
    },
    buy(product){
      this.$refs.purchaseDialog.openDialog(product)
    }
  },
  computed:{
    productRows() {
      const rows = [];
      for (let i = 0; i < this.products.length; i += 4) {
        rows.push(this.products.slice(i, i + 4));
      }
      console.log(rows)
      return rows;
    }
  }
  ,
  created() {
    this.fetchProducts();
  }

}
</script>

<style scoped>
  /* 头部、尾部布局 */
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 100px;
    height: 100px; /* 确保高度足够 */
  padding: 0 20px; /* 添加一些内边距 */
  box-sizing: border-box; /* 确保内边距不会影响总高度 */
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
    margin-left: 0px;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }


  /* 商品栏布局 */
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
  }
</style>
