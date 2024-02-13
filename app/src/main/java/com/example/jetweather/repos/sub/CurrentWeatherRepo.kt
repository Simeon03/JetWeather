package com.example.jetweather.repos.sub

import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepo {

    fun fetchCurrentTemperature(): Flow<Float>

    fun fetchCurrentApparentTemperature(): Flow<Float>

    fun fetchCurrentWeatherStatus(): Flow<Int?>

    fun fetchCurrentMinTemp(): Flow<Float>

    fun fetchCurrentMaxTemp(): Flow<Float>

    fun fetchCurrentSunsetTime(): Flow<String>

    fun fetchCurrentSunriseTime(): Flow<String>

}
