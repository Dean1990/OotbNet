package com.deanlib.ootb.net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Log拦截器
 */
class LogInterceptor: Interceptor {
    private val TAG = "okhttp"
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
//            .newBuilder()
//            .addHeader()
//            .addHeader()
//            .addHeader()
//            .build()
        Log.e(TAG,"request:${request.toString()}")
        var t1 = System.nanoTime()
        var response = chain.proceed(chain.request())
        var t2 = System.nanoTime()
        Log.e(TAG,String.format(Locale.getDefault(),"Received response for %s in %.1fms%n%s",
            response.request.url, (t2 - t1) / 1e6, response.headers))
        var mediaType = response.body!!.contentType()
        var content = response.body!!.string()
        Log.e(TAG,"response body:$content")
        return response.newBuilder()
            .body(content.toResponseBody(mediaType))
//            .header()
            .build()
    }
}