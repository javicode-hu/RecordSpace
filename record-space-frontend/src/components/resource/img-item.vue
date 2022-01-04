<template>
  <el-card class="rs-img-item">
    <div class="rs-img-body">
     <el-image src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg" :fit="fill"></el-image>
    </div>
    <div class="res-img-footer">
        <span class="res-img-size"><i class="fa fa-file"></i> 12.3KB</span>
        <span class="res-img-date"><i class="fa fa-clock-o"></i> 2019-12-12</span>
        <span class="res-img-dwd"><i class="fa fa-download"></i></span>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "ImgItem"
}
</script>

<style scoped>
.rs-img-item{
  margin-left: 5px;
  margin-right: 5px;

}
.rs-img-body{
  text-align: center;
}

.rs-img-body img{
  width: 150px;
  height: 100%;

}

.res-img-footer{
  font-size: .835rem;
}
.res-img-size{
  margin-right: 20px;
}

.res-img-footer .res-img-dwd{
  float: right;
}


</style>