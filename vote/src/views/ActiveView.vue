<template>
  <el-main>
    <div>
      <Menu/>
    </div>
    <div>
      <div style="padding-top: 50px;text-align: center">
        <h2 style="color: #9927e0">视图展示</h2>
      </div>
      <div style="margin-top: 100px; padding: 0px 200px 0px 200px">
        <el-row :gutter="20">
          <el-col :span="12"><div style="margin: auto">
            <div ref="a" style="width: 500px;height: 500px;margin: auto"></div>
          </div></el-col>
          <el-col :span="12"><div style="margin: auto">
            <div ref="b" style="width: 500px;height: 500px;margin: auto"></div>
          </div></el-col>
        </el-row>
      </div>
      <div style="padding: 100px 300px 40px 300px">
        <el-table :data="tableData" height="250" style="width: 100%">
          <el-table-column prop="post_time" label="Date" width="180" />
          <el-table-column prop="studentName" label="studentName" width="180" />
        </el-table>
      </div>
    </div>
  </el-main>
</template>

<script>
import Menu from "../components/Menu";
import * as echarts from 'echarts'
import {ElMessage} from "_element-plus@1.1.0-beta.24@element-plus";


export default {
  name: "ActiveView",
  components: {Menu},
  data(){
    return{
      list : [
        { value: 1048, name: 'Search Engine' },
        { value: 735, name: 'Direct' },
        { value: 580, name: 'Email' },
        { value: 484, name: 'Union Ads' },
        { value: 300, name: 'Video Ads' }
      ],
      tableData: [
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-08',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-06',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-07',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
      ],
    }
  },
  created() {
    this.axios.get("/getactiveV", {params:{id:this.$route.params.id2}}).then(result =>{
      if (result.data.code === 200){
        ElMessage({
          message: '获取数据成功！',
          type: 'success',
          duration : 3000
        })
        var c = result.data.data.replace(/key/g,"name")


        this.list = JSON.parse(c)
        this.createChart(JSON.parse(c))
        this.createChart2(JSON.parse(c))
      }else {
        ElMessage({
          message: '获取数据失败！',
          type: 'error',
          duration : 3000
        })
      }
    })
    this.axios.get("/getallTP", {params:{active_id: this.$route.params.id2}}).then(result =>{
      if (result.data.code === 200){
        this.tableData = result.data.data;
      }
    })
  },
  mounted() {
  },
  methods: {
    createChart(list) {
      var myChart = echarts.init(this.$refs.a);
      var option;
      option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '投票数',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#232222',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: list
          }
        ]
      }
      myChart.setOption(option);
    },
    createChart2(list){
      var myChart = echarts.init(this.$refs.b);
      var option;
      option = {
        title: {
          text: '漏斗图'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c}%'
        },
        toolbox: {
          feature: {
            dataView: { readOnly: false },
            restore: {},
            saveAsImage: {}
          }
        },
        legend: {
          data: []
        },
        series: [
          {
            name: '投票展示值',
            type: 'funnel',
            left: '10%',
            top: 60,
            bottom: 60,
            width: '80%',
            min: 0,
            max: 100,
            minSize: '0%',
            maxSize: '100%',
            sort: 'descending',
            gap: 2,
            label: {
              show: true,
              position: 'inside'
            },
            labelLine: {
              length: 10,
              lineStyle: {
                width: 1,
                type: 'solid'
              }
            },
            itemStyle: {
              borderColor: '#fff',
              borderWidth: 1
            },
            emphasis: {
              label: {
                fontSize: 20
              }
            },
            data: list
          }
        ]
      }
      myChart.setOption(option);
    }
  }
}
</script>

<style scoped>

</style>