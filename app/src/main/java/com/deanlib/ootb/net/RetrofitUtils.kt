package com.deanlib.ootb.net

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit封装
 */
class RetrofitUtils {
    companion object{
        var mApiUrl:ApiUrl? = null
        /**
         * 单例模式
         */
        fun getApiUrl(): ApiUrl {
            if (mApiUrl == null){
                synchronized(RetrofitUtils::class){
                    if (mApiUrl == null){
                        mApiUrl = RetrofitUtils().getRetrofit()
                    }
                }
            }
            return mApiUrl!!
        }
    }

    fun getRetrofit():ApiUrl{
        //初始化Retrofit
        var apiUrl = initRetrofit(initOkHttp()).create(ApiUrl::class.java)
        return apiUrl
    }

    /**
     * 初始化Retrofit
     */
    private fun initRetrofit(client:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constans.BaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * 初始化okhttp
     */
    private fun initOkHttp():OkHttpClient{
        return OkHttpClient().newBuilder()
            .readTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//读取超时
            .connectTimeout(Constans.DEFAULT_TIME,TimeUnit.SECONDS)//请求超时
            .writeTimeout(Constans.DEFAULT_TIME,TimeUnit.SECONDS)//写入超时
            .addInterceptor(LogInterceptor())//日志
            .retryOnConnectionFailure(true)
            .build()
    }


}