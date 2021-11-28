package com.example.keepthetime_20211121

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "6d5ebbe9f122c9e3d680e309479cc3b6")



    }
}