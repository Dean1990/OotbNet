package com.deanlib.ootb.net.base

import android.util.Log
import com.deanlib.ootb.net.RxExceptionUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 创建Base抽象类实现Observer
 */
abstract class BaseObserver<T>(var successCode:String) : Observer<BaseResponse<T>>{

    private val TAG = "BaseObserver"

    override fun onSubscribe(d: Disposable) {
        Log.e(TAG,"onSubscribe")
    }

    override fun onNext(t: BaseResponse<T>) {
        //对基础数据进行统一处理
        if (t.getRealCode() == successCode){
            onSuccess(t.getRealData())
        }else{
            onFailure(null,t.getRealMsg())
        }
    }

    override fun onError(e: Throwable) {
        Log.e(TAG,"Throwable: ${e.message}")
        onFailure(e,
            RxExceptionUtil.exceptionHandler(e)
        )
    }

    override fun onComplete() {
        Log.e(TAG,"onComplete")
    }

    abstract fun onSuccess(data:T?)
    abstract fun onFailure(e:Throwable?,errMsg:String?)
}