package com.example.keepthetime_20211121.datas

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class ScheduleData(

    var id : Int,
    var title : String,
    var dateTime : Date,
    var place : String,
    var latitude : Double,
    var longitude : Double,
    @SerializedName("created_at")
    var createdAt : String
) {





    fun getFormattedDatetime( ) : String {

        //    datetime ( 약속일시) : Date로 저장되어 있다.
//    기능 추가 -> Data = > 가공된 String으로 내보내주는 함수.

        val twoLineFormat = SimpleDateFormat( "M월 d일\na h:mm" )

        return twoLineFormat.format(this.dateTime)


    }


}