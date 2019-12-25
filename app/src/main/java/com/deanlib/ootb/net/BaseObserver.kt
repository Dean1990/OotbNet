package com.deanlib.ootb.net

import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 创建Base抽象类实现Observer
 */
abstract class BaseObserver<T> : Observer<BaseResponse<T>>{

    private val TAG = "BaseObserver"

    override fun onSubscribe(d: Disposable) {
        Log.e(TAG,"onSubscribe")
    }

    override fun onNext(t: BaseResponse<T>) {
        //对基础数据进行统一处理
        if (t.code == 200){
            onSuccess(t.data)
        }else{
            onFailure(null,t.message)
        }
    }

    override fun onError(e: Throwable) {
        Log.e(TAG,"Throwable: ${e.message}")
        onFailure(e,RxExceptionUtil.exceptionHandler(e))
    }

    override fun onComplete() {
        Log.e(TAG,"onComplete")
    }

    abstract fun onSuccess(data:T)
    abstract fun onFailure(e:Throwable?,errMsg:String)
}