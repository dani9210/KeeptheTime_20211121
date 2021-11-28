package com.example.keepthetime_20211121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20211121.databinding.ActivityEditAppointmentBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditAppointmentActivity : BaseActivity() {

    lateinit var binding : ActivityEditAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnOk.setOnClickListener {



            val inputTitle = binding.edtTitle.text.toString()
            val inputDataTime = binding.edtDateTime.text.toString()
            val inputPlace = binding.edtPlace.text.toString()
            val inputLat = binding.edtLatitude.text.toString().toDouble()
            val inputLng = binding.edtLongitude.text.toString().toDouble()


            apiService.postRequestAppointment(inputTitle,inputDataTime,inputPlace,inputLat,inputLng).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {



                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }


            })


        }

    }

    override fun setValues() {

    }
}