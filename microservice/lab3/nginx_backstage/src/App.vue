<template>
  <a-layout style="min-height: 100vh">
    <BackstageSider/>
    <a-layout>
      <a-layout-header>
        <a-button type="primary" class="loginButton" @click="showLoginModal" v-show="!admin.token"><span>登录</span></a-button>
        <a-popconfirm
            title="确认退出登录？"
            ok-text="是"
            cancel-text="否"
            @confirm="logout"
        >
          <a class="loginButton" v-show="admin.token"><span>退出登录</span></a>
        </a-popconfirm>
        <a class="loginButton" v-show="admin.token"><span>{{ admin.token }}</span></a>

        <a-modal v-model:visible="loginModalVisible"
                 title="用户登录"
                 @ok="login"
                 :confirm-loading="loginModalLoading"
        >
          <a-form :model="loginAdmin" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
            <a-form-item label="登陆名">
              <a-input v-model:value="loginAdmin.username"/>
            </a-form-item>
            <a-form-item label="密码">
              <a-input v-model:value="loginAdmin.password" type="password"/>
            </a-form-item>
          </a-form>
        </a-modal>
        <a-menu
            theme="dark"
            mode="horizontal"
            :style="{ lineHeight: '64px' }"
        >
        </a-menu>

      </a-layout-header>
      <a-layout-content
          :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
      >
        <router-view></router-view>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Onlinemart-Backstage @ LAB
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>
<script lang="ts">
import {
  PieChartOutlined,
  DesktopOutlined,
  UserOutlined,
  TeamOutlined,
  FileOutlined,
} from '@ant-design/icons-vue';
import {computed, defineComponent, ref} from 'vue';
import {message} from "ant-design-vue";
import store from "@/store";
import axios from 'axios'
import BackstageSider from '@/components/BackstageSider.vue'
import Qs from 'qs'

export default defineComponent({
  components: {
    PieChartOutlined,
    DesktopOutlined,
    UserOutlined,
    TeamOutlined,
    FileOutlined,
    BackstageSider
  },
  data() {
    return {
      collapsed: ref<boolean>(false),
      selectedKeys: ref<string[]>(['1']),
    };
  },
  setup(){
    //============================全局变量admin=========================================
    const admin = computed(()=>store.state.admin)


    //============================登录菜单==============================================
    const loginModalVisible = ref<boolean>(false);
    const loginModalLoading = ref(false)
    const loginAdmin = ref({
      username: "test",
      password: "test123"
    })

    // 操作方法
    //------------------------------------------
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };

    const login = (e: MouseEvent) => {
      console.log("开始登陆")
      loginModalLoading.value = true
      axios.post('/admin/login', loginAdmin.value).then((response)=>{
        loginModalLoading.value = false;
        const data = response.data
        if(data.success) {
          loginModalVisible.value = false
          message.success("登陆成功")
          store.commit("setAdmin", data.content1)
        }else{
          message.error(data.message)
        }
      })
    };

    //============================退出登录==============================================

    // 操作方法
    //------------------------------------------
    const logout = () =>{
      console.log("退出登录开始")
      axios.get('/admin/logout/' + admin.value.token).then((response)=>{
        const data = response.data
        if(data.success) {
          message.success("退出登录")
          store.commit("setAdmin", {})
        }else{
          message.error(data.message)
        }
      })
    }



    return {
      admin,

      loginAdmin,
      loginModalVisible,
      loginModalLoading,
      showLoginModal,
      login,

      logout,

    }
  }
});
</script>
<style>
#components-layout-demo-side .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}



.site-layout .site-layout-background {
  background: #fff;
}
[data-theme='dark'] .site-layout .site-layout-background {
  background: #141414;
}

.loginButton{
  float: right;
  color: white;
  padding-left: 10px;
  margin-top: 15px;
}
</style>
