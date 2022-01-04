<template>
  <div id="home" class="rs-main">
    <el-container class="rs-main-container">
      <header-bar></header-bar>
      <router-view class="rs-main-content"/>
      <footer-bar></footer-bar>
    </el-container>
  </div>
</template>

<script>
import HeaderBar from "./components/base/header-bar";
import FooterBar from "./components/base/footer-bar";
export default {
  name: "Home",
  components: {FooterBar, HeaderBar},

}
</script>

<style scoped>

.rs-main{
  /*height: 2000px;*/
}

.rs-main-container{

}
.rs-main-content{
  margin-top: 60px;
}

</style>