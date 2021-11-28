package com.example.keepthetime_20211121

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20211121.adapters.MyFriendsRecyclerAdapter
import com.example.keepthetime_20211121.databinding.ActivityViewFriendListBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import com.example.keepthetime_20211121.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewFriendListActivity : BaseActivity() {

    lateinit var binding : ActivityViewFriendListBinding

    val mMyFriendsList = ArrayList<UserData>()
    lateinit var  mMyFriendsAdapter: MyFriendsRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_friend_list)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        getMyFriendsFromServer()
        mMyFriendsAdapter = MyFriendsRecyclerAdapter(mContext,mMyFriendsList)
        binding.myFriendsRecyclerView.adapter = mMyFriendsAdapter


    }

    fun getMyFriendsFromServer(){

        apiService.getRequestMyFriends("my").enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){

                    val br = response.body()!!

                    mMyFriendsList.addAll(br.data.friends)
                    mMyFriendsAdapter.notifyDataSetChanged()


                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })

    }


}