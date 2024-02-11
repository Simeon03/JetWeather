package com.example.jetweather.repos.subrepos

import kotlinx.coroutines.flow.Flow

interface HourlyWeatherRepo {

    fun fetchHourlyTemperature(): Flow<List<Float>>

    fun fetchHourlyTime(): Flow<List<String>>

    fun fetchHourlyWeatherStatus(): Flow<List<Int>>

    fun fetchHourlyHumidity(): Flow<List<Int>>

}
