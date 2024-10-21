<!-- Product.vue -->
<template>
  <el-card :body-style="{ padding: '0px' }"
    :class="{'frozen':(product.goodNum<=0),'active':(product.goodNum>0),'clicked': clicked}" >

    <div class="image-container" @click="handleClick">
      <el-carousel trigger="click" height="150px" :autoplay="false" indicator-position="none">
        <el-carousel-item v-for="(src,index) in product.goodImage" :key="index">
          <img :src="src" class="image">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="padding: 14px; line-height: 13px;" @click="handleClick">
      <span>{{product.goodName}}</span>
      <div style="display: flex; justify-content: space-between;">
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
    data(){
      return {
        clicked:false
      }
    },
    methods:{
      handleClick() {
        this.$emit('Userclick');
      if (this.product.goodNum > 0) { // 只有在 active 状态下才响应点击
        this.clicked = true;
        setTimeout(() => {
          this.clicked = false;
        }, 500); // 500ms 后移除 clicked 类
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
    height: 200px;
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

</style>