package com.deanlib.ootb.net.test

import com.deanlib.ootb.net.base.BaseResponse


data class Demo(var id:String,var name:String)
class MyResponse<T> : BaseResponse<T>() {
    var code:Int = 0
    var message:String? = null
    var data:T? = null
    override fun getRealCode(): String? {
        return ""+code
    }

    override fun getRealMsg(): String? {
        return message
    }

    override fun getRealData(): T? {
        return data
    }
}