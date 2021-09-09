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
import com.andraganoid.myweather.databinding.CurrentFragmentBinding
import com.andraganoid.myweather.databinding.ForecastFragmentBinding
import com.andraganoid.myweather.model.response.ForecastResponse
import com.andraganoid.myweather.ui.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()

    private var _binding: ForecastFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ForecastFragmentBinding.inflate(inflater, container, false)
        setup()
        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}