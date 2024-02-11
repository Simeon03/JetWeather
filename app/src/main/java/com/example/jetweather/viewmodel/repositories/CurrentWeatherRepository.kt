package com.example.jetweather.viewmodel.repositories

import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {

    fun fetchCurrentTemperature(): Flow<Float>

    fun fetchCurrentWeatherStatus(): Flow<Int?>

    fun fetchCurrentMinTemp(): Flow<Float>

    fun fetchCurrentMaxTemp(): Flow<Float>

    fun fetchSunsetTime(): Flow<List<String>>

    fun fetchSunriseTime(): Flow<List<String>>

}
