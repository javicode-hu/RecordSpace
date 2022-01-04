<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>
* { margin: 0; padding: 0; }
body {
  background-color: #f5f5f5;
  font-weight: 400;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  line-height: 1.5;

}
::-webkit-scrollbar {
  width: 8px;/*竖向*/
  height: 5px;/*横向*/
}
::-webkit-scrollbar-thumb {
  background-color: #bbb;
  border-radius: 4px;
}



</style>
