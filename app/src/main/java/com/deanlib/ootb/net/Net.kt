package com.deanlib.ootb.net

import androidx.annotation.MainThread
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.cacheGet
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit封装
 */
class Net {
    companion object {

        var apiUrls: List<Any> = listOfNotNull()

        /**
         * 这里返回单例的
         */
        inline fun <reified T> of(clazz: Class<T>): T {
            var apiUrl = getApiUrl<T>()
            if (apiUrl == null) {
                synchronized(Net::class) {
                    apiUrl = getApiUrl<T>()
                    if (apiUrl == null) {
                        apiUrl = Net().getRetrofit(clazz, OotbNet.config)
                    }
                }
            }
            return apiUrl as T
        }

        /**
         * 这里不返回单例的
         * 用于用户自定义 Config
         */
        inline fun <reified T> of(clazz: Class<T>, config: Config): T {
            return Net().getRetrofit(clazz, config)
        }

        inline fun <reified T> getApiUrl(): T? {
            for (item in apiUrls) {
                if (item is T) {
                    return item
                }
            }
            return null
        }

    }

    fun <T> getRetrofit(clazz: Class<T>, config: Config): T {
        //初始化Retrofit
        var apiUrl = initRetrofit(initOkHttp(config), config).create(clazz)
        return apiUrl
    }

    /**
     * 初始化Retrofit
     */
    private fun initRetrofit(client: OkHttpClient, config: Config): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(config.baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactories(config.callAdapterFactories)
            .addConverterFactories(config.converterFactories)
            .build()
    }

    /**
     * 扩展
     */
    private fun Retrofit.Builder.addCallAdapterFactories(factories: List<CallAdapter.Factory>?)
            :Retrofit.Builder{
        factories!!.forEach{
            addCallAdapterFactory(it)
        }
        return this
    }

    private fun Retrofit.Builder.addConverterFactories(factories: List<Converter.Factory>?)
            :Retrofit.Builder{
        factories!!.forEach{
            addConverterFactory(it)
        }
        return this
    }


    /**
     * 初始化okhttp
     */
    private fun initOkHttp(config: Config): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(config.readTimeout, TimeUnit.MILLISECONDS)//读取超时
            .connectTimeout(config.connectTimeout, TimeUnit.MILLISECONDS)//请求超时
            .writeTimeout(config.writeTimeout, TimeUnit.MILLISECONDS)//写入超时
            .addInterceptor(LogInterceptor())//日志
            .addInterceptors(config.interceptors)
            .addNetworkInterceptors(config.networkInterceptors)
            .retryOnConnectionFailure(config.retryOnConnectionFailure)
            .build()
    }

    /**
     * 扩展
     */
    private fun OkHttpClient.Builder.addInterceptors(interceptors: List<Interceptor>?
    ): OkHttpClient.Builder {
        interceptors?.forEach {
            addInterceptor(it)
        }
        return this
    }

    private fun OkHttpClient.Builder.addNetworkInterceptors(interceptors: List<Interceptor>?
    ): OkHttpClient.Builder {
        interceptors?.forEach {
            addNetworkInterceptor(it)
        }
        return this
    }

}