package com.example.jetweather.viewmodel.repositories

import kotlinx.coroutines.flow.Flow

interface WeeklyWeatherRepository {

    fun fetchWeeklyMinTempText(): Flow<List<Int>>

    fun fetchWeeklyMaxTempText(): Flow<List<Int>>

    fun fetchDayOfWeek(): Flow<List<String>>

    fun fetchWeeklyWeatherCode(): Flow<List<Int>>

}
