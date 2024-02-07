package com.example.jetweather.views.hourlyweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.DataFormatter
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun HourlyWeatherStats(viewModel: WeatherViewModel, index: Int) {
    val weatherData by viewModel.weatherData.collectAsState()

    val fullHourlyTime = weatherData.hourlyTime[index]
    val fullTemps = DataFormatter.formatTemperatureText(weatherData.hourlyTemperature[index])

    HourlyWeatherSlotView(fullHourlyTime, fullTemps)
}
