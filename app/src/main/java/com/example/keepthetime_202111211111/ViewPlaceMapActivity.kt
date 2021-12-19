package com.example.keepthetime_202111211111

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_202111211111.databinding.ActivityViewPalceMapBinding
import com.example.keepthetime_202111211111.datas.ScheduleData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import com.odsay.odsayandroidsdk.API
import com.odsay.odsayandroidsdk.ODsayData
import com.odsay.odsayandroidsdk.ODsayService
import com.odsay.odsayandroidsdk.OnResultCallbackListener

class ViewPlaceMapActivity : BaseActivity() {

    lateinit var binding: ActivityViewPalceMapBinding

    lateinit var mScheduleData: ScheduleData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_palce_map)
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

            val coord = LatLng(mScheduleData.latitude, mScheduleData.longitude)

            val cameraUpdate = CameraUpdate.scrollTo(coord)
            naverMap.moveCamera(cameraUpdate)

            val marker = Marker()
            marker.position = coord
            marker.map = naverMap

//            추가 기능 체험 - 정보창( 말풍선) => 마커에 반영

            val infoWindow = InfoWindow()
//            infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(mContext) {
//                override fun getText(p0: InfoWindow): CharSequence {
//                    return mScheduleData.place
//                }
//
//
//            }

            infoWindow.open(marker)

            naverMap.setOnMapClickListener { pointF, latLng ->

//                지도 아무데나 클릭하면, 정보창 닫기

                infoWindow.close()


            }


            marker.setOnClickListener {

                if (marker.infoWindow == null) {

//                    정보창이 안열려있다

                    infoWindow.open(marker)

                } else {

                    infoWindow.close()
                }

                return@setOnClickListener true
            }


            //            ODsay 라이브러리 재횔용 -> JSON 파싱 => 출발지 ~ 도착지 대중교통경로 지도에 표시.
//            복잡한 모양의 JSONObject 직접 파싱 복습 => ~12:30 분


//            출발지 정보~ 도착지 API 호출
//            or 본인 집 좌표 직접 출발지 지정

            val startingPoint = LatLng(37.79304201000072, 127.07423972566195)


            val mODsayService = ODsayService.init(mContext, resources.getString(R.string.odsay_key))

            mODsayService.requestSearchPubTransPath(
                startingPoint.longitude.toString(),
                startingPoint.latitude.toString(),
                coord.longitude.toString(),
                coord.latitude.toString(),
                null,
                null,
                null,
                object : OnResultCallbackListener {
                    override fun onSuccess(p0: ODsayData?, p1: API?) {


                        val jsonObj = p0!!.json


                        val transCoords = ArrayList<LatLng>()

                        transCoords.add(startingPoint)

                        val resultObj = jsonObj.getJSONObject("result")
                        val pathArr = resultObj.getJSONArray("path")

//                        만약 추천경로가 안나오면 중간 좌표추가 X

                        if (pathArr.length() > 0) {

                            val firstRecommendPath = pathArr.getJSONObject(0)

                            val subPathArr = firstRecommendPath.getJSONArray("subPath")

//                            세부 경로들 하나씩 꺼내보자 -> 정거장 목록이 있는가 ? 추가검사

                            for (i in 0 until subPathArr.length()) {

                                val subPathObj = subPathArr.getJSONObject(i)

//                                정거장 목록이 null이 아닌가? =>  내려 주는가?

                                if (!subPathObj.isNull("passStopList")) {

//                                    실제 정거장 목록 추출 -> tranCoords에 추가등록

                                    val passStopListObj = subPathObj.getJSONObject("passStopList")
                                    val stationsArr = passStopListObj.getJSONArray("stations")

                                    for (j in 0 until stationsArr.length()) {

                                        val stationsObj = stationsArr.getJSONObject(j)

//                                        x : 경도 (lng), y : 위도 (lat)

                                        val lat = stationsObj.getString("y").toDouble()
                                        val lng = stationsObj.getString("x").toDouble()

//                                        네이버 지도 좌표로 가공

                                        val naverLatLng = LatLng(lat, lng)

//                                        교통 좌표 목록에 추가

                                        transCoords.add(naverLatLng)


                                    }


                                }


                            }


                        }

//                        마지막좌표 : 도착지를 등록.
                        transCoords.add(coord)

//                        경로는 지도 상세화면에서는, 한번만 그려줄 생각.

                        val path = PathOverlay()
                        path.coords = transCoords
                        path.map = naverMap


//                        말풍선의 내용을, 경로찾기가 끝나고 나서 세팅.
//                        커스텀 뷰를 => 말풍선 내에 띄워보자.
//                        네이버 지도 기능 (설명 x) + 안드로이드 코딩 지식 활용 =>
                        infoWindow.adapter = object : InfoWindow.DefaultViewAdapter(mContext){
                            override fun getContentView(p0: InfoWindow): View {

//                                말풍선에 들어갈 xml그리고 => inflate => getContentView 함수의 결과로 지정.

                            }
                        }

                    }

                    override fun onError(p0: Int, p1: String?, p2: API?) {

                    }


                }

            )


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