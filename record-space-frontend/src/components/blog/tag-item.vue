<template>
  <el-col :span="6">
    <el-card class="rs-tag-item"  shadow="hover">
      <el-row>
        <el-col :span="10">
          <div class="rs-tag-pic">
            <img src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg" alt="">
          </div>
        </el-col>
        <el-col :span="14" class="rs-tag-content">
          <div class="rs-item-word-body">
            <div class="rs-tag-name">
              <router-link :to="{name:'TagView',params:{id:tag.id}} " target = _blank>
                <h3>
                  {{ tag.name }}
                </h3>
              </router-link>
            </div>

          </div>
          <div class="rs-tag-footer">
            <div>{{ tag.articleCount }} 文章</div>
            <div>{{tag.createTime}}</div>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </el-col>

</template>

<script>
import moment from "moment";

export default {
  name: "TagItem",
  props:{
    tag:Object
  },
  created() {
    this.tag.createTime = moment(this.tag.createTime).format('YYYY-MM-DD HH:mm')
  }
}
</script>

<style scoped>

.rs-tag-item{
  margin-left:10px ;
  margin-right: 10px;
  text-align: center;
  margin-bottom: 10px;
}


.rs-tag-pic img{
  width: 80px;
  height: 80px;
  border-radius: 10px;
}

.rs-tag-content{
  text-align: center;
}

.rs-tag-name a{
  text-decoration:none;
  color: #444;
}
.rs-tag-name a:hover{
  color: #409EFF;
}


.rs-tag-footer{
  margin-top: 10px;
  font-size: .835rem;
  color: #909399;
}


</style>