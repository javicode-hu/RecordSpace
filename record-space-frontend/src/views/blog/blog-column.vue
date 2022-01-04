<template>
  <item-list :item-type="itemType" :page-size="pageSize" :content-updated="contentUpdated">
    <template v-slot:aside>
      <blog-aside @contentUpdate="contentUpdated=!contentUpdated"></blog-aside>
    </template>
    <template v-slot:item-list="slotProps">
      <column-item v-for="column in slotProps.itemList" :key="column.id" :column="column"></column-item>
    </template>

  </item-list>

</template>

<script>

import ItemList from "../../components/common/item-list";
import ColumnItem from "../../components/blog/column-item";
import BlogAside from "../../components/blog/blog-aside";
export default {
  name: "ArticleColumn",
  components: {BlogAside, ColumnItem, ItemList},
  data(){
    return{
      itemType:"column",
      pageSize:8,
      contentUpdated:false
    }
  }
}
</script>
<style scoped>


</style>