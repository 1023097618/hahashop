<template>
  <div id="app"  >
    <router-view/>
  </div>
</template>

<style>
    body {
      position: absolute;
      top: 0;
      bottom: 0;
      left: 0;
      right: 0;
      margin: 8px !important;
    }

    #app {
      width: 100%;
      height: 100%;
      background: #f5f4f4;
    }

    .el-container{
      height: 100%;
    }

    /* 多选框可以选择任意级 */
    .el-cascader-panel .el-radio{
        width: 100%;
        height: 100%;
        z-index: 10;
        position: absolute;
        top: 10px;
        right: 10px;
    }
    .el-cascader-panel .el-radio__input{
        visibility: hidden;
    }
    .el-cascader-panel .el-cascader-node__postfix {
        top: 10px;
    }
</style>
