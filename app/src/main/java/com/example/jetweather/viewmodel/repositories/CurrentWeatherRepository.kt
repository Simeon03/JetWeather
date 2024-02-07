package com.example.jetweather.viewmodel.repositories

import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {

    fun fetchCurrentTemperature(): Flow<Float>

    fun fetchCurrentWeatherStatusText(): Flow<String?>

    fun fetchCurrentMinTempText(): Flow<Int>

    fun fetchCurrentMaxTempText(): Flow<Int>

}
