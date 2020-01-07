package com.deanlib.ootb.net

import com.deanlib.ootb.net.base.ILoadDialog

/**
 * 全局配置
 * @auther Dean
 * @create 2020/1/7
 */
class Config {
    var netConfig:NetConfig? = null
    var loadDialog:ILoadDialog? = null

    private constructor()

    class Builder{
        private var netConfig:NetConfig? = null
        private var loadDialog:ILoadDialog? = DefaultLoadDialog()

        fun setNetConfig(netConfig: NetConfig):Builder{
            this.netConfig = netConfig
            return this
        }

        fun setLoadDialog(loadDialog: ILoadDialog):Builder{
            this.loadDialog = loadDialog
            return this
        }

        fun build():Config{
            var config = Config()
            config.netConfig = netConfig
            config.loadDialog = loadDialog
            return config
        }
    }
}