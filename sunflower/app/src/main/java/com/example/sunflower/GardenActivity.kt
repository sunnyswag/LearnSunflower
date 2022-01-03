package com.example.sunflower

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import com.example.sunflower.databinding.ActivityGardenBinding

class GardenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityGardenBinding>(this, R.layout.activity_garden)
    }
}