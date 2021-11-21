package com.example.keepthetime_20211121.api

import com.example.keepthetime_20211121.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServerAPIService {

//    기능별 주소 (endpoint) / 메쏘드 (POST) / 파라미터 명시.  <=  이것만 명시하는 별도의 문서

//     POST/ PUT /PATCH - FormData (제 서버 : FormUrlEncoded 처리 필요)


    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password")  pw : String
        ) : Call<BasicResponse>


}