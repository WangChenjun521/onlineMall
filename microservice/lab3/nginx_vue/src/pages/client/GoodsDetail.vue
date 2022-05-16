<template>
  <div class="GoodsDetail">
    <div class="content">
      <div class="goodsInfo">
        <img class="infoLeft" :src="goodsImg" alt="商品图片" />
        <div class="infoRight">
          <div class="infoBox">
            <h3 class="name">{{goodsName}}</h3>
          </div>
          <div class="infoBox">
            <p>{{goodsDesc}}</p>
          </div>
          <div class="infoBox">
            <h3 class="price">{{'¥'+goodsPrice}}</h3>
          </div>
          <div class="infoBox">
            <span>数量：</span>
            <NumberInput v-model="num"/>
          </div>
          
          <ul class="box">
              <li v-for="c,index of specsList" :class="{checked:index==selected}" @click="changeSpecs(index, c.price)">{{c.specName}}</li>
          </ul>
          <button class="buyBtn" @click="settleAccounts">立即购买</button>
          <button @click="addToCart">加入购物车</button>
          <button @click="addToFav">收藏商品</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import {buyNow,getGoodsInfo,settleAccounts,getGoodsList,addToFav,addToCart} from '../../api/client';
import NumberInput from '../../components/NumberInput';
import Radio from '../../components/Radio';
import GoodsItem from '../../components/GoodsItem';

export default {
  name: 'GoodsDetail',
  components:{
    NumberInput,
    Radio,
    GoodsItem,
  },
  computed:{
    el : ".box",
    ...mapState([
      'clientToken',
      'clientName'
    ]),
    id(){
      return this.$route.params.id;
    },
    filterList(){
      return this.goodsList.filter((item)=>{
        return String(item.id)!==String(this.id);
      })
    }
  },
  data () {
    return {
      goodsId:0,
      goodsImg:'',
      goodsName:'',
      goodsDesc:'',
      typeId:'',
      goodsPrice:0,
      num:1,
      msgList:[],
      askContent:'',
      tagList:['评价','商品问答'],
      curIndex:0,
      rate:'',
      commentList:Array,
      goodsList:[],
      specsNum:0,
      specsList:[],
      selected:-1
    }
  },

  methods:{
    changeSpecs(index, specPrice){
      this.selected = index;//this指向app
      this.goodsPrice = specPrice
    },
    changeIndex(i){
      this.curIndex = i;
    },

    getGoodsInfo(id){
      const res = getGoodsInfo(
        {
          goodsId:id
        }
      );
      res
      .then((data)=>{//TODO
        this.goodsId = data.goodsId;
        this.goodsImg = data.img;
        this.goodsName = data.name;
        this.goodsDesc = data.desc;
        this.goodsSeller = data.seller;
        this.goodsPrice = data.price;
        this.specsNum = data.specsNum;
        this.specsList = data.specs;
      })
      .catch((e)=>{
        alert(e);
      });
      
    },

    addToFav(){
      if(!this.clientToken){
        alert('请先登录！');
        return this.$route.go(0);
      }
      const res = addToFav({
        username:this.clientToken,
        goodsId:this.goodsId
      });
      res
      .then(()=>{
        alert('收藏成功！')
      })
      .catch((e)=>{
        alert(e);
      })
    },
    addToCart(){
      if(!this.clientToken){
        alert('请先登录！');
        return this.$route.go(0);
      }
      if (this.num < 1){
          alert('数量不能小于1')
          return;
      }
      if (this.specsNum > 0 && this.selected == -1){
          alert('请选择商品规格')
          return;
      }
      var specName = "";
      if (this.specsNum>0)
      {
        specName = this.specsList[this.selected].specName;
      }
      const res = addToCart({
        username:this.clientToken,
        goodsId:this.goodsId,
        num:this.num,
        specName:specName,
      });
      res
      .then(()=>{
        alert('加入购物车成功！请前往 个人中心->购物车 结算')
      })
      .catch((e)=>{
        alert(e);
      })
    },
    settleAccounts(){
      if(!this.clientToken){
        alert('请先登录！');
        return this.$route.go(0);
      }
      if (this.num < 1){
          alert('数量不能小于1')
          return;
      }
      if (this.specsNum > 0 && this.selected == -1){
          alert('请选择商品规格')
          return;
      }
      var specName = "";
      if (this.specsNum > 0)
      {
        specName = this.specsList[this.selected].specName;
      }
      const res = buyNow({
        username:this.clientToken,
        goodsId:this.id,
        num:this.num,
        specName:specName,
      });
      res
      .then(()=>{
        alert('下单成功，请去订单页面付款');
        this.orderList = [];
      })
      .catch((e)=>{
        alert(e);
      })
    },
  },
  created(){
    this.getGoodsInfo(this.id);
  },

  watch:{
    $route(to,from){
      this.getGoodsInfo(to.params.id);
    }
  }
}
</script>

