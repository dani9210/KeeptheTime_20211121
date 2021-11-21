package com.example.keepthetime_20211121.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide

import com.example.keepthetime_20211121.R
import com.example.keepthetime_20211121.ViewFriendListActivity
import com.example.keepthetime_20211121.databinding.FragmentMyProfileBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import com.example.keepthetime_20211121.utils.ContextUtil
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment : BaseFragment() {

    lateinit var binding : FragmentMyProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_profile,container,false)
        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setEvents()
        setValues()


    }


    override fun setEvents() {

        binding.btnFriendList.setOnClickListener {

            val myIntent = Intent(mContext,ViewFriendListActivity::class.java)
            startActivity(myIntent)


        }


    }

    override fun setValues() {
        //        내 정보를 서버에서 받아오자. -> 이미지 반영 / 닉네임 반영

//         1. 프랙드먼트에서 retrofit 어떻게 활용?

//         2.dataBinding -> 프래그먼트에서는 어떻게 데이터바인딩?

        getMyInfoFromServer()

    }


    fun getMyInfoFromServer(){
        apiService.getRequestMyInfo().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful){

                    val br = response.body()!!

//                    닉네임 / 프로필 이미지 주소 추출 => UI반영?

                    binding.txtNickname.text = br.data.user.nickname

                    Glide.with(mContext).load(br.data.user.profileImageURL).into(binding.imgProfile)


                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })



    }
}