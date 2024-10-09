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
</style>
