package com.example.keepthetime_202111211111.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.keepthetime_202111211111.R
import com.example.keepthetime_202111211111.ViewPlaceMapActivity
import com.example.keepthetime_202111211111.api.ServerAPIService
import com.example.keepthetime_202111211111.datas.BasicResponse
import com.example.keepthetime_202111211111.datas.ScheduleData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleRecyclerAdapter(val mContext : Context, val mList : List<ScheduleData>) : RecyclerView.Adapter<ScheduleRecyclerAdapter.ScheduleViewHolder>() {

    lateinit var apiService : ServerAPIService
    lateinit var mScheduleData : ScheduleData

    inner class ScheduleViewHolder(row : View) : RecyclerView.ViewHolder(row) {

        val txtAppointmentPlace = row.findViewById<TextView>(R.id.txtAppointmentPlace)
        val txtAppointmentTitle = row.findViewById<TextView>(R.id.txtAppointmentTitle)
        val txtDateTime = row.findViewById<TextView>(R.id.txtDateTime)
        val imgMap = row.findViewById<ImageView>(R.id.imgMap)
        val btnDelete = row.findViewById<Button>(R.id.btnDelete)

        fun bind(data: ScheduleData) {

            txtAppointmentTitle.text = data.title
            txtAppointmentPlace.text = data.place
            txtDateTime.text= data.getFormattedDatetime()


            btnDelete.setOnClickListener {

                apiService.deleteRequestDeleteAppointment(mScheduleData.id).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if(response.isSuccessful){

                            val br = response.body()

                            Log.d("실패사유",br.toString())


                            Toast.makeText(mContext, "약속이 삭제되었습니다", Toast.LENGTH_SHORT).show()

                        }

                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }


                })

            }

            imgMap.setOnClickListener {

                val myIntent = Intent(mContext,ViewPlaceMapActivity::class.java)

//                어떤 약속을 보러가는지 데이터 첨부

                myIntent.putExtra("schedule",data)

                mContext.startActivity(myIntent)
            }



//            txtDateTime.text = data.dateTime

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.schedule_list_item,parent,false)
        return  ScheduleViewHolder(row)

    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {

        holder.bind( mList[position] )

    }

    override fun getItemCount() = mList.size


}