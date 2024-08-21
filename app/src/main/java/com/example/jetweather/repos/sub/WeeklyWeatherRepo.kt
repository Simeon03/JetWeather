package com.example.jetweather.repos.sub

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.BaseWeatherRepository
import kotlinx.coroutines.flow.Flow

interface WeeklyWeatherRepo {

    fun fetchMinTemp(): Flow<List<Float>>

    fun fetchMaxTemp(): Flow<List<Float>>

    fun fetchDay(): Flow<List<String>>

    fun fetchWeatherStatus(): Flow<List<Int>>

}

class DefaultWeeklyWeatherRepository(
    private val weatherApi: OpenMeteo,
    locationProvider: LocationProvider,
): BaseWeatherRepository(locationProvider), WeeklyWeatherRepo {

    override fun fetchMinTemp(): Flow<List<Float>> = handleResponseNew(
        response = { lat, long -> weatherApi.getWeeklyWeather(lat, long) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.minTemperature },
        defaultValue = emptyList<Float>()
    )

    override fun fetchMaxTemp(): Flow<List<Float>> = handleResponseNew(
        response = { lat, long -> weatherApi.getWeeklyWeather(lat, long) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.maxTemperature },
        defaultValue = emptyList<Float>()
    )

    override fun fetchDay(): Flow<List<String>> = handleResponseNew(
        response = { lat, long -> weatherApi.getWeeklyWeather(lat, long) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.time },
        defaultValue = emptyList<String>()
    )

    override fun fetchWeatherStatus(): Flow<List<Int>> = handleResponseNew(
        response = { lat, long -> weatherApi.getWeeklyWeather(lat, long) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.weatherCode },
        defaultValue = emptyList<Int>()
    )
}
