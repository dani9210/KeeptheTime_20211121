package com.example.keepthetime_202111211111.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.keepthetime_202111211111.R
import com.example.keepthetime_202111211111.datas.PlaceData

class StartingPointSpinnerAdapter(
    val mContext : Context,
    resId: Int,
    val mList : List<PlaceData>
    ) : ArrayAdapter<PlaceData>(mContext,resId,mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if(tempRow == null) {


//            돌려막기 할 만큼 충분한 row가 그려지지 않은 상태.
//            mInflater 변수 대신, 바로 LayoutInflater.from 기능 활용

            tempRow = LayoutInflater.from(mContext).inflate(R.layout.starting_point_list_item,null)

        }

        val row = tempRow!!
        val data = mList[position]
        val txtPlaceName = row.findViewById<TextView>(R.id.txtPlaceName)

        txtPlaceName.text= data.placeName

        return row

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if(tempRow == null) {


//            돌려막기 할 만큼 충분한 row가 그려지지 않은 상태.
//            mInflater 변수 대신, 바로 LayoutInflater.from 기능 활용

            tempRow = LayoutInflater.from(mContext).inflate(R.layout.starting_point_list_item,null)

        }

        val row = tempRow!!
        val data = mList[position]
        val txtPlaceName = row.findViewById<TextView>(R.id.txtPlaceName)

        txtPlaceName.text= data.placeName

        return row

    }
}