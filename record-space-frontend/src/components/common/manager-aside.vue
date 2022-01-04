<template>
  <el-card  shadow="hover" class="rs-manager-aside">
    <el-menu
        default-active="1"
        class="el-menu-vertical"
        :default-openeds="['1','2','3']"
        @open="handleOpen"
        @close="handleClose" :router="true">
      <el-submenu index="3" >
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>个人中心</span>
        </template>
        <el-menu-item index="/manager/user-profile">个人资料</el-menu-item>
        <el-menu-item index="/manager/user-account">账号设置</el-menu-item>
<!--        <el-menu-item index="/manager/tag">隐私设置</el-menu-item>-->
      </el-submenu>
      <el-submenu index="1" >
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>管理中心</span>
        </template>
        <el-menu-item-group>
          <template slot="title">创作管理</template>
          <el-menu-item index="/manager/article">文章管理</el-menu-item>
          <el-menu-item index="/manager/article-column">专栏管理</el-menu-item>
          <el-menu-item index="/manager/article-tag">标签管理</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group>
          <template slot="title">资源管理</template>
          <el-menu-item index="/manager/article">网站管理</el-menu-item>
          <el-menu-item index="/manager/article-column">文档管理</el-menu-item>
          <el-menu-item index="/manager/article-tag">图片管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-card>
</template>

<script>
export default {
  name: "ManagerAside",
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  created() {

  }
}
</script>

<style scoped>
.rs-manager-aside{
  height: 600px;
  margin-top: 20px;
  margin-left: 10px;
  overflow: auto;
}

.el-menu{
  border: none;

}

</style>