package com.example.jetweather.repos.sub

import com.example.jetweather.constants.Main
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.main.MainRepoHelpers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface HourlyWeatherRepo {

    fun fetchTemp(): Flow<List<Float>>

    fun fetchTime(): Flow<List<String>>

    fun fetchWeatherStatus(): Flow<List<Int>>

    fun fetchHumidity(): Flow<List<Int>>

}

class DefaultHourlyWeatherRepository(
    private val weatherApi: OpenMeteo
): MainRepoHelpers(), HourlyWeatherRepo {

    override fun fetchTemp(): Flow<List<Float>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { hourlyData ->
                val pos = getNextDayHours(hourlyData)
                val removedBeforeTimes = hourlyData.hourly.temperature.subList(pos, pos + 24)
                emit(removedBeforeTimes.map { it })
            },
            onError = { emit(emptyList<Float>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchTime(): Flow<List<String>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { hourlyData ->
                val pos = getNextDayHours(hourlyData)
                val removedBeforeTimes = formattedHoursTime(hourlyData).subList(pos, pos + 24)
                emit(removedBeforeTimes)
            },
            onError = { emit(listOf<String>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeatherStatus(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.hourly.weatherCode) },
            onError = { emit(listOf<Int>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchHumidity(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.hourly.relativeHumidity) },
            onError = { emit(listOf<Int>()) }
        )
    }.flowOn(Dispatchers.IO)
}
