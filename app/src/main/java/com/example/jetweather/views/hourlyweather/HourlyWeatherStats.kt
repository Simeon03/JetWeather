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
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherStats(viewModel: WeatherViewModel, index: Int) {
    val weatherData by viewModel.weatherData.collectAsState()

    val fullHourlyTime = weatherData.hourlyTime[index]
    val unformattedHourlyTime = LocalDateTime.parse(fullHourlyTime, DateTimeFormatter.ISO_DATE_TIME)
    val formatter24Hour = DateTimeFormatter.ofPattern("HH:mm")
    val hourlyTime = unformattedHourlyTime.format(formatter24Hour)

    val hourlyTemperature = weatherData.hourlyTemperature[index].toInt()

    HourlyWeatherSlotView(hourlyTime, hourlyTemperature)
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
            text = "$hourlyTemperature"
        )
    }
}
