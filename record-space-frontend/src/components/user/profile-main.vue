<template>
  <el-card shadow="hover" class="rs-user-profile">
    <div slot="header" class="clearfix rs-profile-title">
      <span>基本信息</span>
      <el-button style="float: right; padding: 3px 0" type="text">编辑</el-button>
    </div>
    <div class="rs-profile-body">
      <el-row class="rs-profile-img">
        <el-col :span="2" class="rs-profile-label">头像</el-col>
        <el-col :span="6" class="rs-profile-value">
          <el-avatar :size="80" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
        </el-col>
      </el-row>
      <el-row class="rs-profile-item">
        <el-col :span="2" class="rs-profile-label">用户名</el-col>
        <el-col :span="6" class="rs-profile-value"> <el-input v-model="input" placeholder="请输入内容"></el-input></el-col>
      </el-row>
      <el-row class="rs-profile-item">
        <el-col :span="2" class="rs-profile-label">昵称</el-col>
        <el-col :span="6" class="rs-profile-value"> <el-input v-model="input" placeholder="请输入内容"></el-input></el-col>
      </el-row>
      <el-row class="rs-profile-item">
        <el-col :span="2" class="rs-profile-label">个人简介</el-col>
        <el-col :span="6" class="rs-profile-value">
          <el-input v-model="input" type="textarea" :rows="2" placeholder="请输入内容"></el-input>
        </el-col>
      </el-row>
    </div>

  </el-card>
</template>

<script>
export default {
  name: "ProfileMain",
  data(){
    return{
      input:''
    }
  }
}
</script>

<style scoped>
.rs-user-profile{


}
.rs-profile-title{
  font-size: 18px;
  font-weight: 600;

}
.rs-profile-body{
  padding: 10px;
}

.rs-profile-img{
  margin-bottom: 20px;
  font-size: 14px;
}

.rs-profile-item{
  height: 40px;
  margin-bottom: 20px;
  font-size: 14px;
}
.rs-profile-label{
  line-height: 40px;
}

</style>