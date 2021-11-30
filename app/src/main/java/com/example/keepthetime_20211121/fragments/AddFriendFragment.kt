package com.example.keepthetime_20211121.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime_20211121.R
import com.example.keepthetime_20211121.adapters.MyFriendsRecyclerAdapter
import com.example.keepthetime_20211121.adapters.RequestedFriendsRecyclerAdapter
import com.example.keepthetime_20211121.databinding.FragmentAddFriendBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import com.example.keepthetime_20211121.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddFriendFragment : BaseFragment() {

    lateinit var binding : FragmentAddFriendBinding

    val mMyFriendsList = ArrayList<UserData>()

    lateinit var mRequestedFriendsRecyclerAdapter: RequestedFriendsRecyclerAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_friend, container, false)
        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setEvents()
        setValues()
    }


    override fun setEvents() {
        


    }

    override fun setValues() {

        mRequestedFriendsRecyclerAdapter = RequestedFriendsRecyclerAdapter(mContext,mMyFriendsList)
        binding.myFriendsRecyclerView.adapter = mRequestedFriendsRecyclerAdapter
        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(mContext)


        getMyFriendsFromServer()




    }

    fun getMyFriendsFromServer(){

        apiService.getRequestMyFriends("requested").enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){

                    val br = response.body()!!
                    mMyFriendsList.addAll(br.data.friends)
                    mRequestedFriendsRecyclerAdapter.notifyDataSetChanged()

                }




            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })


    }


}