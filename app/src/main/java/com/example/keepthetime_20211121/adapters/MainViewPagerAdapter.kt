package com.example.keepthetime_20211121.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.keepthetime_20211121.fragments.MyProfileFrgment
import com.example.keepthetime_20211121.fragments.ScheduleListFrgment

class MainViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount() = 2


    override fun getItem(position: Int): Fragment {

        return when (position){

            0 -> ScheduleListFrgment()
            else -> MyProfileFrgment()

        }

    }
}