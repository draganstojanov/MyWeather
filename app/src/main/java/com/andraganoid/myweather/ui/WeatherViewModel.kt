package com.andraganoid.myweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andraganoid.myweather.api.WeatherRepository
import com.andraganoid.myweather.util.Prefs
import com.andraganoid.myweather.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository, private val prefs: Prefs) : ViewModel() {

    private val _weatherData = MutableLiveData<ResponseState>()
    val weatherData: LiveData<ResponseState>
        get() = _weatherData

    val _showFragment = MutableLiveData<Int>()
    val showFragment: LiveData<Int>
        get() = _showFragment

    fun getForecast(query: String) {
        viewModelScope.launch {
            val response = weatherRepository.getForecast(query)
            if (response is ResponseState.ForecastData) {
                getAstronomy(query)
            }
            _weatherData.postValue(response)
            prefs.saveLastCalledQuery(query)
        }
    }

    private fun getAstronomy(query: String) {
        viewModelScope.launch { _weatherData.postValue(weatherRepository.getAstronomy(query)) }
    }


    fun repeatLastCall() {
        getForecast(prefs.getLastCalledQuery().toString())
    }

}