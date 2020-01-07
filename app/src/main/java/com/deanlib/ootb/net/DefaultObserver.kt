package com.deanlib.ootb.net

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import com.deanlib.ootb.net.base.BaseObserver
import com.deanlib.ootb.net.base.ILoadDialog
import io.reactivex.disposables.Disposable

/**
 * 提供了联网检查
 */
abstract class DefaultObserver<T> : BaseObserver<T> {

    var mLoadDialog:ILoadDialog?=null
    var dialog: Dialog? = null
    var mContext:Context?=null
    var d:Disposable?=null

    constructor(context: Context):this(context,OotbNet.config.loadDialog)

    constructor(context:Context,loadDialog: ILoadDialog?):super("200"){
        mContext = context
        mLoadDialog = loadDialog
    }

    override fun onSubscribe(d: Disposable) {
        this.d = d
        if (!isConnected(mContext!!)){
            Toast.makeText(mContext,"未连接网络",Toast.LENGTH_SHORT).show()
            if (!d.isDisposed){
                d.dispose()
            }
        }else {
            if (dialog == null && mLoadDialog != null){
                dialog = mLoadDialog?.getDialog(mContext!!)
            }
            dialog?.show()
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
            if (isShowing){
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