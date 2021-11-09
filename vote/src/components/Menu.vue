<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <div class="menudiv" style="color: aliceblue;font-size: 20px;padding: 10px 10px 10px 10px">
          <router-link to="/" style="color: #5454c4">
            <i class="fa fa-check-square"></i>&nbsp;
            <span><b>南交投票系统</b></span></router-link>
      </div>
    </el-col>
    <el-col :span="6">
      <div class="menudiv">
        <router-link v-if="pd.a === 1" to="/admin" style="margin-right: 10px"><el-button type="primary" plain>后台管理中心</el-button></router-link>
        <router-link v-if="userinfo.id === 6" to="/addmanage"><el-button type="success" plain>添加管理员</el-button></router-link>
        <router-link v-if="userinfo.id !== 6" to="#"><el-button type="success" plain>待开发</el-button></router-link>
        <el-button type="info" plain style="margin-left: 10px">待开发</el-button>
      </div>
    </el-col>
    <el-col :span="6">
      <div class="menudiv">
      </div>
    </el-col>
    <el-col :span="6">
      <div class="menudiv">
        <router-link v-if="!userinfo.imgurl" to="/login"><el-button type="success" round>登入</el-button></router-link>
        <el-dropdown v-if="userinfo.imgurl">
          <span class="el-dropdown-link">
            <el-avatar
                v-bind:src="userinfo.imgurl"
            ></el-avatar>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>姓名<span style="float: right">{{userinfo.studentName}}</span></el-dropdown-item>
              <el-dropdown-item>学院<span style="float: right">{{userinfo.college}}</span></el-dropdown-item>
              <el-dropdown-item>身份<span style="float: right">{{userinfo.aboutme}}</span></el-dropdown-item>
              <router-link v-if="userinfo.imgurl" to="/user"><el-dropdown-item divided>个人中心</el-dropdown-item></router-link>
              <router-link v-if="!userinfo.imgurl" to="/login"><el-dropdown-item divided>个人中心</el-dropdown-item></router-link>
              <router-link v-if="userinfo.select" to="/active"><el-dropdown-item divided>活动发布</el-dropdown-item></router-link>
              <router-link v-if="!userinfo.imgurl" to="/login"><el-dropdown-item divided>活动发布</el-dropdown-item></router-link>
              <router-link to="/login"><el-dropdown-item divided>退出登入</el-dropdown-item></router-link>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import {ElMessage} from "_element-plus@1.1.0-beta.24@element-plus";

export default {
  name: "Menu",
  data(){
    return{
      userinfo: {

      },
      pd:{}
    }
  },
  methods:{
    add(){
      this.axios.get("/student/getmanage", {params:{name:this.userinfo.studentName}}).then(result =>{
        if (result.data.code !== 200){
          this.pd={}
        }else {
          this.pd={a:1}
        }
      })
    }
  },
  created() {
    this.userinfo = JSON.parse(localStorage.getItem("user_info"))
    this.add()
  }
}
</script>

<style scoped>
.menudiv{
  text-align: center;
}
.el-dropdown-menu{
  width: 260px;
}
a {
  text-decoration: none;
}

.router-link-active {
  text-decoration: none;
}
</style>