package com.example.keepthetime_20211121.fragments


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.keepthetime_20211121.*

import com.example.keepthetime_20211121.databinding.FragmentMyProfileBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import com.example.keepthetime_20211121.utils.ContextUtil
import com.example.keepthetime_20211121.utils.GlobalData
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment : BaseFragment() {

    lateinit var binding : FragmentMyProfileBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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

        binding.btnLogout.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인",DialogInterface.OnClickListener { dialog, which ->


                ContextUtil.setToken(mContext,"")

                val myIntent = Intent(mContext,SplashActivity::class.java)
                startActivity(myIntent)

            })

            alert.setNegativeButton("취소",null)
            alert.show()




        }

        binding.btnFriendList.setOnClickListener {

            val myIntent = Intent(mContext,MyFriendListActivity::class.java)
            startActivity(myIntent)


        }


    }

    override fun setValues() {
        //        내 정보를 서버에서 받아오자. -> 이미지 반영 / 닉네임 반영

        binding.txtNickname.text = GlobalData.loginUser!!.nickname
        Glide.with(mContext).load(GlobalData.loginUser!!.profileImageURL).into(binding.imgProfile)

//         1. 프랙드먼트에서 retrofit 어떻게 활용?

//         2.dataBinding -> 프래그먼트에서는 어떻게 데이터바인딩?

//        getMyInfoFromServer()

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