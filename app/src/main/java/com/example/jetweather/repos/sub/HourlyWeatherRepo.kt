package com.example.jetweather.repos.sub

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.BaseWeatherRepository
import com.example.jetweather.repos.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

interface HourlyWeatherRepo {

    fun fetchTemp(): Flow<List<Float>>

    fun fetchTime(): Flow<List<String>>

    fun fetchWeatherStatus(): Flow<List<Int>>

    fun fetchHumidity(): Flow<List<Int>>

}

class DefaultHourlyWeatherRepository(
    private val weatherApi: OpenMeteo,
    locationProvider: LocationProvider,
    userPreferencesRepository: UserPreferencesRepository,
): BaseWeatherRepository(locationProvider), HourlyWeatherRepo {

    private val temperatureUnit: Flow<String> = userPreferencesRepository.temperatureUnit

    override fun fetchTemp(): Flow<List<Float>> = handleResponse(
        response = { lat, long, unit -> weatherApi.getHourlyData(lat, long, unit) },
        transform = { hourlyData ->
            val pos = getNextDayHours(hourlyData)
            val removedBeforeTimes = hourlyData.hourly.temperature.subList(pos, pos + 24)
            removedBeforeTimes.map { it }
        },
        defaultValue = emptyList<Float>(),
        temperatureUnit = temperatureUnit,
    )

    override fun fetchTime(): Flow<List<String>> = handleResponse(
        response = { lat, long, unit -> weatherApi.getHourlyData(lat, long, unit) },
        transform = { hourlyData ->
            val pos = getNextDayHours(hourlyData)
            formattedHoursTime(hourlyData).subList(pos, pos + 24)
        },
        defaultValue = emptyList<String>(),
        temperatureUnit = temperatureUnit,
    )

    override fun fetchWeatherStatus(): Flow<List<Int>> = handleResponse(
        response = { lat, long, unit -> weatherApi.getHourlyData(lat, long, unit) },
        transform = { it.hourly.weatherCode },
        defaultValue = emptyList<Int>(),
        temperatureUnit = temperatureUnit,
    )

    override fun fetchHumidity(): Flow<List<Int>> = handleResponse(
        response = { lat, long, unit -> weatherApi.getHourlyData(lat, long, unit) },
        transform = { it.hourly.relativeHumidity },
        defaultValue = emptyList<Int>(),
        temperatureUnit = temperatureUnit,
    )
}
