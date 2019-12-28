package com.deanlib.ootb.net.base

/**
 * 基础请求结果
 * @auther Dean
 * @create 2019/12/27
 */
abstract class BaseResponse<T>{
    var baseCode:String? = null
    var baseMsg:String? = null
    var baseData:T? = null
    abstract fun getRealCode():String?
    abstract fun getRealMsg():String?
    abstract fun getRealData():T?

}