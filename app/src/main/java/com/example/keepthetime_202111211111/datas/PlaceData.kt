package com.example.keepthetime_202111211111.datas
import com.google.gson.annotations.SerializedName

class PlaceData(

    var id : Int,
    @SerializedName("name")
    var placeName : String,
    var latitude : Double,
    val longitude: Double,

) {
}