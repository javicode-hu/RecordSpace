<template>
  <el-container >
    <el-main>
      <el-form ref="articleForm" :model="articleForm">
        <el-card class="rs-article-title" >
            <input placeholder="请输入标题（最多 50 个字）"
                   v-model="articleForm.title"
                   type="textarea"></input>
            <el-button type="primary" @click="cancelPublish">取消</el-button>
            <el-button type="primary"  @click="handlePublish">发布</el-button>
          <span>
        </span>
        </el-card>
        <mavon-editor v-model="articleForm.content" class="rs-article-md"></mavon-editor>
        <el-dialog
            title="发布文章"
            :visible.sync="publishVisible"
            width="40%"
            class="rs-article-publish">
          <el-form-item  label="文章专栏" >
            <el-select v-model="articleForm.columnIdList"  multiple placeholder="请选择">
              <el-option
                  v-for="item in columnList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
            <el-button icon="el-icon-plus" size="medium" @click="addColumnVisible=true">新建专栏</el-button>
          </el-form-item>
          <el-form-item  label="文章标签">
            <el-select v-model="articleForm.tagIdList"  multiple placeholder="请选择">
              <el-option
                  v-for="item in tagList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
            <el-button icon="el-icon-plus" size="medium" @click="addTagVisible=true">新建标签</el-button>
          </el-form-item>
          <span slot="footer" class="dialog-footer">
            <el-button @click="publishVisible = false">取 消</el-button>
            <el-button type="primary" @click="submitForm">确 定</el-button>
          </span>
        </el-dialog>
        <column-add :dialog-visible="addColumnVisible"
                            :append-to-body="true"
                            @changeVisible="addComplete"></column-add>
        <tag-add :dialog-visible="addTagVisible"
                         :append-to-body="true"
                         @changeVisible="addComplete"></tag-add>
      </el-form>
    </el-main>

  </el-container>

</template>

<script>

import ApiManager from "../../api";
import ColumnAdd from "../../components/blog/column-add";
import TagAdd from "../../components/blog/tag-add";
export default {
  name: "ArticleWrite",
  components: {TagAdd, ColumnAdd, },
  data(){
    return{
      publishVisible: false,
      addColumnVisible:false,
      addTagVisible:false,
      columnList:[],
      tagList:[],
      articleForm:{
        id:null,
        title:'',
        content:'',
        columnIdList:'',
        tagIdList:''
      }
    }
  },
  methods:{
    validateArticle(){
      if(this.articleForm.title.length<3||this.articleForm.title.length>50){
        this.$message.error('文章标题长度应该再 3~50 之间');
        return false
      }
      if(this.articleForm.content.length===0){
        this.$message.error('文章内容不能为空!');
        return false
      }
      return true
    },
    handlePublish(){
      if(this.validateArticle()){
        this.publishVisible = true
      }
    },
    cancelPublish(){
      this.$router.go(-1)
    }
    ,
    submitForm(){
      let loading = this.$loading({
        lock: true,
        text: '发布中，请稍后...'
      })
      ApiManager.addBlogArticle(this.articleForm).then(res=>{
        loading.close()
        this.$message.success("文章成功发布啦!")
        this.$router.push("/article")
      })
      setTimeout(() => {loading.close();}, 5000);

    },
    addComplete(hasAdd){
      if(this.addColumnVisible){
        this.addColumnVisible = false
        if(hasAdd){
          this.getColumnList()
        }
      }
      if(this.addTagVisible){
        this.addTagVisible = false
        if(hasAdd){
          this.getTagList()
        }
      }
    },
    getColumnList(){
      ApiManager.listBlogColumnByUserId().then((res)=>{
        this.columnList = res.data
        console.log(this.columnList)
      });
    },
    getTagList(){
      ApiManager.listBlogTagByUserId().then((res)=>{
        this.tagList = res.data
        console.log(this.tagList)
      });
    }
  },
  created() {
    this.getColumnList()
    this.getTagList()
  }
}
</script>

<style scoped>
.rs-article-title{
  margin-bottom: 5px;
}

.rs-article-md{
  min-height: 600px;
}
.rs-article-title input{
  border: 0;
  width: 80%;
  line-height: 1.4;
  font-size: 32px;
  font-weight: 600;
  outline: none;
}

.rs-article-title button{
  float: right;
  margin-right: 10px;
}

.rs-article-publish button{
  margin-left: 20px;
}

</style>