package com.andraganoid.myweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andraganoid.myweather.api.WeatherRepository
import com.andraganoid.myweather.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _weatherData = MutableLiveData<ResponseState>()
    val weatherData: LiveData<ResponseState>
        get() = _weatherData

    init {
     //   getCurrentWeather()
        getAstronomy()
        getForecast()
    }

    private fun getCurrentWeather() {
        viewModelScope.launch { _weatherData.postValue(weatherRepository.getCurrentWeather()) }
    }

    private fun getAstronomy() {
        viewModelScope.launch { _weatherData.postValue(weatherRepository.getAstronomy()) }
    }

    private fun getForecast() {
        viewModelScope.launch { _weatherData.postValue(weatherRepository.getForecast()) }
    }

}