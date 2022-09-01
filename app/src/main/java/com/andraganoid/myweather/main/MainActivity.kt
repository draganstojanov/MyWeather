package com.andraganoid.myweather.main

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.andraganoid.connectivity.ConnectivityState
import com.andraganoid.myweather.R
import com.andraganoid.myweather.api.ResponseState
import com.andraganoid.myweather.databinding.ActivityMainBinding
import com.andraganoid.myweather.ui.WeatherFragment
import com.andraganoid.myweather.ui.WeatherViewModel
import com.andraganoid.myweather.util.actionSnackbar
import com.andraganoid.myweather.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()

    @Inject
    lateinit var conn: ConnectivityState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, WeatherFragment(), WeatherFragment::class.simpleName)
            .commit()
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.repeatLastCall()
    }

    private fun setup() {
        conn.setListeners()
        viewModel.weatherData.observe(this) { responseState ->
            when (responseState) {
                is ResponseState.Loading -> {
                    binding.root.showSnackbar("LOADING")
                }
                is ResponseState.Error -> {
                    binding.root.actionSnackbar(responseState.message) {}
                }
                else -> {}
            }
        }
    }
}