package com.example.jetweather

import androidx.lifecycle.ViewModel
import com.example.jetweather.data.CurrentWeatherData
import com.example.jetweather.data.TodayWeatherData
import com.example.jetweather.model.WeatherApiService
import com.example.jetweather.model.WeatherInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherViewModel : ViewModel() {

    private val weatherApi = WeatherInstance.getInstance().create(WeatherApiService::class.java)

    fun fetchTodayWeatherData(): Flow<TodayWeatherData> = flow {
        val response = weatherApi.getTodayWeatherData(52.52f, 13.41f, "temperature_2m_max,temperature_2m_min")
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            // Handle error, you might want to throw an exception or emit a specific state
        }
    }.flowOn(Dispatchers.IO)

    fun fetchCurrentWeatherData(): Flow<CurrentWeatherData> = flow {
        val response = weatherApi.getCurrentWeather(52.52f, 13.41f, "temperature_2m,weather_code")
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            // Handle error
        }
    }.flowOn(Dispatchers.IO)
}