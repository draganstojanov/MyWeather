package com.andraganoid.myweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andraganoid.myweather.api.WeatherRepository
import com.andraganoid.myweather.model.QueryModel
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
     //   getForecast()
      ///  getAstronomy()
        //   getCurrentWeather()
    }


    fun getForecast(queryModel: QueryModel) {
        viewModelScope.launch { _weatherData.postValue(weatherRepository.getForecast(queryModel)) }
     //   getAstronomy(queryModel)
    }

     fun getAstronomy(queryModel: QueryModel) {
        viewModelScope.launch { _weatherData.postValue(weatherRepository.getAstronomy(queryModel)) }
    }

    //    private fun getCurrentWeather() {
//        viewModelScope.launch { _weatherData.postValue(weatherRepository.getCurrentWeather()) }
//    }

}