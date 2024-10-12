<template>
  <div>
    <el-result title="404" subTitle="抱歉，请求错误" v-if="error">
        <template slot="icon">
          <el-image src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"></el-image>
        </template>
    </el-result>
  </div>
</template>

<script>
export default{
    name:'webErrorResult',
    props:{
        error:{
            type:Boolean,
            required:true
        }
    }
}
</script>
