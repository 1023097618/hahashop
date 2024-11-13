---
title: hahashop
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.23"

---

# hahashop

Base URLs:

# Authentication

# root/商品接口

## GET 获取商品信息

GET /api/good/list

此处goodState改为了goodNum
如果goodNum为0，则前端不可选中
goodImage以数组传回来，用于前端展示走马灯效果

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|pageSize|query|integer| 是 |返回10个商品数据|
|pageNum|query|integer| 是 |第1页|
|goodName|query|string| 否 |搜索这个名称的商品(可选)|
|categoryId|query|integer| 否 |商品类别|
|X-Hahashop-Token|header|string| 否 |证明这是商家，是商家是返回buyerNum，该商品现在有多少购买者|

#### 详细说明

**X-Hahashop-Token**: 证明这是商家，是商家是返回buyerNum，该商品现在有多少购买者
如果这是买家或者没有token，就不返回已售罄的商品

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "goods": [
      {
        "goodName": "比亚迪跑车",
        "goodPrice": "100 ￥",
        "goodId": 10086,
        "goodImage": [
          "http://localhost:8080/storage/fetch/sjhcc8pkpxu0ofoymwdu.png"
        ],
        "goodNum": 2,
        "buyerNum": 0,
        "categoryId": 10086
      }
    ],
    "totalGoods": 100
  },
  "msg": "查询商品成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||400表示查询成功<br />303表示查询失败|
|» data|object|true|none||none|
|»» goods|[object]|true|none||none|
|»»» goodName|string|true|none||商品名称|
|»»» goodPrice|string|true|none||用string传输主要考虑了货币的种类，比如100€   100$|
|»»» goodId|integer|true|none||此goodid会用在后续对后端的网络请求，在网络请求中对这个商品进行标识。<br />比如购买该商品时，前端会往后端传递这个id用于标识是这个商品|
|»»» goodImage|[string]|true|none||商品图片的url地址|
|»»» goodNum|integer|true|none||商品的数量|
|»»» buyerNum|integer|false|none||此商品购买者数量，在token校验为商家时返回给前端|
|»»» categoryId|integer|true|none||商品子种类id|
|»» totalGoods|integer|true|none||商品总数量，用于前端计算总共有多少页|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|msg|查询商品成功|
|msg|查询商品失败|

## GET 获取商品种类

GET /api/good/category

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "categoryList": [
      {
        "label": "电子设备",
        "value": 10086,
        "children": [
          {
            "label": null,
            "value": null
          }
        ]
      }
    ]
  },
  "msg": "查询商品成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||400表示查询成功<br />303表示查询失败|
|» data|object|true|none||none|
|»» categoryList|[object]|true|none||none|
|»»» label|string|true|none||none|
|»»» value|integer|true|none||none|
|»»» children|[object]|true|none||none|
|»»»» label|string|true|none||none|
|»»»» value|integer|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|msg|查询商品成功|
|msg|查询商品失败|

## GET 获取商品详细

GET /api/good/detail

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|integer| 是 |商品id|
|X-Hahashop-Token|header|string| 否 |token为买家或者卖家均可|

> 返回示例

