<template>
  <el-container>
    <el-aside>
      <slot name="aside"></slot>
    </el-aside>
    <el-main>
      <scroll-page :loading="loading" :offset="offset" :no-data="noData" v-on:load="load">
        <search-bar @startSearch="startSearch">
          <slot name="add-action"></slot>
        </search-bar>
        <slot name="item-list" v-bind:itemList="itemList"></slot>
      </scroll-page>
    </el-main>
  </el-container>

</template>

<script>
import BlogAside from "../blog/blog-aside";
import SearchBar from "../../components/common/search-bar";
import ScrollPage from "../../components/common/scroll-page";
import ApiManager from "../../api";
export default {
  name: "ItemList",
  components: {ScrollPage, SearchBar, BlogAside,},
  props:{
    itemType:String,
    pageSize: Number,
    columnId:Number,
    tagId:Number,
    categoryIds:Array,
    contentUpdated:Boolean,
  },
  data(){
    return{
      loading:false,
      noData:false,
      offset:50,
      itemList:[],
      searchObj:{
        sortParam:String,
        sortMethod:String,
        searchKey:String,
        pageSize:Number,
        currentPage: 1,
        columnId:Number,
        tagId:Number,
        categoryIds:Array,
        type:String,
      }
    }
  },
  watch:{
    categoryIds:{
      deep: true,
      handler(){
        this.resetAndGetItemList()
      }
    },
    contentUpdated:{
      deep: true,
      handler(){
        this.resetAndGetItemList()
      }
    }

  },
  methods:{

    //重置参数并获取列表
    resetAndGetItemList(){
      this.itemList= []
      this.loading = false
      this.noData = false
      this.searchObj.currentPage = 1
      this.getItemList()
    },
    //初始化参数
    initParams(){
      this.loading = true
      this.searchObj.pageSize = this.pageSize
      this.searchObj.columnId = this.columnId
      this.searchObj.tagId = this.tagId
      this.searchObj.categoryIds= this.categoryIds
      this.searchObj.type = this.itemType
    },
    //获取内容列表
    getItemList(){
      let _this = this
      this.initParams()
      ApiManager.getItemList(this.itemType,this.searchObj).then(res=>{
          let appendList = res.data.records
          if (appendList && appendList.length > 0){
            _this.searchObj.currentPage += 1
            _this.itemList = _this.itemList.concat(appendList)
            let that = _this
            _this.$nextTick(()=>{
              that.highLightKey()
            })
          }else {
            _this.noData = true
          }
      }).finally(()=>{
        _this.loading = false
      })
    },

    //滚动加载
    load(){
      this.getItemList()
    },

    //处理搜索
    startSearch(obj){
      this.searchObj = obj
      this.resetAndGetItemList()
    },
    //高亮搜索关键字
    highLightKey(){
      let key = this.searchObj.searchKey
      if(key.length >0 && this.itemList.length>0){
        let ele = document.getElementsByClassName("rs-item-word-body");
        for(let i =0,len=ele.length;i<len;i++){
          let replaceReg = new RegExp(key, 'ig')
          ele[i].innerHTML = ele[i].innerHTML.replace(replaceReg, (rs)=>{
            return  '<span class="highlights-text">' + rs + '</span>'
          })
        }
      }
    }
  },
  created() {
    this.getItemList()
  }
}
</script>
<style>
.highlights-text {
  color: #fc5531;
}
</style>
<style scoped>

</style>