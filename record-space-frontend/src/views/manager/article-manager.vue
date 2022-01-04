<template>
  <el-container>
    <el-aside>
      <manager-aside></manager-aside>
    </el-aside>
    <el-main>
      <search-bar></search-bar>
      <article-manager-table></article-manager-table>
    </el-main>
  </el-container>

</template>

<script>
import ManagerAside from "../../components/common/manager-aside";
import ArticleManagerTable from "../../components/blog/article-manager-table";
import SearchBar from "../../components/common/search-bar";
export default {
  name: "ArticleManager",
  components: {SearchBar, ArticleManagerTable, ManagerAside}
}
</script>

<style scoped>

</style>