```json
{
  "code": "400",
  "msg": "查询商品成功",
  "data": {
    "goodDesc": "这个商品物美价廉"
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|string|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» goodDesc|string|true|none||商品描述|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|303|
|code|201|
|msg|查询商品成功|
|msg|查询商品失败|

## POST 更新商品

POST /api/good/update

> Body 请求参数

```json
{
  "goodImage": [
    "string"
  ],
  "goodId": 0,
  "goodDesc": "string",
  "goodPrice": "string",
  "goodName": "string",
  "categoryId": 0,
  "goodNum": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|
|body|body|object| 否 |none|
|» goodImage|body|[string]| 是 |从storage/image返回的商品图片url组成的数组|
|» goodId|body|integer| 是 |商品id|
|» goodDesc|body|string| 是 |商品描述信息|
|» goodPrice|body|string| 是 |商品价格|
|» goodName|body|string| 是 |商品名称|
|» categoryId|body|integer| 是 |商品子种类id|
|» goodNum|body|integer| 是 |商品数量|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "购买成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|303|
|code|201|
|msg|购买成功|
|msg|购买失败，商品已经被冻结|
|msg|购买失败，填入的信息不合法|

## POST 添加商品

POST /api/good/add

> Body 请求参数

```json
{
  "goodImage": [
    "string"
  ],
  "goodDesc": "string",
  "goodPrice": "string",
  "goodName": "string",
  "categoryId": 0,
  "goodNum": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|
|body|body|object| 否 |none|
|» goodImage|body|[string]| 是 |从storage/image返回的商品图片url组成的数组|
|» goodDesc|body|string| 是 |商品描述信息|
|» goodPrice|body|string| 是 |商品价格|
|» goodName|body|string| 是 |商品名称|
|» categoryId|body|integer| 是 |商品子种类id|
|» goodNum|body|integer| 是 |商品数量|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "购买成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|msg|购买成功|
|msg|购买失败，商品已经被冻结|
|msg|购买失败，填入的信息不合法|

## DELETE 删除商品

DELETE /api/good/delete

> Body 请求参数

```json
{
  "goodId": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|
|body|body|object| 否 |none|
|» goodId|body|string| 是 |商品id|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "购买成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|303|
|msg|购买成功|
|msg|购买失败，商品已经被冻结|
|msg|购买失败，填入的信息不合法|

# root/验证用户信息接口

## POST 买家/卖家登录接口

POST /api/auth/login

> Body 请求参数

```json
{
  "username": "zhangsan",
  "password": "123456"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» username|body|string| 是 |用户名|
|» password|body|string| 是 |密码|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "token": "string"
  },
  "msg": "登录成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||400代表操作成功<br />301代表用户不存在<br />302代表用户密码错误|
|» data|object|true|none||none|
|»» token|string|true|none||根据jwt令牌生成的一长串字符串|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|301|
|code|302|
|msg|登录成功|
|msg|用户不存在|
|msg|用户密码错误|

## POST 买家注册接口

POST /api/auth/register

> Body 请求参数

```json
{
  "username": "zhangsan",
  "password": "123456",
  "userPhone": "string",
  "phoneCode": "string",
  "userAddress": "string",
  "userRealName": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» username|body|string| 是 |用户名|
|» password|body|string| 是 |密码|
|» userPhone|body|string| 是 |手机号|
|» phoneCode|body|string| 是 |手机号验证码，这边假装"1111"为正确的验证码|
|» userAddress|body|string| 是 |默认购买地点|
|» userRealName|body|string| 是 |用户真实姓名|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "登录成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||400代表注册成功<br />310代表用户已存在|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|304|
|msg|登录成功|
|msg|用户不存在|
|msg|用户密码错误|

## POST 买家/卖家修改密码

POST /api/auth/changePassword

用户的username从token中获得

> Body 请求参数

```json
{
  "oldPassword": "zhangsan",
  "newPassword": "123456"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|header|string| 是 |none|
|body|body|object| 否 |none|
|» oldPassword|body|string| 是 |老密码|
|» newPassword|body|string| 是 |新密码|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "登录成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|code|302|
|msg|登录成功|
|msg|用户不存在|
|msg|用户密码错误|

## GET 获取用户信息

GET /api/auth/info

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|token|query|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "username": "string",
    "privilege": 1,
    "userPhone": "string",
    "userAddress": "string",
    "userId": 0,
    "userRealName": "string"
  },
  "msg": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|»» username|string|true|none|用户名|none|
|»» privilege|integer|true|none|权限|1代表卖家   2代表买家|
|»» userPhone|string|false|none|买家手机号|(如果是买家则返回)|
|»» userAddress|string|false|none|默认交易地址|(如果是买家则返回)|
|»» userId|integer|true|none|用户id|none|
|»» userRealName|string|true|none||真实姓名|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|privilege|1|
|privilege|2|

# root/订单接口

## GET 卖家查看商品订单信息

GET /api/order/sellerlist

移除了goodState，增加了totalOrders

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|goodId|query|integer| 是 |商品id|
|pageNum|query|integer| 否 |none|
|pageSize|query|integer| 否 |none|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "orders": [
      {
        "buyerPhone": "110",
        "buyerAddress": "商专",
        "buyerDesc": "这个我真的需要",
        "orderId": 11,
        "buyerRealName": "zhangsan",
        "orderState": 0,
        "buyerGoodsNum": 0,
        "orderPrice": "string"
      }
    ],
    "totalOrders": 0
  },
  "msg": "请求成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|»» orders|[object]|true|none||none|
|»»» buyerPhone|string|true|none||买家手机|
|»»» buyerAddress|string|true|none||买家地址|
|»»» buyerDesc|string|true|none||买家备注|
|»»» orderId|integer|true|none||该订单的编号|
|»»» buyerRealName|string|true|none||买家姓名|
|»»» orderState|integer|true|none||订单状态，目前暂时只有三个|
|»»» buyerGoodsNum|integer|true|none||买了多少件商品|
|»»» orderPrice|string|true|none||下单时支付的价格|
|»» totalOrders|integer|true|none||总共有多少条|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|code|303|
|orderState|0|
|orderState|1|
|orderState|2|
|msg|请求成功|
|msg|token不合法|
|msg|查询商品失败|

## GET 买家查看订单信息

GET /api/order/buyerlist

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|token|query|integer| 是 |用于标明用户id，根据id返回此用户的相关订单|
|pageNum|query|integer| 否 |none|
|pageSize|query|integer| 否 |none|
|X-Hahashop-Token|header|string| 是 |需验证token为买家|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "totalOrders": 0,
    "orders": [
      {
        "buyerPhone": "110",
        "buyerAddress": "商专",
        "buyerDesc": "这个我真的需要",
        "orderId": 11,
        "buyerRealName": "zhangsan",
        "goodId": "string",
        "goodImage": "string",
        "goodName": "string",
        "orderState": 0,
        "buyerGoodsNum": 0,
        "orderPrice": "string"
      }
    ]
  },
  "msg": "请求成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|»» totalOrders|integer|true|none||总共有多少个订单，用于分页|
|»» orders|[object]|true|none||none|
|»»» buyerPhone|string|true|none||买家手机|
|»»» buyerAddress|string|true|none||买家地址|
|»»» buyerDesc|string|true|none||买家备注|
|»»» orderId|integer|true|none||该订单的编号|
|»»» buyerRealName|string|true|none||买家姓名|
|»»» goodId|string|true|none||商品id|
|»»» goodImage|string|true|none||商品图片，只传第一张|
|»»» goodName|string|true|none||商品名称|
|»»» orderState|integer|true|none||订单状态|
|»»» buyerGoodsNum|integer|true|none||none|
|»»» orderPrice|string|true|none||下单时支付的价格|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|code|303|
|totalOrders|0|
|totalOrders|1|
|msg|请求成功|
|msg|token不合法|
|msg|查询商品失败|

## POST 购买商品

POST /api/order/buy

> Body 请求参数

```json
{
  "goodId": 0,
  "buyerRealName": "zhangsan",
  "buyerPhone": "+86 12312341234",
  "buyerAddress": "浙江省杭州市下沙浙江工商大学南1门",
  "buyerDesc": "您好，我们花为公司真的很需要这个产品来制作芯片，为了国家大业考虑，请优先将这个商品卖给我们。",
  "buyerGoodsNum": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|header|string| 是 |需验证token为买家|
|body|body|object| 否 |none|
|» goodId|body|integer| 是 |要购买的商品id|
|» buyerRealName|body|string| 是 |购买人姓名|
|» buyerPhone|body|string| 是 |购买人电话|
|» buyerAddress|body|string| 是 |约定交易地点|
|» buyerDesc|body|string| 是 |备注|
|» buyerGoodsNum|body|integer| 是 |购买的数量|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "购买成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|305|
|code|201|
|code|306|
|code|307|
|msg|购买成功|
|msg|购买失败，商品已经被冻结|
|msg|购买失败，填入的信息不合法|

## POST 卖商品

POST /order/sell

选中订单之后商品进入冻结状态

> Body 请求参数

```json
{
  "orderId": 0,
  "goodId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|cookie|string| 是 |none|
|body|body|object| 否 |none|
|» orderId|body|integer| 是 |订单编号|
|» goodId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|303|
|code|307|
|code|201|

## POST 商家确认订单完成

POST /api/order/confirmSell

商家确认订单卖出的接口，订单由"正在进行"状态转为"完成"状态

> Body 请求参数

```json
{
  "orderId": 0,
  "goodId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|
|body|body|object| 否 |none|
|» orderId|body|integer| 是 |订单编号|
|» goodId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|303|
|code|201|

## POST 商家取消订单

POST /api/order/cancelsell

商家取消订单的接口，订单由"正在进行"状态转为"取消"状态

> Body 请求参数

```json
{
  "orderId": 0,
  "goodId": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|
|body|body|object| 否 |none|
|» orderId|body|integer| 是 |订单编号|
|» goodId|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|303|
|code|201|

# root/商品修改历史接口

## GET 获取商品修改历史

GET /api/history/list

这个categoryid先不传，不想做

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|pageSize|query|integer| 否 |返回10个数据|
|pageNum|query|integer| 否 |第1页|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "histories": [
      {
        "historyId": 0,
        "operationTime": "2024-01-01 00:00:00",
        "goodName": "比亚迪跑车",
        "goodPrice": "100 ￥",
        "goodId": 0,
        "goodImage": "https://img.alicdn.com/imgextra/i2/O1CN01kcwuQk1LzVafnz3rv_!!6000000001370-0-tps-480-672.jpg",
        "goodDesc": "非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好非常好"
      }
    ],
    "totalHistories": 100
  },
  "msg": "查询商品成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|»» histories|[object]|true|none||none|
|»»» historyId|integer|true|none||历史记录的id|
|»»» operationTime|string|true|none||操作的时间|
|»»» goodName|string|true|none||商品名称|
|»»» goodPrice|string|true|none||用string传输主要考虑了货币的种类，比如100€   100$|
|»»» goodId|integer|true|none||此goodid会用在后续对后端的网络请求，在网络请求中对这个商品进行标识。<br />比如购买该商品时，前端会往后端传递这个id用于标识是这个商品|
|»»» goodImage|string|true|none||商品图片的url地址，只返回第一张|
|»»» goodDesc|string|true|none||商品描述信息|
|»» totalHistories|integer|true|none||历史记录总数量，用于前端计算总共有多少页|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|msg|查询商品成功|
|msg|查询商品失败|

## DELETE 删除历史记录

DELETE /api/history/delete

> Body 请求参数

```json
{
  "historyId": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|
|body|body|object| 否 |none|
|» historyId|body|string| 是 |历史记录id|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {},
  "msg": "购买成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|303|
|code|201|
|msg|购买成功|
|msg|购买失败，商品已经被冻结|
|msg|购买失败，填入的信息不合法|

# root/储存接口

## POST 上传图片

POST /api/storage/upload

后端可参考litemall商城的写法
```java
    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        LitemallStorage litemallStorage = storageService.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        return ResponseUtil.ok(litemallStorage);
    }
 ```

> Body 请求参数

```yaml
file: ""

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|
|body|body|object| 否 |none|
|» file|body|string(binary)| 是 |二进制表单|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "url": "http://localhost:8080/storage/fetch/sjhcc8pkpxu0ofoymwdu.png",
    "key": "sjhcc8pkpxu0ofoymwdu.png"
  },
  "msg": "查询商品成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|»» url|string|true|none||图片的url|
|»» key|string|true|none||图片的key|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|msg|查询商品成功|
|msg|查询商品失败|

## GET 获取图片

GET /api/storage/fetch/{key:.+}

使用路径变量的方式获取到图片，比如
http://localhost:8080/storage/fetch/sjhcc8pkpxu0ofoymwdu.png
litemall商城的接口是这么写的
``` java
@GetMapping("/fetch/{key:.+}")
    public ResponseEntity<Resource> fetch(@PathVariable String key) {
        LitemallStorage litemallStorage = litemallStorageService.findByKey(key);
        if (litemallStorage==null||key == null) {
            return ResponseEntity.notFound().build();
        }
        if (key.contains("../")) {
            return ResponseEntity.badRequest().build();
        }
        String type = litemallStorage.getType();
        MediaType mediaType = MediaType.parseMediaType(type);

        Resource file = storageService.loadAsResource(key);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(mediaType).body(file);
    }
   ```
此处ResponseEntity不是你自己写的，是这么导入的
import org.springframework.http.ResponseEntity;

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|key:.+|path|string| 是 |none|

> 返回示例

> 200 Response

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

# root/获取用户信息

## GET 获取所有已注册用户信息

GET /api/user/list

返回所有身份为"购买者"的信息

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|pageSize|query|integer| 否 |返回10个数据|
|pageNum|query|integer| 否 |第1页|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "users": [
      {
        "userId": 0,
        "userPhone": "string",
        "userAddress": "string",
        "userRealName": "string"
      }
    ],
    "totalUsers": 100
  },
  "msg": "查询商品成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|»» users|[object]|true|none||none|
|»»» userId|integer|true|none||none|
|»»» userPhone|string|true|none||购买者手机号|
|»»» userAddress|string|true|none||购买者地址|
|»»» userRealName|string|true|none||购买者真实姓名|
|»» totalUsers|integer|true|none||用户总数量，用于前端计算总共有多少页|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|msg|查询商品成功|
|msg|查询商品失败|

## GET 获取某个用户所有的购买记录

GET /api/user/orderlist

根据用户的id来查询该用户的购买记录

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|pageSize|query|integer| 否 |返回10个数据|
|pageNum|query|integer| 否 |第1页|
|userId|query|integer| 否 |这个用户的id|
|X-Hahashop-Token|header|string| 是 |需验证token为卖家|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "orders": [
      {
        "orderId": 0,
        "orderState": 0,
        "goodId": 0,
        "goodImage": "string",
        "goodName": "string",
        "orderPrice": "string",
        "buyerDesc": "string",
        "buyerRealName": "string",
        "buyerAddress": "string",
        "buyerPhone": "string",
        "buyerGoodsNum": 0
      }
    ],
    "totalOrders": 100
  },
  "msg": "查询商品成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|»» orders|[object]|true|none||none|
|»»» orderId|integer|true|none||none|
|»»» orderState|integer|true|none||订单状态|
|»»» goodId|integer|true|none||商品Id|
|»»» goodImage|string|true|none||商品图片，只传第一张|
|»»» goodName|string|true|none||商品名称|
|»»» orderPrice|string|true|none||商品价格|
|»»» buyerDesc|string|true|none||none|
|»»» buyerRealName|string|true|none||none|
|»»» buyerAddress|string|true|none||none|
|»»» buyerPhone|string|true|none||none|
|»»» buyerGoodsNum|integer|true|none||购买商品数量|
|»» totalOrders|integer|true|none||用户总数量，用于前端计算总共有多少页|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|msg|查询商品成功|
|msg|查询商品失败|

# 数据模型

<h2 id="tocS_response结构">response结构</h2>

<a id="schemaresponse结构"></a>
<a id="schema_response结构"></a>
<a id="tocSresponse结构"></a>
<a id="tocsresponse结构"></a>

```json
{
  "code": 400,
  "data": {
    "token": "string"
  },
  "msg": "登录成功"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer|true|none||400代表登录成功<br />301代表用户不存在<br />302代表用户密码错误|
|data|object|true|none||none|
|» token|string|true|none||根据jwt令牌生成的一长串字符串|
|msg|string|true|none||登录成功<br />用户不存在<br />用户密码错误|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|301|
|code|302|
|msg|登录成功|
|msg|用户不存在|
|msg|用户密码错误|

