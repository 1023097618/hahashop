<!-- Product.vue -->
<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="image-container">
      <img :src="product.goodImage" class="image">
    </div>
    <div style="padding: 14px; line-height: 13px;">
      <span>{{product.goodName}}</span>
      <div class="bottom clearfix">
        <time class="time">{{ product.goodPrice }}</time>
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
    height: 200px; /* 固定高度，根据需要调整 */
    overflow: hidden;
  }

  .image {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 保持图片比例并裁剪以填充容器 */
    object-position: center; /* 显示图片的中心部分 */
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

  .el-card:hover {
box-shadow: 0 1px 6px rgba(255, 255, 255, 0.932);
border-color: #eee;
transition: all 0.2s ease-in-out;
cursor:pointer;
}
</style>
