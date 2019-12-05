package com.deanlib.ootb.net

import org.json.JSONException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

class RxExceptionUtil {

    companion object{
        fun exceptionHandler(e:Throwable):String{
            var errorMsg = "未知错误"
            if (e is UnknownHostException){
                errorMsg = "网络不可用"
            } else if (e is SocketTimeoutException){
                errorMsg = "请求网络超时"
            } else if (e is HttpException){
                var httpException = e;
                errorMsg = convertStatusCode(httpException)
            } else if (e is ParseException || e is JSONException){
                errorMsg = "数据解析错误"
            }
             return errorMsg
        }

        fun convertStatusCode(httpException: HttpException):String{
            var msg:String
            if (httpException.code() >= 500 && httpException.code() < 600){
                msg = "服务器处理请求出错"
            }else if (httpException.code() >= 400 && httpException.code() < 500){
                msg = "服务器无法处理请求"
            }else if (httpException.code() >= 300 && httpException.code() <400){
                msg = "请求被重定向到其他页面"
            }else{
                msg = httpException.message()
            }
            return msg
        }
    }


}