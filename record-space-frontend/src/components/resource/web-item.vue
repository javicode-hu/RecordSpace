<template>
  <el-col :span="8">
    <el-card class="resource-web-item" shadow="hover">
      <el-row>
        <el-col :span="10">
          <div class="rs-web-item-pic">
            <img src="https://636f-codenav-8grj8px727565176-1256524210.tcb.qcloud.la/img/1610267516825-LeetCode_Cup.png" alt="">
          </div>
        </el-col>
        <el-col :span="14">
          <div class="rs-web-item-title">
            <a>{{ webItem.name }}</a>
          </div>
          <div class="rs-web-item-body">
           {{ description }}
          </div>
        </el-col>
      </el-row>
    </el-card>
  </el-col>
</template>

<script>
export default {
  name: "WebItem",
  props:{
    webItem:Object
  },
  computed:{
    description(){
      if(this.webItem.description.length > 40){
        return this.webItem.description.substring(0,40) + "..."
      }
      return this.webItem.description
    }
  }
}
</script>

<style scoped>
.resource-web-item{
  margin-right: 10px;
  margin-left: 10px;
  margin-bottom: 10px;
}
.rs-web-item-pic img{
  width: 80px;
  height: 80px;
  border-radius: 10px;;
}
.rs-web-item-title a{
  font-size: 16px;
  font-weight: bold;
}

.rs-web-item-body{
  font-size: 13px;
  color: #909399;
}
.rs-web-item-tag span{
  margin-right: 10px;
}

</style>