<template>
  <el-main>
    <div>
      <Menu/>
    </div>
    <div style="margin-top: 50px">
      <div>
        <el-header style="width: 100%;height: 80px;padding: 20px 20px 20px 20px;text-align: center">
          <!--      <span style="font-size: 30px;color: dodgerblue"><b>学生成绩分析系统</b></span>-->
          <el-dropdown style="float: right">
            <el-button type="primary">
              程序员的一生<i class="el-icon-arrow-down el-icon--right"></i>
            </el-button>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item></el-dropdown-item>
                <el-dropdown-item>坟头草</el-dropdown-item>
                <el-dropdown-item>码仙</el-dropdown-item>
                <el-dropdown-item>码神</el-dropdown-item>
                <el-dropdown-item>码皇</el-dropdown-item>
                <el-dropdown-item>程序猿</el-dropdown-item>
                <el-dropdown-item>码农</el-dropdown-item>
                <el-dropdown-item>码畜</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <el-button type="success" style="float: right;margin-right: 10px" @click="dialogVisible = true">添加管理员</el-button>
        </el-header>
        <el-row>
          <el-col :span="4">
            <div>
              <el-menu
                  default-active="2"
                  class="el-menu-vertical-demo"
                  style="height: calc(100vh - 160px)"
              >
                <el-sub-menu index="1">
                  <template #title>
                    <i class="el-icon-location"></i>
                    <span>Navigator One</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="1-1">item one</el-menu-item>
                    <el-menu-item index="1-2">item one</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="Group Two">
                    <el-menu-item index="1-3">item three</el-menu-item>
                  </el-menu-item-group>
                  <el-sub-menu index="1-4">
                    <template #title>item four</template>
                    <el-menu-item index="1-4-1">item one</el-menu-item>
                  </el-sub-menu>
                </el-sub-menu>
                <el-menu-item index="2">
                  <i class="el-icon-menu"></i>
                  <span>Navigator Two</span>
                </el-menu-item>
                <el-menu-item index="3" disabled>
                  <i class="el-icon-document"></i>
                  <span>Navigator Three</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <i class="el-icon-setting"></i>
                  <span>Navigator Four</span>
                </el-menu-item>
              </el-menu>
            </div>
          </el-col>
          <el-col :span="20">
            <div>
              <el-table
                  :data="
      tableData.filter(
        (data) =>
          !search || data.name.toLowerCase().includes(search.toLowerCase())
      )
    "
                  style="width: 100%"
              >
                <el-table-column label="ID" prop="id" />
                <el-table-column label="头像" prop="imgurl" />
                <el-table-column label="姓名" prop="name" />
                <el-table-column label="性别" prop="sex" />
                <el-table-column label="班级" prop="nclass" />
                <el-table-column label="住址" prop="address" />
                <el-table-column label="电话" prop="tel" />
                <el-table-column label="注册时间" prop="post_time" />
                <el-table-column align="right">
                  <template #header>
                    <el-input v-model="search" size="mini" placeholder="Type to search" />
                  </template>
                  <template #default="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)"
                    >修改</el-button
                    >
                    <el-popconfirm title="Are you sure to delete this?"
                                   @confirm="handleDelete(scope.row)">
                      <template #reference>
                        <el-button
                            size="mini"
                            type="danger"
                        >删除</el-button>
                      </template>
                    </el-popconfirm>
                  </template>
                </el-table-column>
              </el-table>
              <br>
              <el-pagination
                  v-model:currentPage="currentPage4"
                  :page-sizes="[5, 10, 15]"
                  :page-size="5"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="total"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
              >
              </el-pagination>
            </div>
          </el-col>
        </el-row>

        <el-dialog
            v-model="dialogVisible"
            title="提示"
            width="30%"
            :before-close="handleClose"
        >
          <el-form
              ref="ruleForm"
              :model="studentForm"
              status-icon
              :rules="rules"
              style="text-align: center"
          >
            <el-form-item prop="name">
              <el-input
                  v-model="studentForm.name"
                  type="text"
                  placeholder="姓名"
                  autocomplete="off"
                  style="width: 80%"
              ></el-input>
            </el-form-item>
            <el-form-item prop="tel">
              <el-input
                  v-model="studentForm.tel"
                  type="text"
                  placeholder="电话"
                  autocomplete="off"
                  style="width: 80%"
              ></el-input>
            </el-form-item>
            <el-form-item prop="性别">
              <el-radio v-model="studentForm.sex" label="男">男</el-radio>
              <el-radio v-model="studentForm.sex" label="女">女</el-radio>
            </el-form-item>
            <el-form-item prop="nclass">
              <el-input
                  v-model="studentForm.nclass"
                  type="text"
                  placeholder="班级"
                  autocomplete="off"
                  style="width: 80%"
              ></el-input>
            </el-form-item>
            <el-form-item prop="address">
              <el-input
                  v-model="studentForm.address"
                  type="text"
                  placeholder="住址"
                  autocomplete="off"
                  style="width: 80%"
              ></el-input>
            </el-form-item>
          </el-form>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save()">保存</el-button
        >
      </span>
          </template>
        </el-dialog>
      </div>
    </div>
  </el-main>
