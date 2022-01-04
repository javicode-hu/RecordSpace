<template>
  <item-list :item-type="itemType" :page-size="pageSize" :column-id="columnId" >
    <template v-slot:aside>
      <column-aside :column-id="columnId"></column-aside>
    </template>
    <template v-slot:add-action>
      <el-button type="primary" icon="el-icon-search" size="medium" >新建文章</el-button>
    </template>
    <template v-slot:item-list="slotProps">
      <article-item v-for="article in slotProps.itemList" :key="article.id" :article="article"></article-item>
    </template>
  </item-list>
</template>

<script>
import ItemList from "../../components/common/item-list";
import ArticleItem from "../../components/blog/article-item";
import ColumnAside from "../../components/blog/column-aside";
export default {
  name: "ColumnView",
  components: {ColumnAside, ArticleItem, ItemList},
  data(){
    return{
      itemType:"article",
      pageSize:4,
      columnId:parseInt(this.$route.params.id)
    }
  },

}
</script>

<style scoped>

</style>