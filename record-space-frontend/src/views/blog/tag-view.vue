<template>
  <item-list :item-type="itemType" :page-size="pageSize" :tag-id="tagId">
    <template v-slot:aside>
      <tag-aside :tag-id="tagId"></tag-aside>
    </template>
    <template v-slot:item-list="slotProps">
      <article-item v-for="article in slotProps.itemList" :key="article.id" :article="article"></article-item>
    </template>

  </item-list>
</template>

<script>
import ItemList from "../../components/common/item-list";
import ArticleItem from "../../components/blog/article-item";
import TagAside from "../../components/blog/tag-aside";
export default {
  name: "TagView",
  components: {TagAside, ArticleItem, ItemList},
  data(){
    return{
      itemType:"article",
      pageSize:4,
      tagId:parseInt(this.$route.params.id)
    }
  },
}
</script>

<style scoped>

</style>