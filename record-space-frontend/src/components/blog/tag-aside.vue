<template>
  <el-card class="rs-tag-aside" shadow="hover">
    <el-row>
      <el-col :span="8" class="rs-tag-aside-img">
        <el-avatar :size="40" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
      </el-col>
      <el-col :span="16" class="rs-tag-aside-name">
        <div >{{ tag.name }}</div>
      </el-col>
    </el-row>
    <div class="rs-line"></div>


    <el-row class="rs-tag-aside-data">
      <el-col :span="8">
        <el-row class="rs-tag-aside-data-number">{{ tag.articleCount }}</el-row>
        <el-row class="rs-tag-aside-data-word">文章数</el-row>
      </el-col>
      <el-col :span="8">
        <el-row class="rs-tag-aside-data-number">{{ tag.likeCount }}</el-row>
        <el-row class="rs-tag-aside-data-word">点赞数</el-row>
      </el-col>
      <el-col :span="8">
        <el-row class="rs-tag-aside-data-number">{{ tag.viewCount }}</el-row>
        <el-row class="rs-tag-aside-data-word">阅读量</el-row>
      </el-col>

    </el-row>
    <div class="rs-line"></div>


    <el-row class="rs-tag-aside-action">
      <el-button size="medium">创作管理 <i class="el-icon-arrow-right"></i></el-button>
    </el-row>


  </el-card>
</template>

<script>
import ApiManager from "../../api";

export default {
  name: "TagAside",
  props:{
    tagId:Number
  },
  data(){
    return{
      tag:''
    }
  },
  methods:{
    getTag(){
      ApiManager.getBlogTagById(this.tagId).then(res=>{
        this.tag = res.data
      })
    }
  },
  created() {
    this.getTag()
  }
}
</script>

<style scoped>
.rs-tag-aside{
  position:fixed;
  height: max-content;
  margin-left: 10px;
  margin-top: 20px;
  width: 300px;
}

.rs-tag-aside-img{
  padding-left: 20px;
}

.rs-tag-aside-name{
  line-height: 45px;
  font-size: 16px;
  font-weight: bold;
  color: #444;
}
.rs-line{
  box-sizing: border-box;
  min-width: 0;
  border-top: 1px solid;
  border-color: #EBEBEB;
  margin-top: 20px;
  margin-bottom: 20px;
}
.rs-tag-aside-data {
  text-align: center;
}

.rs-tag-aside-data-number{
  font-size: 20px;
}
.rs-tag-aside-data-word{
  font-size: 12px;
  color: #444;
}

.rs-tag-aside-desc p{
  font-weight: bold;
  font-size: 15px;
}

.rs-tag-aside-action{
  text-align: center;
}
.rs-tag-aside-action button{
  width: 100%;
}
</style>