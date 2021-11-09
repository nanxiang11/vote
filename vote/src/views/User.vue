<template>
  <div style="background-color: rgb(255,255,255)">
    <el-main>
      <div style="margin-bottom: 80px">
        <Menu/>
      </div>
      <div style="width: 70%;min-height: 600px;background-color: aliceblue;border-radius: 8px;margin: auto;padding: 10px 10px 10px 10px">
        <div>
          <el-descriptions
              title="个人信息详情表"
              direction="vertical"
              :column="4"
              border
          >
            <el-descriptions-item label="Username">{{userinfo.studentName}}</el-descriptions-item>
            <el-descriptions-item label="Email">{{userinfo.studentEmail}}</el-descriptions-item>
            <el-descriptions-item label="学院" :span="2">{{userinfo.college}}</el-descriptions-item>
            <el-descriptions-item label="身份">
              <el-tag size="small">{{userinfo.aboutme}}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="提供个人身份，对虚假身份会进行注销" style="text-align: center"
            >
              <el-select v-model="value" placeholder="身份选择">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                >
                </el-option>
              </el-select>
              <el-button type="success" plain @click="upsf(userinfo.aboutme)">提交</el-button>
            </el-descriptions-item
            >
          </el-descriptions>
        </div>
        <div style="padding: 20px 30px 10px 30px">
          <div style="text-align: center;color: #067f32">
            我所参加的活动
          </div>
          <br>
          <div>
            <el-table :data="userAcList" style="width: 100%">
              <el-table-column label="参加时间" width="200">
                <template #default="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px">{{ scope.row.post_time }}</span>
                </template>
              </el-table-column>
              <el-table-column label="活动名称" width="180">
                <template #default="scope">
                  <el-popover effect="light" trigger="hover" placement="top">
                    <template #default>
                      <p>活动名称: {{ scope.row.activename }}</p>
                      <p>所投票名: {{ scope.row.choose }}</p>
                    </template>
                    <template #reference>
                      <div class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.activename }}</el-tag>
                      </div>
                    </template>
                  </el-popover>
                </template>
              </el-table-column>
              <el-table-column label="快速通道">
                <template #default="scope">
                  <el-button size="mini" @click="handleEdit(scope.row)"
                  >查询投票结果</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </div>
    </el-main>
  </div>
</template>

<script>
import Menu from "../components/Menu";
import {ref} from "vue";
import {ElMessage} from "_element-plus@1.1.0-beta.24@element-plus";
import router from "../router";

export default {
  name: "User",
  components:{
    Menu
  },
  data(){
    return{
      userinfo:{},
      options: ref([
        {
          value: '学生会成员',
          label: '学生会成员',
        },
        {
          value: '协会成员',
          label: '协会成员',
        },
        {
          value: '分院学生会成员',
          label: '分院学生会成员',
        },
        {
          value: '工作室成员',
          label: '工作室成员',
        },
      ]),
      value: ref(''),
      userAcList:[]
    }
  },
  created() {
    this.userinfo = this.$store.state.userinfo
    this.axios.get("/getalluseractive", {params:{id:this.userinfo.id}}).then(result =>{
      if (result.data.code !== 200){
        ElMessage({
          message: '数据导入失败！',
          type: 'warning',
          duration : 3000
        })
      }else {
        ElMessage({
          message: '数据导入成功！',
          type: 'success',
          duration : 3000
        })
        this.userAcList = result.data.data;
      }
    })
  },
  methods:{
    upsf(aboutme){
      if (!aboutme){
        var qs = require('querystring')
        var a = this.value;
        if (a !== ''){
          var map = {id: this.userinfo.id, aboutme: a}
          this.axios.post("/edituser", qs.stringify(map)).then(result =>{
            if (result.data.code !== 200){
              ElMessage({
                message: '修改失败！',
                type: 'warning',
                duration : 3000
              })
            }else {
              ElMessage({
                message: '修改成功,等待审核！',
                type: 'success',
                duration : 3000
              })
            }
          })
        }
      }else {
        ElMessage({
          message: '身份已经存在,修改无效！',
          type: 'warning',
          duration : 3000
        })
      }

    },
    handleEdit(row) {
      router.push({name: "ActiveView", params: {id2: row.active_id}});
    },
  }

}
</script>

<style scoped>

</style>