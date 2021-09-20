package com.andraganoid.myweather.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.CurrentFragmentBinding
import com.andraganoid.myweather.databinding.ForecastFragmentBinding
import com.andraganoid.myweather.databinding.WeatherFragmentBinding
import com.andraganoid.myweather.model.response.ForecastResponse
import com.andraganoid.myweather.ui.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForecastFragment : Fragment(R.layout.forecast_fragment) {

    private val viewModel: WeatherViewModel by activityViewModels()
    private val binding: ForecastFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        viewModel.weatherData.observe(viewLifecycleOwner, { responseState ->
            if (responseState.data is ForecastResponse) {
                setForecastWeather(responseState.data)
            }
        })
    }

    private fun setForecastWeather(forecast: ForecastResponse?) {
        binding.daysRecView.apply {
            adapter = DayAdapter(forecast?.forecast?.forecastday)
            isVisible = true
        }
    }

}