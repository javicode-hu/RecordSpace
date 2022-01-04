<template>
  <el-container>
    <el-aside>
      <manager-aside></manager-aside>
    </el-aside>
    <el-main>
      <account-main></account-main>
    </el-main>
  </el-container>
</template>

<script>
import ManagerAside from "../../components/common/manager-aside";
import AccountMain from "../../components/user/account-main";
export default {
  name: "AccountManager",
  components: {AccountMain, ManagerAside}
}
</script>

<style scoped>

</style>