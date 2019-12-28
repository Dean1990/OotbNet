package com.deanlib.ootb.net.test

import com.deanlib.ootb.net.test.Demo
import com.deanlib.ootb.net.test.MyResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiUrl {
    @GET("/api/v1/app/heartRate?beginTime=1573776000000&endTime=1574121600000&appUserId=3")
    fun getDemo():Observable<MyResponse<List<Demo>>>
}