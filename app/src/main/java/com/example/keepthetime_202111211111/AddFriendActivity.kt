package com.example.keepthetime_202111211111

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime_202111211111.adapters.SearchedRecyclerAdapter
import com.example.keepthetime_202111211111.databinding.ActivityAddFriendBinding
import com.example.keepthetime_202111211111.datas.BasicResponse
import com.example.keepthetime_202111211111.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFriendActivity : BaseActivity() {

    lateinit var binding : ActivityAddFriendBinding

    val mSearchedUserList = ArrayList<UserData>()
    lateinit var mAdapter: SearchedRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_add_friend)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnSearch.setOnClickListener {

//            검색 눌리면 -> API 호출, 목록 가져오기.

            val inputKeyword = binding.edtNickname.text.toString()

//            최소 2글자 이상

            if (inputKeyword.length <2 ){

                Toast.makeText(mContext, "최소 2자 이상은 입력해야합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            apiService.getRequestSearchFriend(inputKeyword).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if(response.isSuccessful){

                        val br = response.body()!!

//                        기존 검색 목록은 전부 삭제

                        mSearchedUserList.clear()

//                        검색된 사용자 목록을 -> 멤버변수 ArrayList에 추가

                        mSearchedUserList.addAll(br.data.users)
                        mAdapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }


            })

        }

    }

    override fun setValues() {

        mAdapter = SearchedRecyclerAdapter(mContext,mSearchedUserList)
        binding.serchFriendRecyclerView.adapter = mAdapter
        binding.serchFriendRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }
}