## 汽车租赁平台接口说明文档



#### 1、returnCode状态码说明

| returnCode | 说明                               |
| ---------- | ---------------------------------- |
| 0          | 成功                               |
| 1001       | 参数有误                           |
| 1002       | 参数有误，开始时间不能大于结束时间 |
| 2001       | 预订失败，请重新查询               |
| 2002       | 没有可预订的车辆                   |
| 3001       | 取消车辆失败，请重新输入           |
| 3002       | 该车辆尚未预订，不可以取消         |



#### 2、查询所有汽车model接口说明

##### 2.1功能描述

获取所有汽车的model，供用户选择。

##### 2.2请求说明

请求方法：get

请求地址：http://139.198.117.184:8080/user/queryCarModelList

##### 2.3请求参数

无

##### 2.4返回结果

| 字段       | 字段类型 | 字段说明                       |
| ---------- | -------- | ------------------------------ |
| returnCode | String   | 返回状态码，具体含义见状态码表 |
| message    | String   | 提示信息                       |
| resultData | Json     | 返回的查询结果，               |
| carModel   | String   | 汽车型号                       |

示例

```json
{
  "returnCode": "0",                //返回码
  "message": "查询成功",             //提示信息
  "resultData": [                  //返回的查询结果
    {
      "carModel": "BMW 650"        //汽车型号
    },
    {
      "carModel": "Toyota Camry"   //汽车型号
    }
  ]
}
```





#### 3、查询具体车辆的可租日期接口说明

##### 3.1功能描述

根据输入的车辆型号和租用时间查询库存信息，并返回可租车辆的信息

##### 3.2请求说明

请求方法：post

请求地址：http://139.198.117.184:8080/user/queryCarStock

##### 3.3请求参数

| 字段      | 字段类型 | 是否必传 | 字段说明 |
| --------- | -------- | -------- | -------- |
| carModel  | String   | 是       | 汽车型号 |
| startDate | String   | 是       | 开始日期 |
| endDate   | String   | 是       | 结束日期 |

示例

```json
{
  "carModel": "BMW 650",//汽车型号
  "endDate": "2021-07-14",// 结束日期
  "startDate": "2021-07-10"//开始日期
}
```



##### 3.4返回结果

| 字段       | 字段类型 | 字段说明                       |
| ---------- | -------- | ------------------------------ |
| returnCode | String   | 返回状态码，具体含义见状态码表 |
| message    | String   | 提示信息                       |
| resultData | Json     | 返回的查询结果，               |
| id         | int      | 汽车id                         |
| time       | Date     | 可租日期                       |
| stock      | int      | 库存                           |
| carModel   | String   | 汽车型号                       |

示例1

```json
{
  "returnCode": "0",//返回码
  "message": "查询成功",//提示信息
  "resultData": [//返回的查询结果
    {
      "id": 9,//汽车id
      "time": "2021-07-10T16:00:00.000+0000",//可租的时间
      "stock": 2,//库存
      "carModel": "BMW 650"//汽车型号
    },
    {
      "id": 10,
      "time": "2021-07-11T16:00:00.000+0000",
      "stock": 2,
      "carModel": "BMW 650"
    },
    {
      "id": 11,
      "time": "2021-07-12T16:00:00.000+0000",
      "stock": 2,
      "carModel": "BMW 650"
    },
    {
      "id": 12,
      "time": "2021-07-13T16:00:00.000+0000",
      "stock": 2,
      "carModel": "BMW 650"
    }
  ]
}
```

示例2

```json
{
  "returnCode": "1002",
  "message": "参数有误,开始时间不能大于结束时间",
  "resultData": null
}
```



#### 4、预订车辆接口说明

##### 4.1功能描述

根据输入的车辆型号和租用时间预订车辆，返回租用的汽车型号和租赁时间

##### 4.2请求说明

请求方法：post

请求地址：http://139.198.117.184:8080/user/bookingCar

##### 4.3请求参数

| 字段      | 字段类型 | 是否必传 | 字段说明 |
| --------- | -------- | -------- | -------- |
| carModel  | String   | 是       | 汽车型号 |
| startDate | String   | 是       | 开始日期 |
| endDate   | String   | 是       | 结束日期 |

示例

```json
{
  "carModel": "BMW 650",//汽车型号
  "endDate": "2021-07-14",// 结束日期
  "startDate": "2021-07-10"//开始日期
}
```



##### 4.4返回结果

| 字段       | 字段类型 | 字段说明                       |
| ---------- | -------- | ------------------------------ |
| returnCode | String   | 返回状态码，具体含义见状态码表 |
| message    | String   | 提示信息                       |
| resultData | Json     | 返回的查询结果，               |
| carModel   | String   | 汽车型号                       |
| startDate  | Date     | 开始日期                       |
| endDate    | Date     | 结束日期                       |

示例1

```json
{
  "returnCode": "0",
  "message": "预订成功",
  "resultData": {
    "endDate": "2021-07-14T00:00:00.000+0000",
    "startDate": "2021-07-10T00:00:00.000+0000",
    "carModel": "BMW 650"
  }
}
```

示例2

```json
{
  "returnCode": "2002",
  "message": "没有可预订的车辆",
  "resultData": {
    "endDate": "2021-07-14T00:00:00.000+0000",
    "startDate": "2021-07-10T00:00:00.000+0000",
    "carModel": "BMW 650"
  }
}
```





#### 5、取消预订车辆接口说明

##### 5.1功能描述

根据输入的车辆型号和租用时间取消预订车辆，返回取消车辆的型号和租赁时间

##### 5.2请求说明

请求方法：post

请求地址：http://139.198.117.184:8080/user/cancelBookingCar

##### 5.3请求参数

| 字段      | 字段类型 | 是否必传 | 字段说明 |
| --------- | -------- | -------- | -------- |
| carModel  | String   | 是       | 汽车型号 |
| startDate | String   | 是       | 开始日期 |
| endDate   | String   | 是       | 结束日期 |

示例

```json
{
  "carModel": "BMW 650",//汽车型号
  "endDate": "2021-07-14",// 结束日期
  "startDate": "2021-07-10"//开始日期
}
```



##### 5.4返回结果

| 字段       | 字段类型 | 字段说明                       |
| ---------- | -------- | ------------------------------ |
| returnCode | String   | 返回状态码，具体含义见状态码表 |
| message    | String   | 提示信息                       |
| resultData | Json     | 返回的查询结果，               |
| carModel   | String   | 汽车型号                       |
| startDate  | Date     | 开始日期                       |
| endDate    | Date     | 结束日期                       |

示例1

```json
{
  "returnCode": "0",
  "message": "取消成功",
  "resultData": {
    "endDate": "2021-07-14T00:00:00.000+0000",
    "startDate": "2021-07-10T00:00:00.000+0000",
    "carModel": "BMW 650"
  }
}
```

示例2

```json
{
  "returnCode": "3002",
  "message": "该车辆尚未预订，不可取消",
  "resultData": {
    "endDate": "2021-07-14T00:00:00.000+0000",
    "startDate": "2021-07-10T00:00:00.000+0000",
    "carModel": "BMW 650"
  }
}
```

