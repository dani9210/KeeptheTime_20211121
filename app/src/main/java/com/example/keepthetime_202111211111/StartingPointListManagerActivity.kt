package com.example.keepthetime_202111211111

import android.content.Intent
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

        binding.btnAddStartingPoint.setOnClickListener {

            val myIntent = Intent(mContext,EditStartingPointActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {

    }
}