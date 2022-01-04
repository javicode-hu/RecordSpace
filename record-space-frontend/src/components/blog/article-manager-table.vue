<template>
<el-card>
  <el-table
      :data="tableData"
      stripe
      class="rs-blog-manager-table">
    <el-table-column
        prop="blogTitle"
        label="标题"
        width="400">
    </el-table-column>
    <el-table-column
        prop="createTime"
        label="创建时间"
        width="180">
    </el-table-column>
    <el-table-column
        prop="updateTime"
        label="更新时间"
        width="180">
    </el-table-column>
    <el-table-column
        prop="readCount"
        label="阅读量"
        width="100">
    </el-table-column>
    <el-table-column
        prop="readCount"
        label="点赞数"
        width="100">
    </el-table-column>
    <el-table-column
        fixed="right"
        label="操作"
        width="200">
      <template slot-scope="scope">
        <el-button type="text" size="small">查看</el-button>
        <el-button type="text" size="small">编辑</el-button>
        <el-button type="text" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

</el-card>
</template>

<script>
export default {
  name: "ArticleManagerTable",
  data(){
    return{
      tableData: [{
        blogTitle: '一本可以让你搞定所有编程算法知识点的神书—《算法图解》',
        createTime: '2019-01-01 11:33',
        updateTime: '2019-01-01 11:33',
        readCount: '156'
      }, {
        blogTitle: '一本可以让你搞定所有编程算法知识点的神书—《算法图解》',
        createTime: '2019-01-01 11:33',
        updateTime: '2019-01-01 11:33',
        readCount: '156'
      }, {
        blogTitle: '一本可以让你搞定所有编程算法知识点的神书—《算法图解》',
        createTime: '2019-01-01 11:33',
        updateTime: '2019-01-01 11:33',
        readCount: '156'
      }, {
        blogTitle: '一本可以让你搞定所有编程算法知识点的神书—《算法图解》',
        createTime: '2019-01-01 11:33',
        updateTime: '2019-01-01 11:33',
        readCount: '156'
      }]
    }
  }
}
</script>

<style scoped>
.rs-blog-manager-table{
  width: 100%;
}

</style>