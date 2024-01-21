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
}