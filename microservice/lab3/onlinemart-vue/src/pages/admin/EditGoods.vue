<template>
  <div class="EditGoods">
    <header class="clear">
      <span>商品编辑</span>
    </header>
    <div class="content">
      <div class="inputBox">
        <span>商品名称：</span>
        <TextInput class="long" placeholder="请输入商品名称" v-model="goodsName" :initText="goodsName"/>
      </div>
      <div class="inputBox">
        <span>图片地址：</span>
        {{goodsImg}}
        <TextInput class="long" placeholder="请输入图片地址" v-model="goodsImg" :initText="goodsImg"/>
<!--        <ImgInput @imageUrl="imageUrl"></ImgInput> -->
      </div>
      <div class="inputBox">
        <span>详情描述：</span>
        <TextInput class="long" placeholder="请输入简单描述" v-model="desc" :initText="desc"/>
      </div>
      <div class="btnBox">
        <button class="confirmBtn" @click="saveChange">保存修改</button>
        <button class="normalBtn" @click="back">返回</button>
      </div>
    </div>
  </div>
</template>

<script>
import Popup from '../../components/Popup';
import TextInput from '../../components/TextInput';
import ImgInput from '../../components/ImgInput';
import Radio from '../../components/Radio';
import {getTypes,getGoodsInfo,addGoods,updateGoods} from '../../api/admin';
export default {
  name: 'EditGoods',
  components: {
    Popup,
    Radio,
    TextInput,
    ImgInput
  },
  data() {
    return {
      popupShow: false,
      id: this.$route.params.id,
      goodsName: '',
      types: [],
      initTypeId: '',
      goodsImg: '',
      desc: '',
    }
  },
  methods: {
    imageUrl(imageUrlStr) {
      this.goodsImg = imageUrlStr;
    },
    getTypes() {
      const res = getTypes();
      res
        .then((data) => {
          this.types = data;
        })
        .catch((e) => {
          alert(e)
        })
    },
    back() {
      this.$router.go(-1);
    },
    saveChange() {
      if (this.id.indexOf('new') !== -1) {
        const res = addGoods({
          name: this.goodsName,
          typeId: this.initTypeId,
          img: this.goodsImg,
          desc: this.desc,
        });
        res
          .then(() => {
            alert('创建商品成功！')
          })
          .catch((e) => {
            alert(e);
          })
      } else {
        const res = updateGoods({
          id: this.id,
          name: this.goodsName,
          typeId: this.initTypeId,
          img: this.goodsImg,
          desc: this.desc,
        });
        res
          .then(() => {
            alert('修改成功！')
          })
          .catch((e) => {
            alert(e);
          })
      }
    },
    closePopup() {
      this.popupShow = false;
    },
    showPopup() {
      this.popupShow = true;
    },

    mounted() {
      this.getTypes();
      //新建商品
      if (this.id.indexOf('new') !== -1) {
        var words = this.id.split('=');
        // alert(words[words.length-1])
        this.initTypeId = words[words.length - 1];
      } else {
        const res = getGoodsInfo(this.id);
        res
          .then((data) => {
            this.initTypeId = data.typeId;
            this.goodsName = data.name;
            this.goodsImg = data.img;
            this.desc = data.desc;
          })
          .catch((e) => {
            alert(e);
          })
      }
    }
  }
}
</script>

<style scoped lang="less">
@import "../../assets/css/var.less";
.EditGoods{
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
      textarea{
        width: 80%;
        height: 300px;
        border: 1px solid @borderColor;
      }
      .inputBox{
        margin-bottom: 30px;
        span{
          width: 90px;
          display: inline-block;
          color:@fontDefaultColor;
          font-weight: 600;
          vertical-align: middle;
        }
        .verTop{
          vertical-align: top;
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
          color:@fontDeepColor;
        }
        ul{
          display: inline-block;
          width: 500px;
          li{
            width: 100%;
            height: 40px;
            span{
              width: auto;
              font-size: 13px;
              font-weight: 500;
            }
            input{
              margin-right: 10px;
            }
            button{
              width: 50px;
              height: 25px;
              color:#d7514a;
              border:none;
              background-color: white;
              border-radius: 5px;
            }
          }
          .addSpec{
            text-align: center;
            line-height: 30px;
            background-color: #337da4;
            color:white;
            border: none;
            width: 100px;
            height: 30px;
            border-radius: 5px;
            font-size: 13px;
            cursor: pointer;
            margin:10px auto;
          }
        }
        .long{
          width: 900px;
        }
        .numInput{
          width: 30px;
          text-align: center;
          border: none;
          border-bottom: 2px solid @mainColor;
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
  .popupContent{
    padding: 10px;
    input{
      display: block;
      width: 400px;
      margin-bottom: 10px;
      height: 30px;
    }
    button{
        display: block;
        margin:auto;
        background-color: #333333;
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
