<template>
  <el-container>
    <el-aside>
      <manager-aside></manager-aside>
    </el-aside>
    <el-main>

    </el-main>
  </el-container>
</template>

<script>
import ManagerAside from "../../components/common/manager-aside";
export default {
  name: "ColumnManager",
  components: {ManagerAside}
}
</script>

<style scoped>

</style>