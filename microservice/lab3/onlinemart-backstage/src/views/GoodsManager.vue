<template>
  <p>
    <a-form
        layout="inline"
        :model="param"
    >
      <a-space>
        <a-form-item>
          <a-input v-model:value="param.name" placeholder="名称">
          </a-input>
        </a-form-item>
        <a-from-item>
          <a-button
              type="primary" @click="handleSearchQuery()"
          >
            查询
          </a-button>
        </a-from-item>
        <a-form-item>
          <a-button type="primary" @click="add()">
            新增
          </a-button>
        </a-form-item>
      </a-space>
    </a-form>
  </p>
  <a-table :columns="columns" :data-source="goods">
    <template v-slot:action="{text, record}">

      <a-space size="small">

        <a-button type="primary" @click="edit(record)">
          编辑
        </a-button>
        <a-popconfirm
            title="删除后不可恢复，确认删除？"
            ok-text="Yes"
            cancel-text="No"
            @confirm="handleDelete(record.id)"
        >
          <a-button type="primary" danger>
            删除
          </a-button>
        </a-popconfirm>
      </a-space>

    </template>
  </a-table>

  <a-modal
      v-model:visible="goodModalVisible"
      title="订单编辑"
      :confirm-loading="goodModalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="good" :label-col="{span:6}">
      <a-form-item label="商品名称">
        <a-input v-model:value="good.name" />
      </a-form-item>
      <a-form-item label="商品图片">
        <a-input v-model:value="good.img" />
      </a-form-item>
      <a-form-item label="商品价格">
        <a-input v-model:value="good.price" />
      </a-form-item>
      <a-form-item label="商品简介">
        <a-textarea v-model:value="good.desc" placeholder="商品简介" :rows="4" />
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
    title: '商品名称',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '图片',
    dataIndex: 'img',
    key: 'img'
  },
  {
    title: '价格',
    dataIndex: 'price',
    key: 'price',
  },
  {
    title: '商品简介',
    dataIndex: 'desc',
    key: 'desc',
  },
  {
    title: '操作',
    key: 'action',
    slots:{customRender: 'action'},
  },
];

const goods_ = [
  {
    goodsId: '1',
    name: '薯条',
    img: "000/",
    price: 50,
    desc: '吃嘛嘛香！',
  },
];

export default defineComponent({
  setup() {

    //============================全局变量admin=========================================
    const admin = computed(()=>store.state.admin)
    const goods = ref()


    //============================全局方法-刷新列表======================================

    const handleQuery = () => {
      axios.get("/admin/goodsAll/" + admin.value.token).then((response)=>{
        const data = response.data
        if(data.success){
          goods.value = data.content
        } else{
          message.error(data.message)
        }
      })
    }

    onMounted(()=>{
      handleQuery()
    })


    //============================编辑订单==============================================

    // 模态框属性
    //------------------------------------------
    const good = ref()
    good.value = {}
    const goodModalVisible = ref(false)
    const goodModalLoading = ref(false)

    // 新增按钮
    //------------------------------------------

    const add = () => {
      goodModalVisible.value = true
      good.value = {}
    }

    // 编辑按钮
    //------------------------------------------
    const edit = (record:any) => {
      goodModalVisible.value = true
      console.log(record)
      good.value = Tool.copy(record)
    }


    // 模态框方法
    //------------------------------------------

    /*
    * 发送更新数据
    *
    * */

    const handleModalOk = () => {
      goodModalLoading.value = true
      const req = {
        token: admin.value.token,
        content: good.value,
      }
      axios.post("/admin/updateGood", req).then((response)=>{
        const data = response.data
        if(data.success){
          goodModalVisible.value = false
          goodModalLoading.value = false
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


    const handleDelete = (id: number) => {
      const req = {
        token: admin.value.token,
        id:id,
      }
      console.log(req)
      axios.delete("/admin/deleteGood", {data:req}).then((response)=>{
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


    //============================搜索栏功能=============================================

    const param = ref()
    param.value = {}

    // 搜索方法
    //------------------------------------------

    const handleSearchQuery = () => {

      const req = {
        token: admin.value.token,
        name: param.value.name,
      }
      axios.post("/admin/goodsSearch", req).then((response)=>{
        const data = response.data
        if(data.success){
          goods.value = data.content
        } else{
          message.error(data.message)
        }
      })

    }

    return {
      admin,
      handleQuery,

      goodModalVisible,
      goodModalLoading,
      handleModalOk,
      handleDelete,
      edit,
      add,


      goods_,
      goods,
      columns,
      good,

      focus,
      handleStatusChange,

      param,
      handleSearchQuery
    };
  },
});
</script>

