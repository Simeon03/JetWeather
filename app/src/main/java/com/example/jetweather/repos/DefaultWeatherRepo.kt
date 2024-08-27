package com.example.jetweather.repos

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

    fun getNextDayHours(hourlyData: HourlyWeather): Int {
        val formattedTimes = formattedHoursTime(hourlyData)

        val currentTime = LocalTime.now().withMinute(0).withSecond(0).withNano(0)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val currentTimeFormatted = currentTime.format(formatter)

        return formattedTimes.indexOf(currentTimeFormatted)
    }

    fun formattedHoursTime(hourlyData: HourlyWeather): List<String> {
        val allHours = hourlyData.hourly.time
        val mutableListHours = allHours.toMutableList()

        return formatDateTimeList(mutableListHours)
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
