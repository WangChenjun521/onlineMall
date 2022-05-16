<template>
  <a-layout-sider v-model:collapsed="collapsed" collapsible>
    <div class="logo" >Backstage</div>
    <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
      <a-menu-item key="1">
        <router-link to="/">
          <pie-chart-outlined />
          <span>欢迎</span>
        </router-link>
      </a-menu-item>
      <a-sub-menu key="subOrder">
        <template #title>
            <span>
              <user-outlined />
              <span>商单管理</span>
            </span>
        </template>
        <a-menu-item key="order1"><router-link to="/admin/orderlist/now">现有订单</router-link></a-menu-item>
        <a-menu-item key="order2"><router-link to="/admin/orderlist/History">历史订单</router-link></a-menu-item>
      </a-sub-menu>
      <a-menu-item key="2">
        <router-link to="/admin/goods">
          <pie-chart-outlined />
          <span>商品管理</span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="3">
        <router-link to="/about">
          <pie-chart-outlined />
          <span>关于我们</span>
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-sider>
</template>

<script lang="ts">
import {defineComponent, ref} from "vue";

export default defineComponent({
  name: "BackstageSider",

  setup(){
    return {
      collapsed: ref<boolean>(false),
      selectedKeys: ref<string[]>(['1']),
    }
  }
})
</script>

<style scoped>
.logo{
  height: 27px;
  margin: 16px;
  padding-top: 4px;
  color: white;
  font-size: 15px;
}
</style>