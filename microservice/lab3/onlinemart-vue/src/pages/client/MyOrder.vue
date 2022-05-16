<template>
  <div class="MyOrder">
    <ul class="tagList">
      <li :class="{selected:curIndex===index}" v-for="(item,index) in tagList" :key="'tag'+index" @click="changeIndex(index)">
        {{item}}
      </li>
    </ul>
    <div class="orderBox">
      <div class="orderTableHeader">
        <span>商品</span>
        <span>单价</span>
        <span>数量</span>
        <span>实付款</span>
        <span>交易操作</span>
      </div>
      <ul class="orderList">
        <li v-for="(item,index) in orderList" :key="'order'+item.orderId">
          <div class="orderHeader">
            <span class="orderId">{{'订单号：'+item.orderId}}</span>
            <span class="orderTime">{{item.createtime}}</span>
            <span class="amount">{{'总计：'+item.amount}}</span>
            <span class="state">{{tagList[item.state+1]}}</span>
            <button v-if="item.state===1" @click="cancelOrder(item.orderId)">申请退款</button>
            <button @click="complainOrder(item.orderId)">投诉订单</button>
            <span class="deleteBtn" @click="deleteOrder(item.orderId)"><i class="iconfont icon-close" /></span>
            <button v-if="item.state===0" @click="confirmPay(item.orderId)">付款</button>
          </div>

          <ul class="suborderList">
            <li v-for="(item2,index2) in item.subOrders" :key="'suborder'+item2.orderId">
              <div class="suborderHeader">
                <span class="orderId">{{'子订单号：'+item2.orderId}}</span>
                <span class="goodsNum">{{'货物数量：'+item2.goodsNum}}</span>
                <span class="seller">{{'卖家：'+item2.seller}}</span>
                <span class="amount">{{'总计：'+item2.amount}}</span>
              </div>
              <ul>
                <li v-for="(item3,index3) in item2.goods" :key="'goodsId'+item3.goodsId">
                  <div class="orderDetail">
                    <img :src="item3.img" alt="商品图片" />
                    <div class="goodsName">
                      <p @click="navTo('/mall/goods/'+item3.goodsId)">{{item3.name}}</p>
                      <span>{{item3.specName}}</span>
                    </div>
                    <span class="unitPrice">{{'￥'+item3.price}}</span>
                    <span class="num">{{item3.num}}</span>
                    <span class="amount">{{'￥'+item3.amount}}</span>
                    <button v-if="item.state===0">未付款</button>
                    <button v-if="item.state===1">等待发货</button>
                    <button v-else-if="item.state===2" @click="confirmReceive(item.orderId)">确认收货</button>
                    <button v-else-if="item.state===3" >已完成</button>
                  </div>
                </li>
              </ul>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import {getOrderByState,deleteOrder,cancelOrder,complainOrder,confirmReceive,pay,sendComment} from '../../api/client';
import Popup from '../../components/Popup';

export default {
  name: 'MyOrder',
  components:{
    Popup
  },
  computed:{
    ...mapState([
      'clientToken'
    ]),
  },
  data () {
    return {
      tagList:['全部订单','待付款','待发货','已发货','已完成','已取消'],
      curIndex:0,
      orderList:[],
      popupShow:false,
      curOrderId:'',
      curCommentGoodsId:'',
      curCommentGoodsDetailId:'',
      curStar:0,
      hasClickStar:false,
      comment:'',
    }
  },

  methods:{
    changeIndex(i){
      this.curIndex = i;
      this.getOrderByState({
        username:this.clientToken,
        state:this.curIndex-1
      });
    },
    navTo(route){
      this.$router.push(route);
    },
    getOrderByState(state){
      const res = getOrderByState(state);
      res
      .then((data)=>{
        this.orderList=data
      })
      .catch((e)=>{
        alert(e);
      })
     


    },
    deleteOrder(orderId){
      const res = deleteOrder(orderId);
      res
      .then(()=>{
        alert('删除订单成功！');
        this.orderList.map((item,index)=>{
          if(item.id===orderId){
            this.orderList.splice(index,1);
          }
        })
      })
      .catch((e)=>{
        alert(e);
      })
    },

    cancelOrder(orderId){
      const res = cancelOrder({
        orderId:orderId
      });
      res
      .then(()=>{
        alert('申请退款成功！');
      })
      .catch((e)=>{
        alert(e);
      })
    },

    complainOrder(orderId){
      const res = complainOrder({
        username:this.clientToken,
        orderId:orderId,
        reason:"bad product",
      });
      res
      .then(()=>{
        alert('投诉订单成功！');
      })
      .catch((e)=>{
        alert(e);
      })
    },

    confirmPay(orderId){
      const res = pay({orderId:orderId});
      res
      .then(()=>{
        alert('支付成功！');
        this.orderList.map((item,index)=>{
          if(item.id===orderId){
            item.state = 1;
          }
        })
      })
      .catch((e)=>{
        alert(e);
      })
    },
    confirmReceive(orderId){
      const res = confirmReceive({orderId:orderId});
      res
      .then(()=>{
        alert('确认收货成功！');
        this.orderList.map((item,index)=>{
          if(item.id===orderId){
            item.state = 3;
          }
        })
      })
      .catch((e)=>{
        alert(e);
      })
    },

    closePopup(){
      this.popupShow = false;
      this.curCommentGoodsId = '';
      this.curOrderId = '';
      this.curCommentGoodsDetailId = '';
      this.hasClickStar = false;
      this.curStar = 0;
      this.comment = '';
    },
    showPopup(orderId,goodsId,goodsDetailId){
      this.curCommentGoodsId = goodsId;
      this.curOrderId = orderId;
      this.curCommentGoodsDetailId = goodsDetailId;
      this.popupShow = true;
    },
    sendComment(){
      if(this.curStar<=0 || this.comment==''){
        alert('评分和评价不能为空！');
        return;
      }
      const res = sendComment({
        token:this.clientToken,
        orderId:this.curOrderId,
        goodsId:this.curCommentGoodsId,
        goodsDetailId:this.curCommentGoodsDetailId,
        content:this.comment,
        score:this.curStar*20
      });
      res
      .then(()=>{
        alert('评价成功！');
        for(let order of this.orderList){
          if(order.id===this.curOrderId){
            order.hasComment = true;
          }
        }
        this.closePopup();
      })
      .catch((e)=>{
        alert(e);
      })
    },
    setCurStar(star){
      if(this.hasClickStar){
        return;
      }
      this.curStar = star;
    },
    confirmStar(star){
      this.curStar = star;
      this.hasClickStar = true;
    }
  },

  mounted(){
    this.getOrderByState(-1);
  }
}
</script>

