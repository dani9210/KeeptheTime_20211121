package com.example.keepthetime_20211121

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20211121.databinding.ActivityEditAppointmentBinding
import com.example.keepthetime_20211121.datas.BasicResponse
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {


//    약속을 잡을 일시를 저장할 변수(Calendar)

    val mSelectedDateTime = Calendar.getInstance()      // 기본값 : 현재 일시

    lateinit var binding : ActivityEditAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


        binding.txtTime.setOnClickListener {

//            시간 선택 팝업 (TimePickerDialog) 사용 예시

//            1. 선택 완료시 할 일  (OnTimeSetListener설정 -> 변수에 담아두자.

            val timeSetListener = object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {

//                    Log.d("선택된 시간", "${hourOfDay}시 ,${minute}분")

//                    선택한 시간도 실제로 저장

                    mSelectedDateTime.set( Calendar.HOUR_OF_DAY, hourOfDay)
                    mSelectedDateTime.set( Calendar.MINUTE, minute)

//                    선택한 시간을 -> 오후 4:05 형태로  txtTime 에 표시
//                    SimpleDateFormat 활용
                    val timeFormat = SimpleDateFormat("a h:mm")
                    val timeStr = timeFormat.format(mSelectedDateTime.time)

                    binding.txtTime.text = timeStr

                }


            }

//            2. 시간 선택 팝업 출현

            val timePicker = TimePickerDialog(
                mContext,
                timeSetListener,
                mSelectedDateTime.get(Calendar.HOUR_OF_DAY),
                mSelectedDateTime.get(Calendar.MINUTE),
                false
            )

            timePicker.show()

        }

        binding.txtDate.setOnClickListener {

//            날짜 선택 팝업 (DatePickerDialog)  사용 예시

//            선택 완료 시 할 일 (JAVA - Interface 설정 => 변수에 담아두자

            val dateSetListener =  object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    실제로 날짜가 선택되면 할 일 적는 공간.

//                    Log.d("선택한년월일","${year}년 ${month}월 ${dayOfMonth}일 선택됨")

//                    선택된 일시를 저장할 변수에 ,  연/ 월/ 일 을저장

//                    mSelectedDateTime.set(Calendar.YEAR,year)
//                    mSelectedDateTime.set(Calendar.MONTH,month)
//                    mSelectedDateTime.set(Calendar.DAY_OF_MONTH,dayOfMonth)

//                    년/ 월/일 을 한번에 저장하는 set 함수 활용


                    mSelectedDateTime.set(year,month,dayOfMonth)

//                    txtDate 의 문구를 - > 21년  8월 5일 와 같은 양식으로 가공해서 텍스트 세팅.

//                    Calendar를 다룰 양식만 미리 지정.


                    val dateFormat = SimpleDateFormat("yy년 M월 d일")

                    val dateStr = dateFormat.format(mSelectedDateTime.time)

                    binding.txtDate.text = dateStr


                }

            }

//            실제 팝업창 띄우기

//            Kotlin : JAVA 기반 언어 => 월 : 0 ~ 11로 만들어져있음.

//            오늘 날짜 기본으로 띄우도록. => mSelectedDateTime에 저장된 값 활용

            val datePickerDialog = DatePickerDialog(
                mContext,
                dateSetListener,
                mSelectedDateTime.get(Calendar.YEAR),
                mSelectedDateTime.get(Calendar.MONTH),
                mSelectedDateTime.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.show()


        }




        binding.btnOk.setOnClickListener {

//           입력값 검증.  (vaildation)

//          1.  일자 /시간을 모두  선택했는지?

            if (binding.txtDate.text == "날짜 선택" || binding.txtTime.text == "시간선택" ){

//                둘중 하나를 앚기 입력하지 않은 상황.

                Toast.makeText(mContext, "약속 일시를 모두 선택해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

//            2. 선택된 일시가 (mSelectedDateTime)가, 현재시간보다 더 나중 시간인가?
//              => 과거의 약속이라면 앱에서 등록 거부.

//            현재시간을 변수에 저장

            val now = Calendar.getInstance() // 현재시간 (클릭된 시간)을 기록

//             두개의 시간을 양으로 변환해서 대소비교.

            if(mSelectedDateTime.timeInMillis  < now.timeInMillis)  {

//                약속시간이, 현재시간보다 덜 시간이 흐른상태.  (더 이전시간)

//                약속시간은 지금보다 미래여야 의미가 있다.

                Toast.makeText(mContext, "약속시간은 더 미래의 시간으로 설정해주세요", Toast.LENGTH_SHORT).show()

//                 이 함수를 강제 종료.(서버에 데이터 보내는걸 취소)

                return@setOnClickListener


            }



            val inputTitle = binding.edtTitle.text.toString()
//            val inputDataTime = binding.edtDateTime.text.toString()

//            mSelectedDateTime에 저장된 약속 일시를 => Stirng으로 가공 (SimpleDateFormat)= > 서버에 첨부.

            val serverFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val finalDateTimeStr = serverFormat.format(mSelectedDateTime.time)

            val inputPlace = binding.edtPlace.text.toString()
//            val inputLat = binding.edtLatitude.text.toString().toDouble()
//            val inputLng = binding.edtLongitude.text.toString().toDouble()


            apiService.postRequestAppointment(inputTitle,finalDateTimeStr,inputPlace,37.123,127.123).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {



                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }


            })


        }

    }

    override fun setValues() {

        binding.naverMapView.getMapAsync {

//            로딩이 끝난 네이버맵 객체 (인스턴스가) => it 변수에 담겨있다.

            val naverMap = it

//            예시 . 카메라를 본인 집근처로 이동


//            위 경도 정보 => 카메라 이동명령을 변수에 저장만.
            val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.79300809693715, 127.07428264091268))

            naverMap.moveCamera(cameraUpdate)


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