<template>
  <div class="EditOrders">
    <header class="clear">
      <span>订单编辑</span>
    </header>
    <div class="content">
      <div class="inputBox">
        <span>订单号：</span>
        <span class="val">{{id}}</span>
      </div>
      <div class="inputBox">
        <span>商品名称：</span>
        <span class="val">{{name}}</span>
      </div>
      <div class="inputBox">
        <span>总金额：</span>
        <span class="val">{{'¥'+amount}}</span>
      </div>
      <div class="inputBox">
        <span>数量：</span>
        <NumberInput v-model="amount" :initNum="amount"/>
      </div>
      <div class="btnBox">
        <button class="confirmBtn" @click="saveChange">保存修改</button>
        <button class="normalBtn" @click="back">返回</button>
      </div>
    </div>
  </div>
</template>

<script>
import {getAOrder,changeOrder} from '../../api/admin';
import Radio from '../../components/Radio';
import NumberInput from '../../components/NumberInput';

export default {
  name: 'EditOrders',
  components:{
    Radio,
    NumberInput
  },
  data(){
    return{
      id:this.$route.params.id,
      name:'',
      amount:'',
    }
  },
  methods:{
    fetchOrderDetail(id){
      const res = getAOrder(id);
      res
      .then((order)=>{
        this.name = order.name;
        this.amount = order.amount;
      })
      .catch((e)=>{
        alert(e);
      })
    },

    back(){
      this.$router.go(-1);
    },

    saveChange(){
      const res = changeOrder({
        id:this.$route.params.id,
        num:this.amount
      });
      res
      .then(()=>{
        alert('修改成功');
        this.fetchOrderDetail(this.$route.params.id);
      })
      .catch((e)=>{
        alert(e);
      })
    }
  },
  mounted(){
    this.fetchOrderDetail(this.$route.params.id);
  }
}
</script>

<style scoped lang="less">
@import "../../assets/css/var.less";
.EditOrders{
  header{
    width: 100%;
    height: 40px;
    line-height: 40px;
    span{
      float: left;
    }
  }
  .content{
    width: 100%;
    background-color: white;
    padding: 10px;
    .inputBox{
      margin-bottom: 30px;
      span{
        width: 90px;
        display: inline-block;
        color:@fontDefaultColor;
        font-weight: 600;
      }
      .Radio /deep/ input:checked+.tipsBox{
        &:after{
          background-color:#337da4;
        }
      }
      .tips{
        font-weight: normal;
        width: auto;
        font-size: 13px;
        position: relative;
        left: 3px;
      }
      .val{
        width: auto;
        font-weight: 500;
        font-size: 14px;
        color:@fontDeepColor;
      }
      .NumberInput{
        display: inline-block;
        vertical-align: middle;
      }
    }
    .btnBox{
      margin: auto;
      display: block;
      width: 250px;
    }
    .confirmBtn{
      display: inline-block;
      margin-right: 30px;
      background-color: #337da4;
      color:white;
      border: none;
      width: 100px;
      height: 30px;
      border-radius: 5px;
      cursor: pointer;
    }
    .normalBtn{
      display: inline-block;
      background-color: grey;
      color:white;
      border: none;
      width: 100px;
      height: 30px;
      border-radius: 5px;
      cursor: pointer;
    }
  }
}
</style>
