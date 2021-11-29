package com.example.keepthetime_20211121.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime_20211121.AddFriendActivity
import com.example.keepthetime_20211121.R
import com.example.keepthetime_20211121.adapters.MyFriendsRecyclerAdapter
import com.example.keepthetime_20211121.adapters.RequestedFriendsRecyclerAdapter
import com.example.keepthetime_20211121.databinding.FragmentMyFriendListBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import com.example.keepthetime_20211121.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFriendListFragment : BaseFragment() {

    lateinit var binding : FragmentMyFriendListBinding

    val mMyFriendList = ArrayList<UserData>()

    lateinit var mMyFriendsRecyclerAdapter: MyFriendsRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_friend_list, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setEvents()
        setValues()

    }

    override fun setEvents() {

        binding.btnAddFriend.setOnClickListener {

//            친구 추가 화면으로 이동

            val myIntent = Intent(mContext, AddFriendActivity::class.java)
            startActivity(myIntent)


        }


    }

    override fun setValues() {

        mMyFriendsRecyclerAdapter = MyFriendsRecyclerAdapter(mContext,mMyFriendList)
        binding.myFriendsRecyclerView.adapter = mMyFriendsRecyclerAdapter
        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(mContext)

        getMyFriendsFromServer()



    }

    fun getMyFriendsFromServer(){

        apiService.getRequestMyFriends("my").enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){

                    val br = response.body()!!
                    mMyFriendList.addAll(br.data.friends)
                    mMyFriendsRecyclerAdapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })

    }
}