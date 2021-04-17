package com.andraganoid.myweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.WeatherFragmentBinding
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.logA
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    @Inject
    lateinit var viewModel: WeatherViewModel

    private lateinit var binding: WeatherFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_fragment, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        binding.locationChooser.requestFocus()
        viewModel.weatherData.observe(viewLifecycleOwner, { responseState ->
            logA(responseState.toString())
            when (responseState) {
                is ResponseState.Loading -> {
                    Snackbar.make(binding.root, responseState.loaderMsg, Snackbar.LENGTH_LONG).show()//TODO
                }
                is ResponseState.Error -> {
                    Snackbar.make(binding.root, responseState.errorMsg, Snackbar.LENGTH_LONG).show()//TODO
                }
                is ResponseState.CurrentWeather -> {
                    responseState.currentResponse.let { binding.currentResponse = it }
                }
            }
        }
        )
    }


}