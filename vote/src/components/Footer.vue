<template>
  <el-footer>
    <div style="margin: auto;width: 220px">
      <span style="color: #ffffff">期待您的满分好评</span>
      <br>
      <el-rate
          v-model="value"
          :texts="['有本事你就点', '你敢吗', '普通', '满意', '非常满意']"
          show-text
      >
      </el-rate>
      <div style="margin-top: 8px;text-align: center;color: white">
      </div>
    </div>
  </el-footer>
</template>

<script>
import {ref} from "vue";

export default {
  name: "Footer",
  data(){
    return{
      value: ref(null),
    }
  }
}
</script>

<style scoped>
.el-footer {
  background-color: #303133;
  color: var(--el-text-color-primary);
  min-height: 80px;
  bottom: 0;
  padding: 10px 10px 10px 10px;
}
</style>