package com.deanlib.ootb.net

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

class RequestUtils{
    companion object{
        fun getDemo(context:RxAppCompatActivity,observer:MyObserver<List<Demo>>){
            RetrofitUtils.getApiUrl().getDemo()
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(observer)
        }
    }
}