</template>

<script>
import Menu from "../components/Menu";
import { defineComponent, ref } from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
export default {
  name: "Addmanage",
  components: {Menu},
  data() {
    const handleSizeChange = (pageSize) => {
      this.page.pageSize = pageSize
      this.load()
    }
    const handleCurrentChange = (pageNum) => {
      this.page.pageNum = pageNum
      this.load()
    }
    const dialogVisible = ref(false)
    const handleClose = (done) => {
      ElMessageBox.confirm('你确定要关闭窗口吗')
          .then(() => {
            done()
          })
          .catch(() => {
            // catch error
          })
    }
    return {
      tableData: [
        {
          id: '0',
          imgurl: 'null',
          name: 'Tom',
          sex: 'boy',
          nclass: '高一一班',
          tel: '123456',
          address: 'No. 189, Grove St, Los Angeles',
          post_time: '2016-05-03',
        }
      ],
      studentForm:
          {
            id: '',
            name: '',
            tel: '',
            sex: '',
            nclass: '',
            address: '',
          },
      page: {
        pageNum: '',
        pageSize: ''
      },
      dialogVisible,
      handleClose,
      search: '',
      currentPage4: ref(4),
      handleSizeChange,
      handleCurrentChange,
      total: '',
      flag: ''
    }
  },
  created() {
    this.load();
  },
  methods: {
    load(){
      var pageNum = this.page.pageNum;
      var pageSize = this.page.pageSize;
      this.axios.get('/student/queryall/', {params:{pageNum: pageNum, pageSize: pageSize}}).then(res =>{
        this.tableData = res.data.data;
        this.total = res.data.total;
      })
    },
    handleEdit(row) {
      this.studentForm.id = row.id;
      this.dialogVisible = true
    },
    handleDelete(row) {
      this.axios.get('/student/del', {params:{id:row.id}}).then(res =>{
        if (res.data.code === 200){
          ElMessage({
            message: '删除成功！',
            type: 'success',
            duration : 3000
          })
          this.load();
        }else {
          ElMessage({
            message: '删除失败！',
            type: 'warning',
            duration : 3000
          })
        }

      })
    },
    save(){
      var qs = require('querystring')
      if (this.studentForm.id !== ''){
        this.axios.post("/student/edit", qs.stringify(this.studentForm)).then(res =>{
          if (res.data.code !== 200){
            ElMessage({
              message: '修改失败！',
              type: 'warning',
              duration : 3000
            })
          }else {
            ElMessage({
              message: '修改成功！',
              type: 'success',
              duration : 3000
            })
            this.dialogVisible = false;
            this.studentForm = {}
            this.load()
          }
        })
      }else {
        this.axios.post("/student/save", qs.stringify(this.studentForm)).then(res =>{
          if (res.data.code !== 200){
            ElMessage({
              message: '提交失败！',
              type: 'warning',
              duration : 3000
            })
          }else {
            ElMessage({
              message: '提交成功！',
              type: 'success',
              duration : 3000
            })
            this.dialogVisible = false;
            this.studentForm = {}
            this.load()
          }
        })
      }
    }

  },
}
</script>

<style scoped>

</style>