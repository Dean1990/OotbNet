package com.deanlib.ootb.net.test

import android.os.Bundle
import com.deanlib.ootb.net.*
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OotbNet.init(
            true, Config.Builder()
                .setLoadDialog(DefaultLoadDialog())
                .setBaseUrl(Constans.BaseUrl)
                .build()
        )

        RequestUtils.getDemo(this,
            object :
                DefaultObserver<List<Demo>>(
                    this
                ) {
                override fun onSuccess(data: List<Demo>?) {

                }

                override fun onFailure(e: Throwable?, errMsg: String?) {

                }

            })
    }
}
