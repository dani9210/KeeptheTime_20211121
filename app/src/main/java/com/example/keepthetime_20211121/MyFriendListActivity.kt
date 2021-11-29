package com.example.keepthetime_20211121

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20211121.adapters.MyFriendListViewPagerAdapter
import com.example.keepthetime_20211121.databinding.ActivityMyFriendListBinding


class MyFriendListActivity : BaseActivity() {

    lateinit var binding : ActivityMyFriendListBinding
    lateinit var mflvp : MyFriendListViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_my_friend_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mflvp = MyFriendListViewPagerAdapter(supportFragmentManager)
        binding.friendListViewPager.adapter = mflvp
        binding.friendListTabLayout.setupWithViewPager(binding.friendListViewPager)
    }
}