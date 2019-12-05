package com.deanlib.ootb.net

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RequestUtils.getDemo(this,object: MyObserver<Demo>(this){
            override fun onSuccess(data: Demo) {

            }

            override fun onFailure(e: Throwable?, errMsg: String) {

            }

        })
    }
}
