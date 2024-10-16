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

## GET 获取商品信息接口

GET /good/list

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|cookie|string| 否 |证明这是商家，是商家是返回buyerNum，该商品现在有多少购买者|
|pageSize|query|integer| 否 |返回10个商品数据|
|pageNum|query|integer| 否 |第1页|

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
        "goodId": 0,
        "goodImage": "https://img.alicdn.com/imgextra/i2/O1CN01kcwuQk1LzVafnz3rv_!!6000000001370-0-tps-480-672.jpg",
        "goodState": 0,
        "buyerNum": 0
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
|»»» goodImage|string|true|none||商品图片的url地址|
|»»» goodState|integer|true|none||0代表没被冻结<br />1代表被冻结|
|»»» buyerNum|integer|false|none||此商品购买者数量，在token校验为商家时返回给前端|
|»» totalGoods|integer|true|none||商品总数量，用于前端计算总共有多少页|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|303|
|msg|查询商品成功|
|msg|查询商品失败|

## GET 获取商品详细信息

GET /good/detail

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|integer| 是 |商品id|

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
|msg|查询商品成功|
|msg|查询商品失败|

## POST 更新商品

POST /good/update

> Body 请求参数

```yaml
goodImage: ""
goodId: 0
goodDesc: ""
goodPrice: ""
goodName: ""

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|cookie|string| 是 |none|
|body|body|object| 否 |none|
|» goodImage|body|string(binary)| 是 |商品图片|
|» goodId|body|integer| 是 |商品id|
|» goodDesc|body|string| 是 |商品描述信息|
|» goodPrice|body|string| 是 |商品价格|
|» goodName|body|string| 是 |商品名称|

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

## POST 添加商品

POST /good/add

> Body 请求参数

```yaml
goodImage: ""
goodDesc: ""
goodPrice: ""
goodName: ""

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|cookie|string| 是 |none|
|body|body|object| 否 |none|
|» goodImage|body|string(binary)| 是 |商品图片|
|» goodDesc|body|string| 是 |商品描述信息|
|» goodPrice|body|string| 是 |商品价格|
|» goodName|body|string| 是 |商品名称|

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
|msg|购买成功|
|msg|购买失败，商品已经被冻结|
|msg|购买失败，填入的信息不合法|

## DELETE 删除商品

DELETE /good/delete

> Body 请求参数

```json
{
  "goodId": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|cookie|string| 是 |none|
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

## POST 卖家登录接口

POST /auth/login

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
|» code|integer|true|none||400代表登录成功<br />301代表用户不存在<br />302代表用户密码错误|
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

## POST 卖家修改密码

POST /auth/changePassword

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
|X-Hahashop-Token|cookie|string| 否 |none|
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

GET /auth/info

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
    "username": "string"
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
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|

# root/订单接口

## GET 查看购买者信息

GET /order/list

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|cookie|string| 是 |证明这是商家|
|goodId|query|integer| 是 |商品id|
|pageNum|query|integer| 否 |none|
|pageSize|query|integer| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 400,
  "data": {
    "goodState": 0,
    "orders": [
      {
        "buyerPhone": "110",
        "buyerAddress": "商专",
        "buyerDesc": "这个我真的需要",
        "oderId": 11,
        "buyerName": "zhangsan",
        "isConfirmed": true
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
|»» goodState|integer|true|none||该商品的状态，0代表没被冻结，1代表被冻结。|
|»» orders|[object]|true|none||none|
|»»» buyerPhone|string|true|none||买家手机|
|»»» buyerAddress|string|true|none||买家地址|
|»»» buyerDesc|string|true|none||买家备注|
|»»» oderId|integer|true|none||该订单的编号|
|»»» buyerName|string|true|none||买家姓名|
|»»» isConfirmed|boolean|true|none||该买家是否被卖家选中|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|201|
|code|303|
|goodState|0|
|goodState|1|
|msg|请求成功|
|msg|token不合法|
|msg|查询商品失败|

## POST 购买商品

POST /order/buy

> Body 请求参数

```json
{
  "goodId": 0,
  "buyerName": "zhangsan",
  "buyerPhone": "+86 12312341234",
  "buyerAddress": "浙江省杭州市下沙浙江工商大学南1门",
  "buyerDesc": "您好，我们花为公司真的很需要这个产品来制作芯片，为了国家大业考虑，请优先将这个商品卖给我们。"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» goodId|body|integer| 是 |要购买的商品id|
|» buyerName|body|string| 是 |购买人姓名|
|» buyerPhone|body|string| 是 |购买人电话|
|» buyerAddress|body|string| 是 |约定交易地点|
|» buyerDesc|body|string| 是 |备注|

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
|code|304|
|code|305|
|code|307|
|msg|购买成功|
|msg|购买失败，商品已经被冻结|
|msg|购买失败，填入的信息不合法|

## POST 卖商品

POST /order/sell

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

## POST 撤销卖商品

POST /order/cancelsell

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
|X-Hahashop-Token|cookie|string| 是 |none|
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
|code|308|
|code|201|

# root/历史信息接口

## GET 获取商品历史信息

GET /history/list

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|cookie|string| 是 |证明这是商家|
|pageSize|query|integer| 否 |返回10个数据|
|pageNum|query|integer| 否 |第1页|

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
|»»» goodImage|string|true|none||商品图片的url地址|
|»»» goodDesc|string|true|none||商品描述信息|
|»» totalHistories|integer|true|none||历史记录总数量，用于前端计算总共有多少页|
|» msg|string|true|none||none|

#### 枚举值

|属性|值|
|---|---|
|code|400|
|code|1001|
|code|201|
|msg|查询商品成功|
|msg|查询商品失败|

## DELETE 删除历史记录

DELETE /history/delete

> Body 请求参数

```json
{
  "historyId": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|X-Hahashop-Token|cookie|string| 是 |none|
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
|code|309|
|code|201|
|code|1001|
|msg|购买成功|
|msg|购买失败，商品已经被冻结|
|msg|购买失败，填入的信息不合法|

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

