package com.andraganoid.myweather.ui.current

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
import com.andraganoid.myweather.model.response.*
import com.andraganoid.myweather.ui.WeatherViewModel
import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class CurrentFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()

    private lateinit var binding: CurrentFragmentBinding
    private lateinit var itemList: ArrayList<ItemModel>
    lateinit var detailsAdapter: ItemAdapter
    lateinit var astroAdapter: ItemAdapter
    lateinit var airAdapter: ItemAdapter

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
        viewModel.weatherData.observe(viewLifecycleOwner, { responseState ->
            if (responseState is ResponseState.ResponseData) {
                val resp = responseState.responseData
                when (resp) {
                    is CurrentResponse -> {
                        if (resp.current != null) {
                            if (resp.location != null) {
                                setCurrentWeather(resp.current, resp.location)
                            }
                        } else {
                            binding.rootScrollView.isVisible = false
                        }
                    }
                    is AstronomyResponse -> {
                        if (resp.astronomy != null) {
                            setAstronomy(resp)
                        } else {
                            binding.astroRecView.isVisible = false
                        }
                    }
                    is ForecastResponse -> {
                        if (resp.forecast!= null) {
                            if (resp.current != null && resp.location != null) {
                                setCurrentWeather(resp.current, resp.location)
                            }
                        } else {
                            binding.rootScrollView.isVisible = false
                        }
                    }
                }
            } else if (responseState is ResponseState.Loading) {
                binding.loading = true
            }
        }
        )
    }


    private fun setCurrentWeather(current: Current?, location: Location?) {
        hideKeyboard(binding.root)
        viewModel._showFragment.value = 0
        binding.loading = false


        binding.current = current
        binding.location = location
        binding.weekDayToday.text = DateFormatter.todayWeekDay()
        binding.dateToday.text = DateFormatter.dateToday()

        itemList = arrayListOf<ItemModel>()
        itemList.apply {
            add(ItemModel(label = getString(R.string.wind_dir), value = current?.windDir))
            add(ItemModel(label = getString(R.string.wind_speed), value = current?.windKph, unit = getString(R.string.kmh)))
            add(ItemModel(label = getString(R.string.wind_gust), value = current?.gustKph, unit = getString(R.string.kmh)))
            add(ItemModel(label = getString(R.string.pressure), value = current?.pressureMb, unit = getString(R.string.mbar)))
            add(ItemModel(label = getString(R.string.humidity), value = current?.humidity, unit = getString(R.string.percent)))
            add(ItemModel(label = getString(R.string.precipitation), value = current?.precipMm, unit = getString(R.string.mm)))
            add(ItemModel(label = getString(R.string.uv_index), value = current?.precipMm))
            add(ItemModel(label = getString(R.string.visibility), value = current?.visKm, unit = getString(R.string.km)))
            add(ItemModel(label = getString(R.string.clouds), value = current?.cloud, unit = getString(R.string.percent)))
        }
        detailsAdapter.itemList = itemList

        itemList = arrayListOf<ItemModel>()
        itemList.apply {
            add(ItemModel(label = getString(R.string.carbon_monoxide), value = current?.airQuality?.co, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.ozone), value = current?.airQuality?.o3, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.nitrogen_dioxide), value = current?.airQuality?.no2, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.sulphur_dioxide), value = current?.airQuality?.so2, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.pm2_5), value = current?.airQuality?.pm25, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.pm10), value = current?.airQuality?.pm10, unit = getString(R.string.mgm3)))
        }

        airAdapter.itemList = itemList
        binding.rootScrollView.isVisible = true
    }

    private fun setAstronomy(astronomyResponse: AstronomyResponse?) {
        itemList = arrayListOf<ItemModel>()
        itemList.apply {

            add(ItemModel(label = getString(R.string.sunrise), value = DateFormatter.to24hFormat(astronomyResponse?.astronomy?.astro?.sunrise!!)))
            add(ItemModel(label = getString(R.string.moonrise), value = DateFormatter.to24hFormat(astronomyResponse.astronomy.astro.moonrise!!)))
            add(ItemModel(label = getString(R.string.moon_phase), value = astronomyResponse.astronomy.astro.moonPhase))
            add(ItemModel(label = getString(R.string.sunset), value = DateFormatter.to24hFormat(astronomyResponse.astronomy.astro.sunset!!)))
            add(ItemModel(label = getString(R.string.moonset), value = DateFormatter.to24hFormat(astronomyResponse.astronomy.astro.moonset!!)))
            add(ItemModel(label = getString(R.string.moon_illumination), value = astronomyResponse.astronomy.astro.moonIllumination))
        }

        astroAdapter.itemList = itemList
        binding.astroRecView.isVisible = true
    }

}