package com.andraganoid.myweather.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.ActivityMainBinding
import com.andraganoid.myweather.ui.WeatherFragment
import com.andraganoid.myweather.ui.WeatherViewModel
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.logA
import com.andraganoid.myweather.util.logD
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
      //  getPermission()
        setup()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, WeatherFragment(), WeatherFragment::class.simpleName)
            .commit()

    }

    override fun onResume() {
        super.onResume()
        logA("RESUME")
        viewModel.repeatLastCall()
    }


    private fun setup() {
        viewModel.weatherData.observe(this, { responseState ->
            when (responseState) {
                is ResponseState.Loading -> {
                    Snackbar.make(binding.root, responseState.loaderMsg, Snackbar.LENGTH_LONG).show()//TODO
                }
                is ResponseState.Error -> {
                    Snackbar.make(binding.root, responseState.errorMsg, Snackbar.LENGTH_LONG).show()//TODO
                }
            }
        }
        )
    }

    private fun start() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, WeatherFragment(), WeatherFragment::class.simpleName)
            .commit()
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

}