<template>
  <div class="Mall">
    <header>
      <div class="container clear">
        <div class="left" v-if="clientToken">
          <span class="name">欢迎您，{{clientName}}</span>
          <button @click="navTo1('/mall/personal')">个人中心</button>
          <span class="yue">    余额：{{balance}} 元</span>
          <button @click="refresh">刷新</button>
          <input type="number" v-model="creditsChange">
          <button @click="addCredits">充值</button>
          <button @click="logout">退出登录</button>
        </div>
        <div class="left" v-else>
          <span @click="navTo('MallLogin', 'login')">登录</span>
          <span @click="navTo('MallLogin', 'register')">注册</span>
        </div>
      </div>
    </header>
    <div class="content" :style="{minHeight:clientHeight+'px'}"  v-if="clientToken">
      <div class="container">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState,mapMutations } from 'vuex';
import NoticeList from '../../components/NoticeList';
import {getClientSize,backToTop} from '../../util/util';
import {logoutClient} from '../../api/client';
import {getCredits,addCredits} from '../../api/client';

export default {
  name: 'Mall',
  computed:{
    ...mapState([
      'clientToken',
      'clientName'
    ]),
  },
  created(){
    this.token = this.clientToken
  },
  components:{
    NoticeList
  },
  data () {
    return {
      notices:['特别消息:生鲜大放送......',' 注册即可获千元好礼......','美味大闸蟹,源自阳澄湖......'],
      clientHeight:getClientSize().height,
      shouldShowBT:false,
      token:'',
      creditsChange:0,
      balance:0,
    }
  },

  methods:{
    ...mapMutations({
      clientLogout: 'CLIENT_LOGOUT',
    }),
    navTo1(route){
        this.$router.push(route);
    },
    navTo(route, parm1){
      this.$router.push({name: route, params: {parm: parm1}})
    },
    logout(){
      this.clientLogout();

      var username = this.token
      const res = logoutClient({
          token:username
      });
      res.then(()=>{
      })
      .catch((e)=>{
          alert(e);
      })
      this.$router.go(0);
    },
    addCredits(){
      const res = addCredits({
          username: this.clientToken,
          balanceChange: this.creditsChange
      });
      res.then(()=>{
      })
      .catch((e)=>{
          alert(e);
      })
      refresh();
    },
    refresh(){
      const res = getCredits({
          username: this.clientToken
      });
      res.then((data)=>{
        this.balance = data.balance;
      })
      .catch((e)=>{
          alert(e);
      })
    },
    backToTop(){
      backToTop();
    },
    watchScrollTop(){
      let scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
      if(scrollTop>150){
        this.shouldShowBT = true;
      }else{
        this.shouldShowBT = false;
      }
    }
  },

  mounted(){
    document.addEventListener('scroll',this.watchScrollTop,false);
  },

  beforeDestroyed(){
    document.removeEventListener('scroll',this.watchScrollTop,false);
  }
}
</script>

<style scoped lang="less">
@import "../../assets/css/var.less";
.Mall{
  width: 100%;
  header{
    width: 100%;
    background-color: #ffffff;
    height:38px;
    color:#000000;
    user-select:none;
    z-index: 10000;
    position: absolute;
    left: 0;
    top: 0;
    .container{
      position: relative;
      height:38px;
      .title{
        position: absolute;
        left: 0;
        display: inline-block;
        height: 26px;
        top: 50%;
        margin-top: -13px;
        line-height: 26px;
        font-size: 14px;
        cursor: pointer;
      }
      .NoticeListBox{
        position: absolute;
        left: 200px;
      }
      .right{
        position: absolute;
        right: 0;
        display: inline-block;
        height: 26px;
        top: 50%;
        margin-top: -13px;
        line-height: 26px;
        font-size: 14px;
        span{
          margin-left: 20px;
          cursor: pointer;
        }
        .name{
          cursor: default;
        }
        .balance{
          cursor: default;
        }
      }
    }
  }
  .content{
    padding-top: 40px;
  }
  .fixedAd{
    position: fixed;
    right: 0;
    top: 108px;
    width: 72px;
    img{
      display: block;
      width: 100%;
      height: 154px;
    }
    .fixedList{
      margin-top: 2px;
      background-color: white;
      width: 100%;
      li{
        width: 100%;
        height: 80px;
        text-align: center;
        border-bottom: 1px solid @borderColor;
        cursor: pointer;
        padding-top: 12px;
        i{
          display: block;
          font-size: 30px;
          color:#666666;
        }
        span{
          display: block;
          font-size: 14px;
          color:#666666;
          margin-top: 4px;
        }
        &:last-child{
          border-bottom: none;
        }
        &:hover{
          i{
            color:@thirdColor;
          }
          span{
            color:@thirdColor;
          }
        }
      }
    }
  }
  .bottomInfo{
    width: 100%;
    height: 300px;
    border-top: 1px solid @borderColor;
    overflow: hidden;
    margin-top: 80px;
    .footerItem{
      width: 33%;
      height: 210px;
      position: relative;
      top: 45px;
      display: inline-block;
      text-align: center;
      vertical-align: middle;
      color:@fontDefaultColor;
      .title{
        color: @fontDeepColor;
        margin-bottom: 30px;
      }
    }
    .service{
      border-right: 1px solid @borderColor;
      span{
        display: inline-block;
        width: 80px;
        height: 100px;
        border:1px solid @borderColor;
        text-align: center;
        margin: 0 10px;
        font-size: 14px;
        cursor: pointer;
        &:hover{
          color:@thirdColor;
        }
        i{
          display: block;
          font-size: 30px;
          margin-top: 20px;
          margin-bottom: 10px;
        }
      }
    }
    .intro{
      border-right: 1px solid @borderColor;
      .intro-p{
        font-size: 13px;
        width: 300px;
        margin: 0 auto;
        text-align: left;
        color:@fontDeepColor;
        line-height: 1.8em;
      }
      div{
        text-align: left;
        font-size: 14px;
        margin-left: 47px;
        margin-top: 20px;
        img{
          margin: 0 6px;
          display: inline-block;
          vertical-align: middle;
        }
      }
    }
    .code{
      img{
        display: block;
        margin: 0 auto;
      }
      span{
        font-size: 12px;
        color:@thirdColor;
        margin-top: 10px;
        display: block;
      }
    }
  }
  footer{
    width: 100%;
    height: 208px;
    background-color: #414141;
    color:white;
    overflow: hidden;
    .footerTop{
      padding: 36px 0;
      border-bottom: 1px solid #4f4f4f;
      width: 100%;
      li{
        display: inline-block;
        width: 33%;
        text-align: center;
        img{
          vertical-align: middle;
        }
        span{
          vertical-align: middle;
          font-size: 18px;
          margin-left: 10px;
        }
      }
    }
    .footerBottom{
      color:@fontDefaultColor;
      margin-top: 30px;
      font-size: 13px;
      text-align: center;
      ul{
        li{
          display: inline-block;
          cursor: pointer;
          padding: 0 6px;
          border-right: 2px solid @fontDefaultColor;
          &:last-child{
            border-right:none;
          }
        }
      }
      p{
        margin-top: 5px;
      }
    }
  }
}
</style>
