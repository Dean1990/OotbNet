package com.deanlib.ootb.net.test

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.deanlib.ootb.net.*
import com.deanlib.ootb.net.base.ILoadDialog
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OotbNet.init(
            true, Config.Builder()
                .setNetConfig(NetConfig.Builder().setBaseUrl(Constans.BaseUrl).build())
                .setLoadDialog(DefaultLoadDialog())
                .build()
        )

        RequestUtils.getDemo(this,
            object :
                DefaultObserver<List<Demo>>(
                    this
//                    ,object: ILoadDialog {
//                        override fun getDialog(context: Context): Dialog {
//                            return AlertDialog.Builder(context).setTitle("1234566")
//                                .setMessage("sdklfjhldkjfldhjf").create()
//                        }
//
//                    }
                ) {
                override fun onSuccess(data: List<Demo>?) {
                    Toast.makeText(this@MainActivity,"1kjkdjfldjflk",Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(e: Throwable?, errMsg: String?) {

                }

            })
    }
}
