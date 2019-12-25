package com.deanlib.ootb.net

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import io.reactivex.disposables.Disposable

abstract class MyObserver<T> : BaseObserver<T> {

    var mShowDialog:Boolean = true
    var dialog:ProgressDialog? = null
    var mContext:Context?=null
    var d:Disposable?=null

    constructor(context: Context):this(context,true)

    constructor(context:Context,showDialog:Boolean):super("200"){
        mContext = context
        mShowDialog = showDialog
    }

    override fun onSubscribe(d: Disposable) {
        this.d = d
        if (!isConnected(mContext!!)){
            Toast.makeText(mContext,"未连接网络",Toast.LENGTH_SHORT).show()
            if (!d.isDisposed){
                d.dispose()
            }
        }else {
            if (dialog == null && mShowDialog == true){
                dialog = ProgressDialog(mContext)
                dialog?.setMessage("正在加载中")
                dialog?.show()
            }
        }
        super.onSubscribe(d)
    }

    override fun onError(e: Throwable) {
        if (!d!!.isDisposed){
            d!!.dispose()
        }
        hideDialog()
        super.onError(e)
    }

    override fun onComplete() {
        if (!d!!.isDisposed){
            d!!.dispose()
        }
        hideDialog()
        super.onComplete()
    }

    fun hideDialog(){
        dialog = dialog?.run {
            if (mShowDialog == true){
                dismiss()
            }
            null
        }
    }

    /**
     * 是否有网络连接
     */
    fun isConnected(context: Context):Boolean{
        var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            var network = cm.activeNetwork
            var capabilities = cm.getNetworkCapabilities(network)
            capabilities?.let {
                it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            }?:false
        }else{
            var info = cm.activeNetworkInfo
            info.isAvailable
        }

    }
}