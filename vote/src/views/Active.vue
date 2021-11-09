<template>
  <el-main>
    <div>
      <Menu/>
    </div>
    <div style="padding: 30px 300px 10px 300px">
      投票方式：<el-tooltip :content="'投票方式: ' + dynamicValidateForm.choose" placement="top">
        <el-switch
            v-model="dynamicValidateForm.choose"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-value="实名"
            inactive-value="匿名"
        />
      </el-tooltip><br>
      上传相关资料和证件：<Upimg/>
      <el-form
          ref="dynamicValidateForm"
          :model="dynamicValidateForm"
          label-width="120px"
          class="demo-dynamic"
      >
        <div style="margin-bottom: 20px">
          <span class="demonstration">选择截止日期</span>
          <el-date-picker
              v-model="dynamicValidateForm.endtime"
              type="date"
              placeholder="Pick a date"
              :default-value="new Date(2010, 9, 1)"
          >
          </el-date-picker>
        </div>
        <el-form-item
            prop="email"
            label="验证邮箱"
            :rules="[
            {
              required: true,
              message: 'Please input email address',
              trigger: 'blur',
            },
            {
              type: 'email',
              message: 'Please input correct email address',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <el-input v-model="dynamicValidateForm.email"></el-input>
        </el-form-item>
        <el-form-item
            prop="active"
            label="活动名称"
            :rules="[
            {
              required: true,
              message: '请输入活动名称',
              trigger: 'blur',
            },
            {
              min: 3,
              max: 20,
              message: 'Length should be 3 to 20',
              trigger: 'blur',
            },
          ]"
        >
          <el-input v-model="dynamicValidateForm.active"></el-input>
        </el-form-item>
        <el-form-item
            prop="msg"
            label="描述"
            :rules="[
            {
              required: true,
              message: '请输入活动描述',
              trigger: 'blur',
            },
            {
              min: 10,
              max: 100,
              message: 'Length should be 10 to 100',
              trigger: 'blur',
            },
          ]"
        >
          <el-input v-model="dynamicValidateForm.msg"></el-input>
        </el-form-item>

        <el-form-item
            v-for="(domain, index) in dynamicValidateForm.domains"
            :key="domain.key"
            :label="'新建选项' + index"
            :prop="'domains.' + index + '.value'"
            :rules="{
        required: true,
        message: '不能为空！！！沙雕！！！！',
        trigger: 'blur',
      }"
        >
          <el-input v-model="domain.value"></el-input
          ><el-button @click.prevent="removeDomain(domain)">Delete</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('dynamicValidateForm')"
          >提交</el-button
          >
          <el-button @click="addDomain">新增选项</el-button>
          <el-button @click="resetForm('dynamicValidateForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-main>
</template>

<script>
import Menu from "../components/Menu";
import {ElMessage} from "_element-plus@1.1.0-beta.24@element-plus";
import Upimg from "../components/Upimg";
import router from "../router";
import { defineComponent, ref } from 'vue'

export default {
  name: "Active",
  components:{
    Menu,
    Upimg,
  },
  data() {
    const endtime = ref('')
    return {
      dynamicValidateForm: {
        domains: [
          {
            key: 1,
            value: '',
          },
        ],
        email: '',
        active: '',
        msg: '',
        imageUrl: this.$store.state.imgurl,
        userid: this.$store.state.userinfo.id,
        choose: '实名',
        endtime,
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
          this.axios.post("/setactive", this.dynamicValidateForm).then(result =>{
            console.log(result);
            if (result.data.code === 200){
              ElMessage({
                message: '发送成功,请尽快联系管理员方便快速过审！',
                type: 'success',
                duration : 3000
              })
              router.push("/");
            }else {
              ElMessage({
                message: '发送失败！',
                type: 'error',
                duration : 3000
              })
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    removeDomain(item) {
      const index = this.dynamicValidateForm.domains.indexOf(item)
      if (index !== -1) {
        this.dynamicValidateForm.domains.splice(index, 1)
      }
    },
    addDomain() {
      this.dynamicValidateForm.domains.push({
        key: Date.now(),
        value: '',
      })
    },


  },

}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 2px dashed #000000;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
</style>