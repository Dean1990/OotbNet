package com.deanlib.ootb.net

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.deanlib.ootb.net.base.ILoadDialog

/**
 * 提供一个默认的 加载框
 * @auther Dean
 * @create 2019/12/27
 */
class DefaultLoadDialog : ILoadDialog {
    override fun getDialog(context: Context): Dialog {
        var view = FrameLayout(context)
        view.addView(ProgressBar(context).apply {

        })
        return AlertDialog.Builder(context)
            .setView(view).create()
    }

}