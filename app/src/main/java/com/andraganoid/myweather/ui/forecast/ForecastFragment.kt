package com.andraganoid.myweather.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.ForecastFragmentBinding
import com.andraganoid.myweather.model.response.ForecastResponse
import com.andraganoid.myweather.ui.WeatherViewModel
import com.andraganoid.myweather.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForecastFragment : Fragment() {


    private val viewModel: WeatherViewModel by activityViewModels()

    private lateinit var binding: ForecastFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.forecast_fragment, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        viewModel.weatherData.observe(viewLifecycleOwner, { responseState ->
            if (responseState is ResponseState.ForecastData) {
                if (responseState.forecastResponse != null) {
                    setForecastWeather(responseState.forecastResponse)
                } else {
                    binding.daysRecView.isVisible = false
                }
            }
        }
        )
    }

    private fun setForecastWeather(forecastResponse: ForecastResponse) {
        val dayAdapter = DayAdapter(forecastResponse.forecast?.forecastday)
        binding.daysRecView.apply {
            adapter = dayAdapter
            isVisible = true
        }
    }


}