<template>
  <el-container>
    <el-main>
      <div style="background-color: #ffffff;padding: 10px 10px 10px 10px">
        <Menu/>
      </div>
      <div>
        <el-row :gutter="20">
          <el-col :span="4">
            <div>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="bg" style="min-height: 70vh;background-color: rgb(255,255,255);margin-top: 20px;padding: 10px 10px 10px 10px;border-radius: 6px">
              <div class="block" style="text-align: center">
                <el-carousel height="250px">
                  <el-carousel-item v-for="item in imglist" :key="item">
                    <div  :style="{width:'100%',height:'100%',backgroundImage: 'url(' + item + ')', backgroundSize:'contain' ,backgroundSize:'100%'}"></div>
                  </el-carousel-item>
                </el-carousel>
              </div>
              <div style="margin-top: 20px; text-align: center">
                <div style="padding: 10px 100px 20px 100px">
                  <el-input v-model="input" placeholder="搜索活动" />
                  <el-button type="success" icon="el-icon-check" circle style="margin-top: 10px" @click="ss(this.input)"></el-button>
                  <el-button type="danger" icon="el-icon-delete" circle style="margin: 10px 0px 0px 20px" @click="clear()"></el-button>
                </div>
                <div style="margin-top: 10px">
                  <el-scrollbar max-height="30vh">
                    <el-timeline>
                      <el-timeline-item
                          v-for="(activity, index) in querylist"
                          :key="index"
                          :timestamp="activity.post_time"
                      >
                        <div v-if="activity.mange === '1'" style="background-color: rgba(0,0,0,0.38);border-radius: 8px;width: 80%;height: 100px;margin: auto;text-align: center">
                          <br>
                          <span style="color: #317078">《{{activity.activename}}》</span>
                          <p>活动结束时间：{{activity.endtime}}</p>
                          <el-button type="danger" round @click="cj(activity.id, activity.post_time, activity.endtime)" style="margin-top: 8px" size="mini">参加活动</el-button>
                        </div>
                        <div v-if="activity.mange !== '1'" style="background-color: rgba(0,0,0,0.38);border-radius: 8px;width: 80%;height: 100px;margin: auto;text-align: center">
                          <br>
                          <span style="color: #317078">《{{activity.activename}}》</span>
                          <p>活动结束时间：{{activity.endtime}}</p>
                          <p style="color: red">审核中</p>
                        </div>
                      </el-timeline-item>
                    </el-timeline>
                  </el-scrollbar>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div style="height: 70vh;background-color: #e1f3d8;margin-top: 20px;padding: 50px 50px 20px 50px;overflow: auto;word-break: break-word;border-radius: 4px">
              <el-scrollbar max-height="70vh">
                <div style="text-align: center;color: green">
                  活动时间轴
                </div>
                <br>
                <br>
                <div class="block">
                  <el-timeline>
                    <el-timeline-item
                        v-for="(activity, index) in activities"
                        :key="index"
                        :timestamp="activity.post_time"
                    >
                      <div v-if="activity.mange === '1'" style="background-color: white;border-radius: 8px;width: 80%;height: 100px;margin: auto;text-align: center">
                        <br>
                        <span style="color: #317078">《{{activity.activename}}》</span>
                        <p>活动结束时间：{{activity.endtime}}</p>
                        <el-button type="danger" round @click="cj(activity.id, activity.post_time, activity.endtime)" style="margin-top: 8px" size="mini">参加活动</el-button>
                      </div>
                      <div v-if="activity.mange !== '1'" style="background-color: white;border-radius: 8px;width: 80%;height: 100px;margin: auto;text-align: center">
                        <br>
                        <span style="color: #317078">《{{activity.activename}}》</span>
                        <p>活动结束时间：{{activity.endtime}}</p>
                        <p style="color: red">还在审核中</p>
                      </div>
                    </el-timeline-item>
                  </el-timeline>
                </div>
              </el-scrollbar>

            </div>
          </el-col>
          <el-col :span="2">
          </el-col>
        </el-row>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import Menu from "../components/Menu";
import router from "../router";
import {ElMessage} from "_element-plus@1.1.0-beta.24@element-plus";
import {ref} from "vue";

export default {
  name: "Index",
  components:{
    Menu
  },
  data() {
    return {
      activities: [

      ],
      imglist:[
        require('../assets/images/bg.jpg'),
        require('../assets/images/bg2.jpg'),
        require('../assets/images/bg3.jpg'),
        require('../assets/images/bg4.jpg'),
      ],
      input: ref(''),
      querylist:[

      ]
    }
  },
  created() {
    this.axios.get("/getactiveall").then(result =>{
      this.activities = result.data.data;
    })
  },
  methods:{
    cj(id, post_time, endtime){
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var day = date.getDate();
      var a = post_time.split("-");
      var b = endtime.split("-");
      if (year > b[0]){
        ElMessage({
          message: '活动已经过期无法参加！',
          type: 'error',
          duration : 3000
        })
      }else if (month > b[1]){
        ElMessage({
          message: '活动已经过期无法参加！',
          type: 'error',
          duration : 3000
        })
      }else {
        router.push({name: 'ActiveInfo', params: {id: id}})
      }
    },
    ss(cs){
      this.axios.get("/getnameactive", {params:{name: cs}}).then(result =>{
        if (result.data.code !== 200){
          ElMessage({
            message: '没有该活动',
            type: 'warning',
            duration : 3000
          })
        }else {
          ElMessage({
            message: '查询成功',
            type: 'success',
            duration : 3000
          })
          this.querylist = result.data.data;
        }
      })
    },
    clear(){
      this.querylist = []
    }
  }
}
</script>

<style scoped>
.el-main{
  width: 100vh;
  height: calc(100vh - 80px);
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.bg{
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04)
}
</style>