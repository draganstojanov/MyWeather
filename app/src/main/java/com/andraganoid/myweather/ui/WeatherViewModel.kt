package com.andraganoid.myweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andraganoid.myweather.api.WeatherRepository
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.logA
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _weatherData = MutableLiveData<ResponseState>()
    val weatherData: LiveData<ResponseState>
        get() = _weatherData

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {

        _weatherData.value = ResponseState.Error("current weather")
        viewModelScope.launch { _weatherData.postValue(weatherRepository.getCurrentWeather()) }
    }

}