package com.deanlib.ootb.net

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiUrl {
    @GET("/api/v1/calcQXM?beginTime=1573776000000&endTime=1574121600000&appUserId=2")
    fun getDemo():Observable<MyRespanse<List<Demo>>>
}