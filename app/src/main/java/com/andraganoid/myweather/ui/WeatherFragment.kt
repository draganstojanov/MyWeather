package com.andraganoid.myweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andraganoid.myweather.R
import com.andraganoid.myweather.common.ItemAdapter
import com.andraganoid.myweather.common.ItemModel
import com.andraganoid.myweather.databinding.WeatherFragmentBinding
import com.andraganoid.myweather.model.CurrentResponse
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.logA
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
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
                    responseState.currentResponse.let { setCurrentWeather(it) }

                }
            }
        }
        )
    }

    private fun setCurrentWeather(currentResponse: CurrentResponse?) {
        binding.currentResponse = currentResponse
        binding.weekDayToday.text = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
        val dateToday = "${LocalDate.now().dayOfMonth}.${LocalDate.now().month.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH)}"
        binding.dateToday.text = dateToday

        var itemList = arrayListOf<ItemModel>()
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

        val airAdapter = ItemAdapter(itemList)
        binding.airRecView.adapter = detailsAdapter



    }

}