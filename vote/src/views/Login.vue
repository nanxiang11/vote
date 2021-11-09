<template>
  <div>
    <el-container>
      <el-main>
        <el-row :gutter="20">
          <el-col :sm="24" :md="9"><div></div></el-col>
          <el-col :sm="24" :md="6">
            <div style="margin-top: 30%;padding: 30px 50px 30px 50px">
              <div style="margin: auto;width: 100px;height: 100px;text-align: center">
                <span style="font-size: 50px"><b>NX</b></span>
              </div>
              <el-form
                  ref="ruleForm"
                  :model="ruleForm"
                  status-icon
                  :rules="rules"
                  class="demo-ruleForm loginfrom"
              >
                <el-form-item prop="name">
                  <el-input
                      v-model="ruleForm.name"
                      type="text"
                      placeholder="姓名"
                      autocomplete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item prop="Pass">
                  <el-input
                      v-model="ruleForm.Pass"
                      type="password"
                      placeholder="密码"
                      autocomplete="off"
                      show-password
                  ></el-input>
                </el-form-item>
                <div style="width: 100%;margin-top: 50px;padding-left: 30px;padding-right: 30px">
                  <router-link to="/post" style="float: left">
                    <i class="fa fa-hand-o-left" style="color: #3d3e3d;">&nbsp;注册会员</i>
                  </router-link>
                  <router-link to="/forget" style="float: right">
                    <i class="fa fa-hand-o-right" style="color: #7f0606;">&nbsp;忘记密码</i>
                  </router-link>
                </div>
                <el-form-item style="margin-top: 100px">
                  <el-button type="primary" @click="submitForm('ruleForm')"
                  >登入</el-button
                  >
                  <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
          <el-col ::sm="24" :md="9"><div></div></el-col>
        </el-row>
      </el-main>

    </el-container>
  </div>
</template>

<script>
import {ElMessage} from "element-plus";
import router from "../router";

export default {
  name: "Login",
  data() {
    const validatename = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入您的姓名'))
      } else {
        if (this.ruleForm.name !== '') {
          this.$refs.ruleForm.validateField('name')
        }
        callback()
      }
    }
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入您的密码！'))
      } else {
        if (this.ruleForm.Pass !== '') {
          this.$refs.ruleForm.validateField('Pass')
        }
        callback()
      }
    }
    return {
      ruleForm: {
        name: '',
        Pass: '',
      },
      rules: {
        name: [{ validator: validatename, trigger: 'blur' }],
        Pass: [{ validator: validatePass, trigger: 'blur' }]
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var qs = require('querystring')
          this.axios.post('http://localhost:9000/login', qs.stringify(this.ruleForm)).then(result =>{
            console.log(result);
            if (result.data.code !== 200){
              ElMessage({
                message: '用户名错误或者密码错误！',
                type: 'warning',
                duration : 3000
              })
            }else {
              this.$store.commit('SET_USERINFO',result.data.data)
              router.push("/");
            }
          }).catch(function (error) { // 请求失败处理
            console.log(error);
          });
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
  },
}
</script>

<style>
.el-main {
  min-height: calc(100vh - 80px);
}
.loginfrom{
  text-align: center;
}

</style>