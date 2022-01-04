<template>
<el-container >
  <el-aside class="rs-article-catalogue">
  <el-card>
      <el-tree :data="directoryData"
               :props="defaultProps"
                :render-content = "renderContent"
      :highlight-current="true"></el-tree>
  </el-card>
  </el-aside>
  <el-main class="rs-detail-main">
    <el-backtop target=".rs-detail-main">
    </el-backtop>
    <el-card>
      <el-row class="rs-detail-title">
        <h2>{{article.title}}</h2>

      </el-row>
      <el-row class="rs-detail-brief" >
        <el-descriptions :column="4">
          <el-descriptions-item label="作者">{{ article.author}}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ article.createTime }}</el-descriptions-item>
          <el-descriptions-item label="阅读量">{{ article.viewCount }}</el-descriptions-item>
          <el-descriptions-item label="点赞数">{{ article.likeCount}}</el-descriptions-item>
          <el-descriptions-item label="文章专栏" :span="2">
            <el-tag class="rs-article-detail-tag" size="small" type="success" v-for="column in article.columnList" :key="column.id">{{column.name}}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="文章标签" :span="2">
            <el-tag class="rs-article-detail-tag" size="small" v-for="tag in article.tagList" :key="tag.id">{{tag.name}}</el-tag>
          </el-descriptions-item>

        </el-descriptions>
      </el-row>
      <el-row class="rs-detail-content">
        <div class="content markdown-body" ref="content" v-html="content">
        </div>
      </el-row>
    </el-card>

  </el-main>

</el-container>
</template>

<script>
import ApiManager from "../../api";
import 'github-markdown-css/github-markdown.css'
import moment from "moment";

const md = require('markdown-it')({})
export default {
  name: "ArticleView",
  data(){
    return {
      article: {},
      catalogue:'',
      directoryData:[],
      content:'',
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  methods:{
    renderContent(h, { node, data, store }){
      return(
          <span>
            <span ><a style="text-decoration:none;color: #444;" href={data.url}>{node.label}</a></span>
          </span>
      )
    },
    updateViewCount(){
      ApiManager.updateViewCount(this.$route.params.id)
    },
    getArticle(){
      ApiManager.getBlogArticleById(this.$route.params.id).then(res=>{
        this.article = res.data
        console.log( this.article)
        this.article.createTime =moment(this.article.createTime).format('YYYY-MM-DD HH:mm')
        this.article.content = md.render(this.article.content);
        this.getArticleDirectory()
      })
    },
    getArticleDirectory(){
      // 获取正文dom
      let elem = new DOMParser().parseFromString(this.article.content, 'text/html');
      //获取所有元素节点
      let nodes = elem.getElementsByTagName("*");
      //h标签的正则
      let hPatt = /^h\d/i
      //h标签个数
      let hNum = 0
      //遍历所有元素节点
      for (let i = 0; i < nodes.length; i++) {
          if(hPatt.test(nodes[i].nodeName)){
            //设置标签链接
            let nodeTitle = document.createElement("a")
            nodeTitle.setAttribute("id", "article-title"+hNum);
            nodeTitle.setAttribute("style","position: relative;top: -80px;");
            nodes[i].appendChild(nodeTitle)
            hNum ++
            //获取h标签文本内容
            let nodeText =  nodes[i].innerText
            //创建树形控件数据
            let nodeObj = {
                label:nodeText,
                nodeName:nodes[i].nodeName,
                children:[],
                url:"#"+nodeTitle.getAttribute("id")
            }
            let tmpDir = this.directoryData
            let tmpObj = tmpDir[tmpDir.length-1]
            let tmpLength = tmpDir.length
            while (tmpLength>0 && nodeObj.nodeName>tmpObj.nodeName){
              tmpDir = tmpObj.children
              tmpLength = tmpDir.length
              if(tmpLength>0){
                tmpObj = tmpDir[tmpDir.length-1]
              }
            }
            tmpDir.push(nodeObj)
          }
      }
      this.content =elem.children[0].innerHTML
    },
  },
  mounted() {
      this.getArticle()
      this.updateViewCount()
  }
}
</script>

<style scoped>
.rs-article-catalogue{
  margin-top: 20px;
  display: block;
  position: absolute;
  left: 10px;
  top: 60px;
  bottom: 0;
}
.rs-article-detail-tag{
  margin-right: 10px;
}

.rs-detail-brief{
  margin-top: 10px;
  margin-bottom: 10px;

}

.rs-detail-main{
  position: absolute;
  left: 300px;
  right: 0;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;
}
.rs-detail-title{
  text-align: center;
}

</style>