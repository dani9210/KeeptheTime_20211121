package com.example.keepthetime_20211121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20211121.databinding.ActivityMainBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnLogin.setOnClickListener {

//            1.입력 id/ pw 변수 담자

            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()


//            2. 서버에 로그인 API 호출 -> Retrofit


            apiService.postRequestLogin(inputEmail,inputPassword).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
//                    최종 성공 / 실패 여부에 따라 별도 코딩

                    if(response.isSuccessful){


                        val basicResponse = response.body()!!
                        Log.d("로그인성공",basicResponse.message)



                    }



                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    Toast.makeText(mContext, "서버연결에 실패했습니다.", Toast.LENGTH_SHORT).show()

                }


            } )

        }

    }

    override fun setValues() {

    }
}