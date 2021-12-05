package com.example.keepthetime_20211121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20211121.databinding.ActivityViewPalceMapBinding
import com.example.keepthetime_20211121.datas.ScheduleData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker

class ViewPlaceMapActivity : BaseActivity() {

    lateinit var binding : ActivityViewPalceMapBinding

    lateinit var mScheduleData: ScheduleData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_palce_map)
        binding.naverMapView.onCreate(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mScheduleData = intent.getSerializableExtra("schedule") as ScheduleData

//        0. 프로젝트에 네이버 지도 설치 (완료)

//        1. 화면 (xml 에 네이버 맵 띄워주기

//        2. 네이버 맵 객체를 실제로 얻어내기 -> getMapAsync

        binding.naverMapView.getMapAsync {


            val naverMap = it
//        3. 카메라 이동 / 마커 추가   (받아온 스케쥴의 위도,경도 이용)

//            위치 (좌표)  데이터 객체

            val coord = LatLng( mScheduleData.latitude,mScheduleData.longitude )

          val cameraUpdate = CameraUpdate.scrollTo(coord)
            naverMap.moveCamera(cameraUpdate)

            val marker = Marker()
            marker.position = coord
            marker.map = naverMap

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