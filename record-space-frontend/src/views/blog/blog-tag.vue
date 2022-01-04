<template>
  <item-list :item-type="itemType" :page-size="pageSize" >
    <template v-slot:aside>
      <blog-aside></blog-aside>
    </template>
    <template v-slot:item-list="slotProps">
      <tag-item v-for="tag in slotProps.itemList" :key="tag.id" :tag="tag"></tag-item>
    </template>
  </item-list>
</template>

<script>

import ItemList from "../../components/common/item-list";
import TagItem from "../../components/blog/tag-item";
import BlogAside from "../../components/blog/blog-aside";
export default {
  name: "ArticleTag",
  components: {BlogAside, TagItem, ItemList,},
  data(){
    return{
      itemType:"tag",
      pageSize:16
    }
  }
}
</script>

<style scoped>

</style>