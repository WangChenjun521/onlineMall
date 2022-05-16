<template>
  <div class="Fav">
    <div v-if="orderList.length>0">
      <ul class="orderList">
        <li v-for="(item,index) in orderList" :key="'order'+item.goodsId">
          <div class="orderDetail">
            <img :src="item.img" alt="商品图片" @click="navTo('/mall/goods/'+item.goodsId)" />
            <div class="goodsName">
              <p @click="navTo('/mall/goods/'+item.goodsId)">{{item.name}}</p>
            </div>
            <span class="Price">{{'￥'+item.price}}</span>
            <button @click="deleteItemFromFav(item.goodsId)">删除</button>
          </div>
        </li>
      </ul>
    </div>
    <p class="emptyTips" v-else>收藏夹为空</p>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import {getFavList, deleteItemFromFav} from '../../api/client';

export default {
  name: 'Fav',
  computed:{
    ...mapState([
      'clientToken'
    ])
  },
  data () {
    return {
      orderList:[],
    }
  },

  methods:{
    getFavList(){
      const res = getFavList({
        username:this.clientToken
      });
      res
      .then((data)=>{
        this.orderList=data;
      })
      .catch((e)=>{
        alert(e);
      })

     

    },
    deleteItemFromFav(itemId){
      const res = deleteItemFromFav({
        username:this.clientToken,
        goodsId:itemId
      });
      res
      .then(()=>{
        alert('删除收藏的宝贝成功！');
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
    navTo(route){
      this.$router.push(route);
    }
  },
  mounted(){
    this.getFavList();
  },
}
</script>

<style scoped lang="less">
@import "../../assets/css/var.less";
.Fav{
  width: 100%;
  .emptyTips{
      width: 200px;
      text-align: center;
      display: block;
      margin: 30px auto;
      color:@thirdColor;
      font-size: 20px;
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
        .Price,.num,.amount{
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
          background-color: @falseColor;
          color:white;
          border: none;

        }
      }
    }
  }
}
</style>