<template>
  <a-row>
    <a-col :span="12">
      <a-statistic title="Active Users" :value="112893" style="margin-right: 50px" />
    </a-col>
    <a-col :span="12">
      <a-statistic title="Account Balance (CNY)" :precision="2" :value="admin.balance" />
    </a-col>
  </a-row>
</template>

<script>
import {computed, defineComponent, ref} from 'vue';
import {message} from "ant-design-vue";
import store from "@/store";
import axios from 'axios'

export default {
  name: "BalanceAccount",

  setup(){
    const admin = computed(()=>store.state.admin)

    const checkBalance = () => {
      console.log("正在检查余额")
    }

    return {
      admin,
      checkBalance,
    }
  }
}
</script>

<style scoped>

</style>