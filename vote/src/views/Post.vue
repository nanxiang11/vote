<template>
  <div>
    <el-container>
      <el-main>
        <el-row :gutter="20">
          <el-col :sm="24" :md="9"><div></div></el-col>
          <el-col :sm="24" :md="6">
            <div style="padding: 20px 50px 30px 50px">
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
                <el-form-item prop="Pass2">
                  <el-input
                      v-model="ruleForm.Pass2"
                      type="password"
                      placeholder="再次输入密码"
                      autocomplete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item prop="email">
                  <el-input
                      v-model="ruleForm.email"
                      type="text"
                      placeholder="QQ邮箱"
                      autocomplete="off"
                  ></el-input>
                </el-form-item>
                <el-select v-model="ruleForm.college" placeholder="Select">
                  <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                  >
                  </el-option>
                </el-select>
                <el-form-item prop="sex">
                  <el-radio-group v-model="ruleForm.sex">
                    <el-radio :label="0">女</el-radio>
                    <el-radio :label="1">男</el-radio>
                  </el-radio-group>
                </el-form-item>
                <div style="width: 100%;margin-top: 10px;padding-left: 30px;padding-right: 30px">
                  <router-link to="/login" style="float: left">
                    <i class="fa fa-hand-o-left" style="color: #067f32;">&nbsp;登入</i>
                  </router-link>
                </div>
                <el-form-item style="margin-top: 50px">
                  <el-button type="primary" @click="submitForm('ruleForm')"
                  >注册</el-button
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
import router from "../router";
import {ref} from "vue";

export default {
  name: "Post",
  data() {
    var vd = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/
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
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再一次输入密码！'))
      } else if (value !== this.ruleForm.Pass) {
        callback(new Error("两次密码不一样!"))
      } else {
        callback()
      }
    }
    const validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱！'))
      }else if(!vd.test(value)) {
        ElMessage.error('邮箱格式有问题！')
      }else {
        callback()
      }
    }
    return {
      ruleForm: {
        name: '',
        Pass: '',
        Pass2: '',
        email: '',
        college: ref(''),
        sex: ''
      },
      rules: {
        name: [{ validator: validatename, trigger: 'blur' }],
        Pass: [{ validator: validatePass, trigger: 'blur' }],
        Pass2: [{ validator: validatePass2, trigger: 'blur' }],
        email: [{ validator: validateEmail, trigger: 'blur' }],
      },
      options: ref([
        {
          value: '人工智能学院',
          label: '人工智能学院',
        },
        {
          value: '交通运输学院',
          label: '交通运输学院',
        },
        {
          value: '土木建筑学院',
          label: '土木建筑学院',
        },
        {
          value: '智能制造学院',
          label: '智能制造学院',
        },
        {
          value: '商学院',
          label: '商学院',
        },
        {
          value: '设计与艺术学院',
          label: '设计与艺术学院',
        },
        {
          value: '文法学院',
          label: '文法学院',
        },
        {
          value: '体育学院',
          label: '体育学院',
        },
        {
          value: '继续教育学院',
          label: '继续教育学院',
        },
      ]),
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var qs = require('querystring')
          alert('表单提交成功!');
          this.axios.post('/post', qs.stringify(this.ruleForm)).then(result =>{
            console.log(result);
            router.push("/login")
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

<style scoped>

</style>