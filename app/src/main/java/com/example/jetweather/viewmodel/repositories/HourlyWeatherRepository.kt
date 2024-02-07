package com.example.jetweather.viewmodel.repositories

import kotlinx.coroutines.flow.Flow

interface HourlyWeatherRepository {

    fun fetchHourlyTemperature(): Flow<List<Float>>

    fun fetchHourlyTime(): Flow<List<String>>

}