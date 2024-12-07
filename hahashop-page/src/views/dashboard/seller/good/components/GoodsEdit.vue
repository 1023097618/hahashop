<template>
  <el-dialog title="修改商品" :visible="this.visible" width="90%" :before-close="closeDialog" center
    style="overflow: hidden" top="3vh" :close-on-click-modal="false">
    <div style="overflow-y: auto; height: 70vh">
      <el-form :model="good" label-position="right" label-width="80px">
        <el-form-item label="商品名称">
          <el-input v-model="good.goodName"></el-input>
        </el-form-item>
        <el-form-item label="商品价格">
          <el-input v-model="good.goodPrice"></el-input>
        </el-form-item>
        <el-form-item label="商品图片">
          <UploadPicture :urls.sync="good.goodImage" ref="uploadPicture"/>
        </el-form-item>
        <el-form-item label="剩余库存">
          <el-input v-model.number="good.goodNum" type="number"></el-input>
        </el-form-item>
        <el-form-item label="商品种类">
          <el-cascader v-model="good.categoryId" :options="options"
            :props="{ expandTrigger: 'hover',checkStrictly: true }" clearable @change="handleChange"></el-cascader>
        </el-form-item>
        <div ref="quillEditor"></div>
        <!-- <quill-editor v-model="good.goodDesc" ref="myQuillEditor" :options="editorOption">
        </quill-editor> -->
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
  import { getGoodsDetail } from '@/api/shop/goods.js'
  import { updateGoods } from '@/api/shop/goods.js'
  import UploadPicture from '@/components/UploadPicture.vue'
  import Quill from 'quill'
  import { upload } from '@/api/storage/storage.js';
  import DOMPurify from 'dompurify';
  export default {
    name: 'GoodsEdit',
    data() {
      return {
        good: {
          goodName: '',
          goodPrice: '',
          goodImage: '',
          goodDesc: '',
          categoryId: -1,
          goodNum: 0
        },
        options: [],
        // editorOption: { /* quill options */ }
        toolbar: [
          ['bold', 'italic'],
          ['link', 'blockquote', 'code-block', 'image'],
          [{ list: 'ordered' }, { list: 'bullet' }]
        ],
        quill: null
      };
    },
    components: {
      UploadPicture
    },
    props: {
      visible: {
        type: Boolean,
        required: true
      }
    },
    methods: {
      openDialog(good, options) {
        Object.assign(this.good, good);
        this.options = options
        this.$emit('update:visible', true)
        this.$nextTick(() => {
          this.initQuill();
          this.$refs.uploadPicture.initFileList();
        });
        getGoodsDetail(this.good.goodId)
          .then(res => {
            this.good.goodDesc = DOMPurify.sanitize(res.data.data.goodDesc)
            if (this.editor) {
              this.editor.root.innerHTML = this.good.goodDesc;
            }
          }).catch(err => {
            console.log(err)
          })
      },
      closeDialog() {
        this.$emit('update:visible', false)
        this.good={
          goodName: '',
          goodPrice: '',
          goodImage: [],
          goodDesc: '',
          goodNum: 0,
          categoryId: -1
        }
        this.options=[]
        this.$refs.uploadPicture.clearFileList();
      },
      sureDialog() {
        this.$emit('update:visible', false)
        updateGoods({
          goodName: this.good.goodName,
          goodPrice: this.good.goodPrice,
          goodImage: this.good.goodImage,
          goodDesc: this.good.goodDesc,
          goodId: this.good.goodId,
          categoryId: this.good.categoryId,
          goodNum: this.good.goodNum
        }).then(res => {
          this.$emit('updateGoods')
          console.log(res)
        }).catch(err => {
          console.log(err)
        })
      },
      handleChange(value) {
        console.log(value)
        if (Array.isArray(value) && value.length > 0) {
          this.good.categoryId = value[value.length - 1];
        } else {
          this.good.categoryId = value;
        }
      },
      initQuill() {
        // 设置编辑器内容
        
        if (this.editor) {
          this.editor.root.innerHTML = this.good.goodDesc;
          return;
        }
        const toolbarOptions = [
          [{ 'header': [1, 2, false] }],
          ['bold', 'italic', 'underline'],
          ['image'], // 默认的图片按钮
          ['code-block']
        ];

        this.editor = new Quill(this.$refs.quillEditor, {
          theme: 'snow',
          placeholder: '请输入商品描述...',
          modules: {
            toolbar: {
              container: toolbarOptions,
              handlers: {
                image: this.selectLocalImage // 自定义图片上传处理
              }
            }
          }
        });
        this.editor.root.innerHTML = this.good.goodDesc;


        // 监听编辑器内容变化
        this.editor.on('text-change', () => {
          this.good.goodDesc = this.editor.root.innerHTML;
        });
      },

      // 选择本地图片
      selectLocalImage() {
        // 创建一个隐藏的文件输入框
        const input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');
        input.click();

        // 当用户选择文件时，触发上传
        input.onchange = () => {
          const file = input.files[0];
          if (file) {
            this.uploadImage(file);
          }
        };
      },

      // 上传图片
      uploadImage(file) {
        const formData = new FormData();
        formData.append('file', file);

        // 调用上传接口
        upload(formData).then(res => {
          const url = res.data.data.url; // 获取上传后的图片 URL
          this.insertToEditor(url); // 将图片插入到编辑器中
        }).catch(err => {
          console.error('图片上传失败', err);
        });
      },

      // 将图片插入到编辑器中
      insertToEditor(url) {
        const range = this.editor.getSelection(); // 获取当前光标位置
        this.editor.insertEmbed(range.index, 'image', url); // 在光标位置插入图片
      }
    }
  };
</script>