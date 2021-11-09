<template>
  <el-main>
    <div>
      <Menu/>
    </div>
    <div style="margin-top: 20px;text-align: center;color: #067f32">
      <router-link to="/admin"><el-button type="danger" round style="margin-right: 50px">活动管理</el-button></router-link>
      <span style="font-size: 20px">后台用户活动管理</span>
      <router-link to="/adminuser"><el-button type="warning" round style="margin-left: 50px">权限管理</el-button></router-link>
    </div>
    <div style="padding: 50px 300px 20px 300px">
      <el-table
          :data="
          tableData.filter(
            (data) =>
              !search || data.studentName.toLowerCase().includes(search.toLowerCase())
          )
        "
          style="width: 100%"
      >
        <el-table-column label="注册时间" prop="post_time" />
        <el-table-column label="姓名" prop="studentName" />
        <el-table-column label="学院" prop="college" />
        <el-table-column label="申报身份" prop="aboutme" />
        <el-table-column label="状态" prop="select" />
        <el-table-column align="right">
          <template #header>
            <el-input v-model="search" size="mini" placeholder="Type to search" />
          </template>
          <template #default="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >同意</el-button
            >
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
            >驳回</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>

  </el-main>
</template>

<script>
import Menu from "../components/Menu";
import {ElMessage} from "_element-plus@1.1.0-beta.24@element-plus";
export default {
  name: "AdminUser",
  components: {Menu},
  data() {
    return {
      tableData: [

      ],
      search: '',
    }
  },
  created() {
    this.getinfo()
  },
  methods: {
    handleEdit(index, row) {
      var qs = require('querystring')
      this.axios.post("/setuseradmin", qs.stringify({id:row.id, select:'1'})).then(result =>{
        ElMessage({
          message: '操作成功',
          type: 'success',
          duration : 3000
        })
      })
    },
    handleDelete(index, row) {
      var qs = require('querystring')
      this.axios.post("/setuseradmin", qs.stringify({id:row.id, select:'0'})).then(result =>{
        ElMessage({
          message: '操作成功',
          type: 'success',
          duration : 3000
        })
      })
    },
    getinfo(){
      this.axios.get("/getadminuser").then(result =>{
        if (result.data.code === 200){
          ElMessage({
            message: '数据加载成功',
            type: 'success',
            duration : 3000
          })
          this.tableData = result.data.data;
        }else {
          ElMessage({
            message: '数据加载失败！',
            type: 'error',
            duration : 3000
          })
        }
      })
    }
  },
}
</script>

<style scoped>

</style>