package com.example.jetweather.viewmodel

import com.example.jetweather.helper.weatherCode
import com.example.jetweather.model.apiservice.LocationApiService
import com.example.jetweather.model.apiservice.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BaseWeatherRepository(
    private val weatherApi: WeatherApiService,
    private val googleMapsApi: LocationApiService
): WeatherRepository {
    override fun fetchLocationText(): Flow<String> = flow {
        val response = googleMapsApi.getLocationData("52.52,13.41")
        val location = response.body()?.results?.get(0)?.addressComponents?.get(0)?.shortName

        if (response.isSuccessful) {
            location?.let { emit(it) }
        } else {
            emit("Location not available")
        }

    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentTemperatureText(): Flow<String> = flow {
        val response = weatherApi.getWeatherData(52.52f,13.41f)
        val currentTemp = response.body()?.data?.temperature?.toInt()

        if (response.isSuccessful) {
            emit("$currentTemp°")
        } else {
            emit("Current temperature not found")
        }

    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentWeatherStatusText(): Flow<String> = flow {
        val response = weatherApi.getWeatherData(52.52f,13.41f)
        val currentWeatherCode = response.body()?.data?.weatherCode
        val currentWeatherStatus = weatherCode[currentWeatherCode]

        if (response.isSuccessful) {
            emit("$currentWeatherStatus")
        } else {
            emit("Current weather status not found")
        }

    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMinTempText(): Flow<Int> = flow {
        val response = weatherApi.getWeatherData(52.52f,13.41f)
        val minTemp = response.body()?.maxMinTemperature?.minTemperature?.get(0)?.toInt()

        if (response.isSuccessful) {
            minTemp?.let { emit(it) }
        } else {
            emit(0)
        }

    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMaxTempText(): Flow<Int> = flow {
        val response = weatherApi.getWeatherData(52.52f,13.41f)
        val maxTemp = response.body()?.maxMinTemperature?.maxTemperature?.get(0)?.toInt()

        if (response.isSuccessful) {
            maxTemp?.let { emit(it) }
        } else {
            emit(0)
        }

    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyMinTempText(): Flow<List<Int>> = flow {
        val response = weatherApi.getWeeklyWeatherData(52.52f,13.41f)
        val weeklyMinTemp = response.body()?.dailyTemperature?.minTemperature?.map { it.toInt() }

        if (response.isSuccessful) {
            weeklyMinTemp?.let { emit(it) }
        } else {
            emit(emptyList<Int>())
        }

    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyMaxTempText(): Flow<List<Int>> = flow {
        val response = weatherApi.getWeeklyWeatherData(52.52f,13.41f)
        val weeklyMaxTemp = response.body()?.dailyTemperature?.maxTemperature?.map { it.toInt() }

        if (response.isSuccessful) {
            weeklyMaxTemp?.let { emit(it) }
        } else {
            emit(emptyList<Int>())
        }

    }.flowOn(Dispatchers.IO)
    override fun fetchDayOfWeek(): Flow<List<String>> = flow {
        val response = weatherApi.getWeeklyWeatherData(52.52f,13.41f)
        val dayOfWeek = response.body()?.dailyTemperature?.time?.map { it }

        if (response.isSuccessful) {
            dayOfWeek?.let { emit((it)) }
        } else {
            emit(emptyList<String>())
        }

    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyWeatherCode(): Flow<List<Int>> = flow {
        val response = weatherApi.getWeeklyWeatherData(52.52f,13.41f)
        val dayOfWeek = response.body()?.dailyTemperature?.weatherCode?.map { it }

        if (response.isSuccessful) {
            dayOfWeek?.let { emit(it) }
        } else {
            emit(emptyList<Int>())
        }

    }.flowOn(Dispatchers.IO)

    override fun fetchHourlyTemperature(): Flow<List<Float>> = flow {
        val response = weatherApi.getHourlyData(52.52f,13.41f)
        val hourlyTemp = response.body()?.hourly?.temperature?.map { it }

        if (response.isSuccessful) {
            hourlyTemp?.let { emit((it)) }
        } else {
            emit(emptyList<Float>())
        }

    }.flowOn(Dispatchers.IO)

    override fun fetchHourlyTime(): Flow<List<String>> = flow {

        val response = weatherApi.getHourlyData(52.52f,13.41f)
        val hourlyTime = response.body()?.hourly?.time?.map { it }

        if (response.isSuccessful) {
            hourlyTime?.let { emit(it) }
        } else {
            emit(emptyList<String>())
        }

    }.flowOn(Dispatchers.IO)
}