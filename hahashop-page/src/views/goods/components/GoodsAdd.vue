<template>
  <el-dialog title="增加商品" :visible="this.visible" width="90%" :before-close="closeDialog" center
    style="overflow: hidden" top="3vh">
    <div style="overflow-y: auto; height: 70vh">
      <el-form :model="good" label-position="right" label-width="80px">
        <el-form-item label="商品名称">
          <el-input v-model="good.goodName"></el-input>
        </el-form-item>
        <el-form-item label="商品价格">
          <el-input v-model="good.goodPrice"></el-input>
        </el-form-item>
        <el-form-item label="商品图片">
          <UploadPicture :urls.sync="good.goodImage" />
        </el-form-item>
        <el-form-item label="剩余库存">
          <el-input v-model.number="good.goodNum" type="number"></el-input>
        </el-form-item>
        <el-form-item label="商品种类">
          <el-cascader v-model="good.categoryId" :options="options" :props="{ expandTrigger: 'hover',checkStrictly: true }" clearable
            @change="handleChange"></el-cascader>
        </el-form-item>
        <quill-editor v-model="good.goodDesc" ref="myQuillEditor" :options="editorOption">
        </quill-editor>
        <!-- <el-form-item label="商品备注">
          <el-input type="textarea" v-model="good.goodDesc"></el-input>
        </el-form-item> -->
      </el-form>
    </div>
    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog()">取 消</el-button>
      <el-button type="primary" @click="sureDialog()">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { addGoods } from '@/api/shop/goods.js'
  import UploadPicture from '@/components/UploadPicture.vue'
  export default {
    name: 'GoodsAdd',
    data() {
      return {
        good: {
          goodName: '',
          goodPrice: '',
          goodImage: [],
          goodDesc: '',
          goodNum:0,
          categoryId: undefined
        },
        options: [],
        editorOption: { /* quill options */ }
      };
    },

    props: {
      visible: {
        type: Boolean,
        required: true
      }
    },
    components: {
      UploadPicture
    }
    ,
    methods: {
      openDialog(options) {
        this.options = options
        this.$emit('update:visible', true)
      },
      closeDialog() {
        this.$emit('update:visible', false)
      },
      sureDialog() {
        this.$emit('update:visible', false)
        addGoods({
          goodName: this.good.goodName,
          goodPrice: this.good.goodPrice,
          goodImage: this.good.goodImage,
          goodDesc: this.good.goodDesc,
          categoryId:this.good.categoryId,
          goodNum:this.good.goodNum
        }).then(res => {
          this.$emit('updateGoods')
          console.log(res)
        }).catch(err => {
          console.log(err)
        })
      },
      handleChange(value) {
        if (Array.isArray(value) && value.length > 0) {
          this.good.categoryId = value[value.length - 1];
        } else {
          this.good.categoryId = undefined;
        }
      }
    }
  };
</script>