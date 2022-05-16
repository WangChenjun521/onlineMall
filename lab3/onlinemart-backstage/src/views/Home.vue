<template>
  <div class="home">
    <div></div>
    <div :style="{ paddingTop:'24px', padding: '24px', background: '#fff', minHeight: '360px' }">
      <BalanceAccount/>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import BalanceAccount from '@/components/BalanceAccount.vue'

export default defineComponent({
  name: 'Home',
  components: {
    HelloWorld,
    BalanceAccount
  },
});
</script>
