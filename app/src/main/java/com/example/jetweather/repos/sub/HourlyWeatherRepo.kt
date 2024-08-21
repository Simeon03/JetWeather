package com.example.jetweather.repos.sub

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.BaseWeatherRepository
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
): BaseWeatherRepository(locationProvider), HourlyWeatherRepo {

    override fun fetchTemp(): Flow<List<Float>> = handleResponseNew(
        response = { lat, long -> weatherApi.getHourlyData(lat, long) },
        transform = { hourlyData ->
            val pos = getNextDayHours(hourlyData)
            val removedBeforeTimes = hourlyData.hourly.temperature.subList(pos, pos + 24)
            removedBeforeTimes.map { it }
        },
        defaultValue = emptyList<Float>()
    )

    override fun fetchTime(): Flow<List<String>> = handleResponseNew(
        response = { lat, long -> weatherApi.getHourlyData(lat, long) },
        transform = { hourlyData ->
            val pos = getNextDayHours(hourlyData)
            formattedHoursTime(hourlyData).subList(pos, pos + 24)
        },
        defaultValue = emptyList<String>()
    )

    override fun fetchWeatherStatus(): Flow<List<Int>> = handleResponseNew(
        response = { lat, long -> weatherApi.getHourlyData(lat, long) },
        transform = { it.hourly.weatherCode },
        defaultValue = emptyList<Int>()
    )

    override fun fetchHumidity(): Flow<List<Int>> = handleResponseNew(
        response = { lat, long -> weatherApi.getHourlyData(lat, long) },
        transform = { it.hourly.relativeHumidity },
        defaultValue = emptyList<Int>()
    )
}
