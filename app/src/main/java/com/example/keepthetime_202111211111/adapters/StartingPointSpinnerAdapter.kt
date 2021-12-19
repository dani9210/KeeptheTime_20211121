package com.example.keepthetime_202111211111.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.example.keepthetime_202111211111.datas.PlaceData

class StartingPointSpinnerAdapter(
    val mContext : Context,
    resId: Int,
    val mList : List<PlaceData>
    ) : ArrayAdapter<PlaceData>(mContext,resId,mList) {
}