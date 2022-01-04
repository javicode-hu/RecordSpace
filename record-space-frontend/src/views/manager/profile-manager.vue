<template>
<el-container>
  <el-aside>
    <manager-aside></manager-aside>
  </el-aside>
  <el-main>
    <profile-main></profile-main>
  </el-main>
</el-container>
</template>

<script>
import ManagerAside from "../../components/common/manager-aside";
import ProfileMain from "../../components/user/profile-main";
export default {
  name: "ProfileManager",
  components: {ProfileMain, ManagerAside}
}
</script>

<style scoped>

</style>