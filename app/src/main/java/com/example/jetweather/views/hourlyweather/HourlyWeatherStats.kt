package com.example.jetweather.views.hourlyweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.WeatherViewModel
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherStats(viewModel: WeatherViewModel, index: Int) {
    val weatherData by viewModel.weatherData.collectAsState()

    val fullHourlyTime = weatherData.hourlyTime
    val mutableListHours = fullHourlyTime.toMutableList()
    val formattedTimes = formatDateTimeList(mutableListHours)

    val currentTime = LocalTime.now().withMinute(0).withSecond(0).withNano(0)
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val currentTimeFormatted = currentTime.format(formatter)

    val pos = formattedTimes.indexOf(currentTimeFormatted)

    val removedBeforeTimes = formattedTimes.subList(pos, pos + 24)

    val hourTemperatureList = weatherData.hourlyTemperature.subList(pos, pos + 24)
    val hourlyTemperature = hourTemperatureList[index].toInt()

    HourlyWeatherSlotView(removedBeforeTimes[index], hourlyTemperature)
}

@Composable
fun HourlyWeatherSlotView(
    time: String,
    hourlyTemperature: Int
) {
    Column {
        Text(
            text = time
        )
        Text(
            text = "$hourlyTemperature°"
        )
    }
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
