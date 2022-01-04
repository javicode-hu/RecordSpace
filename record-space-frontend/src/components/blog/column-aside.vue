<template>
  <el-card class="rs-column-aside" shadow="hover">
    <el-row>
      <el-col :span="8" class="rs-column-aside-img">
        <el-avatar :size="40" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
      </el-col>
      <el-col :span="16" class="rs-column-aside-name">
        <div >{{ column.name }}</div>
      </el-col>
    </el-row>
    <div class="rs-line"></div>

    <el-row class="rs-column-aside-desc">
      <p>简介:</p>
      <div class="rs-column-aside-desc-word">{{column.description}}</div>
    </el-row>

    <div class="rs-line"></div>

    <el-row class="rs-column-aside-data">
      <el-col :span="8">
        <el-row class="rs-column-aside-data-number">{{ column.articleCount }}</el-row>
        <el-row class="rs-column-aside-data-word">文章数</el-row>
      </el-col>
      <el-col :span="8">
        <el-row class="rs-column-aside-data-number">{{column.likeCount }}</el-row>
        <el-row class="rs-column-aside-data-word">点赞数</el-row>
      </el-col>
      <el-col :span="8">
        <el-row class="rs-column-aside-data-number">{{ column.viewCount }}</el-row>
        <el-row class="rs-column-aside-data-word">阅读量</el-row>
      </el-col>

    </el-row>
    <div class="rs-line"></div>


    <el-row class="rs-column-aside-action">
      <el-button size="medium">创作管理 <i class="el-icon-arrow-right"></i></el-button>
    </el-row>


  </el-card>
</template>

<script>
import ApiManager from "../../api";

export default {
  name: "ColumnAside",
  props:{
    columnId:Number,
  },
  data(){
    return{
      column:''
    }
  },
  methods:{
    getBlogColumn(){
      ApiManager.getBlogColumnById(this.columnId).then(res=>{
        this.column = res.data
      })
    }
  },
  created() {
    this.getBlogColumn()
  }
}
</script>

<style scoped>
.rs-column-aside{
  position:fixed;
  height: max-content;
  margin-left: 10px;
  margin-top: 20px;
  width: 300px;
}

.rs-column-aside-img{
  padding-left: 20px;
}

.rs-column-aside-name{
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
.rs-column-aside-data {
  text-align: center;
}

.rs-column-aside-data-number{
  font-size: 20px;
}
.rs-column-aside-data-word{
  font-size: 12px;
  color: #444;
}

.rs-column-aside-desc{
  margin-bottom: 10px;
  margin-left: 10px;
  margin-right: 10px;
}

.rs-column-aside-desc p{
  font-weight: bold;
  font-size: 15px;
}

.rs-column-aside-desc-word{

  font-size: 14px;
  color: #444;
  margin-top: 10px;
}

.rs-column-aside-action{
  text-align: center;
}
.rs-column-aside-action button{
  width: 100%;
}
</style>