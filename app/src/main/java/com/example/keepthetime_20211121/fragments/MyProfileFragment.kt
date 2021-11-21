package com.example.keepthetime_20211121.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.keepthetime_20211121.R

class MyProfileFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_profile,container,false)

        setEvents()
        setValues()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        내 정보를 서버에서 받아오자. -> 이미지 반영 / 닉네임 반영

//         1. 프랙드먼트에서 retrofit 어떻게 활용?

//         2.dataBinding -> 프래그먼트에서는 어떻게 데이터바인딩?
    }


    override fun setEvents() {

    }

    override fun setValues() {

    }
}