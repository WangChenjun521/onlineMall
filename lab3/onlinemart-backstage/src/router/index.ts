import { createRouter, createWebHashHistory,createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import OrderListNow from "@/views/OrderListNow.vue";
import OrderListNow_ from "@/views/OrderListNow.vue";
import OrderListHistory from "@/views/OrderListHistory.vue";
import GoodsManager from "@/views/GoodsManager.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/admin/orderlist/now',
    name: 'orderlistnow',
    component: OrderListNow
  },
  {
    path: '/admin/orderlist/history',
    name: 'orderlistHistory',
    component: OrderListHistory
  },
  {
    path: '/admin/goods',
    name: 'goods',
    component: GoodsManager
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
