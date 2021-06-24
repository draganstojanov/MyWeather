package com.andraganoid.myweather.ui

import androidx.lifecycle.*
import com.andraganoid.myweather.api.ApiRepository
import com.andraganoid.myweather.database.DatabaseRepository
import com.andraganoid.myweather.model.db.QueryModel
import com.andraganoid.myweather.util.Prefs
import com.andraganoid.myweather.api.ResponseState
import com.andraganoid.myweather.util.toQueryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dbRepository: DatabaseRepository,
    private val prefs: Prefs
) : ViewModel() {

    private val _weatherData = MutableLiveData<ResponseState>()
    val weatherData: LiveData<ResponseState>
        get() = _weatherData

    val _showFragment = MutableLiveData<Int>()
    val showFragment: LiveData<Int>
        get() = _showFragment

    private val _getLocation = MutableLiveData(false)
    val getLocation: LiveData<Boolean>
        get() = _getLocation

    var canRepeatLastCall = true

    fun getForecast(query: String) {
        viewModelScope.launch {
            val response = apiRepository.getForecast(query)
            if (response is ResponseState.ForecastData) {
                getAstronomy(query)
                dbRepository.saveQuery(response.forecastResponse?.location?.toQueryModel().also { queryModel -> queryModel?.query = query }!!)
            }
            _weatherData.postValue(response)
            prefs.saveLastCalledQuery(query)
        }
    }

    private fun getAstronomy(query: String) {
        viewModelScope.launch { _weatherData.postValue(apiRepository.getAstronomy(query)) }
    }

    fun repeatLastCall() {
        if (canRepeatLastCall) {
            prefs.getLastCalledQuery().toString().also {
                if (it.isNotEmpty()) {
                    getForecast(it)
                } else {
                    _getLocation.value = true
                }
            }
        }
    }

    fun getSavedQuerys() = dbRepository.getAllQueries().asLiveData()

    fun deleteSavedQuery(query: QueryModel) {
        viewModelScope.launch {
            dbRepository.deleteQuery(query)
        }
    }

}

