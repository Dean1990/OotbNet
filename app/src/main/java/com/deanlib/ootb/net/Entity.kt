package com.deanlib.ootb.net

data class BaseResponse<T>(var code:Int, var message:String, var data:T)
data class Demo(var id:String,var name:String)