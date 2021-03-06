package com.example.keepthetime_202111211111.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.keepthetime_202111211111.api.ServerAPI
import com.example.keepthetime_202111211111.api.ServerAPIService
import retrofit2.Retrofit

abstract class BaseFragment : Fragment() {
    lateinit var mContext : Context

//    모든 프래그먼트에서도 서버 통신이 필요.

    private lateinit var retrofit : Retrofit
    lateinit var apiService: ServerAPIService

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = requireContext()

        retrofit = ServerAPI.getRetrofit(mContext)
        apiService = retrofit.create(ServerAPIService::class.java)
    }

    abstract fun setEvents()
    abstract fun setValues()
}