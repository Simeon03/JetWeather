package com.example.jetweather.repos.sub

import kotlinx.coroutines.flow.Flow

interface WeeklyWeatherRepo {

    fun fetchWeeklyMinTemp(): Flow<List<Float>>

    fun fetchWeeklyMaxTemp(): Flow<List<Float>>

    fun fetchWeeklyDay(): Flow<List<String>>

    fun fetchWeeklyWeatherStatus(): Flow<List<Int>>

}
