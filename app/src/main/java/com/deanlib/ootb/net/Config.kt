package com.deanlib.ootb.net

import com.deanlib.ootb.net.base.ILoadDialog

/**
 * 配置
 * 配置应该可以分级，有本地，有全局，最终请求的那个接口也可以设置 Config
 * 这样就可以个性化每个请求接口了 //TODO
 * @auther Dean
 * @create 2019/12/26
 */
class Config{
    var loadDialog: ILoadDialog? = null
    var readTimeout = 0L
    var connectTimeout = 0L
    var writeTimeout = 0L
    var baseUrl:String? = null

    private constructor()

    class Builder(){
        private var loadDialog: ILoadDialog? = null
        private var readTimeout = 10000L
        private var connectTimeout = 10000L
        private var writeTimeout = 10000L
        private var baseUrl:String? = null

        fun setLoadDialog(dialog: ILoadDialog):Builder{
            this.loadDialog = dialog
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
            this.baseUrl = url;
            return this
        }

        fun build():Config{
            var config = Config()
            config.loadDialog = loadDialog
            config.readTimeout = readTimeout
            config.connectTimeout = connectTimeout
            config.writeTimeout = writeTimeout
            config.baseUrl = baseUrl

            return config
        }
    }
}