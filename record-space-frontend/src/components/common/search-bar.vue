<template>
  <el-card class="rs-article-search" shadow="hover">
    <el-form :inline="true" >
      <el-form-item >
        <el-cascader
            placeholder="排序规则"
            clearable
            v-model="selectSortRule"
            :options="sortRules"
            :props="{ expandTrigger: 'hover' }"
            @change="handleChange"></el-cascader>
      </el-form-item>

      <el-form-item >
          <el-input
              clearable
              class="rs-search-input"
              placeholder="请输入搜索内容"
              prefix-icon="el-icon-search"
              v-model="searchKey">
          </el-input>
      </el-form-item>

      <el-form-item >
          <el-button type="primary" icon="el-icon-search" size="medium" @click="handleSearch">搜索</el-button>
      </el-form-item>

      <el-form-item class="rs-search-extra">
        <slot></slot>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "SearchBar",
  data(){
    return{
      searchKey:'',
      selectSortRule:'',

    }
  },
  methods:{
    handleSearch(){
      let searchObj = {
        sortParam:this.selectSortRule[0],
        sortMethod:this.selectSortRule[1],
        searchKey:this.searchKey.trim(),
      }
      this.$emit("startSearch",searchObj)
    },
    handleChange(value) {

    },
  },
  created() {
  },
  computed:{
    sortRules(){
      let sortList=[
        {value: 'desc', label: '降序'},
        {value: 'asc', label: '升序'},
      ]
      return [
        {value:'create_time',label:'创建时间',children:sortList},
        {value:'view_count',label:'阅读量',children:sortList},
        {value:'like_count',label:'点赞数',children:sortList}
      ]
    }
  }
}
</script>

<style scoped>
.rs-article-search{
  /*position:fixed;*/
  margin-bottom: 10px;
  text-align: center;
}
.rs-article-search .el-form-item{
  margin-bottom: 0px;
}
.rs-search-input{
  width: 400px;
}
.rs-search-extra{
  text-align: left;
}


</style>