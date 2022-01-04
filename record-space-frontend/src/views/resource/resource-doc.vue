<template>
  <el-container>
    <el-aside>
      <resource-aside></resource-aside>
    </el-aside>
    <el-main>
      <el-row>
        <el-col :span="6">
          <resource-doc-item></resource-doc-item>
        </el-col>
        <el-col :span="6">
          <resource-doc-item></resource-doc-item>
        </el-col>
        <el-col :span="6">
          <resource-doc-item></resource-doc-item>
        </el-col>
        <el-col :span="6">
          <resource-doc-item></resource-doc-item>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import ResourceDocItem from "../../components/resource/doc-item";
import ResourceAside from "../../components/resource/resource-aside";
export default {
  name: "ResourceDoc",
  components: {ResourceAside, ResourceDocItem}
}
</script>

<style scoped>

</style>