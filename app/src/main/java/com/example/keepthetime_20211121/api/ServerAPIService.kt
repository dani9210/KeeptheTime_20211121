package com.example.keepthetime_20211121.api

import retrofit2.http.Field
import retrofit2.http.POST

interface ServerAPIService {

//    기능별 주소 (endpoint) / 메쏘드 (POST) / 파라미터 명시.  <=  이것만 명시하는 별도의 문서


    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password")  pw : String
        )


}