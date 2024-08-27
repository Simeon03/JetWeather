package com.example.jetweather.repos.sub

import android.content.Context
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.DefaultWeatherRepo
import kotlinx.coroutines.flow.Flow

interface HourlyWeatherRepo {

    fun fetchTemp(): Flow<List<Float>>

    fun fetchTime(): Flow<List<String>>

    fun fetchWeatherStatus(): Flow<List<Int>>

    fun fetchHumidity(): Flow<List<Int>>

    fun fetchPrecipitationProbability(): Flow<List<Int>>

}

class DefaultHourlyWeatherRepository(
    private val context: Context,
    private val weatherApi: OpenMeteo,
    private val weatherRepo: DefaultWeatherRepo,
): HourlyWeatherRepo {

    override fun fetchTemp(): Flow<List<Float>> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getHourlyData(lat, long, unit) },
        transform = { hourlyData ->
            val pos = weatherRepo.getNextDayHours(context, hourlyData)
            val removedBeforeTimes = hourlyData.hourly.temperature.subList(pos, pos + 24)
            removedBeforeTimes.map { it }
        },
        defaultValue = emptyList<Float>(),
    )

    override fun fetchTime(): Flow<List<String>> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getHourlyData(lat, long, unit) },
        transform = { hourlyData ->
            val pos = weatherRepo.getNextDayHours(context, hourlyData)
            weatherRepo.formattedHoursTime(context, hourlyData).subList(pos, pos + 24)
        },
        defaultValue = emptyList<String>(),
    )

    override fun fetchWeatherStatus(): Flow<List<Int>> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getHourlyData(lat, long, unit) },
        transform = { it.hourly.weatherCode },
        defaultValue = emptyList<Int>(),
    )

    override fun fetchHumidity(): Flow<List<Int>> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getHourlyData(lat, long, unit) },
        transform = { it.hourly.relativeHumidity },
        defaultValue = emptyList<Int>(),
    )

    override fun fetchPrecipitationProbability(): Flow<List<Int>> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getHourlyData(lat, long, unit) },
        transform = { it.hourly.precipitationProbability },
        defaultValue = emptyList<Int>(),
    )
}
