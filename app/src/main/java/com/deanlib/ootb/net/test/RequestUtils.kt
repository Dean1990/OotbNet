package com.deanlib.ootb.net.test

import com.deanlib.ootb.net.DefaultObserver
import com.deanlib.ootb.net.Net
import com.deanlib.ootb.net.RxHelper
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

class RequestUtils{
    companion object{
        fun getDemo(context:RxAppCompatActivity,observer: DefaultObserver<List<Demo>>){
            Net.of(ApiUrl::class.java)
                .getDemo()
                .compose(
                    RxHelper.observableIO2Main(
                        context
                    )
                )
                .subscribe(observer)
        }
    }
}