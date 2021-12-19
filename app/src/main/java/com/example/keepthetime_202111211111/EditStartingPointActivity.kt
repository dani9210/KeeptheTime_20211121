package com.example.keepthetime_202111211111

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_202111211111.databinding.ActivityEditStartingPointBinding

class EditStartingPointActivity : BaseActivity() {

    lateinit var binding : ActivityEditStartingPointBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_starting_point)
        binding.naverMapView.onCreate(savedInstanceState)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {


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