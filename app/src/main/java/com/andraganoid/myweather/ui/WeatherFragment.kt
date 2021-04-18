package com.andraganoid.myweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.WeatherFragmentBinding
import com.andraganoid.myweather.model.AstronomyResponse
import com.andraganoid.myweather.model.CurrentResponse
import com.andraganoid.myweather.ui.common.ItemAdapter
import com.andraganoid.myweather.ui.common.ItemModel
import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.DateFormatter.dateToday
import com.andraganoid.myweather.util.DateFormatter.todayWeekDay
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.logA
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    @Inject
    lateinit var viewModel: WeatherViewModel

    private lateinit var binding: WeatherFragmentBinding

    private lateinit var itemList: ArrayList<ItemModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_fragment, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        //  binding.locationChooser.requestFocus()
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
                    setCurrentWeather(responseState.currentResponse)
                }
                is ResponseState.AstroData -> {
                    setAstronomy(responseState.astronomyResponse)
                }
            }
        }
        )
    }


    private fun setCurrentWeather(currentResponse: CurrentResponse?) {
        binding.currentResponse = currentResponse
        binding.weekDayToday.text = todayWeekDay()
        binding.dateToday.text = dateToday()

        itemList = arrayListOf<ItemModel>()
        itemList.apply {
            add(ItemModel(label = getString(R.string.wind_dir), value = currentResponse?.current?.windDir))
            add(ItemModel(label = getString(R.string.wind_speed), value = currentResponse?.current?.windKph, unit = getString(R.string.kmh)))
            add(ItemModel(label = getString(R.string.wind_gust), value = currentResponse?.current?.gustKph, unit = getString(R.string.kmh)))
            add(ItemModel(label = getString(R.string.pressure), value = currentResponse?.current?.pressureMb, unit = getString(R.string.mbar)))
            add(ItemModel(label = getString(R.string.humidity), value = currentResponse?.current?.humidity, unit = getString(R.string.percent)))
            add(ItemModel(label = getString(R.string.precipitation), value = currentResponse?.current?.precipMm, unit = getString(R.string.mm)))
            add(ItemModel(label = getString(R.string.uv_index), value = currentResponse?.current?.precipMm))
            add(ItemModel(label = getString(R.string.visibility), value = currentResponse?.current?.visKm, unit = getString(R.string.km)))
            add(ItemModel(label = getString(R.string.clouds), value = currentResponse?.current?.cloud, unit = getString(R.string.percent)))
        }
        val detailsAdapter = ItemAdapter(itemList)
        binding.detailsRecView.adapter = detailsAdapter

        itemList = arrayListOf<ItemModel>()
        itemList.apply {
            add(ItemModel(label = getString(R.string.carbon_monoxide), value = currentResponse?.current?.airQuality?.co, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.ozone), value = currentResponse?.current?.airQuality?.o3, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.nitrogen_dioxide), value = currentResponse?.current?.airQuality?.no2, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.sulphur_dioxide), value = currentResponse?.current?.airQuality?.so2, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.pm2_5), value = currentResponse?.current?.airQuality?.pm25, unit = getString(R.string.mgm3)))
            add(ItemModel(label = getString(R.string.pm10), value = currentResponse?.current?.airQuality?.pm10, unit = getString(R.string.mgm3)))
        }
        val airAdapter = ItemAdapter(itemList)
        binding.airRecView.adapter = airAdapter

    }

    private fun setAstronomy(astronomyResponse: AstronomyResponse?) {
        itemList = arrayListOf<ItemModel>()
        itemList.apply {

            add(ItemModel(label = getString(R.string.sunrise), value = DateFormatter.to24hFormat(astronomyResponse?.astronomy?.astro?.sunrise!!)))
            add(ItemModel(label = getString(R.string.moonrise), value = DateFormatter.to24hFormat(astronomyResponse.astronomy?.astro?.moonrise!!)))
            add(ItemModel(label = getString(R.string.moon_phase), value = astronomyResponse?.astronomy?.astro?.moonPhase))
            add(ItemModel(label = getString(R.string.sunset), value = DateFormatter.to24hFormat(astronomyResponse?.astronomy?.astro?.sunset!!)))
            add(ItemModel(label = getString(R.string.moonset), value = DateFormatter.to24hFormat(astronomyResponse?.astronomy?.astro?.moonset!!)))
            add(ItemModel(label = getString(R.string.moon_illumination), value = astronomyResponse?.astronomy?.astro?.moonIllumination))
        }
        val astroAdapter = ItemAdapter(itemList)
        binding.astroRecView.adapter = astroAdapter
    }

}