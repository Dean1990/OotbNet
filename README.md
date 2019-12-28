# OotbNet

#### 初始化



#### 定义自己的请求结果 Response

```kotlin
class MyResponse<T>(code:Int,message:String?,data:T?) : BaseResponse<T>(""+code,message,data)
```

