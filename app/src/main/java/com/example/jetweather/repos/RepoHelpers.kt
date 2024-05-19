package com.example.jetweather.repos

import com.example.jetweather.data.weather.HourlyWeather
import retrofit2.Response
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

open class RepoHelpers {
    suspend fun <T> handleResponse(
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