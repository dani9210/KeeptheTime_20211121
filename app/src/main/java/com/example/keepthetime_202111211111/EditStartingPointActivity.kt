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
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}