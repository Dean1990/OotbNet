package com.deanlib.ootb.net

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiUrl {
    @GET("")
    fun getDemo():Observable<BaseResponse<Demo>>
}