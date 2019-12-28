package com.deanlib.ootb.net

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit封装
 */
class Net {
    companion object{
        private var apiUrl:Any? = null

        /**
         * 单例模式
         */
        fun <T> of(clazz:Class<T>): T {
            if (apiUrl == null){
                synchronized(Net::class){
                    if (apiUrl == null){
                        apiUrl = Net().getRetrofit(clazz)
                    }
                }
            }
            return (apiUrl as T?)!!
        }

    }

    fun <T> getRetrofit(clazz:Class<T>):T{
        //初始化Retrofit
        var apiUrl = initRetrofit(initOkHttp()).create(clazz)
        return apiUrl
    }

    /**
     * 初始化Retrofit
     */
    private fun initRetrofit(client:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(OotbNet.config.baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * 初始化okhttp
     */
    private fun initOkHttp():OkHttpClient{
        return OkHttpClient().newBuilder()
            .readTimeout(OotbNet.config.readTimeout, TimeUnit.MILLISECONDS)//读取超时
            .connectTimeout(OotbNet.config.connectTimeout,TimeUnit.MILLISECONDS)//请求超时
            .writeTimeout(OotbNet.config.writeTimeout,TimeUnit.MILLISECONDS)//写入超时
            .addInterceptor(LogInterceptor())//日志
            .retryOnConnectionFailure(true)
            .build()
    }

}