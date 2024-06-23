package com.example.jetweather.repos.sub

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.RepoHelpers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface CurrentWeatherRepo {

    fun fetchTemp(): Flow<Float>

    fun fetchApparentTemp(): Flow<Float>

    fun fetchWeatherStatus(): Flow<Int?>

    fun fetchMinTemp(): Flow<Float>

    fun fetchMaxTemp(): Flow<Float>

    fun fetchSunsetTime(): Flow<String>

    fun fetchSunriseTime(): Flow<String>

}

class DefaultCurrentWeatherRepository(
    private val weatherApi: OpenMeteo,
    private val latitude: Double,
    private val longitude: Double,
): CurrentWeatherRepo, RepoHelpers() {

    override fun fetchTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(latitude, longitude),
            onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.temperature) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchApparentTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(latitude, longitude),
            onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.apparentTemperature) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeatherStatus(): Flow<Int?> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(latitude, longitude),
            onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.weatherCode) },
            onError = { emit(0) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchMinTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(latitude, longitude),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.minTemperature[0]) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchMaxTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(latitude, longitude),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.maxTemperature[0]) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchSunsetTime(): Flow<String> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(latitude, longitude),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.sunsetTime[0]) },
            onError = { emit("") }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchSunriseTime(): Flow<String> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(latitude, longitude),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.sunriseTime[0]) },
            onError = { emit("") }
        )
    }.flowOn(Dispatchers.IO)
}

