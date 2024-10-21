<template>
    <div>
        <el-upload action="#" list-type="picture-card" :http-request="uploadFile" accept=".jpg,.jpeg,.png" ref="upload" :before-upload="beforeUpload"
            :file-list="fileList" :show-file-list="true" >
            <i slot="default" class="el-icon-plus"></i>
            <div slot="file" slot-scope="{file}">
                <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
                <span class="el-upload-list__item-actions">
                    <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                        <i class="el-icon-zoom-in"></i>
                    </span>
                    <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
                        <i class="el-icon-download"></i>
                    </span>
                    <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                        <i class="el-icon-delete"></i>
                    </span>
                </span>
            </div>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
    </div>
</template>

<script>
    import { upload } from '@/api/storage/storage.js'
    export default {
        props: {
            urls: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                dialogImageUrl: '',
                dialogVisible: false,
                disabled: false,
                fileList: []
            };
        },
        methods: {
            handleRemove(file) {
                this.fileList = this.fileList.filter(item => item.url !== file.url);
                this.$emit('update:urls', this.fileList.map(item => item.url));
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
            },
            uploadFile(item) {
                let FormDatas = new FormData()
                FormDatas.append('file', item.file);
                upload(FormDatas).
                    then(res => {
                        const url = res.data.data.url
                        this.fileList.push({
                            name: item.file.name,
                            url: url
                        });
                        this.$emit('update:urls', this.fileList.map(item => item.url));
                    })
            },
            beforeUpload(file){
                console.log(file)
            }
        },
        created(){
            this.fileList = this.urls.map(url => ({
                    name: url.split('/').pop(), // 从 URL 中提取文件名
                    url: url
            }));
        }
    }
</script>
<style>
/* 解决上传图片时闪动问题，简单粗暴，我喜欢 */
.el-upload-list__item.is-ready {
    display: none;
}
</style>