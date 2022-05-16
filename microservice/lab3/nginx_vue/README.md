# 接口说明

## 运行

```
npm install
npm run dev
```

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
        "goodsDetailId": 1233,
        "spec": "l",
        "unitPrice": 500.0
	}, {
        "id": 2,
        "img": "http://115.29.141.32:8084/static/image/161sd54412.jpg",
        "name": "水杯",
        "goodsDetailId": 1234,
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
| token  | str      | 必须     | 用户名 |
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
| data   | list     | 必须     | 购物车列表 |

```json
{
	"code": 0,
	"data": [
		{
			"id": 476,
			"img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
			"name": "西服套装的",
			"goodsDetailId": 1233,
			"spec": "l",
			"unitPrice": 500.0,
			"goodsNum": 1
		},
		{
			"id": 476,
			"img": "http://115.29.141.32:8084/static/image/16188185954412.jpg",
			"name": "西服套装的",
			"goodsDetailId": 1233,
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
| token  | str      | 必须     | 用户名 |
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
| token  | str      | 必须     | 用户名 |
| itemId | integer  | 必须     | 商品id |
| itemId | integer  | 必须     | 新的商品数量 |

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
