package com.deanlib.ootb.net

open class BaseResponse<T>(var code:String,var msg:String?,var data:T?)
data class Demo(var id:String,var name:String)
class MyRespanse<T>(code:Int,message:String?,data:T?) : BaseResponse<T>(""+code,message,data)