<style scoped lang="less">
@import "../../assets/css/var.less";
.GoodsDetail{
  .content{
    width: 100%;
    padding-top: 20px;
    .goodsInfo{
      width: 100%;
      overflow: hidden;
      .infoLeft{
        display: inline-block;
        width: 400px;
        height: 400px;
        float: left;
      }
      .infoRight{
        display: inline-block;
        float: right;
        width: 700px;
        body{margin:0;}
        ul{
            padding:0; list-style:none; 
            margin:15px 15px;
        }
        li{
            width:580px; height:50px; 
            display:inline-block;
            border-radius:8px; border:1px #000 solid;
            text-align:center; line-height:50px;
            cursor:pointer; 
            transition:all 0.3s linear;
            margin-left:5px;
        }
        li:hover{
            background-color:#b4a078; color:#fff; 
            border:1px #fff solid;
        }    
        li.checked{
            background-color:#b4a078; color:#fff; 
            border:1px #fff solid;
        }
        .infoBox{
          margin-bottom: 30px;
          .name{
            font-size: 20px;
          }
          p{
            color:@fontDefaultColor;
            font-size: 15px;
          }
          .price{
            font-size: 35px;
            color:@falseColor;
          }
          span{
            color:@fontDefaultColor;
            font-size: 13px;
            display: inline-block;
            width: 50px;
          }
          .tips{
            width: auto;
            margin: 0 20px 0 5px;
          }
          .NumberInput{
            display: inline-block;
            vertical-align: middle;
          }
        }
        button{
          background-color: #b4a078;
          width: 170px;
          height: 50px;
          color:white;
          border: none;
          font-size: 19px;
          margin-right: 20px;
          margin-top: 10px;
          &:hover{
            opacity:0.8;
          }
        }
        .buyBtn{
          border: 1px solid #b4a078;
          color:#b4a078;
          background-color: #f5f3ef;
        }
      }
    }
    .msgBox{
      margin: 50px 0;
      border: 1px solid @borderColor;
      padding-top: 0;
      .tagList{
        width: 100%;
        height: 40px;
        border-bottom: 1px solid @borderColor;
        background-color: #f5f5f5;
        li{
          width: 170px;
          height: 42px;
          position: relative;
          top: -2px;
          display: inline-block;
          text-align: center;
          line-height: 40px;
          font-size: 13px;
          cursor: pointer;
          &:hover{
            color:@thirdColor;
          }
        }
        .selected{
          background-color: white;
          border-top: 4px solid @thirdColor;
        }
      }
      .commentBody{
        padding: 20px;
        min-height: 300px;
        .rateBox{
          margin-bottom: 10px;
          span{
            color:@fontDefaultColor;
            display: inline-block;
            margin-right: 10px;
          }
          .rate{
            color:@falseColor;
            font-weight: 600;
            font-size: 30px;
          }
        }
        .commentList1{
          width: 100%;
          li{
            width: 100%;
            display: block;
            margin: 0 auto;
            border-bottom:1px solid @borderColor;
            padding: 20px 0;
            .userInfo{
              width: 80px;
              display: inline-block;
              text-align: center;
              img{
                margin: auto;
                display: block;
                width: 50px;
                height: 50px;
                border-radius: 50%;
                margin-bottom: 6px;
              }
              span{
                font-size: 13px;
                color:@fontDefaultColor;
              }
            }
            .commentInfo{
              display: inline-block;
              vertical-align: top;
              .starList{
                i{
                  color:#f9bd4f;
                }
              }
              .specName,.time{
                color:@fontDefaultColor;
                font-size: 13px;
                margin-top: 10px;
              }
              .comment{
                margin-top: 10px;
              }
            }
          }
        }
        .noComment{
          width: 100%;
          text-align: center;
          color:@thirdColor;
          padding-top: 30px;
        }
      }
      .msgBody{
        padding: 20px;
        min-height: 300px;
        .inputBox{
          margin-top: 20px;
          textarea{
            width: 88%;
            height: 50px;
            padding: 5px;
            border:2px solid @borderColor;
            display: inline-block;
            overflow: hidden;
          }
          button,.banAsk{
            float: right;
            width: 10%;
            height: 50px;
            position: relative;
            display: inline-block;
            overflow: hidden;
            background-color: white;
            border: 2px solid @fontShallowColor;
            color:@fontDefaultColor;
          }
          .banAsk{
            background-color: @fontShallowColor;
            text-align: center;
            font-size: 12px;
            line-height: 50px;
            color:white;
            user-select:none;
          }
        }
        .msgList{
          margin-top: 20px;
          li{
            border-bottom: 1px solid @borderColor;
            padding: 10px 0;
            .ask,.answer{
              margin: 8px 0;
              width: 100%;
              .note{
                display: inline-block;
                color:white;
                text-align: center;
                width: 20px;
                height: 20px;
                border-radius: 50%;
                font-size: 10px;
                line-height: 20px;
              }
              .text{
                font-size: 14px;
              }
              .tipsInfo{
                float: right;
                font-size: 14px;
                color:@fontDefaultColor;
              }
            }
            .ask{
              .note{
                background-color: @falseColor;
              }
            }
            .answer{
              .note{
                background-color: @mainColor;
              }
            }
          }
        }
      }
    }
    .typeGoods{
      margin: 50px 0;
      border: 1px solid @borderColor;
      padding-top: 0;
      .title{
        text-align: center;
        width: 100%;
        height: 40px;
        line-height: 40px;
        background-color: #f5f5f5;
        font-weight: 600;
        border-bottom: 1px solid @borderColor;
      }
      .list{
        .GoodsItem{
          display:block;
          border-bottom:1px dotted @borderColor;
          margin: 0 auto;
        }
      }
    }
  }
  .ban{
    user-select: none;
    cursor:not-allowed;
  }
}
</style>
