# 在线商城接口文档

[toc]

## 1. 后台管理系统（/api/admin）

### 1.1 管理员账号模块（/api/admin/admin/*）

#### 1.1.1 login

**接口说明**

登录后台管理系统

**请求方式**

POST

**URL**

```html
/api/admin/admin/login
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| email  | str      | 必须     | 用户名 |
| pwd    | str      | 必须     | 密码   |

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
{"code":0,"data":{"token":"admin","name":"admin"}}
```

1

#### 1.1.2 allAdmins

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
     {"id":1,"email":"admin","nickname":"admin","pwd":"admin"},
     {"id":269,"email":"1@qq.com","nickname":"chen","pwd":"chen"}]
}
```



#### 1.1.3 addAdminss

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
| nickname | str      | 必须     | 管理员昵称 |
| email    | str      | 必须     | 管理员邮箱 |
| pwd      | str      | 必须     | 密码       |

```json
{nickname: "fasdf", email: "19989627@qq.com", pwd: "Zzzzzz456!"}
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



#### 1.1.4 deleteAdmins

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



#### 1.1.5 getAdminsInfo

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
{"code":0,"data":{"id":11,"email":"22332233@qq.com","nickname":"TestUser","pwd":"AAAaaa555%%%"}}
```



#### 1.1.6 updateAdminss

**接口说明**

更新管理员信息

**请求方式**

POST

**URL**

```
/api/admin/admin/updateAdminss
```

**请求参数**

| 参数名   | 参数类型 | 是否必须 | 说明       |
| -------- | -------- | -------- | ---------- |
| email    | str      | 必须     | 管理员邮箱 |
| id       | integer  | 必须     | 管理员id   |
| nickname | str      | 必须     | 昵称       |
| pwd      | str      | 必须     | 密码       |

```json
{id: 12, nickname: "testadmin1", email: "12345677777@qq.com", pwd: "Test!1234567"}
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



#### 1.1.7 changePwd

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
{adminToken: "admin", oldPwd: "admin", newPwd: "admin", confirmPwd: "admin123"}
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



#### 1.1.8 getSearchAdmins

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
| email    | str      | 必须     | 邮箱 |
| nickname | str      | 必须     | 昵称 |

```json
{nickname: "b", email: "a"}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明       |
| ------ | -------- | -------- | ---------- |
| code   | integer  | 必须     | 状态码     |
| data   | list     | 必须     | 管理员列表 |

```json
{"code":0,"data":[{"id":1,"email":"admin","nickname":"admin","pwd":"admin"}]}
```



#### 1.1.9 logoutAdmin

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
{token: "admin"}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



### 1.2 用户管理模块（/api/admin/user/*）

#### 1.2.1 allUser

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
{"code":0,"data":[{"id":1,"email":"admin","nickname":"admin","pwd":"admin","recipient":"admin","address":"admin","phone":"11111111111"},{"id":4,"email":"666229527@qq.com","nickname":"test","pwd":"Test!123qqqq","recipient":"旺旺网","address":"武汉","phone":"15555555555"}]}
```



#### 1.2.2 deleteUser

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



#### 1.2.3 searchUser

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
{"code":0,"data":[{"id":1,"email":"admin","nickname":"admin","pwd":"admin","recipient":"admin","address":"admin","phone":"11111111111"}]}
```



### 1.3 商品管理模块（/api/admin/goods/*）

#### 1.3.1 getType

**接口说明**

获取所有商品类型

**请求方式**

GET

**URL**

```
api/admin/goods/getType
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



#### 1.3.2 getGoodsByType

**接口说明**

获取指定类型下的所有商品

**请求方式**

GET

**URL**

```html
/api/admin/goods/getGoodsByType
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| typeId | integer  | 必须     | 商品类型 |

```
?typeId=1
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
		"img": "http://115.29.141.32:8084/static/image/15712302279071567429968611lo.jpg",
		"name": "西服套装",
		"price": 500.0,
		"typeId": 1,
		"stockNum": 2956
	}, {
		"id": 477,
		"img": "http://115.29.141.32:8084/static/image/157123045512415669928366681566992814(1).jpg",
		"name": "外套",
		"price": 300.0,
		"typeId": 1,
		"stockNum": 2997
	}]
}
```



#### 1.3.3 addType

**接口说明**

增加商品类型

**请求方式**

POST

**URL**

```
/api/admin/goods/addType
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| name   | str      | 必须     | 类型名称 |

