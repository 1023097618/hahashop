<template>
    <div id="searchBox" style="margin-bottom: 20px;">
        <el-input v-model="keyword" :disabled="false" :placeholder="placeholder" style="width: 600px ;margin-right: 10px"
            clearable @clear="search" @keydown.enter.native="search">
            <template slot="append">
                <el-button :disabled="false" icon="el-icon-search" type="primary" @click="search">
                </el-button>
            </template>
            <template slot="prepend">
                <el-cascader v-model="category" :options="options" style="width: 200px;"
                    :props="{ expandTrigger: 'hover',checkStrictly: true }" clearable
                    @change="handleChange"></el-cascader>
            </template>
        </el-input>

    </div>
</template>

<script>
    export default {
        name: "SearchInput",
        props: {
            disabled: {
                type: Boolean,
                default: true
            },
            placeholder: {
                type: String,
                default: '搜索商品'
            },
            options: {
                default: []
            }
        },
        data() {
            return {
                keyword: '',
                category:undefined
            }
        },
        methods: {
            search() {
                this.$emit("search", this.keyword,this.category)
            },
            handleChange(value) {
                if (Array.isArray(value) && value.length > 0) {
                    this.category = value[value.length - 1];
                } else {
                    this.category = null; // 或者其他默认值，根据需要设置
                }
            }
        }
    }
</script>

<style>
    #searchBox .el-input-group__prepend{
        border: 0px;
        padding: 0px;
    }
    #searchBox .el-scrollbar__wrap{
        overflow: auto;
    }

    #searchBox .el-cascader .el-input__inner{
        height: 40px;
    }
</style>