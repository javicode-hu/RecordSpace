<template>
<item-list :item-type="itemType" :page-size="pageSize" :category-ids="categoryIds" :content-updated="contentUpdated">
  <template v-slot:aside>
    <resource-aside @categoryClick="changeCategory"></resource-aside>
  </template>

  <template v-slot:add-action>
    <web-add :cur-category-id="categoryIds[0]" @contentUpdated="contentUpdated=!contentUpdated"></web-add>
  </template>

  <template v-slot:item-list="slotProps">
    <web-item v-for="webItem in slotProps.itemList" :key="webItem.id" :web-item="webItem"></web-item>
  </template>
</item-list>

</template>

<script>

import ItemList from "../../components/common/item-list";
import ResourceAside from "../../components/resource/resource-aside";
import WebItem from "../../components/resource/web-item";
import WebAdd from "../../components/resource/web-add";
import da from "element-ui/src/locale/lang/da";
export default {
  name: "ResourceWeb",
  components: {WebAdd, WebItem, ResourceAside, ItemList},
  data(){
    return{
      itemType:"web",
      pageSize:16,
      webAddVisible:false,
      contentUpdated:false,
      categoryIds:[],
    }
  },methods:{
    addComplete(){
      this.webAddVisible=false
    },
    changeCategory(data){
      this.categoryIds = data
    },
  }
}
</script>

<style scoped>

</style>