package com.example.keepthetime_202111211111

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_202111211111.databinding.ActivityEditStartingPointBinding
import com.example.keepthetime_202111211111.datas.BasicResponse
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditStartingPointActivity : BaseActivity() {

    var mSelectedLatLng: LatLng? = null
    var mSelectedMarker: Marker? = null

    lateinit var binding : ActivityEditStartingPointBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_starting_point)
        binding.naverMapView.onCreate(savedInstanceState)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

        binding.btnSaveStartingPoint.setOnClickListener {



            val inputPlace = binding.edtPlace.text.toString()

            apiService.postRequestAddPlace(
                inputPlace,
                mSelectedLatLng!!.latitude,
                mSelectedLatLng!!.longitude,
                true).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }


            })




            if (mSelectedLatLng == null) {

                Toast.makeText(mContext, "약속 장소를 지도에서 선택해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }



        }



    }

    override fun setValues() {

        binding.naverMapView.getMapAsync {

            val naverMap = it

            naverMap.setOnMapClickListener { pointF,latLng ->

//                클릭이 될때 마다 생성자 호출 =>  매번 새 마커 그려주기.
//                단 하나의 마커만 유지하자. => 아직 안그려졌을때만 생성하자.

//                val marker = Marker()
//
//                marker.position = latLng
//                marker.map = naverMap





                if ( mSelectedMarker == null) {

//                    멤버변수로 만들어 둔 마커가 null 일때만 생성.  => 하나의 객체를 유지.

                    mSelectedMarker = Marker()

                }

                mSelectedMarker!!.position = latLng
                mSelectedMarker!!.map = naverMap

//                클릭한 위치 (latlng) 로 카메라 이동 => 마커가 가운데 위치

                val cameraUpdate = CameraUpdate.scrollTo(latLng)
                naverMap.moveCamera(cameraUpdate)


            }

//


        }


    }
    override fun onStart() {
        super.onStart()
        binding.naverMapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.naverMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.naverMapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.naverMapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.naverMapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.naverMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.naverMapView.onLowMemory()
    }

}