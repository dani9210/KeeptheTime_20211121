package com.example.keepthetime_20211121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.keepthetime_20211121.adapters.MainViewPagerAdapter
import com.example.keepthetime_20211121.databinding.ActivityMainBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import com.example.keepthetime_20211121.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mvpa  : MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mvpa =  MainViewPagerAdapter(supportFragmentManager)
        binding.mainViewpager.adapter = mvpa
        binding.mainTabLayout.setupWithViewPager(binding.mainViewpager)

        getMyInfoFromServer()

    }


//    연습 - 내 정보를 서버에서 받아오기 (GET / Header 첨부)


    fun getMyInfoFromServer(){

         apiService.getRequestMyInfo(ContextUtil.getToken(mContext)).enqueue(object : Callback<BasicResponse>{
             override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                 if (response.isSuccessful){

                     val basicResponse = response.body()!!

                     Log.d("응답내용",basicResponse.data.user.nickname)

//                     binding.txtNickname.text = basicResponse.data.user.nickname

//                     사용자의 프사 표시
//                     Glide.with(mContext).load(basicResponse.data.user.profileImageURL).into(binding.imgProfile)


                 }

             }

             override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

             }


         })

    }
}