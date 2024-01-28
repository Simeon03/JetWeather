package com.example.jetweather.viewmodel

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun fetchLocationText(): Flow<String>

    fun fetchCurrentTemperatureText(): Flow<String>

    fun fetchCurrentWeatherStatusText(): Flow<String>

    fun fetchCurrentMinTempText(): Flow<Int>

    fun fetchCurrentMaxTempText(): Flow<Int>

    fun fetchWeeklyMinTempText(): Flow<List<Int>>

    fun fetchWeeklyMaxTempText(): Flow<List<Int>>

    fun fetchDayOfWeek(): Flow<List<String>>

    fun fetchWeeklyWeatherCode(): Flow<List<Int>>
}