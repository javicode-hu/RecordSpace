<template>
  <el-col :span="12">
    <el-card class="rs-column-item" shadow="hover">
      <div class="rs-item-word-body">
        <el-row class="rs-column-header">
          <div class="rs-column-title">
            <router-link :to="{name:'ColumnView',params:{id:column.id}} " target = _blank>
              <h3>{{column.name}}</h3>
            </router-link>
          </div>
          <div class="rs-column-date">
            <i class="fa fa-clock-o" ></i>
            {{column.createTime}}
          </div>
        </el-row>
        <el-row class="rs-column-body">
        <span>
             {{column.description}}
        </span>
        </el-row>
      </div>
      <el-row class="rs-column-footer">
          <span> <i class="fa fa-user-o" ></i> {{ column.author }}</span>
          <span class="rs-column-right-data"> <i class="fa fa-eye" ></i> {{ column.viewCount }}</span>
          <span class="rs-column-right-data"> <i class="fa fa-thumbs-o-up" ></i> {{ column.likeCount }}</span>
          <span class="rs-column-right-data"> <i class="fa fa-sticky-note-o" ></i> {{ column.articleCount }}</span>
      </el-row>
    </el-card>
  </el-col>

</template>

<script>
import moment from "moment";

export default {
  name: "ColumnItem",
  props:{
    column:Object
  },created() {
    this.column.createTime = moment(this.column.createTime).format('YYYY-MM-DD HH:mm')
  }
}
</script>

<style scoped>


.rs-column-item{
  margin-right: 10px;
  margin-bottom: 10px;
}

.rs-column-header{
  margin-bottom: 10px;
}

.rs-column-title{
  float: left;
}

.rs-column-title a{
  text-decoration:none;
  color: #444;
}

.rs-column-title a:hover{
  color: #409EFF;
}

.rs-column-date{
  float: right;
  margin-right: 15px;
  color: #8590a6;
  font-size: .835rem;
  line-height: 27px;
}

.rs-column-body{
  margin-bottom: 5px;
  min-height: 30px;
  font-size: 14px;
  color: #646464;
}

.rs-column-footer{
  color: #8590a6;
  font-size: .835rem;
}
.rs-column-right-data{
  float: right;
  margin-right: 15px;
}

</style>