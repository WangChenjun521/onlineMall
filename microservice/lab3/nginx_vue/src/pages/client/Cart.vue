<template>
  <div class="Cart">
    <div v-if="orderList.length>0">
      <div class="cartTableHeader">
        <span>商品信息</span>
        <span>卖家</span>
        <span>单价</span>
        <span>数量</span>
        <span>小计</span>
        <span>交易操作</span>
      </div>
      <ul class="orderList">
        <li v-for="(item,index) in orderList" :key="index">
          <div class="orderDetail">
            <img :src="item.img" alt="商品图片" />
            <div class="goodsName">
              <p @click="navTo('/mall/goods/'+item.goodsId)">{{item.name}}</p>
              <p @click="navTo('/mall/goods/'+item.goodsId)">{{item.specName}}</p>
            </div>
            <span class="seller">{{item.seller}}</span>
            <span class="unitPrice">{{'￥'+item.price}}</span>
            <span class="num">
              <NumberInput 
                @changeHandle="numberChange(item.goodsId,item.num,item.specName)" 
                :initNum="item.num" 
                v-model="item.num" 
                :min="1" 
                :max="999"
              />
            </span>
            <!-- <input @change="numberChange(item.id)" type="text" v-model="item.temGoodsNum" min="1" class="numInput" /> -->
            <span class="amount">{{'￥'+item.num*item.price}}</span>
            <span class="num">
              <input type="checkbox" v-model="picked[index]"/>
            </span>
            <button @click="deleteItemFromCart(item.goodsId, item.specName, index)">删除</button>

          </div>
        </li>
      </ul>
      <div class="cartFooter">
        <span>应付金额：</span>
        <span class="total">{{'￥'+totalAmount}}</span>
        <button @click="deleteItemsFromCart">删除所选</button>
        <button @click="settleAccounts">下单</button>
      </div>
    </div>
    <p class="emptyTips" v-else>购物车为空</p>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import {getCartList,changeItemNumInCart,deleteItemsFromCart,settleAccounts} from '../../api/client';
import NumberInput from '../../components/NumberInput';

export default {
  name: 'Cart',
  components:{
    NumberInput
  },
  computed:{
    ...mapState([
      'clientToken'
    ]),
    totalAmount(){
      let amount = 0;
      this.orderList.map((item,index)=>{
        if(this.picked[index]){
          amount+=item.num*item.price;
        }
      })
      return amount;
    }
  },
  data () {
    return {
      orderList:[],
      picked:[],
      settleList:[],
    }
  },

  methods:{
    getCartList(){
      const res = getCartList({
        username:this.clientToken
      });
      res
      .then((data)=>{
        this.orderList=data;
        // this.orderList.map((item,index)=>{
        //   this.orderList.splice(index,1);//TOCHECK
        // })
      })
      .catch((e)=>{
        alert(e);
      })
      

      this.orderList.map((item,index)=>{
        this.picked.push(false);
      })
    },
    numberChange(itemId, itemNum, specName){
      const res = changeItemNumInCart({
        username:this.clientToken,
        goodsId: itemId,
        specName:specName,
        num:itemNum
      });
      res
      .then(()=>{
        this.orderList.map((item,index)=>{
          if(itemId===item.goodsId){
            item.amount = item.num*item.price;
          }
        })
      })
      .catch((e)=>{
        alert(e);
      })
    },
    deleteItemFromCart(goodsId, specName, index){
      const res = deleteItemsFromCart({
        username:this.clientToken,
        deleteList:[
          {
            goodsId: goodsId,
            specName: specName
          }
        ]
      });
      res
      .then(()=>{
        alert('删除购物车的商品成功！');
        this.orderList.splice(index,1);
      })
      .catch((e)=>{
        alert(e);
      })
    },
    deleteItemsFromCart(){
      this.updateSettleList();
      const res = deleteItemsFromCart({
        username:this.clientToken,
        deleteList:this.settleList,
      });
      res
      .then(()=>{
        alert('删除购物车的商品成功！');
        location.reload();
      })
      .catch((e)=>{
        alert(e);
      })
    },
    navTo(route){
      this.$router.push(route);
    },
    updateSettleList(){
      this.settleList = [];
      this.orderList.map((item,index)=>{
          if(this.picked[index]){
            this.settleList.push({goodsId:item.goodsId, specName:item.specName, num:item.num, price:item.price});
          }
        })
    },
    settleAccounts(){
      this.updateSettleList();
      if(this.settleList.length==0){
        alert('请选择要结算的商品');
        return;
      }
      const res = settleAccounts({
        username:this.clientToken,
        settleList:this.settleList,
      });
      res
      .then(()=>{
        alert('下单成功！,请去我的订单付款');
        this.settleList = [];
        getCartList();
      })
      .catch((e)=>{
        alert(e);
      })

    },
  },

  mounted(){
    this.getCartList();
  },
}
</script>

