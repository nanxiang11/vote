import { createRouter, createWebHistory } from 'vue-router'
import Index from "../views/Index";
import Login from "../views/Login";
import Forget from "../views/Forget";
import User from "../views/User";
import Post from "../views/Post";
import Active from "../views/Active";
import ActiveInfo from "../views/ActiveInfo";
import Activeall from "../views/Activeall";
import ActiveView from "../views/ActiveView";
import Admin from "../views/Admin";
import AdminUser from "../views/AdminUser";
import Addmanage from "../views/Addmanage";


const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/forget',
    name: 'Forget',
    component: Forget
  },
  {
    path: '/user',
    name: 'User',
    component: User
  },
  {
    path: '/post',
    name: 'Post',
    component: Post
  },
  {
    path: '/active',
    name: 'Active',
    component: Active
  },
  {
    path: '/activeinfo/',
    name: 'ActiveInfo',
    component: ActiveInfo
  },
  {
    path: '/activeall',
    name: 'Activeall',
    component: Activeall
  },
  {
    path: '/activeview',
    name: 'ActiveView',
    component: ActiveView
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin
  },
  {
    path: '/adminuser',
    name: 'AdminUser',
    component: AdminUser
  },
  {
    path: '/addmanage',
    name: 'Addmanage',
    component: Addmanage
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
