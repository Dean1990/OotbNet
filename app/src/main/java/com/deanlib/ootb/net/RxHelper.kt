package com.deanlib.ootb.net

import android.content.Context
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.RxActivity
import com.trello.rxlifecycle2.components.RxFragment
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragmentActivity
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 调度类
 */
class RxHelper {

    companion object{
        fun <T> observableIO2Main(context:Context):ObservableTransformer<T,T>{
            return ObservableTransformer {
                    var observable = it.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                    composeContext(context,observable)
            }
        }

        fun <T> observableIO2Main(fragment: RxFragment):ObservableTransformer<T,T>{
            return ObservableTransformer {
                it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(fragment.bindToLifecycle())
            }
        }

        fun <T> flowableIO2Main():FlowableTransformer<T,T>{
            return FlowableTransformer {
                it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun <T> composeContext(context:Context,observable:Observable<T>):ObservableSource<T>{
            if (context is RxActivity){
                return observable.compose(context.bindUntilEvent(ActivityEvent.DESTROY))
            }else if(context is RxFragmentActivity){
                return observable.compose(context.bindUntilEvent(ActivityEvent.DESTROY))
            }else if(context is RxAppCompatActivity){
                return observable.compose(context.bindUntilEvent(ActivityEvent.DESTROY))
            }else{
                return observable
            }
        }
    }
}