package com.example.jetweather.viewmodel.repositories

import kotlinx.coroutines.flow.Flow

interface WeeklyWeatherRepository {

    fun fetchWeeklyMinTemp(): Flow<List<Float>>

    fun fetchWeeklyMaxTemp(): Flow<List<Float>>

    fun fetchDayOfWeek(): Flow<List<String>>

    fun fetchWeeklyWeatherCode(): Flow<List<Int>>

}
