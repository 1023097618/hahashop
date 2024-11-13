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
            <el-button type="primary" @click="login()">
              <div v-if="isLogin()">
                进入主页
              </div>
              <div v-else>
                登录
              </div>
            </el-button>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <SearchInput @search="search" class="search" :options="options"/>
        <!-- <div class="category">
          <span class="demonstration">类别选择</span>
          <el-cascader v-model="category" :options="options" :props="{ expandTrigger: 'hover',checkStrictly: true }"
            clearable @change="handleChange"></el-cascader>
        </div> -->
        <el-skeleton :rows="3" animated :loading="isload" />
        <webErrorResult :error="weberror"></webErrorResult>
        <div v-if="!isload && !weberror">
          <el-row :gutter="20" v-for="(row,rowindex) in productRows" :key="rowindex">
            <el-col :span="6" v-for="good in row" :key="good.goodId">
              <ProductCard :product="good" @Userclick="buy(good)" />
            </el-col>
          </el-row>
        </div>
        <el-pagination :current-page="currentPage" :page-size="pageSize" layout="prev, pager, next" background
        :total="totalProducts" @current-change="handlePageChange" v-if="totalProducts > pageSize" class="pagination-container">
        </el-pagination>
      </el-main>
    </el-container>
    <PurchaseDialog :visible.sync="dialogVisible" ref="purchaseDialog" />

  </div>
</template>

<script>
  import ProductCard from '@/components/ProductCard';
  import PurchaseDialog from '@/components/PurchaseDialog'
  import { getGoods, getCategory } from '@/api/shop/goods.js'
  import webErrorResult from '@/components/webErrorResult.vue'
  import { GetCookie, RemoveCookie } from "@/utils/auth"
  import SearchInput from '@/components/SearchInput.vue'
  // getGoods
  export default {
    components: {
      ProductCard,
      PurchaseDialog,
      webErrorResult,
      SearchInput
    },
    data() {
      return {
        products: [],
        totalProducts: 0,
        currentPage: 1,
        pageSize: 8,
        dialogVisible: false,
        isload: true,
        weberror: false,
        keyword: '',
        category: undefined,
        options: [
          {
            "children": [
              {
                "label": "布艺软装",
                "value": 1008002
              }
            ],
            "label": "居家",
            "value": 1005000
          }
        ]
      };
    },
    methods: {
      fetchProducts() {
        this.isload = true
        this.weberror = false
        const params = {
          pageNum: this.currentPage,
          pageSize: this.pageSize
        }
        if (this.keyword) {
          params.goodName = this.keyword
        }
        if (this.category) {
          params.categoryId = this.category
        }
        getGoods(params).then(response => {
          console.log(response)
          this.products = response.data.data.goods;
          this.totalProducts = response.data.data.totalGoods;
          this.isload = false
          this.weberror = false
        }).catch(error => {
          this.isload = false
          this.weberror = true
          console.error('Error fetching products:', error);
        });
      }
      ,
      handlePageChange(page) {
        this.currentPage = page;
        this.fetchProducts();
      },
      login() {
        this.$router.push('/login').catch(err => {
          console.log(err)
        })
      },
      buy(product) {
        if (product.goodNum <= 0) {
          return
        }
        const token = GetCookie()
        if (token) {
          if (this.$store.getters.permmited === 0) {
            const user = this.$store.getters.user
            this.$store.dispatch("GetUserInfoAction", token).then(() => {
              this.$refs.purchaseDialog.openDialog(product, user)
            }).catch((err) => {
              console.log(err)
              RemoveCookie()
              this.login()
            })
          }
          else {
            const user = this.$store.getters.user
            this.$refs.purchaseDialog.openDialog(product, user)
          }
        } else {
          this.login()
        }
      },
      search(key,category) {
        this.keyword = key
        this.category=category
        this.fetchProducts()
      },
      Getcategoty() {
        getCategory().then(res => {
          this.options = res.data.data.categoryList
        })
      },
      isLogin(){
        const token = GetCookie()
        if(token){
          return true
        }else{
          return false
        }
      }
    },
    computed: {
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
      this.fetchProducts()
      this.Getcategoty();
    }


  }
</script>

<style scoped>
    #layoutview{
    height: 100%;
  }
  #layoutview .category {
    line-height: 10px;
  }

  #layoutview .search {
    line-height: 10px;
  }

  /* 头部、尾部布局 */
  .el-header,
  .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 100px;
    height: 100px;
    /* 确保高度足够 */
    padding: 0 20px;
    /* 添加一些内边距 */
    box-sizing: border-box;
    /* 确保内边距不会影响总高度 */
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

  body>.el-container {
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

  #layoutview .pagination-container {
  display: flex;
  justify-content: center; /* 水平居中 */
  position: fixed; /* 固定定位 */
  bottom: 0; /* 贴着底边 */
  width: 100%; /* 占满宽度 */
  line-height: normal;
  padding: 40px 0;
}
</style>