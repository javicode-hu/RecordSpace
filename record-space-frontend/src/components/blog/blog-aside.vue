<template>
  <el-card class="rs-blog-aside" shadow="hover">
    <el-row>
      <el-col :span="8" class="rs-blog-aside-avatar">
        <el-avatar :size="40" :src="user.avatar"></el-avatar>
      </el-col>
      <el-col :span="16" class="rs-blog-aside-username">
        <div >{{ user.nickname }}</div>
      </el-col>
    </el-row>
    <div class="rs-line"></div>

    <el-row class="rs-blog-aside-data">
      <el-col :span="8">
        <router-link to="/blog/article">
          <el-row class="rs-blog-aside-data-number">{{ articleCount}}</el-row>
          <el-row class="rs-blog-aside-data-word">文章数</el-row>
        </router-link>
      </el-col>
      <el-col :span="8">
        <router-link to="/blog/column">
          <el-row class="rs-blog-aside-data-number">{{ columnCount }}</el-row>
          <el-row class="rs-blog-aside-data-word">专栏数</el-row>
        </router-link>
      </el-col>
      <el-col :span="8">
        <router-link to="/blog/tag">
          <el-row class="rs-blog-aside-data-number">{{ tagCount }}</el-row>
          <el-row class="rs-blog-aside-data-word">标签数</el-row>
        </router-link>
      </el-col>
    </el-row>
    <div class="rs-line"></div>

    <el-row class="rs-blog-aside-action">
      <el-col :span="8">
      <router-link to="/blog/article/write" class="rs-blog-aside-article">
        <el-row>
          <img src="../../assets/img/article.png"/>
        </el-row>
        <el-row class="rs-blog-aside-data-word">新建文章</el-row>
      </router-link>
      </el-col>
      <el-col :span="8">
        <div @click="addColumnVisible=true">
          <el-row>
            <img src="../../assets/img/column.png"/>
          </el-row>
          <el-row class="rs-blog-aside-data-word">新建专栏</el-row>
        </div>
      </el-col>
      <el-col :span="8">
        <div @click="addTagVisible=true">
          <el-row >
            <img src="../../assets/img/tag.png"/>
          </el-row>
          <el-row class="rs-blog-aside-data-word">新建标签</el-row>
        </div>
      </el-col>
    </el-row>
    <div class="rs-line"></div>

    <el-row class="rs-blog-aside-latest">
      <p>最近文章</p>

      <div class="rs-blog-aside-latest-title">
        <router-link :to="{name:'ArticleView',params:{id:recentArticle.id}} " target = _blank>
          {{recentArticle.title}}
        </router-link>
        </div>
      <p>最近专栏</p>
      <div class="rs-blog-aside-latest-title">
        <router-link :to="{name:'ColumnView',params:{id:recentColumn.id}} " target = _blank>
          {{recentColumn.name}}
        </router-link>
      </div>
    </el-row>

    <div class="rs-line"></div>

    <el-row class="rs-blog-aside-to-manager">
      <el-button size="medium">创作管理 <i class="el-icon-arrow-right"></i></el-button>
    </el-row>
    <column-add :dialog-visible="addColumnVisible"
                :append-to-body="false"
                @changeVisible="addComplete"></column-add>
    <tag-add :dialog-visible="addTagVisible"
             :append-to-body="false"
             @changeVisible="addComplete"></tag-add>

  </el-card>

</template>

<script>
import ApiManager from "../../api";
import ColumnAdd from "./column-add";
import TagAdd from "./tag-add";

export default {
  name: "BlogAside",
  components: {TagAdd, ColumnAdd},
  data(){
    return{
      articleCount:'',
      columnCount:'',
      tagCount:'',
      recentArticle:'',
      recentColumn:'',
      addColumnVisible:false,
      addTagVisible:false,
    }
  },
  methods:{
    getBlogAsideInfo(){
      ApiManager.getBlogAsideInfo().then(res=>{
        this.articleCount = res.data.articleCount
        this.columnCount = res.data.columnCount
        this.tagCount = res.data.tagCount
        this.recentArticle = res.data.recentArticle
        this.recentColumn = res.data.recentColumn
      })
    },
    addComplete(hasAdd){
      if(this.addColumnVisible){
        this.addColumnVisible = false
        if(hasAdd){
          this.$router.push("/blog/column")
          this.$emit("contentUpdate")
        }
      }
      if(this.addTagVisible){
        this.addTagVisible = false
        if(hasAdd){
          this.$router.push("/blog/tag")
        }
      }
    }
  },
  created() {
    this.getBlogAsideInfo()
  },
  computed:{
    user(){
      const nickname = this.$store.state.nickname
      const avatar = this.$store.state.avatar
      return {
        nickname, avatar
      }
    }
  }
}
</script>

<style scoped>

.rs-blog-aside{
  position:fixed;
  height:max-content;
  margin-left: 10px;
  margin-top: 20px;
  width: 300px;
}

.rs-blog-aside a{
  text-decoration:none;
  color: #444;
}

.rs-blog-aside a:hover{
  color: #409EFF;
}

.rs-blog-aside-avatar{
  padding-left: 20px;
}

.rs-blog-aside-username{
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
.rs-blog-aside-data {
  text-align: center;
}

.rs-blog-aside-action{
  text-align: center;
}

.rs-blog-aside-action img{
  width: 30px;
  height: 30px;
}

.rs-blog-aside-data-number{
  font-size: 20px;
}

.rs-blog-aside-data-word{
  font-size: 12px;
  color: #444;
}

.rs-blog-aside-latest{
  margin-bottom: 10px;
  margin-left: 10px;
  margin-right: 10px;
}

.rs-blog-aside-latest p{
  font-weight: bold;
  font-size: 15px;
}

.rs-blog-aside-latest-title{
  font-size: 14px;
  margin-bottom: 10px;
  margin-top: 10px;
}

.rs-blog-aside-to-manager{
  text-align: center;
}
.rs-blog-aside-to-manager button{
  width: 100%;
}

</style>