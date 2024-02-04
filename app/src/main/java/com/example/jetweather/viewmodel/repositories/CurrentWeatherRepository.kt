package com.example.jetweather.viewmodel.repositories

import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {

    fun fetchCurrentTemperatureText(): Flow<String>

    fun fetchCurrentWeatherStatusText(): Flow<String>

    fun fetchCurrentMinTempText(): Flow<Int>

    fun fetchCurrentMaxTempText(): Flow<Int>

}
