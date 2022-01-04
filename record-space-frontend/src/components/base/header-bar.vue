<template>
    <el-header class="rs-header">
          <div class="rs-header-logo">
            <router-link to="/">
              <img src="../../assets/img/logo.png"/>
            </router-link>
          </div>


          <div class="rs-header-menu">
            <el-menu :default-active="activeIndex" class="el-menu" mode="horizontal" @select="handleSelect" :router="true">
              <el-submenu index="2" class="rs-header-blog">
                <template slot="title" >博客</template>
                <el-menu-item index="/blog/article">博文</el-menu-item>
                <el-menu-item index="/blog/column">专栏</el-menu-item>
                <el-menu-item index="/blog/tag">标签</el-menu-item>
              </el-submenu>

              <el-submenu index="3" class="rs-header-resource">
                <template slot="title" >资源</template>
                <el-menu-item index="/resource/web">网站资源</el-menu-item>
                <el-menu-item index="/resource/doc">文档资源</el-menu-item>
                <el-menu-item index="/resource/img">图片资源</el-menu-item>
              </el-submenu>
              <el-menu-item index="4">
                <el-input
                    placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model="searchKey">
                </el-input>
              </el-menu-item>
              <el-menu-item index="/blog/article/write"><i class="el-icon-edit"></i>写文章</el-menu-item>
            </el-menu>
          </div>


          <div class="rs-header-login">
            <el-menu  menu-trigger="click" mode="horizontal" :router="true">
              <template v-if="!user.login">
                <el-menu-item index="/login-in">
                  <el-button type="text">登录/注册</el-button>
                </el-menu-item>
              </template>
              <template v-else>
                <el-menu-item index="">
                  <el-dropdown >
                <span class="el-dropdown-link">
                   <el-avatar :size="30" :src="user.avatar" ></el-avatar>
                </span>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item @click.native="goUserProfile"><i class="fa fa-user"></i>个人中心</el-dropdown-item>
                      <el-dropdown-item @click.native="goManager"><i class="fa fa-th-large"></i>管理中心</el-dropdown-item>
                      <!--                    <el-dropdown-item ><i class="fa fa-cog"></i>我的设置</el-dropdown-item>-->
                      <el-dropdown-item @click.native="logout"><i class="fa fa-sign-out"></i>账号退出</el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </el-menu-item>
              </template>
            </el-menu>
          </div>

    </el-header>
</template>

<script>
export default {
  name: "HeaderBar",
  data() {
    return {
      activeIndex: '1',
      searchKey:'',
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    logout(){
      const _this = this
      this.$store.dispatch("logout").then(()=>{
        _this.$router.push('/')
      })
    },
    goManager(){
      this.$router.push("/manager")
    },
    goUserProfile(){
      this.$router.push("/manager/profile")
    }
  },
  computed:{
    user(){
      const login = this.$store.state.username !=null
      const avatar = this.$store.state.avatar
      return {
        login, avatar
      }
    }
  }
}
</script>

<style scoped>

.el-header {
  position: fixed;
  z-index: 1024;
  min-width: 100%;
  box-shadow: 0 2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
}



.rs-header-logo img{
  max-height: 2.4rem;
  max-width: 100%;
  margin-top: 10px;
}

.rs-header{
  background-color: #fff;
  /*display: flex;*/
}
.el-menu.el-menu--horizontal{
  border-bottom:none;
}
.rs-header-logo{
  width: 10%;
  float: left;
}


.rs-header-menu{
  width: 80%;
  float: left;
  overflow:hidden;
  height: 60px;
}

.rs-header-login{
  width: 10%;
  float: left;
}

.rs-header-resource{
  margin-right: 20px;
}


</style>