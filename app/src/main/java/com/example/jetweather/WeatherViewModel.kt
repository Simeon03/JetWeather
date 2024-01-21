package com.example.jetweather

import androidx.lifecycle.ViewModel
import com.example.jetweather.data.CurrentWeather
import com.example.jetweather.model.WeatherApiService
import com.example.jetweather.model.WeatherInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherViewModel : ViewModel() {

    private val weatherApi = WeatherInstance.getInstance().create(WeatherApiService::class.java)

    fun fetchWeatherData(): Flow<CurrentWeather> = flow {
        val response = weatherApi.getWeatherData(52.52f, 13.41f)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            // Handle error
        }
    }.flowOn(Dispatchers.IO)

    fun fetchCurrentTemperature(currentWeather: CurrentWeather?): String {
        val currentTemperature = currentWeather?.data?.temperature?.toInt()
        val temperatureSuffix = currentWeather?.weatherFormat?.temperatureUnit
        return "$currentTemperature $temperatureSuffix"
    }

    fun fetchMinMaxTemperature(currentWeather: CurrentWeather?): String {
        val minTemp = currentWeather?.maxMinTemperature?.minTemperature?.get(0)?.toInt()
        val maxTemp = currentWeather?.maxMinTemperature?.maxTemperature?.get(0)?.toInt()
        val temperatureSuffix = currentWeather?.weatherFormat?.temperatureUnit
        return "$minTemp $temperatureSuffix / $maxTemp $temperatureSuffix"
    }

    fun fetchWeatherStatus(currentWeather: CurrentWeather?): String {
        return weatherCode[currentWeather?.data?.weatherCode].toString()
    }
}