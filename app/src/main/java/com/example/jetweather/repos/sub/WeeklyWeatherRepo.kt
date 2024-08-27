package com.example.jetweather.repos.sub

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.BaseWeatherRepository
import com.example.jetweather.repos.UserPreferencesRepository
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
    userPreferencesRepository: UserPreferencesRepository,
): BaseWeatherRepository(locationProvider), WeeklyWeatherRepo {

    private val temperatureUnit: Flow<String> = userPreferencesRepository.temperatureUnit

    override fun fetchMinTemp(): Flow<List<Float>> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getWeeklyWeather(lat, long, unit) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.minTemperature },
        defaultValue = emptyList<Float>(),
        temperatureUnit = temperatureUnit,
    )

    override fun fetchMaxTemp(): Flow<List<Float>> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getWeeklyWeather(lat, long, unit) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.maxTemperature },
        defaultValue = emptyList<Float>(),
        temperatureUnit = temperatureUnit,
    )

    override fun fetchDay(): Flow<List<String>> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getWeeklyWeather(lat, long, unit) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.time },
        defaultValue = emptyList<String>(),
        temperatureUnit = temperatureUnit,
    )

    override fun fetchWeatherStatus(): Flow<List<Int>> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getWeeklyWeather(lat, long, unit) },
        transform = { weeklyWeatherData -> weeklyWeatherData.dailyTemperature.weatherCode },
        defaultValue = emptyList<Int>(),
        temperatureUnit = temperatureUnit,
    )
}
