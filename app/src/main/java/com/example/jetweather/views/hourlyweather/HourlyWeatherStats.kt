package com.example.jetweather.views.hourlyweather

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun HourlyWeatherStats(viewModel: WeatherViewModel, index: Int) {
    val weatherData by viewModel.weatherData.collectAsState()

    val fullHourlyTime = weatherData.hourlyTime[index]
    val fullTemps = weatherData.hourlyTemperature.map { it.toInt() }[index]

    HourlyWeatherSlotView(fullHourlyTime, fullTemps)
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
