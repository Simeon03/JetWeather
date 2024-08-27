package com.example.jetweather.repos

import android.content.Context
import android.text.format.DateFormat
import com.example.jetweather.data.weather.HourlyWeather
import com.example.jetweather.model.LocationProvider
import com.example.jetweather.weatherdata.CurrentLocationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DefaultWeatherRepo(
    locationProvider: LocationProvider,
    userPreferencesRepository: UserPreferencesRepository,
) {

    private val locationFlow: StateFlow<CurrentLocationData> = locationProvider.locationFlow
    private val temperatureUnit: Flow<String> = userPreferencesRepository.temperatureUnit

    @OptIn(ExperimentalCoroutinesApi::class)
    fun <T, R> handleResponse(
        response: suspend (lat: Double, long: Double, unit: String) -> Response<T>,
        transform: (T) -> R,
        defaultValue: R,
    ): Flow<R> {
        return locationFlow.flatMapLatest { (lat, long) ->
            temperatureUnit.flatMapLatest { unit ->
                flow {
                    val data = response(lat, long, unit)
                    if (data.isSuccessful) {
                        val body = data.body()
                        if (body != null) {
                            emit(transform(body))
                        } else {
                            emit(defaultValue)
                        }
                    } else {
                        emit(defaultValue)
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getNextDayHours(context: Context, hourlyData: HourlyWeather): Int {
        val formattedTimes = formattedHoursTime(context, hourlyData)

        val currentTime = LocalTime.now().withMinute(0).withSecond(0).withNano(0)
        val is24HourFormat = DateFormat.is24HourFormat(context)
        val formatter = if (is24HourFormat) {
            DateTimeFormatter.ofPattern("HH:mm")
        } else {
            DateTimeFormatter.ofPattern("h a")
        }

        val currentTimeFormatted = if (is24HourFormat) {
            currentTime.format(formatter).replace(":00", "")
        } else {
            currentTime.format(formatter).replace(" ", "").uppercase()
        }

        return formattedTimes.indexOf(currentTimeFormatted)
    }

    fun formattedHoursTime(context: Context, hourlyData: HourlyWeather): List<String> {
        val allHours = hourlyData.hourly.time

        // Check if the system is set to use 24-hour format
        val is24HourFormat = DateFormat.is24HourFormat(context)
        val formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")

        // Choose the output formatter based on the user's preference
        val formatterOutput = if (is24HourFormat) {
            DateTimeFormatter.ofPattern("HH:mm")
        } else {
            DateTimeFormatter.ofPattern("h a")
        }

        return allHours.map { time ->
            val parsedTime = LocalTime.parse(time, formatterInput)
            if (is24HourFormat) {
                parsedTime.format(formatterOutput).replace(":00", "")
            } else {
                parsedTime.format(formatterOutput).replace(" ", "").uppercase()
            }
        }
    }

    private fun formatDateTimeList(dateTimes: List<String>): List<String> {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        val outputFormatter = DateTimeFormatter.ofPattern("HH:mm")

        return dateTimes.map { dateTimeString ->
            val dateTime = LocalDateTime.parse(dateTimeString, inputFormatter)
            dateTime.format(outputFormatter)
        }
    }
}
