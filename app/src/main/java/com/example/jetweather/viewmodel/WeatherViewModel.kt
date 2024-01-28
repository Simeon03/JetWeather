package com.example.jetweather.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.data.WeeklyWeather
import com.example.jetweather.helper.getDayOfWeek
import com.example.jetweather.helper.weatherCode
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.model.apiservice.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {

    private val weatherApi = RetrofitInstance.getInstance(OPEN_METEO_BASE_API).create(
        WeatherApiService::class.java)

    var currentTempText = MutableStateFlow("Fetch")
    var currentLocationText = MutableStateFlow("Location")
    var currentWeatherStatusText = MutableStateFlow("Status")
    var currentMinTempText = MutableStateFlow(0)
    var currentMaxTempText = MutableStateFlow(0)

    init {
        fetchCurrentTemperature()
        fetchLocationText()
        fetchCurrentWeatherStatus()
        fetchCurrentMinTemp()
        fetchCurrentMaxTemp()
    }

    private fun fetchLocationText() {
        viewModelScope.launch {
            repo.fetchLocationText().collect {
                currentLocationText.value = it
            }
        }
    }

    private fun fetchCurrentTemperature() {
        viewModelScope.launch {
            repo.fetchCurrentTemperatureText().collect {
                currentTempText.value = it
            }
        }
    }

    private fun fetchCurrentWeatherStatus() {
        viewModelScope.launch {
            repo.fetchCurrentWeatherStatusText().collect {
                currentWeatherStatusText.value = it
            }
        }
    }

    private fun fetchCurrentMinTemp() {
        viewModelScope.launch {
            repo.fetchCurrentMinTempText().collect() {
                currentMinTempText.value = it
            }
        }
    }

    private fun fetchCurrentMaxTemp() {
        viewModelScope.launch {
            repo.fetchCurrentMaxTempText().collect() {
                currentMaxTempText.value = it
            }
        }
    }

    fun fetchDailyMaxTemperature(weeklyWeather: WeeklyWeather?, index: Int): Int {
        return weeklyWeather?.dailyTemperature?.maxTemperature?.get(index)?.toInt() ?: 0
    }

    fun fetchDailyMinTemperature(weeklyWeather: WeeklyWeather?, index: Int): Int {
        return weeklyWeather?.dailyTemperature?.minTemperature?.get(index)?.toInt() ?: 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchDayOfWeek(weeklyWeather: WeeklyWeather?, index: Int): String {
        return getDayOfWeek(weeklyWeather?.dailyTemperature?.time?.get(index) ?: "2023-02-01")
    }

    fun fetchDailyWeatherCode(weeklyWeather: WeeklyWeather?, index: Int): Int {
        return weeklyWeather?.dailyTemperature?.weatherCode?.get(index) ?: 0
    }

    fun fetchDailyWeatherCodeDesc(weeklyWeather: WeeklyWeather?, index: Int): String {
        return weatherCode[weeklyWeather?.dailyTemperature?.weatherCode?.get(index)] ?: "0"
    }

    fun fetchWeeklyWeatherData(): Flow<WeeklyWeather> = flow {
        val response = weatherApi.getWeeklyWeatherData(52.52f, 13.41f)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            // Handle error
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val OPEN_METEO_BASE_API = "https://api.open-meteo.com/"
    }
}