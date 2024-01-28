package com.example.jetweather.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.data.CurrentWeather
import com.example.jetweather.data.WeeklyWeather
import com.example.jetweather.helper.formatTemp
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

    init {
        fetchCurrentTemperature()
    }

    fun fetchWeatherData(): Flow<CurrentWeather> = flow {
        val response = weatherApi.getWeatherData(52.52f, 13.41f)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            // Handle error
        }
    }.flowOn(Dispatchers.IO)

    fun fetchWeeklyWeatherData(): Flow<WeeklyWeather> = flow {
        val response = weatherApi.getWeeklyWeatherData(52.52f, 13.41f)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            // Handle error
        }
    }.flowOn(Dispatchers.IO)

    fun fetchLocationText(): Flow<String> = repo.fetchLocationText()

    private fun fetchCurrentTemperature() {
        viewModelScope.launch {
            repo.fetchCurrentTemperatureText().collect {
                currentTempText.value = it
            }
        }
    }

    fun fetchMinMaxTemperature(currentWeather: CurrentWeather?): String {
        val minTemp = currentWeather?.maxMinTemperature?.minTemperature?.get(0)?.toInt()
        val maxTemp = currentWeather?.maxMinTemperature?.maxTemperature?.get(0)?.toInt()
        val temperatureSuffix = currentWeather?.weatherFormat?.temperatureUnit
        val formattedTemp = formatTemp(temperatureSuffix ?: "")
        return "$minTemp$formattedTemp/$maxTemp$formattedTemp"
    }

    fun fetchTempSuffix(weeklyWeather: WeeklyWeather?): String {
        return weeklyWeather?.maxMinTemperatureUnit?.maxTemperatureUnit ?: ""
    }

    fun fetchWeatherStatus(currentWeather: CurrentWeather?): String {
        return weatherCode[currentWeather?.data?.weatherCode].toString()
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

    companion object {
        private const val OPEN_METEO_BASE_API = "https://api.open-meteo.com/"
    }
}