package com.example.jetweather.repos.sub

import com.example.jetweather.constants.Main
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.main.MainRepoHelpers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface WeeklyWeatherRepo {

    fun fetchWeeklyMinTemp(): Flow<List<Float>>

    fun fetchWeeklyMaxTemp(): Flow<List<Float>>

    fun fetchWeeklyDay(): Flow<List<String>>

    fun fetchWeeklyWeatherStatus(): Flow<List<Int>>

}

class DefaultWeeklyWeatherRepository(
    private val weatherApi: OpenMeteo
): MainRepoHelpers(), WeeklyWeatherRepo {

    override fun fetchWeeklyMinTemp(): Flow<List<Float>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.minTemperature) },
            onError = { emit(emptyList<Float>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyMaxTemp(): Flow<List<Float>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.maxTemperature) },
            onError = { emit(emptyList<Float>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyDay(): Flow<List<String>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.time) },
            onError = { emit(emptyList<String>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyWeatherStatus(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.weatherCode) },
            onError = { emit(emptyList<Int>()) }
        )
    }.flowOn(Dispatchers.IO)
}
