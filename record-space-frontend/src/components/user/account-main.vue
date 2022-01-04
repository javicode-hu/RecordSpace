<template>
  <el-card shadow="hover" class="rs-user-account">
    <div slot="header" class="clearfix rs-account-title">
      <span>账号设置</span>
    </div>
    <div class="rs-account-body">
      <el-row class="rs-account-item">
        <el-col :span="2" class="rs-account-label">密码</el-col>
        <el-col :span="22" class="rs-account-op">
          <span class="rs-account-content">************</span>
          <el-button type="text">修改密码</el-button>
        </el-col>
      </el-row>
      <el-row class="rs-account-item">
        <el-col :span="2" class="rs-account-label">手机</el-col>
        <el-col :span="22" class="rs-account-op">
          <span class="rs-account-content">138*****7696</span>
          <el-button type="text">修改手机</el-button>
        </el-col>
      </el-row>
      <el-row class="rs-account-item">
        <el-col :span="2" class="rs-account-label">邮箱</el-col>
        <el-col :span="22" class="rs-account-op">
          <span class="rs-account-content">164*******@qq.com</span>
          <el-button type="text">修改邮箱</el-button>
        </el-col>
      </el-row>
      <el-row class="rs-account-item">
        <el-col :span="2" class="rs-account-label">登录记录</el-col>
        <el-col :span="22" class="rs-account-op">
          <el-button type="text">查看记录</el-button>
        </el-col>
      </el-row>
      <el-row class="rs-account-item">
        <el-col :span="2" class="rs-account-label">账号注销</el-col>
        <el-col :span="22" class="rs-account-op">
          <el-button type="text">立即注销</el-button>
        </el-col>
      </el-row>

    </div>

  </el-card>
</template>

<script>
export default {
  name: "AccountMain",
  data(){
    return{
      input:''
    }
  }
}
</script>

<style scoped>
.rs-user-account{


}
.rs-account-title{
  font-size: 18px;
  font-weight: 600;

}
.rs-account-body{
  padding: 10px;
}

.rs-account-item{
  height: 40px;
  margin-bottom: 20px;
  font-size: 14px;
}
.rs-account-label{
  line-height: 40px;
}
.rs-account-content{
  line-height: 40px;
}


.rs-account-op button{
  float: right;
}

</style>