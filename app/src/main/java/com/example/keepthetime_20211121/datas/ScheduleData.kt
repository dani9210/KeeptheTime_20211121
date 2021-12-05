package com.example.keepthetime_20211121.datas

import com.google.gson.annotations.SerializedName

class ScheduleData(

    var id : Int,
    var title : String,
    var dateTime : String,
    var place : String,
    var latitude : Double,
    var longitude : Double,
    @SerializedName("created_at")
    var createdAt : String
) {
}