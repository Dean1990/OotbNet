package com.deanlib.ootb.net

/**
 * @auther Dean
 * @create 2019/12/26
 */
class OotbNet {

    companion object{
        var isDebug:Boolean = false
        lateinit var config:Config

        fun init(isDebug:Boolean, config:Config){
            this.isDebug = isDebug
            this.config = config
        }
    }
}