<template>
<div class="rs-side-toolbar">
  <a href="" class="rs-option-box">
    <i class="el-icon-edit"></i>
  </a>
  <a href="" class="rs-option-box">
    <i class="el-icon-arrow-up"></i>
  </a>

</div>
</template>

<script>
export default {
  name: "SideToolBar"
}
</script>

<style scoped>
.rs-side-toolbar{
  position: fixed;
  right: 20px;
  bottom: 100px;
  width: 44px;
  z-index: 1999;
}

.rs-option-box{
  position: relative;
  display: flex;
  align-items: center;
  border-radius: 50%;
  height: 44px;
  background: #a2a3a5;
  color: #fff;
  justify-content: center;
  margin-top: 15px;
}

.rs-option-box:hover{
  right: 2px;
  background: #909295;
}
</style>