<template>
  <el-main>
    <div>
      <Menu/>
    </div>
    <div style="padding: 10px 100px 10px 100px">
      <el-descriptions title="活动详情" :column="3" border>
        <el-descriptions-item
            label="活动名称"
            label-align="right"
            align="center"
            label-class-name="my-label"
            class-name="my-content"
            width="150px"
        >{{activeinfo.activename}}</el-descriptions-item
        >
        <el-descriptions-item label="联系方式" label-align="right" align="center"
        >{{activeinfo.email}}</el-descriptions-item
        >
        <el-descriptions-item label="投票方式" label-align="right" align="center"
        >{{activeinfo.choose}}</el-descriptions-item
        >
        <el-descriptions-item label="负责人" label-align="right" align="center">
          <el-tag size="small">{{name}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="活动描述" label-align="right" align="center"
        >{{activeinfo.activedes}}</el-descriptions-item
        >
      </el-descriptions>
    </div>
    <div style="padding: 50px 200px 20px 200px">
      <div class="block">
        <el-timeline>
          <el-timeline-item
              v-for="(active, index) in actives"
          >
            <el-card>
              <h4>{{active.key}}</h4>
              <br>
              <p><span style="color: #067f32">投票方式：</span>&nbsp;&nbsp;{{activeinfo.choose}}</p>
              <el-button type="primary" circle style="float: right" @click="TP(activeinfo.id, activeinfo.choose, active.key)">票</el-button><br>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
    </div>
  </el-main>
</template>

<script>
import Menu from "../components/Menu";
import {ElMessage} from "_element-plus@1.1.0-beta.24@element-plus";
import router from "../router";

export default {
  name: "ActiveInfo",
  components: {Menu},
  data (){
    return {
      activeinfo:{

      },
      name: '',
      actives:[

      ],

    }
  },
  methods: {
    TP(activeId, choose, atctivkey){
      var activelist = new Array();
      activelist[0] = activeId;
      activelist[1] = choose;
      activelist[2] = atctivkey;
      activelist[3] = this.$store.state.userinfo.id;
      this.axios.post("/getTP", activelist).then(result =>{
        if (result.data.code !== 200){
          ElMessage({
            message: '投票失败',
            type: 'warning',
            duration : 3000
          })
        }else {
          ElMessage({
            message: '投票成功',
            type: 'success',
            duration : 3000
          })
          router.push({name: "ActiveView", params: {id2: this.activeinfo.id}});
        }
      })
    },
    Tz(){
      if(!this.$store.state.userinfo.imgurl){
        router.push("/login");
      }else {
        this.axios.get("/getactiveinfo", {params:{id:this.$route.params.id}}).then(result =>{
          this.activeinfo = result.data.data.active;
          this.name = result.data.data.studentName;
          this.actives = JSON.parse(result.data.data.active.domains);
        })
      }
    }
  },
  created() {
    this.Tz();
  }
}
</script>

<style scoped>

</style>