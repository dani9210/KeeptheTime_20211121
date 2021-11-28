package com.example.keepthetime_20211121

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime_20211121.adapters.MyFriendsRecyclerAdapter
import com.example.keepthetime_20211121.adapters.RequestedFriendsRecyclerAdapter
import com.example.keepthetime_20211121.databinding.ActivityViewFriendListBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import com.example.keepthetime_20211121.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewFriendListActivity : BaseActivity() {

    lateinit var binding : ActivityViewFriendListBinding

    val mMyFriendsList = ArrayList<UserData>()
//    lateinit var  mMyFriendsAdapter: MyFriendsRecyclerAdapter

    lateinit var mRequestedFriendsRecyclerAdapter: RequestedFriendsRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_friend_list)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnAddFriend.setOnClickListener {

//            친구 추가 화면으로 이동

            val myIntent = Intent(mContext,AddFriendActivity::class.java)
            startActivity(myIntent)


        }

    }

    override fun setValues() {
        getMyFriendsFromServer()
//        mMyFriendsAdapter = MyFriendsRecyclerAdapter(mContext,mMyFriendsList)

        mRequestedFriendsRecyclerAdapter = RequestedFriendsRecyclerAdapter(mContext,mMyFriendsList)
        binding.myFriendsRecyclerView.adapter = mRequestedFriendsRecyclerAdapter
//        여러 형태로 목록 배치 가능. -> 어떤 형태로 보여줄건지? 리싸이클러뷰에 세팅.
        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(mContext)


    }

    fun getMyFriendsFromServer(){

        apiService.getRequestMyFriends("requested").enqueue(object : Callback<BasicResponse>{
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