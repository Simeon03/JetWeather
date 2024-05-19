package com.example.jetweather.repos.sub

import com.example.jetweather.constants.Main
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.RepoHelpers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface WeeklyWeatherRepo {

    fun fetchMinTemp(): Flow<List<Float>>

    fun fetchMaxTemp(): Flow<List<Float>>

    fun fetchDay(): Flow<List<String>>

    fun fetchWeatherStatus(): Flow<List<Int>>

}

class DefaultWeeklyWeatherRepository(
    private val weatherApi: OpenMeteo
): RepoHelpers(), WeeklyWeatherRepo {

    override fun fetchMinTemp(): Flow<List<Float>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.minTemperature) },
            onError = { emit(emptyList<Float>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchMaxTemp(): Flow<List<Float>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.maxTemperature) },
            onError = { emit(emptyList<Float>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchDay(): Flow<List<String>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.time) },
            onError = { emit(emptyList<String>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeatherStatus(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.weatherCode) },
            onError = { emit(emptyList<Int>()) }
        )
    }.flowOn(Dispatchers.IO)
}
