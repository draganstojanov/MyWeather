package com.andraganoid.myweather.ui

import androidx.lifecycle.*
import com.andraganoid.myweather.api.ApiRepository
import com.andraganoid.myweather.database.DatabaseRepository
import com.andraganoid.myweather.model.db.QueryModel
import com.andraganoid.myweather.model.response.*
import com.andraganoid.myweather.util.Prefs
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.toQueryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dbRepository: DatabaseRepository,
    private val prefs: Prefs
) : ViewModel() {

    private val _forecastData = MutableLiveData<Forecast?>()
    val forecastData: LiveData<Forecast?>
        get() = _forecastData

    private val _currentData = MutableLiveData<Current?>()
    val currentData: LiveData<Current?>
        get() = _currentData

    private val _locationData = MutableLiveData<Location?>()
    val locationData: LiveData<Location?>
        get() = _locationData

    private val _astronomyData = MutableLiveData<Astronomy?>()
    val astronomyData: LiveData<Astronomy?>
        get() = _astronomyData

    private val _errorData = MutableLiveData<String?>()
    val errorData: LiveData<String?>
        get() = _errorData

    private val _loadingData = MutableLiveData<Boolean?>()
    val loadingData: LiveData<Boolean?>
        get() = _loadingData

    val _showFragment = MutableLiveData<Int>()
    val showFragment: LiveData<Int>
        get() = _showFragment

    private val _getLocation = MutableLiveData(false)
    val getLocation: LiveData<Boolean>
        get() = _getLocation

    var canRepeatLastCall = true
    private var query = ""

    fun getForecast(q: String) {
        viewModelScope.launch {
            query = q
            checkResponse(apiRepository.getForecast(query))
            prefs.saveLastCalledQuery(query)
            getAstronomy(query)
        }
    }

    private fun getAstronomy(query: String) {
        viewModelScope.launch(Dispatchers.Main) {
            checkResponse(apiRepository.getAstronomy(query))
        }
    }

    private suspend fun checkResponse(response: ResponseState) {
        when (response) {
            is ResponseState.ResponseData -> {
                _loadingData.value = false
                when (val resp = response.responseData) {
                    is AstronomyResponse -> _astronomyData.value = resp.astronomy
                    is ForecastResponse -> {
                        _forecastData.value = resp.forecast
                        _locationData.value = resp.location
                        _currentData.value = resp.current
                        dbRepository.saveQuery((response.responseData as ForecastResponse).location?.toQueryModel().also { queryModel -> queryModel?.query = query }!!)
                    }
                }
            }

            is ResponseState.Loading -> _loadingData.value = true
            is ResponseState.Error -> _errorData.value = response.errorMsg
        }
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

    fun getSavedQuerys() = dbRepository.getAllQuerys().asLiveData()

    fun deleteSavedQuery(query: QueryModel) {
        viewModelScope.launch {
            dbRepository.deleteQuery(query)
        }
    }

}

