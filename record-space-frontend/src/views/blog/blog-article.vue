<template>
  <item-list :item-type="itemType" :page-size="pageSize">
    <template v-slot:aside>
      <blog-aside></blog-aside>
    </template>
    <template v-slot:item-list="slotProps">
      <article-item v-for="article in slotProps.itemList" :key="article.id" :article="article"></article-item>
    </template>
  </item-list>
</template>

<script>

import ItemList from "../../components/common/item-list";
import ArticleItem from "../../components/blog/article-item";
import BlogAside from "../../components/blog/blog-aside";
export default {
  name: "BlogArticle",
  components: {BlogAside, ArticleItem, ItemList},
  data(){
    return{
      itemType:"article",
      pageSize:4
    }
  }
}

</script>

<style scoped>

</style>
