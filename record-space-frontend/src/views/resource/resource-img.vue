<template>
  <el-container>
    <el-aside>
      <resource-aside></resource-aside>
    </el-aside>
    <el-main>
      <el-row>
        <el-col :span="6">
         <resource-img-item></resource-img-item>
        </el-col>
        <el-col :span="6">
          <resource-img-item></resource-img-item>
        </el-col>
        <el-col :span="6">
          <resource-img-item></resource-img-item>
        </el-col>
        <el-col :span="6">
          <resource-img-item></resource-img-item>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import ResourceImgItem from "../../components/resource/img-item";
import ResourceAside from "../../components/resource/resource-aside";
export default {
  name: "ResourceImg",
  components: {ResourceAside, ResourceImgItem}
}
</script>

<style scoped>

</style>