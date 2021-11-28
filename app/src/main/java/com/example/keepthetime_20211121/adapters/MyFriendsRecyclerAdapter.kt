package com.example.keepthetime_20211121.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keepthetime_20211121.R
import com.example.keepthetime_20211121.datas.UserData

class MyFriendsRecyclerAdapter(val mContext : Context, val mList : ArrayList<UserData>) : RecyclerView.Adapter<MyFriendsRecyclerAdapter.MyFriendViewHolder>() {

    inner class MyFriendViewHolder(row : View) : RecyclerView.ViewHolder(row){



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFriendViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.my_friend_list_item,parent,false)

        return MyFriendViewHolder(row)

    }

    override fun onBindViewHolder(holder: MyFriendViewHolder, position: Int) {

    }

    override fun getItemCount() = mList.size
    }
}