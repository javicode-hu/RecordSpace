<template>
  <el-card class="rs-doc-item">
    <div class="rs-doc-body">
      <i class="fa fa-file-pdf-o"></i>
    </div>
    <div class="rs-doc-title">
      <span>阿里巴巴java开发手册.pdf</span>
    </div>
    <div class="res-doc-footer">
      <span class="res-doc-size"><i class="fa fa-file"></i> 12.3KB</span>
      <span class="res-doc-date"><i class="fa fa-clock-o"></i> 2019-12-12</span>
      <span class="res-doc-dwd"><i class="fa fa-download"></i></span>
    </div>
  </el-card>

</template>

<script>
export default {
  name: "DocItem"
}
</script>

<style scoped>
.rs-doc-item{
  margin-right: 10px;
  margin-left: 10px;
}
.rs-doc-body{
  background-color: #f5f5f5;
  padding:0.75rem 1.5rem 1.5rem;
  text-align: center;
  font-size: 2rem;
  border-radius: 5px;
}
.rs-doc-title{
  text-align: center;
  margin-top: -22px;
  font-size: .835rem;
}

.res-doc-footer{
  margin-top: 20px;
  font-size: .835rem;

}

.res-doc-size{
  margin-right: 20px;
}

.res-doc-dwd{
  float: right;
}

</style>