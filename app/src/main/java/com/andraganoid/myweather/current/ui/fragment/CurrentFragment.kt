package com.andraganoid.myweather.current.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andraganoid.myweather.R
import com.andraganoid.myweather.current.ui.adapter.ItemAdapter
import com.andraganoid.myweather.current.ItemBuilder
import com.andraganoid.myweather.util.network.ResponseState
import com.andraganoid.myweather.databinding.CurrentFragmentBinding
import com.andraganoid.myweather.util.network.model.response.Astronomy
import com.andraganoid.myweather.util.network.model.response.AstronomyResponse
import com.andraganoid.myweather.util.network.model.response.ForecastResponse
import com.andraganoid.myweather.weather.viewModel.WeatherViewModel
import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CurrentFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()

    @Inject
    lateinit var itemBuilder: ItemBuilder

    private lateinit var binding: CurrentFragmentBinding
    private lateinit var detailsAdapter: ItemAdapter
    private lateinit var astroAdapter: ItemAdapter
    private lateinit var airAdapter: ItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.current_fragment, container, false)
        setup()
        return binding.root
    }

    private fun setup() {

        detailsAdapter = ItemAdapter()
        binding.detailsRecView.apply {
            adapter = detailsAdapter
            itemAnimator = null
        }
        airAdapter = ItemAdapter()
        binding.airRecView.apply {
            adapter = airAdapter
            itemAnimator = null
        }
        astroAdapter = ItemAdapter()
        binding.astroRecView.apply {
            adapter = astroAdapter
            itemAnimator = null
        }

        binding.refreshBtn.setOnClickListener {
            viewModel.repeatLastCall()
        }
        viewModel.weatherData.observe(viewLifecycleOwner) { responseState ->
            when (responseState) {
                is ResponseState.Loading -> binding.loading = true
                is ResponseState.Success -> {
                    when (responseState.data) {
                        is AstronomyResponse -> setAstronomy((responseState.data).astronomy)
                        is ForecastResponse -> setCurrentWeather(responseState.data)
                    }
                }
                else -> {}
            }
        }
    }

    private fun setCurrentWeather(forecastResponse: ForecastResponse?) {
        hideKeyboard(binding.root)
        viewModel.setViewPagerCurrentItem(0)
        binding.loading = false

        binding.current = forecastResponse?.current
        binding.location = forecastResponse?.location
        binding.weekDayToday.text = DateFormatter.todayWeekDay()
        binding.dateToday.text = DateFormatter.dateToday()

        detailsAdapter.itemList = itemBuilder.detailsList(forecastResponse?.current)
        airAdapter.itemList = itemBuilder.airList(forecastResponse?.current)
        binding.rootScrollView.isVisible = true
    }

    private fun setAstronomy(astronomy: Astronomy?) {
        astroAdapter.itemList = itemBuilder.astroList(astronomy)
        binding.astroRecView.isVisible = true
    }

}