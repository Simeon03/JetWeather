package com.example.jetweather.viewmodel

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun fetchLocationText(): Flow<String>

    fun fetchCurrentTemperatureText(): Flow<String>

    fun fetchCurrentWeatherStatusText(): Flow<String>
}