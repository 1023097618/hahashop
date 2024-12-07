<!-- Product.vue -->
<template>
  <el-card :body-style="{ padding: '0px' }"
    :class="{'frozen':(product.goodNum<=0),'active':(product.goodNum>0),'clicked': clicked}">
    <div class="image-container" @click="handleClick">
      <el-carousel trigger="click" height="150px" :autoplay="false" indicator-position="none" arrow="never"
        ref="carousel">
        <el-carousel-item v-for="(item,index) in urlshow" :key="index">
          <img :src="item.src" class="image" :key="item.key">
        </el-carousel-item>
        <el-button @click.stop="prevEvent" class="click-btn prev-btn btn"><i class="el-icon-arrow-left"></i></el-button>
        <el-button @click.stop="nextEvent" class="click-btn next-btn btn"><i class="el-icon-arrow-right"></i></el-button>
      </el-carousel>
    </div>
    <div style="padding: 14px; line-height: 13px;" @click="handleClick">
      <span>{{product.goodName}}</span>
      <div style="display: flex; justify-content: space-between; margin-top: 10px;">
        <time class="time">{{ product.goodPrice }}</time>
        <time class="time">剩余库存:{{ product.goodNum }}</time>
      </div>

    </div>

  </el-card>
</template>

<script>
  export default {
    props: {
      product: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        clicked: false,
        urlshow: [], // 当前需要加载的图片 URL
        nowIndex: 0, // 当前展示的图片在 urlshow 中的索引
        preloadCount: 0, // 预加载的图片数量
        update:false
      }
    },
    created() {
      this.urlshow = this.product.goodImage.map(() => ({
        src: null, // 图片 URL
        key: Math.random().toString(36).substr(2, 9) // 唯一 key
      }));
      this.showImg(); // 初始化图片加载
    },
    methods: {
      handleClick() {
        this.$emit('Userclick');
        if (this.product.goodNum > 0) { // 只有在 active 状态下才响应点击
          this.clicked = true;
          setTimeout(() => {
            this.clicked = false;
          }, 500); // 500ms 后移除 clicked 类
        }
      },
      nextEvent() {
        const total = this.product.goodImage.length;
        this.nowIndex = (this.nowIndex + 1) % total; // 更新当前索引
        this.showImg(); // 动态加载图片
        this.$refs.carousel.setActiveItem(this.nowIndex); // 切换到下一个图片
      },
      prevEvent() {
        const total = this.product.goodImage.length;
        this.nowIndex = (this.nowIndex - 1 + total) % total; // 更新当前索引
        this.showImg(); // 动态加载图片
        this.$refs.carousel.setActiveItem(this.nowIndex); // 切换到上一个图片
      },
      showImg() {
        this.update=!this.update
        const total = this.product.goodImage.length; // 图片总数
        const preloadCount = this.preloadCount; // 预加载数量
        const currentIndex = this.nowIndex; // 当前索引

        // 加载当前图片和前后 preloadCount 张图片
        for (let i = -preloadCount; i <= preloadCount; i++) {
          const index = (currentIndex + i + total) % total; // 确保索引在范围内
          if (!this.urlshow[index].src) {
            // 如果图片未加载，则加载图片
            this.urlshow[index].src = this.product.goodImage[index];
            this.urlshow[index].key = Math.random().toString(36).substr(2, 9); // 更新 key
          }
        }
      }
    }
  }
</script>

<style scoped>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image-container {
    width: 100%;
    height: 180px;
    /* 固定高度，根据需要调整 */
    overflow: hidden;
  }

  .image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    /* 保持图片比例并裁剪以填充容器 */
    object-position: center;
    /* 显示图片的中心部分 */
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .active.el-card:hover {
    box-shadow: 0 1px 6px rgba(255, 255, 255, 0.932);
    border-color: #eee;
    transition: all 0.2s ease-in-out;
    cursor: pointer;
  }

  .el-card.frozen {
    opacity: 0.5;
  }



  .el-card.frozen:hover {

    cursor: not-allowed;
  }

  .el-card.clicked {
    animation: clickEffect 0.5s ease;
  }

  @keyframes clickEffect {
    0% {
      transform: scale(1);
    }

    50% {
      transform: scale(1.05);
    }

    100% {
      transform: scale(1);
    }
  }

  .click-btn{
    border: none;
    outline: 0;
    padding: 0;
    margin: 0;
    height: 36px;
    width: 36px;
    cursor: pointer;
    transition: .3s;
    border-radius: 50%;
    background-color: rgba(31, 45, 61, .11);
    color: #FFF;
    position: absolute;
    top: 50%;
    z-index: 10;
    transform: translateY(-50%);
    text-align: center;
    font-size: 12px;
  }
  .click-btn:hover{
    background-color:rgba(31,45,61,.23)
  }
  .prev-btn{
    left: 16px;
  }
  .next-btn{
    right: 16px;
  }
  .el-icon-arrow{
    cursor: pointer;
    font-family: element-icons !important;
    speak: none;
    font-style: normal;
    font-weight: 400;
    font-variant: normal;
    text-transform: none;
    line-height: 1;
    vertical-align: baseline;
    display: inline-block;
    -webkit-font-smoothing: antialiased;
  }
</style>