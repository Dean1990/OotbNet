package com.deanlib.ootb.net

import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter

/**
 * 网络部分的配置
 * 配置应该可以分级，有本地，有全局，最终请求的那个接口也可以设置 Config
 * 这样就可以个性化每个请求接口了 @see Net#of(class,config)
 * @auther Dean
 * @create 2019/12/26
 */
class NetConfig{
    //okhttp
    var readTimeout = 0L
    var connectTimeout = 0L
    var writeTimeout = 0L
    var baseUrl:String? = null
    var interceptors : List<Interceptor>? = null
    var networkInterceptors : List<Interceptor>? = null
    var retryOnConnectionFailure = true

    //retrofit
    var callAdapterFactories : List<CallAdapter.Factory>? = null
    var converterFactories : List<Converter.Factory>? = null

    private constructor()

    class Builder{
        private var readTimeout = 10000L
        private var connectTimeout = 10000L
        private var writeTimeout = 10000L
        private var baseUrl:String? = null
        private var interceptors:MutableList<Interceptor>? = null
        private var networkInterceptors:MutableList<Interceptor>? = null
        private var retryOnConnectionFailure = true

        private var callAdapterFactories : MutableList<CallAdapter.Factory>? = null
        private var converterFactories : MutableList<Converter.Factory>? = null

        fun addCallAdapterFactory(factory:CallAdapter.Factory):Builder{
            if (callAdapterFactories == null)
                callAdapterFactories = mutableListOf()
            callAdapterFactories!!.add(factory)
            return this
        }

        fun addConverterFactory(factory:Converter.Factory):Builder{
            if (converterFactories == null)
                converterFactories = mutableListOf()
            converterFactories!!.add(factory)
            return this
        }

        fun setRetryOnConnectionFailure(retry:Boolean):Builder{
            this.retryOnConnectionFailure = retry
            return this
        }

        fun addInterceptor(interceptor: Interceptor):Builder{
            if (interceptors == null){
                interceptors = mutableListOf()
            }
            interceptors!!.add(interceptor)
            return this
        }
        fun addNetworkInterceptor(interceptor: Interceptor):Builder{
            if (networkInterceptors == null){
                networkInterceptors = mutableListOf()
            }
            networkInterceptors!!.add(interceptor)
            return this
        }

        fun setReadTimeout(l: Long):Builder{
            this.readTimeout = l
            return this
        }

        fun setConnectTimeout(l: Long):Builder{
            this.connectTimeout = l
            return this
        }

        fun setWriteTimeout(l: Long):Builder{
            this.writeTimeout = l
            return this
        }

        fun setBaseUrl(url:String):Builder{
            this.baseUrl = url
            return this
        }

        fun build():NetConfig{
            var config = NetConfig()
            config.readTimeout = readTimeout
            config.connectTimeout = connectTimeout
            config.writeTimeout = writeTimeout
            config.baseUrl = baseUrl
            config.interceptors = interceptors
            config.networkInterceptors = networkInterceptors
            config.retryOnConnectionFailure = retryOnConnectionFailure
            config.callAdapterFactories = callAdapterFactories
            config.converterFactories = converterFactories

            return config
        }
    }
}