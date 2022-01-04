  <template>
  <el-card class="rs-article-item" shadow="hover">
      <div class="rs-article-item-body">
        <div class="rs-item-word-body">
          <div class="rs-article-title">
            <router-link :to="{name:'ArticleView',params:{id:article.id}} " target = _blank>
              <h3 >
                {{article.title}}
              </h3>
            </router-link>
          </div>

          <div class="rs-article-description">
           <span>
             <div class="content markdown-body" v-html="article.content"></div>
            </span>
          </div>
        </div>

        <div class="rs-article-footer">
          <el-row >
            <el-col :span="6" class="rs-article-author">
              <i class="fa fa-user-o" ></i>
              {{ article.author }}
            </el-col>
            <el-col :span="10" class="rs-article-tag">
              <el-tag size="mini" v-for="tag in article.tagList" :key="tag.id">{{tag.name}}</el-tag>
            </el-col >
            <el-col :span="8" class="rs-article-right">
              <el-col :span="12" class="rs-article-date">
                <i class="fa fa-clock-o" ></i>
                {{ article.createTime }}
              </el-col>
              <el-col :span="6" class="rs-article-up">
                <i class="fa fa-thumbs-o-up" ></i>
                {{ article.likeCount }}
              </el-col>
              <el-col :span="6" class="rs-article-see">
                <i class="fa fa-eye" ></i>
                {{ article.viewCount }}
              </el-col>
            </el-col>
          </el-row>
        </div>
      </div>
  </el-card>
</template>

<script>
const md = require('markdown-it')({})
import moment from "moment";
export default {
  name: "ArticleItem",
  props:{
    article:Object
  },
  data(){
    return{

    }

  },methods:{
    //提取html中的文字
    parseHtml(content){
      let re = new RegExp('<[^<>]+>','g');
      return content.replace(re,"")
    }

  },created() {
    let htmlContent =  md.render(this.article.content)
    this.article.content = this.parseHtml(htmlContent).substring(0,150)+"..."
    this.article.createTime = moment(this.article.createTime).format('YYYY-MM-DD HH:mm')
  }
}
</script>

<style scoped>

.rs-article-item{
  margin-bottom: 10px;
}
.rs-article-description{
  margin-top: 9px;
  margin-bottom: 5px;
}
.rs-article-footer{
  margin-top: 10px;
  color: #8590a6;
  font-size: .835rem;
}

.rs-article-tag span{
  margin-right: 10px;
}

.rs-article-title a:hover{
  color: #409EFF;
}

.rs-article-title a{
  text-decoration:none;
  color: #444;
}

.el-col{
  border: 1px solid transparent;
}
</style>