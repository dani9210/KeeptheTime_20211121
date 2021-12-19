package com.example.keepthetime_202111211111.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime_202111211111.EditAppointmentActivity

import com.example.keepthetime_202111211111.R
import com.example.keepthetime_202111211111.adapters.ScheduleRecyclerAdapter
import com.example.keepthetime_202111211111.databinding.FragmentScheduleListBinding
import com.example.keepthetime_202111211111.datas.BasicResponse
import com.example.keepthetime_202111211111.datas.ScheduleData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleListFragment : BaseFragment() {

    lateinit var binding : FragmentScheduleListBinding
    val  mScheduleList = ArrayList<ScheduleData>()
    lateinit var mScheduleAdapter : ScheduleRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_schedule_list,container,false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setEvents()
        setValues()


    }


    override fun setEvents() {

        binding.btnAddAppointment.setOnClickListener {

            val myIntent = Intent(mContext,EditAppointmentActivity::class.java)
            startActivity(myIntent)

        }


    }

    override fun setValues() {


        mScheduleAdapter = ScheduleRecyclerAdapter(mContext,mScheduleList)
        binding.appointmentRecyclerView.adapter = mScheduleAdapter
        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }

//    생명주기 - onReseume 이 화면으로 돌아올떄마다 실행되는 함수.

    override fun onResume() {
        super.onResume()
        getScheduleListFromServer()
    }

    fun getScheduleListFromServer(){

        apiService.getRequestAppointment().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful){

                    val br = response.body()!!

                    mScheduleList.clear()
                    mScheduleList.addAll(br.data.appointments)
                    mScheduleAdapter.notifyDataSetChanged()



                }



            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {



            }


        })



    }

}