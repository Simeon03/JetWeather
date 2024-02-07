package com.example.jetweather.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jetweather.data.HourlyWeather
import com.example.jetweather.model.apiservice.LocationApiService
import com.example.jetweather.model.apiservice.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class BaseWeatherRepository(
    private val weatherApi: WeatherApiService,
    private val googleMapsApi: LocationApiService
): WeatherRepository {

    override fun fetchLocation(): Flow<String> = flow {
        handleResponse(
            response = googleMapsApi.getLocationData("$LATITUDE,$LONGITUDE"),
            onSuccess = { location -> emit(location.results[0].addressComponents[0].shortName) },
            onError = { emit(LOCATION_NOT_AVAILABLE) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentTemperature(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.data.temperature) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentWeatherStatus(): Flow<Int?> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.data.weatherCode) },
            onError = { emit(0) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMinTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.maxMinTemperature.minTemperature[0]) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentMaxTemp(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentWeather(LATITUDE, LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.maxMinTemperature.maxTemperature[0]) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyMinTempText(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.minTemperature.map { it.toInt() }) },
            onError = { emit(emptyList<Int>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyMaxTempText(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.maxTemperature.map { it.toInt() }) },
            onError = { emit(emptyList<Int>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchDayOfWeek(): Flow<List<String>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.time.map { it }) },
            onError = { emit(emptyList<String>()) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchWeeklyWeatherCode(): Flow<List<Int>> = flow {
        handleResponse(
            response = weatherApi.getWeeklyWeather(LATITUDE, LONGITUDE),
            onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.weatherCode.map { it }) },
            onError = { emit(emptyList<Int>()) }
        )
    }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
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

    // Helper functions
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNextDayHours(hourlyData: HourlyWeather): Int {
        val formattedTimes = formattedHoursTime(hourlyData)

        val currentTime = LocalTime.now().withMinute(0).withSecond(0).withNano(0)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val currentTimeFormatted = currentTime.format(formatter)

        return formattedTimes.indexOf(currentTimeFormatted)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formattedHoursTime(hourlyData: HourlyWeather): List<String> {
        val allHours = hourlyData.hourly.time
        val mutableListHours = allHours.toMutableList()

        return formatDateTimeList(mutableListHours)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateTimeList(dateTimes: List<String>): List<String> {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        val outputFormatter = DateTimeFormatter.ofPattern("HH:mm")

        return dateTimes.map { dateTimeString ->
            val dateTime = LocalDateTime.parse(dateTimeString, inputFormatter)
            dateTime.format(outputFormatter)
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