package com.example.jetweather.viewmodel

import com.example.jetweather.helper.weatherCode
import com.example.jetweather.model.apiservice.LocationApiService
import com.example.jetweather.model.apiservice.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class BaseWeatherRepository(
    private val weatherApi: WeatherApiService,
    private val googleMapsApi: LocationApiService
): WeatherRepository {

    override fun fetchLocationText(): Flow<String> = flow {
        handleResponse(
            response = googleMapsApi.getLocationData("$LATITUDE,$LONGITUDE"),
            onSuccess = { location -> emit(location.results[0].addressComponents[0].shortName) },
            onError = { emit(LOCATION_NOT_AVAILABLE) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentTemperatureText(): Flow<String> = flow {
        handleResponse(
            response = weatherApi.getWeatherData(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit("${weatherData.data.temperature.toInt()}°") },
            onError = { emit(TEMP_NOT_FOUND) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentWeatherStatusText(): Flow<String?> = flow {
        handleResponse(
            response = weatherApi.getWeatherData(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherCode[weatherData.data.weatherCode]) },
            onError = { emit(WEATHER_STATUS_NOT_FOUND) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMinTempText(): Flow<Int> = flow {
        handleResponse(
            response = weatherApi.getWeatherData(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.maxMinTemperature.minTemperature[0].toInt()) },
            onError = { emit(0) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMaxTempText(): Flow<Int> = flow {
        handleResponse(
            response = weatherApi.getWeatherData(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.maxMinTemperature.maxTemperature[0].toInt()) },
            onError = { emit(0) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyMinTempText(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeatherData(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.minTemperature.map { it.toInt() }) },
            onError = { emit(emptyList<Int>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyMaxTempText(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeatherData(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.maxTemperature.map { it.toInt() }) },
            onError = { emit(emptyList<Int>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchDayOfWeek(): Flow<List<String>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeatherData(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.time.map { it }) },
            onError = { emit(emptyList<String>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyWeatherCode(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeatherData(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.weatherCode.map { it }) },
            onError = { emit(emptyList<Int>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchHourlyTemperature(): Flow<List<Float>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(LATITUDE, LONGITUDE),
            onSuccess = { hourlyData -> emit(hourlyData.hourly.temperature.map { it }) },
            onError = { emit(emptyList<Float>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchHourlyTime(): Flow<List<String>> = flow {
        handleResponse(
            response = weatherApi.getHourlyData(LATITUDE, LONGITUDE),
            onSuccess = { hourlyData -> emit(hourlyData.hourly.time.map { it }) },
            onError = { emit(listOf<String>()) }
        )
    }.flowOn(Dispatchers.IO)

    private suspend fun <T> handleResponse(
        response: Response<T>,
        onSuccess: suspend (T) -> Unit,
        onError: suspend () -> Unit,
    ) {
        if (response.isSuccessful) {
            response.body()?.let { onSuccess(it) }
        } else {
            onError()
        }
    }

    companion object {
        private const val LATITUDE = 52.52f
        private const val LONGITUDE = 13.41f
        private const val LOCATION_NOT_AVAILABLE = "Location not available"
        private const val TEMP_NOT_FOUND = "Current temperature not found"
        private const val WEATHER_STATUS_NOT_FOUND = "Current weather status not found"
    }
}