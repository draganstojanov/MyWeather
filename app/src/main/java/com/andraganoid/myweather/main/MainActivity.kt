package com.andraganoid.myweather.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.andraganoid.myweather.R
import com.andraganoid.myweather.api.ResponseState
import com.andraganoid.myweather.databinding.ActivityMainBinding
import com.andraganoid.myweather.ui.WeatherFragment
import com.andraganoid.myweather.ui.WeatherViewModel
import com.andraganoid.myweather.util.actionSnackbar
import com.andraganoid.myweather.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setup()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, WeatherFragment(), WeatherFragment::class.simpleName)
            .commit()
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.repeatLastCall()
    }

    private fun setup() {
        viewModel.weatherData.observe(this, { responseState ->
            when (responseState) {
                is ResponseState.Loading -> {
                    binding.root.showSnackbar("LOADING")
                }
                is ResponseState.Error -> {
                    binding.root.actionSnackbar(responseState.message) {}
                }
            }
        }
        )
    }
}