<style scoped lang="less">
@import "../../assets/css/var.less";
.Cart{
  width: 100%;
  .emptyTips{
      width: 200px;
      text-align: center;
      display: block;
      margin: 30px auto;
      color:@thirdColor;
      font-size: 20px;
  }
  .cartTableHeader{
    width: 100%;
    height: 40px;
    background-color: #f5f5f5;
    border: 1px solid @borderColor;
    color: @fontDefaultColor;
    font-size: 14px;
    line-height: 40px;
    span{
      display: inline-block;
      width: 12%;
      &:first-child{
        width:30%;
        text-align: center;
      }
    }
  }
  .orderList{
    width: 100%;
    li{
      border: 1px solid @borderColor;
      border-top: none;
      font-size: 13px;
      .orderHeader{
        background-color: #f1f1f1;
        height: 40px;
        line-height: 40px;
        padding: 0 5px;
        .orderTime{
          font-weight: 600;
        }
        .orderId,.state{
          margin-left: 10px;
        }
      }
      .orderDetail{
        width: 100%;
        padding: 10px;
        position: relative;
        overflow: hidden;
        img{
          width: 84px;
          height: 84px;
          display: inline-block;
        }
        .goodsName{
          display: inline-block;
          margin-left: 5px;
          width: 150px;
          vertical-align: top;
          p{
            cursor: pointer;
            line-height: 20px;
            &:hover{
              text-decoration:underline;
            }
          }
          span{
            color:@fontDefaultColor;
            display: block;
            margin-top: 10px;
          }
        }
        .seller{
          display: inline-block;
          vertical-align: top;
          width: 10%;
          height: 85px;
          line-height: 85px;
          text-align: center;
        }
        .unitPrice,.num,.amount{
          display: inline-block;
          vertical-align: top;
          width: 13%;
          height: 85px;
          line-height: 85px;
          text-align: center;
        }
        .NumberInput{
          position: relative;
          top: 25px;
        }
        button{
          position: absolute;
          bottom: 40px;
          width: 50px;
          height: 30px;
          border-radius: 3px;
          background-color: @falseColor;
          color:white;
          border: none;

        }
        .CheckBox{
          position: relative;
          top: 15px;
        }
      }
    }
  }
  .cartFooter{
    width: 100%;
    height: 60px;
    line-height: 60px;
    padding: 0 10px;
    border: 1px solid @borderColor;
    border-top: none;
    background-color: #f5f5f5;
    position: relative;
    span{
      color: @fontDefaultColor;
      display: inline-block;
      vertical-align: top;
    }
    .total{
      color:@falseColor;
      width: 600px;
      font-size: 25px;
      font-weight: 500;
    }
    button{
      position: relative;
      width: 100px;
      height: 100%;
      background-color: @thirdColor;
      border: none;
      color:white;
      font-size: 20px;
    }
  }
}
</style>