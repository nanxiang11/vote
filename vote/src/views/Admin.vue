<template>
  <el-main>
    <div>
      <Menu/>
    </div>
    <div style="margin-top: 20px;text-align: center;color: #067f32">
      <router-link to="/admin"><el-button type="danger" round style="margin-right: 50px">活动管理</el-button></router-link>
      <span style="font-size: 20px">后台活动管理</span>
      <router-link to="/adminuser"><el-button type="warning" round style="margin-left: 50px">权限管理</el-button></router-link>
    </div>
    <div style="padding: 50px 300px 20px 300px">
      <el-table
          :data="
          tableData.filter(
            (data) =>
              !search || data.activename.toLowerCase().includes(search.toLowerCase())
          )
        "
          style="width: 100%"
      >
        <el-table-column label="活动时间" prop="post_time" />
        <el-table-column label="活动名称" prop="activename" />
        <el-table-column label="活动负责人" prop="studentName" />
        <el-table-column label="活动结束时间" prop="endtime" />
        <el-table-column align="right">
          <template #header>
            <el-input v-model="search" size="mini" placeholder="Type to search" />
          </template>
          <template #default="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >下载</el-button
            >
            <el-button
                size="mini"
                type="success"
                @click="handleDelete(scope.$index, scope.row)"
            >通过</el-button
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
import router from "../router";
export default {
  name: "Admin",
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
      this.downloadImg("http://localhost:9000" + row.imgurl,row.activename);
    },
    downloadImg(imgsrc, name){
      var image = new Image();
      // 解决跨域 Canvas 污染问题
      image.setAttribute("crossOrigin", "anonymous");
      image.onload = function() {
        var canvas = document.createElement("canvas");
        canvas.width = image.width;
        canvas.height = image.height;
        var context = canvas.getContext("2d");
        context.drawImage(image, 0, 0, image.width, image.height);
        var url = canvas.toDataURL(); //得到图片的base64编码数据
        console.log(url)

        var a = document.createElement("a"); // 生成一个a元素
        var event = new MouseEvent("click"); // 创建一个单击事件
        a.download = name || "photo"; // 设置图片名称
        a.href = url; // 将生成的URL设置为a.href属性
        a.dispatchEvent(event); // 触发a的单击事件
      };
      image.src = imgsrc;
    },
    handleDelete(index, row) {
      var qs = require('querystring')
      var id = row.id;
      this.axios.post("/getactiveID", qs.stringify({id:id})).then(result =>{
        if (result.data.code === 200){
          ElMessage({
            message: '审核成功',
            type: 'success',
            duration : 3000
          })
        }else {
          ElMessage({
            message: '审核失败，请重新审核！',
            type: 'error',
            duration : 3000
          })
        }
      })
    },
    getinfo(){
      this.axios.get("/getadminActive").then(result =>{
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