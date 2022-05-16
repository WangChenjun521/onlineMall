* [前台](#前台)
* [后台](#后台)


## 运行

```
npm install
npm run dev
```
# 注：文档要求返回参数中的token就是username


# 前台



## 1 收藏页面：

### 1.1 getFavList

**接口说明**

获得用户的收藏列表

**请求方式**

GET

**URL**

```
/api/mall/order/getFavList
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| token  | str      | 必须     | 用户名 |

```json
{
    "token":"t"
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 收藏列表 |

```json
{
	"code": 0,
	"data": [{
        "id": 1,
        "img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
        "name": "西服套装的",
        "spec": "l",
        "unitPrice": 500.0
	}, {
        "id": 2,
        "img": "http://115.29.141.32:8084/static/image/161sd54412.jpg",
        "name": "水杯",
        "spec": "l",
        "unitPrice": 50.0
	}]
}
```
### 1.2 deleteItemFromFav

**接口说明**

删除收藏的商品

**请求方式**

POST

**URL**

```
/api/mall/order/deleteItemFromFav
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| token  | str      | 必须     | 用户昵称 |
| itemId | integer  | 必须     | 商品id |

```json
{
    "token":"t",
    "itemId":1
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{
	"code": 0
}
```

### 1.3 addToFav

**接口说明**

收藏商品

**请求方式**

POST

**URL**

```
/api/mall/order/addToFav
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| token  | str      | 必须     | 用户昵称 |
| goodsId | integer  | 必须     | 商品id |

```json
{
    "token":"t",
    "itemId":1
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{
	"code": 0
}
```

## 2 购物车页面：
### 2.1 getCartList

**接口说明**

获得用户的购物车列表

**请求方式**

GET

**URL**

```
/api/mall/order/getCartList
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| token  | str      | 必须     | 用户昵称 |

```json
{
    "token":"t"
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 购物车列表 |

```json
{
	"code": 0,
	"data": [
		{
			"id": 476,
			"img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
			"name": "西服套装的",
			"spec": "l",
			"unitPrice": 500.0,
			"goodsNum": 1
		},
		{
			"id": 476,
			"img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
			"name": "西服套装的",
			"spec": "l",
			"unitPrice": 500.0,
			"goodsNum": 2
		}
	]
}
```
### 2.2 deleteItemFromCart

**接口说明**

删除收藏的商品

**请求方式**

POST

**URL**

```
/api/mall/order/deleteItemFromCart
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| token  | str      | 必须     | 用户昵称 |
| itemId | integer  | 必须     | 商品id |

```json
{
    "token":"t",
    "itemId":1
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{
	"code": 0
}
```
### 2.3 changeItemNumInCart

**接口说明**

改变购物车商品数量

**请求方式**

POST

**URL**

```
/api/mall/order/changeItemNumInCart
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| token  | str      | 必须     | 用户昵称 |
| itemId | integer  | 必须     | 商品id |
| itemNum | integer  | 必须     | 新的商品数量 |

```json
{
    "token":"t",
    "itemId":1,
    "itemNum":10
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{
	"code": 0
}
```
### 2.4 settleAccounts

**接口说明**

结算购物车

**请求方式**

POST

**URL**

```
/api/mall/order/settleAccounts
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| data   | list     | 必须     | 购物车列表 |

```json
{
    "code": 0,
	"data": [{
        "id":123,
        "goodsNum":1,
        "amount":100
	},
	{
		"id":123,
		"goodsNum":1,
		"amount":100
	}]
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{
	"code": 0
}
```


#### 3.1 searchGoods

**接口说明**

搜索商品

**请求方式**

GET

**URL**

```
/api/mall/goods/searchGoods
```

**请求参数**

| 参数名  | 参数类型 | 是否必须 | 说明   |
| ------- | -------- | -------- | ------ |
| keyword | str      | 必须     | 关键字 |

```json
?keyword=12
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 商品列表 |

```json
{
	"code": 0,
	"data": [{
		"id": 546,
		"img": "http://115.29.141.32:8084/static/image/1618838668103gaitubao_2_jpg.jpg",
		"name": "iphone 12",
		"price": 7699.0,
		"typeId": 220
	}]
}
```


# 后台



### 1. 用户账号模块

#### 1.1 login

##### 接口说明

登陆后台管理系统

##### 请求方式

POST

##### URL

```
/api/mall/user/login
```

##### 请求参数

| 参数名   | 参数类型 | 是否必须 | 说明   |
| -------- | -------- | -------- | ------ |
| username | str      | 必须     | 用户名 |
| pwd      | str      | 必须     | 密码   |

````
{username:"admin", pwd:"admin"}
````

##### 返回参数

| 参数名  | 参数类型 | 是否必须 | 说明                                 |
| ------- | -------- | -------- | ------------------------------------ |
| code    | integer  | 必须     | 返回状态码，0表示正常，10000表示错误 |
| data    | object   | 非必须   | 返回的数据                           |
| message | str      | 非必须   | 错误信息                             |

```
// error
{"code":10000,"message":"密码不正确!"}
// success
{"code":0,"data":{"token":"Lyle","username":"Lyle"}}
```



#### 1.2 signup

##### 接口说明

用户注册

##### 请求方式

POST

##### URL

```
/api/mall/user/signup
```

##### 请求参数

| 参数名    | 参数类型 | 是否必须 | 说明       |
| --------- | -------- | -------- | ---------- |
| username  | str      | 必须     | 用户名     |
| pwd       | str      | 必须     | 密码       |
| recipient | str      | 必须     | 收件人姓名 |
| address   | str      | 必须     | 收件人地址 |
| phone     | str      | 必须     | 手机号码   |

````
{
	"username": "test",
	"pwd": "Test!123",
	"recipient": "远志",
	"address": "王道",
	"phone": "13339996666"
}
````

##### 返回参数

| 参数名 | 参数类型 | 是否必须 | 说明                                 |
| ------ | -------- | -------- | ------------------------------------ |
| code   | integer  | 必须     | 返回状态码，0表示正常，10000表示错误 |
| data   | object   | 非必须   | 返回的数据                           |

```
{
	"code": 0,
	"data": {
		"code": 0,
		"username": "Lyle",
		"token": "Lyle"
	}
}
```



#### 1.3 getUserData

**接口说明**

获取用户信息

**请求方式**

GET

**URL**

```
/api/mall/user/data
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明             |
| ------ | -------- | -------- | ---------------- |
| token  | str      | 必须     | 用户账号username |

```json
?token=username
```

**返回参数**

| 参数名    | 参数类型 | 是否必须 | 说明     |
| --------- | -------- | -------- | -------- |
| code      | integer  | 必须     | 状态码   |
| id        | integer  | 必须     | 用户id   |
|           |          |          |          |
| username  | str      | 必须     | 昵称     |
| recipient | str      | 必须     | 收货人   |
| address   | str      | 必须     | 收货地址 |
| phone     | str      | 必须     | 手机号   |

```json
{
	"code": 0,
	"data": {
		"code": 0,
		"id": "1",
		"username": "admin",
		"recipient": "admin",
		"address": "admin",
		"phone": "11111111111"
	}
}
```



#### 1.4 updateUserData

**接口说明**

更新用户信息

**请求方式**

POST

**URL**

```
/api/mall/user/updateUserData
```

**请求参数**

| 参数名    | 参数类型 | 是否必须 | 说明     |
| --------- | -------- | -------- | -------- |
| id        | integer  | 必须     | 用户id   |
| username  | str      | 必须     | 用户昵称 |
| recipient | str      | 必须     | 收货人   |
| address   | str      | 必须     | 收货地址 |
| phone     | str      | 必须     | 手机号   |

```json
{id: 5, username: "test", recipient: "远志", address: "王道", phone: "13339996666"}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{"code":1000,"message":"你不能修改admin账号!"}
{"code":0}
```



#### 1.5 updatePwd

**接口说明**

更改用户密码

**请求方式**

POST

**URL**

```
/api/user/user/updatePwd
```

**请求参数**

| 参数名     | 参数类型 | 是否必须 | 说明     |
| ---------- | -------- | -------- | -------- |
| id         | integer  | 必须     | 用户id   |
| oldPwd     | str      | 必须     | 旧密码   |
| newPwd     | str      | 必须     | 新密码   |
| confirmPwd | str      | 必须     | 确认密码 |

```json
{id: "63", oldPwd: "Test!123", newPwd: "Test!1234", confirmPwd: "Test!1234"}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | code     | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{"code":0}
{"code":10000,"message":"旧密码不正确!"}
```



### 2. 管理员模块

#### 2.1 login

**接口说明**

登录后台管理系统

**请求方式**

POST

**URL**

```html
/api/admin/admin/login
```

**请求参数**

| 参数名   | 参数类型 | 是否必须 | 说明   |
| -------- | -------- | -------- | ------ |
| username | str      | 必须     | 用户名 |
| pwd      | str      | 必须     | 密码   |

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明                                 |
| ------- | -------- | -------- | ------------------------------------ |
| code    | integer  | 必须     | 返回状态码，0表示正常，10000表示错误 |
| data    | object   | 非必须   | 返回的数据                           |
| message | str      | 非必须   | 错误信息                             |

```
// error
{"code":10000,"message":"密码不正确!"}
// success
{"code":0,"data":{"token":"lyle","name":"lyle"}}
```



#### 2.2 changePassword

**接口说明**

修改当前登录用户的密码

**请求方式**

POST

**URL**

```
/api/admin/admin/changePwd
```

**请求参数**

| 参数名     | 参数类型 | 是否必须 | 说明          |
| ---------- | -------- | -------- | ------------- |
| adminToken | str      | 必须     | token，同昵称 |
| oldPwd     | str      | 必须     | 旧密码        |
| newPwd     | str      | 必须     | 新密码        |
| confirmPwd | str      | 必须     | 确认新密码    |

```json
{adminToken: "Lyle", oldPwd: "123", newPwd: "1234", confirmPwd: "1234"}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误原因 |

```json
// error1
{code: 10000, message: "旧密码错误!"}
// error2
{code: 10000, message: "请保持确认新密码一致!"}
// success
{code: 0}
```



#### 2.3 getAllAdmins

**接口说明**

获取所有管理员

**请求方式**

GET

**URL**

```html
/api/admin/admin/allAdmins
```

**请求参数**

空

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明                  |
| ------ | -------- | -------- | --------------------- |
| code   | integer  | 必须     | 返回状态码，0表示正常 |
| data   | list     | 必须     | 返回的管理员列表      |

```json
{"code":0,
 "data":[
     {"id":1,"username":"Lyle","pwd":"1234"},
     {"id":269,"username":"chen","pwd":"1234"}]
}
```



#### 2.4 getSearchAdmins

**接口说明**

通过管理员账户和昵称进行模糊查找

**请求方式**

POST

**URL**

```
/api/admin/admin/getSearchAdmins
```

**请求参数**

| 参数名   | 参数类型 | 是否必须 | 说明 |
| -------- | -------- | -------- | ---- |
|          |          |          |      |
| username | str      | 必须     | 昵称 |

```json
{username: "lyle"}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明       |
| ------ | -------- | -------- | ---------- |
| code   | integer  | 必须     | 状态码     |
| data   | list     | 必须     | 管理员列表 |

```json
{"code":0,"data":[{"id":1,"username":"lyle","pwd":"1234"}]}
```



#### 2.5 deleteAdmins

**接口说明**

删除一个管理员

**请求方式**

GET

**URL**

```
/api/admin/admin/deleteAdmins
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| id     | integet  | 必须     | 管理员id |

```html
?id=7
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误原因 |

```html
// success
{"code":0}
```

**注意：这里要求返回的“code”是Json数据中的一个属性，一定要带上**

**后端端口为8085，前端端口为8080**



### 1. 用户模块

#### 1.1 logoutClient

**接口说明**

退出登录

**请求方式**

POST

**URL**

```
/api/mall/user/logoutClient
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明 |
| ------ | -------- | -------- | ---- |
| token  | str      | 必须     | 昵称 |

```json
{token: "admin"}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{"code":0}
```



### 2. 商品模块

#### 2.1 getTypes

**接口说明**

获取所有商品类型

**请求方式**

GET

**URL**

```
/api/mall/index/getType
```

**请求参数**

空

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 类型列表 |

```json
{
	"code": 0,
	"data": [{
		"id": 1,
		"name": "女装"
	}, {
		"id": 190,
		"name": "男装"
	}, {
		"id": 191,
		"name": "电子产品"
	}]
}
```

#### 2.2 getGoodsByType

**接口说明**

根据类型获取商品

**请求方式**

GET

**URL**

```
api/mall/goods/getGoodsByType
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明       |
| ------ | -------- | -------- | ---------- |
| typeId | integer  | 必须     | 商品类型id |

```json
?typeId=-1
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 商品列表 |

```json
{
	"code": 0,
	"data": [{
		"id": 476,
		"img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
		"name": "西服套装的",
		"price": 13.0,
		"typeId": 223,
		"stockNum": 2622
	}, {
		"id": 478,
		"img": "http://115.29.141.32:8084/static/image/157123055832115669931721908195203.jpg",
		"name": "新品外套",
		"price": 200.0,
		"typeId": 1,
		"stockNum": 3186
	}, 
    ...
    {
		"id": 568,
		"img": "http://115.29.141.32:8084",
		"name": "",
		"price": 1.0,
		"typeId": 1,
		"stockNum": 1
	}]
}
```

**注：typeId=-1表示所有**

**商品属性有id,img,name,price,typeId,stockNum**



#### 2.3 searchGoods

**接口说明**

搜索商品

**请求方式**

GET

**URL**

```
/api/mall/goods/searchGoods
```

**请求参数**

| 参数名  | 参数类型 | 是否必须 | 说明   |
| ------- | -------- | -------- | ------ |
| keyword | str      | 必须     | 关键字 |

```json
?keyword=12
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 商品列表 |

```json
{
	"code": 0,
	"data": [{
		"id": 546,
		"img": "http://115.29.141.32:8084/static/image/1618838668103gaitubao_2_jpg.jpg",
		"name": "iphone 12",
		"price": 7699.0,
		"typeId": 220
	}]
}
```

#### 2.3.3 getGoodsInfo

**接口说明**

获取商品信息

**请求方式**

GET

**URL**

```
/api/mall/goods/getGoodsInfo
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 商品id |

```json
?id=2
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | object   | 必须     | 商品信息 |

```json
{
	"code": 0,
	"data": {
        "id":121,
		"img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
		"name": "西服套装的",
		"desc": "新品特卖",
		"typeId": 223,
		"price": 12
	}
}
```

#### 2.4 addOrder

**接口说明**

增加订单

**请求方式**

POST

**URL**

```
/api/mall/order/addOrder
```

**请求参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| token   | str      | 必须     | 用户昵称 |
| goodsId | integer  | 必须     | 商品id   |
| state   | integer  | 必须     | 订单状态 |
| num     | integer  | 必须     | 商品数量 |
| amount  | double   | 必须     | 总额     |

```json
{token: "Lyle", goodsDetailId: 1233, state: 0, num: 1, amount: 500}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



### 3. 管理员模块

#### 3.1 logoutAdmin

**接口说明**

退出登录

**请求方式**

POST

**URL**

```json
api/admin/admin/logoutAdmin
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明  |
| ------ | -------- | -------- | ----- |
| token  | str      | 必须     | token |

```json
{token: "Lyle"}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



#### 3.2 addAdminss

**接口说明**

增加一个管理员

**请求方式**

POST

**URL**

```html
api/admin/admin/addAdminss
```

**请求参数**

| 参数名   | 参数类型 | 是否必须 | 说明       |
| -------- | -------- | -------- | ---------- |
| username | str      | 必须     | 管理员昵称 |
|          |          |          |            |
| pwd      | str      | 必须     | 密码       |

```json
{username: "fasdf",pwd: "Zzzzzz456!"}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明                     |
| ------- | -------- | -------- | ------------------------ |
| code    | integer  | 必须     | 状态码，0成功，10000异常 |
| message | str      | 非必须   | 错误信息                 |

```json
// success
{"code":0}
// error
{"code":10000,"message":"该账号不允许重复使用"}
// error2
{"code":10000,"message":"该用户名不允许重复使用"}
```



#### 3.3 updateAdminss

**接口说明**

更新管理员信息

**请求方式**

POST

**URL**

```
/api/admin/admin/updateAdminss
```

**请求参数**

| 参数名   | 参数类型 | 是否必须 | 说明     |
| -------- | -------- | -------- | -------- |
|          |          |          |          |
| id       | integer  | 必须     | 管理员id |
| username | str      | 必须     | 昵称     |
| pwd      | str      | 必须     | 密码     |

```json
{id: 12, username: "testadmin1",pwd: "Test!1234567"}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
// success
{"code":0}
// error1
{"code":10000,"message":"该账号不允许重复使用"}
// error2
{"code":10000,"message":"该用户名不允许重复使用"}
```



#### 3.4 getAdminsInfo

**接口说明**

获取管理员信息

**请求方式**

GET

**URL**

```
/api/admin/admin/getAdminsInfo
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| id     | integer  | 必须     | 管理员id |

```
?id=11
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | object   | 必须     | 用户信息 |

```json
{"code":0,"data":{"id":11,"username":"TestUser","pwd":"AAAaaa555%%%"}}
```



#### 3.5 getTypes

**注：这里和商品模块使用的gettypeURL不是同一个**

**接口说明**

获取所有商品类型

**请求方式**

GET

**URL**

```
/api/admin/goods/getType
```

**请求参数**

空

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 类型列表 |

```json
{
	"code": 0,
	"data": [{
		"id": 1,
		"name": "女装"
	}, {
		"id": 190,
		"name": "男装"
	}, {
		"id": 191,
		"name": "电子产品"
	}]
}
```

#### 

#### 3.6 addGoods

**接口说明**

增加商品

**请求方式**

POST

**URL**

```
/api/admin/goods/addGoods
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| name   | str      | 必须     | 商品名称 |
| typeId | integer  | 必须     | 类型id   |
| img    | str      | 必须     | 商品图片 |
| desc   | str      | 必须     | 商品详情 |
|        |          |          |          |

```json
{
	"name": "goodTest",
	"typeId": "204",
	"img": "/static/image/1618728328596v2-a38254d921005809b8faaa2cceb638cf_r.jpg",
	"desc": "商品详情属性",
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```

#### 3.7 updateGoods

**接口说明**

更新商品信息

**请求方式**

POST

**URL**

```html
/api/admin/goods/updateGoods
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| id     | integer  | 必须     | 商品id   |
| name   | str      | 必须     | 商品名称 |
| typeId | integer  | 必须     | 类型id   |
| img    | str      | 必须     | 图片url  |
| desc   | str      | 必须     | 商品详情 |
|        |          |          |          |

```json
{
	"id": "505",
	"name": "goodTest",
	"typeId": 204,
	"img": "http://115.29.141.32:8084/static/image/1618728328596v2-a38254d921005809b8faaa2cceb638cf_r.jpg",
	"desc": "商品详情属性",
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```

#### 3.8 getGoodsInfo

**接口说明**

通过id获取某个商品详细信息

**请求方式**

GET

**URL**

```
/api/admin/goods/getGoodsInfo
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 商品id |

```
?id=505
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | object   | 必须     | 商品详情 |

```json
{
	"code": 0,
	"data": {
			"id": 505,
			"img": "http://115.29.141.32:8084/static/image/1618728328596v2-a38254d921005809b8faaa2cceb638cf_r.jpg",
			"name": "goodTest",
			"desc": "商品详情属性",
			"typeId": 204,
			"price": 10.0
	}
}
```



### 4. 管理员后台订单状态

#### 4.1 getOrder

**接口说明**

获取订单信息

**请求方式**

GET

**URL**

```
/api/admin/order/order
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 商品id |

```
?id=1770
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| id      | integer  | 必须     | 订单id   |
| amount  | double   | 必须     | 总价     |
| num     | integer  | 必须     | 数量     |
| goodsId | integer  | 必须     | 商品id   |
|         |          |          |          |
| name    | str      | 必须     | 商品名称 |
|         |          |          |          |
|         |          |          |          |
|         |          |          |          |
|         |          |          |          |

```json
{
	"code": 0,
	"data": {
		"id": 1782,
		"amount": 80.0,
		"num": 1,
		"goodsId": 1258,
		"name": "老年手机",
	}
}
```



#### 4.2 changeOrder

**接口说明**

更改订单状态

**请求方式**

POST

**URL**

```
/api/admin/order/changeOrder
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 订单id |
|        |          |          |        |
|        |          |          |        |
| num    | integer  | 必须     | 数量   |

```json
{id: "1782",num: 1}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



### 5.管理员后台用户操作页面

### 1.2 用户管理模块（/api/admin/user/*）

#### 5.1 allUser

**接口说明**

获取所有用户

**请求方式**

GET

**URL**

```
/api/admin/user/allUser
```

**请求参数**

空

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 用户信息 |

```json
{"code":0,"data":[{"id":1,"username":"admin","pwd":"admin","recipient":"admin","address":"admin","phone":"11111111111"},{"id":4,"username":"test","pwd":"Test!123qqqq","recipient":"旺旺网","address":"武汉","phone":"15555555555"}]}
```



#### 5.2 searchUser

**接口说明**

通过用户昵称模糊查找

**请求方式**

GET

**URL**

```
/api/admin/user/searchUser
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| word   | str      | 必须     | 关键字 |

```json
?word='a'
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 用户列表 |

```json
{"code":0,"data":[{"id":1,"username":"admin","pwd":"admin","recipient":"admin","address":"admin","phone":"11111111111"}]}
```



#### 5.3 deleteUser

**接口说明**

删除指定用户

**请求方式**

GET

**URL**

```
/api/admin/user/deleteUser
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 用户id |

```
?id=1
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 必须     | 错误信息 |

```json
{"code":0}
```

