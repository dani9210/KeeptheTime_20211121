package com.example.keepthetime_202111211111

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_202111211111.databinding.ActivityStartingPointListManagerBinding

class StartingPointListManagerActivity : BaseActivity() {

    lateinit var binding : ActivityStartingPointListManagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_starting_point_list_manager)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}