package com.example.keepthetime_202111211111.datas

class PlaceData(

    var id : Int,
    @SerializedName("name")
    var placeName : String,
    var latitude : Double,
    val longitude: Double,

) {
}