```
{name: "test"}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
// success
{"code":0}
// error
{"code":10000,"message":"该种类已经存在!"}
```



#### 1.3.4 deleteType

**接口说明**

删除指定商品类目及相关信息

**请求方式**

GET

**URL**

```
/api/admin/goods/deleteType
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| typeId | integer  | 必须     | 类型id |

```
?typeId=205
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



#### 1.3.5 imgUpload

**接口说明**

图片上传

**请求方式**

POST

**URL**

```
/api/admin/goods/imgUpload
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明 |
| ------ | -------- | -------- | ---- |
| file   | file     | 必须     | 图片 |

```
file: (binary)
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明    |
| ------ | -------- | -------- | ------- |
| code   | integer  | 必须     | 状态码  |
| data   | str      | 必须     | 图片url |

```json
{
	"code": 0,
	"data": "/static/image/1618728328596v2-a38254d921005809b8faaa2cceb638cf_r.jpg"
}
```



#### 1.3.6 addGoods

**接口说明**

增加商品

**请求方式**

POST

**URL**

```
/api/admin/goods/addGoods
```

**请求参数**

| 参数名   | 参数类型 | 是否必须 | 说明     |
| -------- | -------- | -------- | -------- |
| name     | str      | 必须     | 商品名称 |
| typeId   | integer  | 必须     | 类型id   |
| img      | str      | 必须     | 商品图片 |
| desc     | str      | 必须     | 商品详情 |
| specList | list     | 必须     | 规格表   |

```json
{
	"name": "goodTest",
	"typeId": "204",
	"img": "/static/image/1618728328596v2-a38254d921005809b8faaa2cceb638cf_r.jpg",
	"desc": "商品详情属性",
	"specList": [{
		"specName": "testSpec",
		"stockNum": "1",
		"unitPrice": "1"
	}, {
		"specName": "testSpec1",
		"stockNum": "22",
		"unitPrice": "123"
	}]
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```





#### 1.3.7 deleteGoods

**接口说明**

删除商品

**请求方式**

GET

**URL**

```html
/api/admin/goods/deleteGoods
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 商品id |

```html
?id=506
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



