// import { createApp } from 'vue'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import axios from 'axios'
import VueAxios from 'vue-axios'

import './assets/css/global.css'
import 'font-awesome/css/font-awesome.min.css'
import './axios'



createApp(App).use(store).use(ElementPlus).use(VueAxios, axios).use(router).mount('#app')
const app = createApp(App)

import * as echarts from 'echarts'


