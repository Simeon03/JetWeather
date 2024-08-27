package com.example.jetweather.repos.sub

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.DefaultWeatherRepo
import kotlinx.coroutines.flow.Flow

interface WeeklyWeatherRepo {

    fun fetchMinTemp(): Flow<List<Float>>

    fun fetchMaxTemp(): Flow<List<Float>>

    fun fetchDay(): Flow<List<String>>

    fun fetchWeatherStatus(): Flow<List<Int>>

}

class DefaultWeeklyWeatherRepository(
    private val weatherApi: OpenMeteo,
    private val weatherRepo: DefaultWeatherRepo,
): WeeklyWeatherRepo {

    override fun fetchMinTemp(): Flow<List<Float>> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getWeeklyWeather(lat, long, unit) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.minTemperature },
        defaultValue = emptyList<Float>(),
    )

    override fun fetchMaxTemp(): Flow<List<Float>> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getWeeklyWeather(lat, long, unit) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.maxTemperature },
        defaultValue = emptyList<Float>(),
    )

    override fun fetchDay(): Flow<List<String>> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getWeeklyWeather(lat, long, unit) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.time },
        defaultValue = emptyList<String>(),
    )

    override fun fetchWeatherStatus(): Flow<List<Int>> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getWeeklyWeather(lat, long, unit) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.weatherCode },
        defaultValue = emptyList<Int>(),
    )
}