<style scoped lang="less">
@import "../../assets/css/var.less";
.MyOrder{
  .tagList{
    li{
      text-align: center;
      display: inline-block;
      font-weight: 550;
      font-size: 18px;
      border-bottom: 2px solid @borderColor;
      cursor: pointer;
      padding: 10px 20px;
      position: relative;
      &:after{
        top:12px;
        right: 0;
        position:absolute;
        content:'';
        width: 1px;
        height: 15px;
        background-color: @borderColor;
      }
      &:last-child{
        &:after{
          display:none;
        }
      }
    }
    .selected{
      color:@thirdColor;
      border-bottom: 2px solid @thirdColor;
    }
  }
  .orderBox{
    width: 100%;
    margin-top: 20px;
    .orderTableHeader{
      width: 100%;
      height: 40px;
      background-color: #f5f5f5;
      border: 1px solid @borderColor;
      color: @fontDefaultColor;
      font-size: 14px;
      line-height: 40px;
      span{
        display: inline-block;
        width: 14%;
        &:first-child{
          width:40%;
          text-align: center;
        }
      }
    }
    .orderList{
      width: 100%;
      li{
        border: 1px solid @borderColor;
        font-size: 13px;
        margin-top: 10px;
        .orderHeader{
          background-color: #f1f1f1;
          height: 40px;
          line-height: 40px;
          padding: 0 5px;
          .orderTime{
            font-weight: 600;
          }
          .orderId{
            margin-left: 10px;
          }
          .amount{
            margin-right: 100px;
          }
          .state{
            margin-right: 300px;
          }
          .deleteBtn{
            float: right;
            cursor: pointer;
            i{

            }
          }
          button{
            position: relative;
            width: 70px;
            height: 30px;
            border-radius: 3px;
            background-color: @thirdColor;
            color:white;
            border: none;
          }
        }
        .suborderList{
          li{
            .suborderHeader{
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
              .deleteBtn{
                float: right;
                cursor: pointer;
                i{

                }
              }
            }
            li{
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
                  width: 230px;
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
                .unitPrice,.num,.amount,.hasComment{
                  display: inline-block;
                  vertical-align: top;
                  width: 15%;
                  height: 85px;
                  line-height: 85px;
                  text-align: center;
                }
                button{
                  position: absolute;
                  right: 90px;
                  bottom: 40px;
                  width: 70px;
                  height: 30px;
                  border-radius: 3px;
                  background-color: @thirdColor;
                  color:white;
                  border: none;
                }
              }
            }
          }
        }
      }
    }
  }
  .popupContent{
    padding: 10px;
    .scoreBox{
      width: 100%;
      height: 50px;
      position: relative;
      line-height: 50px;
      text-align: left;
      .tips{
        font-size: 15px;
        vertical-align: middle;
        display: inline-block;
      }
      i{
        cursor: pointer;
        vertical-align: middle;
        display: inline-block;
        font-size: 25px;
        margin-right: 5px;
        -webkit-text-stroke: 1px #f9bd4f;
      }
    }
    textarea{
      width: 400px;
      height: 80px;
      padding: 5px;
      border:1px solid @borderColor;
      display: block;
      margin-top: 10px;
    }
    button{
      display: block;
      margin: 10px auto;
      width: 70px;
      height: 30px;
      border-radius: 3px;
      background-color: #313541;
      color:white;
      border: none;
    }
  }
}
</style>