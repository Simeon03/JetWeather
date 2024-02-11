package com.example.jetweather.viewmodel.repositories

import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {

    fun fetchCurrentTemperature(): Flow<Float>

    fun fetchCurrentWeatherStatus(): Flow<Int?>

    fun fetchCurrentMinTemp(): Flow<Float>

    fun fetchCurrentMaxTemp(): Flow<Float>

    fun fetchCurrentSunsetTime(): Flow<String>

    fun fetchCurrentSunriseTime(): Flow<String>

}