#### 1.3.8 getGoodsInfo

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
		"specs": [{
			"id": 1305,
			"specName": "testSpec",
			"stockNum": 1,
			"unitPrice": 1.0
		}, {
			"id": 1306,
			"specName": "testSpec1",
			"stockNum": 22,
			"unitPrice": 123.0
		}],
		"goods": {
			"id": 505,
			"img": "http://115.29.141.32:8084/static/image/1618728328596v2-a38254d921005809b8faaa2cceb638cf_r.jpg",
			"name": "goodTest",
			"desc": "商品详情属性",
			"typeId": 204,
			"unitPrice": 0.0
		}
	}
}
```



#### 1.3.9 updateGoods

**接口说明**

更新商品信息

**请求方式**

POST

**URL**

```html
/api/admin/goods/updateGoods
```

**请求参数**

| 参数名   | 参数类型 | 是否必须 | 说明     |
| -------- | -------- | -------- | -------- |
| id       | integer  | 必须     | 商品id   |
| name     | str      | 必须     | 商品名称 |
| typeId   | integer  | 必须     | 类型id   |
| img      | str      | 必须     | 图片url  |
| desc     | str      | 必须     | 商品详情 |
| specList | list     | 必须     | 规格表   |

```json
{
	"id": "505",
	"name": "goodTest",
	"typeId": 204,
	"img": "http://115.29.141.32:8084/static/image/1618728328596v2-a38254d921005809b8faaa2cceb638cf_r.jpg",
	"desc": "商品详情属性",
	"specList": [{
		"id": 1305,
		"specName": "testSpec",
		"stockNum": 1,
		"unitPrice": 1
	}, {
		"id": 1306,
		"specName": "testSpec1",
		"stockNum": 22,
		"unitPrice": 123
	}]
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



#### 1.3.10 addSpec

**接口说明**

增加商品规格

**请求方式**

POST

**URL**

```
/api/admin/goods/addSpec
```

**请求参数**

| 参数名    | 参数类型 | 是否必须 | 说明     |
| --------- | -------- | -------- | -------- |
| goodsId   | integer  | 必须     | 商品id   |
| specName  | str      | 必须     | 规格名称 |
| stockNum  | integer  | 必须     | 库存     |
| unitPrice | integer  | 必须     | 单价     |

```json
{goodsId: "476", specName: "k", stockNum: "13", unitPrice: "13"}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | object   | 必须     | 规格信息 |

```json
// error
{"code":10000,"message":"分类重复"}
// success
{"code":0,"data":{"goodsId":"476","specName":"ks","stockNum":"13","unitPrice":"13"}}
```



#### 1.3.11 deleteSpec

**接口说明**

删除商品规格

**请求方式**

POST

**URL**

```
/api/admin/goods/deleteSpec
```

**请求参数**

| 参数名   | 参数类型 | 是否必须 | 说明     |
| -------- | -------- | -------- | -------- |
| goodsId  | integer  | 必须     | 商品id   |
| specName | str      | 必须     | 规格名称 |

```json
{goodsId: "477", specName: "xl"}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{code: 0}
```



#### 1.3.12 noReplyMsg

**接口说明**

未回复的留言

**请求方式**

GET

**URL**

```
/api/admin/goods/noReplyMsg
```

**请求参数**

空

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明         |
| ------ | -------- | -------- | ------------ |
| code   | integer  | 必须     | 状态码       |
| data   | list     | 必须     | 未回复留言表 |

```json
{
	"code": 0,
	"data": [{
		"id": 307,
		"userId": 1,
		"goodsId": 507,
		"content": "测试",
		"state": 1,
		"createtime": "2021-04-19 10:02:19",
		"goods": {
			"name": "卷力增强卡"
		},
		"user": {
			"name": "admin"
		}
	}, {
		"id": 309,
		"userId": 1,
		"goodsId": 507,
		"content": "测试3",
		"state": 1,
		"createtime": "2021-04-19 11:05:20",
		"goods": {
			"name": "卷力增强卡"
		},
		"user": {
			"name": "admin"
		}
	}]
}
```



#### 1.3.13 repliedMsg

**接口说明**

已回复的留言

**请求方式**

GET

**URL**

```
/api/admin/goods/repliedMsg
```

**请求参数**

空

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明         |
| ------ | -------- | -------- | ------------ |
| code   | integer  | 必须     | 状态码       |
| data   | list     | 必须     | 已回复留言表 |

```json
{
	"code": 0,
	"data": [{
		"id": 308,
		"userId": 1,
		"goodsId": 507,
		"content": "测试2",
		"replyContent": "已回复",
		"state": 0,
		"createtime": "2021-04-19 11:02:43",
		"goods": {
			"name": "卷力增强卡"
		},
		"user": {
			"name": "admin"
		}
	}]
}
```



#### 1.3.14 reply

**接口说明**

回复

**请求方式**

POST

**URL**

```
/api/admin/goods/reply
```

**请求参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| id      | integer  | 必须     | 留言id   |
| content | str      | 必须     | 回复内容 |

```json
{id: 307, content: "回复测试"}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



### 1.4 订单管理模块（/api/admin/order/*）

#### 1.4.1 ordersByPage

**接口说明**

获取订单列表

**请求方式**

POST

**URL**

```
/api/admin/order/ordersByPage
```

**请求参数**

| 参数名      | 参数类型 | 是否必须 | 说明     |
| ----------- | -------- | -------- | -------- |
| state       | integer  | 必须     | 订单状态 |
| currentPage | integer  | 必须     | 当前页   |
| pagesize    | integer  | 必须     | 页大小   |
| moneyLimit1 | str      | 必须     | 金额下限 |
| moneyLimit2 | str      | 必须     | 金额上限 |
| goods       | str      | 必须     | 商品名称 |
| address     | str      | 必须     | 地址     |
| name        | str      | 必须     | 用户昵称 |
| id          | str      | 必须     | 订单id   |

```json
{
	"state": -1,
	"currentPage": 1,
	"pagesize": 5,
	"moneyLimit1": "100",
	"moneyLimit2": "500",
	"goods": "a",
	"address": "花城大道",
	"name": "admin",
	"id": "123"
}
```

**返回参数**

| 参数名        | 参数类型 | 是否必须 | 说明       |
| ------------- | -------- | -------- | ---------- |
| code          | integer  | 必须     | 状态码     |
| total         | integer  | 必须     | 总数       |
| id            | integer  | 必须     | 订单id     |
| userId        | integer  | 必须     | 用户id     |
| goodsDetailId | integer  | 必须     | 商品详情id |
| goods         | str      | 必须     | 商品名称   |
| spec          | str      | 必须     | 规格名称   |
| goodsNum      | integer  | 必须     | 商品数量   |
| amount        | double   | 必须     | 总价格     |
| stateId       | integer  | 必须     | 订单状态id |
| state         | str      | 必须     | 订单状态   |
| nickname      | str      | 必须     | 用户昵称   |
| name          | str      | 必须     | 收件人     |
| address       | str      | 必须     | 地址       |
| phone         | str      | 必须     | 手机号     |

```json
{
	"code": 0,
	"data": {
		"total": 2,
		"orders": [{
			"id": 1773,
			"userId": 1,
			"goodsDetailId": 1248,
			"goods": "摄像机",
			"spec": "型号一",
			"goodsNum": 1,
			"amount": 1000.0,
			"stateId": 3,
			"state": "已到货",
			"user": {
				"nickname": "admin",
				"name": "admin",
				"address": "admin",
				"phone": "11111111111"
			}
		}, {
			"id": 1770,
			"userId": 1,
			"goodsDetailId": 1235,
			"goods": "外套",
			"spec": "x",
			"goodsNum": 1,
			"amount": 300.0,
			"stateId": 1,
			"state": "未发货",
			"user": {
				"nickname": "admin",
				"name": "admin",
				"address": "admin",
				"phone": "11111111111"
			}
		}]
	}
}
```



#### 1.4.2 order

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

| 参数名        | 参数类型 | 是否必须 | 说明         |
| ------------- | -------- | -------- | ------------ |
| code          | integer  | 必须     | 状态码       |
| id            | integer  | 必须     | 订单id       |
| amount        | double   | 必须     | 总价         |
| num           | integer  | 必须     | 数量         |
| goodsDetailId | integer  | 必须     | 商品细节id   |
| state         | integer  | 必须     | 订单状态     |
| goods         | str      | 必须     | 商品名称     |
| spec          | list     | 必须     | 规格表       |
| states        | list     | 必须     | 订单状态表   |
| curState      | object   | 必须     | 当前订单状态 |
| curSpec       | object   | 必须     | 当前规格     |

```json
{
	"code": 0,
	"data": {
		"id": 1782,
		"amount": 80.0,
		"num": 1,
		"goodsDetailId": 1258,
		"state": 0,
		"goods": "老年手机",
		"spec": [{
			"id": 1257,
			"specName": "黑色",
			"unitPrice": 0.0
		}, {
			"id": 1258,
			"specName": "白色",
			"unitPrice": 0.0
		}, {
			"id": 1259,
			"specName": "紫色",
			"unitPrice": 0.0
		}],
		"states": [{
			"id": 0,
			"name": "未付款"
		}, {
			"id": 1,
			"name": "未发货"
		}, {
			"id": 2,
			"name": "已发货"
		}, {
			"id": 3,
			"name": "已完成订单"
		}],
		"curState": {
			"id": 0
		},
		"curSpec": {
			"id": 1258
		}
	}
}
```



#### 1.4.1 changeOrder

**接口说明**

更改订单状态

**请求方式**

POST

**URL**

```
/api/admin/order/changeOrder
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| id     | integer  | 必须     | 订单id   |
| state  | integer  | 必须     | 订单状态 |
| spec   | integer  | 必须     | 规格     |
| num    | integer  | 必须     | 数量     |

```json
{id: "1782", state: 2, spec: 1258, num: 1}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



#### 1.4.1 deleteOrder

**接口说明**

删除订单

**请求方式**

GET

**URL**

```
/api/admin/order/deleteOrder
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 订单id |

```
?id=1778
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



## 2. 商城前台（/api/mall）

### 2.1 首页模块（/api/mall/index/*）

#### 2.1.1 getType

**接口说明**

获得商品类目

**请求方式**

GET

**URL**

```
/api/mall/index/getType
```

**请求参数**

无

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



### 2.2 用户账号模块（/api/mall/user/*）

#### 2.2.1 login

**接口说明**

登录后台管理系统

**请求方式**

POST

**URL**

```html
/api/mall/user/login
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| email  | str      | 必须     | 用户名 |
| pwd    | str      | 必须     | 密码   |

```
{email: "admin", pwd: "admin"}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明                                 |
| ------- | -------- | -------- | ------------------------------------ |
| code    | integer  | 必须     | 返回状态码，0表示正常，10000表示错误 |
| data    | object   | 非必须   | 返回的数据                           |
| message | str      | 非必须   | 错误信息                             |

```json
// error
{"code":10000,"message":"密码不正确!"}
// success
{"code":0,"data":{"token":"admin","name":"admin"}}
```



#### 2.2.2 signup

**接口说明**

注册用户

**请求方式**

POST

**URL**

```
/api/mall/user/signup
```

**请求参数**

| 参数名    | 参数类型 | 是否必须 | 说明     |
| --------- | -------- | -------- | -------- |
| email     | str      | 必须     | 邮箱     |
| nickname  | str      | 必须     | 昵称     |
| pwd       | str      | 必须     | 密码     |
| recipient | str      | 必须     | 收货人   |
| address   | str      | 必须     | 收货地址 |
| phone     | str      | 必须     | 手机号   |

```json
{
	"email": "1234567@qq.com",
	"nickname": "test",
	"pwd": "Test!123",
	"recipient": "远志",
	"address": "王道",
	"phone": "13339996666"
}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | object   | 必须     | 登录回执 |

```json
{
	"code": 0,
	"data": {
		"code": 0,
		"name": "test",
		"token": "test"
	}
}
```



#### 2.2.1 data

**接口说明**

获取用户信息

**请求方式**

GET

**URL**

```
/api/mall/user/data
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| token  | str      | 必须     | 用户昵称 |

```json
?token=test
```

**返回参数**

| 参数名    | 参数类型 | 是否必须 | 说明     |
| --------- | -------- | -------- | -------- |
| code      | integer  | 必须     | 状态码   |
| id        | integer  | 必须     | 用户id   |
| email     | str      | 必须     | 邮箱     |
| nickname  | str      | 必须     | 昵称     |
| recipient | str      | 必须     | 收货人   |
| address   | str      | 必须     | 收货地址 |
| phone     | str      | 必须     | 手机号   |

```json
{
	"code": 0,
	"data": {
		"code": 0,
		"id": "1",
		"email": "admin",
		"nickname": "admin",
		"recipient": "admin",
		"address": "admin",
		"phone": "11111111111"
	}
}
```



#### 2.2.1 updateUserData

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
| nickname  | str      | 必须     | 用户昵称 |
| recipient | str      | 必须     | 收货人   |
| address   | str      | 必须     | 收货地址 |
| phone     | str      | 必须     | 手机号   |

```json
{id: 5, nickname: "test", recipient: "远志", address: "王道", phone: "13339996666"}
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



#### 2.2.1 updatePwd

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



#### 2.2.1 logoutClient

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



### 2.3 商品模块（api/mall/goods/*）

#### 2.3.1 getGoodsByType

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



#### 2.3.1 searchGoods

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

| 参数名 | 参数类型 | 是否必须 | 说明         |
| ------ | -------- | -------- | ------------ |
| code   | integer  | 必须     | 状态码       |
| data   | object   | 必须     | 商品信息     |
| img    | str      | 必须     | 商品图片     |
| name   | str      | 必须     | 商品名称     |
| desc   | str      | 必须     | 商品描述     |
| typeId | integer  | 必须     | 商品类型id   |
| specs  | list     | 必须     | 商品规格列表 |

```json
{
	"code": 0,
	"data": {
		"img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
		"name": "西服套装的",
		"desc": "新品特卖",
		"typeId": 223,
		"specs": [{
			"id": 1233,
			"specName": "l",
			"stockNum": 998,
			"unitPrice": 500.0
		}, {
			"id": 1234,
			"specName": "xl",
			"stockNum": 999,
			"unitPrice": 500.0
		}, {
			"id": 1323,
			"specName": "k",
			"stockNum": 13,
			"unitPrice": 13.0
		}, {
			"id": 1324,
			"specName": "ks",
			"stockNum": 13,
			"unitPrice": 13.0
		}, {
			"id": 1341,
			"specName": "s",
			"stockNum": 500,
			"unitPrice": 50.0
		}],
		"unitPrice": 0.0
	}
}
```



#### 2.3.4 getGoodsMsg

**接口说明**

获取商品留言

**请求方式**

GET

**URL**

```
/api/mall/goods/getGoodsMsg
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 商品id |

```json
?id=476
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明         |
| ------ | -------- | -------- | ------------ |
| code   | integer  | 必须     | 状态码       |
| data   | list     | 必须     | 商品问答列表 |

```json
{
	"code": 0,
	"data": [{
		"id": 311,
		"content": "666666666",
		"asker": "admin",
		"time": "2021-04-19 19:10:09",
		"reply": {
			"content": "111111",
			"time": "2021-04-19 19:54:27"
		}
	}, {
		"id": 312,
		"content": "666",
		"asker": "admin",
		"time": "2021-04-19 20:02:23",
		"reply": {
			"content": "很棒很胖胖\n",
			"time": "2021-04-19 20:03:23"
		}
	}, {
		"id": 313,
		"content": "6666666666",
		"asker": "admin",
		"time": "2021-04-19 20:11:00",
		"reply": {
			"content": "1111",
			"time": "2021-04-19 20:11:11"
		}
	}, {
		"id": 318,
		"content": "88888",
		"asker": "admin",
		"time": "2021-04-20 14:11:09",
		"reply": {}
	}]
}
```



#### 2.3.5 getGoodsComment

**接口说明**

获取商品评论

**请求方式**

GET

**URL**

```
/api/mall/goods/getGoodsComment
```

**请求参数**

| 参数名  | 参数类型 | 是否必须 | 说明   |
| ------- | -------- | -------- | ------ |
| goodsId | integer  | 必须     | 商品id |

```json
?goodsId=476
```

**返回参数**

| 参数名      | 参数类型 | 是否必须 | 说明     |
| ----------- | -------- | -------- | -------- |
| code        | integer  | 必须     | 状态码   |
| commentList | list     | 必须     | 评论列表 |
| rate        | double   | 必须     | 好评率   |

```json
{"code":0,"data":{"commentList":[],"rate":"NaN"}}
// success
{
	"code": 0,
	"data": {
		"commentList": [{
			"user": {
				"nickname": "admin"
			},
			"score": 60.0,
			"id": 109,
			"specName": "型号一",
			"comment": "asdaasdas",
			"time": "2021-04-19 21:51:21",
			"userId": 1
		}],
		"rate": 0.0
	}
}
```



#### 2.3.6 askGoodsMsg

**接口说明**

留言

**请求方式**

POST

**URL**

```
/api/mall/goods/askGoodsMsg
```

**请求参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| token   | str      | 必须     | 邮箱     |
| msg     | str      | 必须     | 商品留言 |
| goodsId | integer  | 必须     | 商品id   |

```json
{"token":"test","msg":"hha ","goodsId":"476"}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



### 2.4 订单模块（/api/mall/order/*）

#### 2.4.1 addOrder

**接口说明**

增加订单

**请求方式**

POST

**URL**

```
/api/mall/order/addOrder
```

**请求参数**

| 参数名        | 参数类型 | 是否必须 | 说明     |
| ------------- | -------- | -------- | -------- |
| token         | str      | 必须     | 用户昵称 |
| goodsDetailId | integer  | 必须     | 商品id   |
| state         | integer  | 必须     | 订单状态 |
| num           | integer  | 必须     | 商品数量 |
| amount        | double   | 必须     | 总额     |

```json
{token: "test", goodsDetailId: 1233, state: 0, num: 1, amount: 500}
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| code   | integer  | 必须     | 状态码 |

```json
{"code":0}
```



#### 2.4.2 getOrderByState

**接口说明**

根据订单状态获取订单

**请求方式**

GET

**URL**

```
/api/mall/order/getOrderByState
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| state  | integer  | 必须     | 订单状态 |
| token  | str      | 必须     | 用户昵称 |

```json
?state=0&token=test
```

**返回参数**

| 参数名 | 参数类型 | 是否必须 | 说明     |
| ------ | -------- | -------- | -------- |
| code   | integer  | 必须     | 状态码   |
| data   | list     | 必须     | 订单列表 |

```json
{
	"code": 0,
	"data": [{
		"id": 1811,
		"state": 0,
		"goodsNum": 1,
		"amount": 500.0,
		"goodsDetailId": 1233,
		"createtime": "2021-04-20 20:40:30",
		"hasComment": false,
		"goods": {
			"id": 476,
			"img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
			"name": "西服套装的",
			"goodsDetailId": 1233,
			"spec": "l",
			"unitPrice": 500.0
		}
	}, {
		"id": 1812,
		"state": 0,
		"goodsNum": 1,
		"amount": 500.0,
		"goodsDetailId": 1233,
		"createtime": "2021-04-20 20:40:32",
		"hasComment": false,
		"goods": {
			"id": 476,
			"img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
			"name": "西服套装的",
			"goodsDetailId": 1233,
			"spec": "l",
			"unitPrice": 500.0
		}
	}]
}
```



#### 2.4.3 deleteOrder

**接口说明**

删除订单

**请求方式**

GET

**URL**

```
/api/mall/order/deleteOrder
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 订单id |

```json
?id=8
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{"code":0}
```



#### 2.4.4 pay

**接口说明**

确认付款

**请求方式**

GET

**URL**

```
/api/mall/order/pay
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 订单id |

```html
?id=10
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{"code":0}
```



#### 2.4.5 settleAccounts

**接口说明**

处理购物车订单

**请求方式**

POST

**URL**

```
/api/mall/order/settleAccounts
```

**请求参数**

| 参数名   | 参数类型 | 是否必须 | 说明       |
| -------- | -------- | -------- | ---------- |
| cartList | list     | 必须     | 购物车列表 |

```json
{cartList: [{id: 10, goodsNum: 1, amount: 2}]}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{"code":0}
```



#### 2.4.6 confirmReceive

**接口说明**

确认收货

**请求方式**

GET

**URL**

```
/api/mall/order/confirmReceive
```

**请求参数**

| 参数名 | 参数类型 | 是否必须 | 说明   |
| ------ | -------- | -------- | ------ |
| id     | integer  | 必须     | 订单id |

```html
?id=10
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 非必须   | 错误信息 |

```json
{"code":0}
```



#### 2.4.7 sendComment

**接口说明**

评论商品

**请求方式**

POST

**URL**

```
/api/mall/order/sendComment
```

**请求参数**

| 参数名        | 参数类型 | 是否必须 | 说明     |
| ------------- | -------- | -------- | -------- |
| token         | str      | 必须     | 用户昵称 |
| orderId       | integer  | 必须     | 订单id   |
| goodsId       | integer  | 必须     | 商品id   |
| goodsDetailId | integer  | 必须     | 规格id   |
| content       | str      | 必须     | 评论内容 |
| score         | double   | 必须     | 评分     |

```json
{token: "test", orderId: 10, goodsId: 2, goodsDetailId: 12, content: "hahah 好", score: 100}
```

**返回参数**

| 参数名  | 参数类型 | 是否必须 | 说明     |
| ------- | -------- | -------- | -------- |
| code    | integer  | 必须     | 状态码   |
| message | str      | 必须     | 错误信息 |

```json
{"code":0}
```



































































































