package com.example.jetweather.repos.sub

import android.util.Log
import com.example.jetweather.constants.Main
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.main.MainRepoHelpers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface CurrentWeatherRepo {

    fun fetchCurrentTemperature(): Flow<Float>

    fun fetchCurrentApparentTemperature(): Flow<Float>

    fun fetchCurrentWeatherStatus(): Flow<Int?>

    fun fetchCurrentMinTemp(): Flow<Float>

    fun fetchCurrentMaxTemp(): Flow<Float>

    fun fetchCurrentSunsetTime(): Flow<String>

    fun fetchCurrentSunriseTime(): Flow<String>

}

class DefaultCurrentWeatherRepository(
    private val weatherApi: OpenMeteo
): CurrentWeatherRepo, LocationRepo, MainRepoHelpers() {

    override fun fetchCurrentTemperature(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.temperature) },
            onError = { emit(0f) }
        )
        Log.d("DefaultCurrentWeatherRepository", "Current Temperature: ${weatherApi.getCurrentWeather(
            Main.LATITUDE, Main.LONGITUDE
        )}")
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentApparentTemperature(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.apparentTemperature) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentWeatherStatus(): Flow<Int?> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.weatherCode) },
            onError = { emit(0) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMinTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.minTemperature[0]) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMaxTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.maxTemperature[0]) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentSunsetTime(): Flow<String> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.sunsetTime[0]) },
            onError = { emit("") }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentSunriseTime(): Flow<String> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.sunriseTime[0]) },
            onError = { emit("") }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentLocation(): Flow<String> = flow {
        emit("Berlin")
    }.flowOn(Dispatchers.IO)

}

