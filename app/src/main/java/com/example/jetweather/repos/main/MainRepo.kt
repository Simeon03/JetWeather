package com.example.jetweather.repos.main

import com.example.jetweather.constants.Main.LATITUDE
import com.example.jetweather.constants.Main.LOCATION_NOT_AVAILABLE
import com.example.jetweather.constants.Main.LONGITUDE
import com.example.jetweather.model.api.GoogleMaps
import com.example.jetweather.model.api.OpenMeteo
import com.example.jetweather.repos.AllRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepo(
    private val weatherApi: OpenMeteo,
    private val googleMapsApi: GoogleMaps
): AllRepos, MainRepoHelpers() {

    override fun fetchCurrentLocation(): Flow<String> = flow {
        handleResponse(
            response = googleMapsApi.getLocationData("$LATITUDE,$LONGITUDE"),
            onSuccess = { location -> emit(location.results[0].addressComponents[0].shortName) },
            onError = { emit(LOCATION_NOT_AVAILABLE) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentTemperature(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.temperature) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentWeatherStatus(): Flow<Int?> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.weatherCode) },
            onError = { emit(0) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMinTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.minTemperature[0]) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMaxTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.maxTemperature[0]) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentSunsetTime(): Flow<String> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.sunsetTime[0]) },
            onError = { emit("") }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentSunriseTime(): Flow<String> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.currentWeather.sunriseTime[0]) },
            onError = { emit("") }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyMinTemp(): Flow<List<Float>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.minTemperature) },
            onError = { emit(emptyList<Float>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyMaxTemp(): Flow<List<Float>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.maxTemperature) },
            onError = { emit(emptyList<Float>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyDay(): Flow<List<String>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.time) },
            onError = { emit(emptyList<String>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyWeatherStatus(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.weatherCode) },
            onError = { emit(emptyList<Int>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchHourlyTemperature(): Flow<List<Float>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(LATITUDE, LONGITUDE),
            onSuccess = { hourlyData ->
                val pos = getNextDayHours(hourlyData)
                val removedBeforeTimes = hourlyData.hourly.temperature.subList(pos, pos + 24)
                emit(removedBeforeTimes.map { it })
            },
            onError = { emit(emptyList<Float>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchHourlyTime(): Flow<List<String>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(LATITUDE, LONGITUDE),
            onSuccess = { hourlyData ->
                val pos = getNextDayHours(hourlyData)
                val removedBeforeTimes = formattedHoursTime(hourlyData).subList(pos, pos + 24)
                emit(removedBeforeTimes)
            },
            onError = { emit(listOf<String>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchHourlyWeatherStatus(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.hourly.weatherCode) },
            onError = { emit(listOf<Int>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchHourlyHumidity(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.hourly.relativeHumidity) },
            onError = { emit(listOf<Int>()) }
        )
    }.flowOn(Dispatchers.IO)
}