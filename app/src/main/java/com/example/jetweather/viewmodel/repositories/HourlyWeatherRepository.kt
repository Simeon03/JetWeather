package com.example.jetweather.viewmodel.repositories

import kotlinx.coroutines.flow.Flow

interface HourlyWeatherRepository {

    fun fetchHourlyTemperature(): Flow<List<Float>>

    fun fetchHourlyTime(): Flow<List<String>>

    fun fetchHourlyWeatherStatus(): Flow<List<Int>>

    fun fetchHourlyHumidity(): Flow<List<Int>>

}
