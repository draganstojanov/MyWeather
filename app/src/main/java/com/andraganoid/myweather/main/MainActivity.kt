package com.andraganoid.myweather.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andraganoid.myweather.R
import com.andraganoid.myweather.ui.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, WeatherFragment(), WeatherFragment::class.simpleName)
            .commit()
    }
}