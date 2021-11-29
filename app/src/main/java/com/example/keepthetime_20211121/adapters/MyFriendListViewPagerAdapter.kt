package com.example.keepthetime_20211121.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.keepthetime_20211121.fragments.AddFriendFragment
import com.example.keepthetime_20211121.fragments.MyFriendListFragment

class MyFriendListViewPagerAdapter( fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return  when (position){

            0-> "내 친구 목록"
            else -> "친구 요청 목록"

        }
    }

    override fun getItem(position: Int): Fragment {

        return when (position){

            0 -> MyFriendListFragment()
            else -> AddFriendFragment()

        }

    }
}