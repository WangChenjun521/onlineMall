<template>
  <p>
    <a-form
        layout="inline"
        :model="param"
    >
      <a-form-item>
        <a-input v-model:value="param.name" placeholder="名称">
        </a-input>
      </a-form-item>
      <a-button
          type="primary" @click="handleSearchQuery()"
      >
        查询
      </a-button>
    </a-form>
  </p>
  <a-table :columns="columns" :data-source="orders" class="components-table-demo-nested" rowKey="orderId">
    <template v-slot:innerAction="{text, record}">

      <a-space size="small">

        <a-button type="primary" @click="edit(record)">
          编辑
        </a-button>
        <a-popconfirm
            title="删除后不可恢复，确认删除？"
            ok-text="Yes"
            cancel-text="No"
            @confirm="handleDelete(record)"
        >
          <a-button type="primary" danger>
            删除
          </a-button>
        </a-popconfirm>
      </a-space>

    </template>
  </a-table>

  <a-modal
      v-model:visible="orderModalVisible"
      title="订单编辑"
      :confirm-loading="orderModalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="order" :label-col="{span:6}">
      <a-form-item label="商品名称">
        <a-input v-model:value="order.name" />
      </a-form-item>
      <a-form-item label="规格名称">
        <a-input v-model:value="order.specName" />
      </a-form-item>
      <a-form-item label="单价">
        <a-input v-model:value="order.price" />
      </a-form-item>
      <a-form-item label="购买数量">
        <a-input v-model:value="order.num" />
      </a-form-item>
      <a-form-item label="总价">
        <a-input v-model:value="order.amount" />
      </a-form-item>
      <a-form-item label="订单状态">
        <a-select
            ref="select"
            v-model:value="order.state"
            style="width: 120px"
            @focus="focus"
            @change="handleStatusChange"
        >
          <a-select-option value="0">无</a-select-option>
          <a-select-option value="0">待付款</a-select-option>
          <a-select-option value="1">待发货</a-select-option>
          <a-select-option value="2">已发货</a-select-option>
          <a-select-option value="3">已完成</a-select-option>
          <a-select-option value="4">已取消</a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts">
import {computed, defineComponent, onMounted, ref} from 'vue';
import {message} from "ant-design-vue";
import axios from 'axios'
import {Tool} from "@/util/tool";
import store from "@/store";
const columns = [
  {
    title: '订单id',
    dataIndex: 'orderId',
    key: 'orderId',
  },
  {
    title: '购买人',
    dataIndex: 'buyer',
    key: 'buyer',
  },
  {
    title: '订单包含商品数',
    dataIndex: 'goodsNum',
    key: 'goodsNum',
  },
  {
    title: '商品名称',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '规格名称',
    dataIndex: 'specName',
    key: 'specName',
  },
  {
    title: '单价',
    dataIndex: 'price',
    key: 'price',
  },
  {
    title: '购买数量',
    dataIndex: 'num',
    key: 'num',
  },
  {
    title: '总价',
    dataIndex: 'amount',
    key: 'amount',
  },
  {
    title: '订单状态',
    dataIndex: 'state',
    key: 'state',
  },
  {
    title: '操作',
    key: 'innerAction',
    slots:{customRender: 'innerAction'},
  },
];

const orders_ = [
  {
    orderId: '1',
    buyer: '王小明',
    goodsNum: 3,
    children: [{
      orderId: '1',
      name: '薯条',
      specName: '15G',
      price: 12,
      num: 1,
      amount: 12,
      state: 1,
    },
      {
        orderId: '1',
        name: '可乐',
        specName: '25G',
        price: 32,
        num: 1,
        amount: 32,
        state: 1,
      },]
  },
  {
    orderId: '2',
    buyer: '王小花',
    goodsNum: 3,
  }
];

export default defineComponent({
  setup() {

    //============================全局变量admin=========================================
    const admin = computed(()=>store.state.admin)
    const orders = ref()


    //============================全局方法-刷新列表======================================

    const handleQuery = () => {
      axios.get("/admin/orderNowAll/" + admin.value.token).then((response)=>{
        const data = response.data
        if(data.success){
          orders.value = data.content
        } else{
          message.error(data.message)
        }
      })
    }

    onMounted(()=>{
      handleQuery()
    })

    const expand = (expanded: any, record: any) => {
      console.log(record)
    }


    //============================编辑订单==============================================

    // 编辑按钮
    //------------------------------------------
    const edit = (record:any) => {
      orderModalVisible.value = true
      console.log(record)
      order.value = Tool.copy(record)
    }


    // 模态框属性
    //------------------------------------------
    const order = ref()
    order.value = {}
    const orderModalVisible = ref(false)
    const orderModalLoading = ref(false)


    // 模态框方法
    //------------------------------------------

    /*
    * 发送更新数据
    *
    * */

    const handleModalOk = () => {
      orderModalLoading.value = true
      const req = {
        token: admin.value.token,
        content: order.value,
      }
      axios.post("/admin/updateOrder", req).then((response)=>{
        const data = response.data
        if(data.success){
          orderModalVisible.value = false
          orderModalLoading.value = false
          //刷新列表
          handleQuery()
        }else{
          message.error(data.message)
        }
      })
    }


    /*
    * 删除订单
    *
    * */


    const handleDelete = (record: any) => {
      const req = {
        token: admin.value.token,
        content:record,
      }
      console.log(req)
      axios.delete("/admin/deleteOrder", {data:req}).then((response)=>{
        const data = response.data
        if(data.success){
          // 重新加载列表
          handleQuery()
        }else{
          message.error(data.message)
        }
      })
    }

    const focus = () => {
      console.log('focus');
    };

    const handleStatusChange = (value: string) => {
      console.log(`selected ${value}`);
    };


    //============================搜索栏=============================================

    const param = ref()
    param.value = {}

    // 搜索方法
    //------------------------------------------

    const handleSearchQuery = () => {

      const req = {
        token: admin.value.token,
        name: param.value.name,
      }
      axios.post("/admin/orderNowSearch", req).then((response)=>{
        const data = response.data
        if(data.success){
          orders.value = data.content
        } else{
          message.error(data.message)
        }
      })

    }

    return {
      admin,
      handleQuery,

      orderModalVisible,
      orderModalLoading,
      handleModalOk,
      handleDelete,
      edit,


      orders_,
      orders,
      columns,
      order,

      focus,
      handleStatusChange,

      param,
      handleSearchQuery
    };
  },
});
</script>

