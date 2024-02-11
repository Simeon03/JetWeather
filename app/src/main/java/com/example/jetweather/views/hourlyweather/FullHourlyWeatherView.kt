package com.example.jetweather.views.hourlyweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.DataFormatter
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun FullHourlyWeatherView(viewModel: WeatherViewModel, index: Int) {
    val weatherData by viewModel.weatherData.collectAsState()

    val hours = weatherData.hourlyTime[index]
    val temps = DataFormatter.formatTemperatureText(weatherData.hourlyTemperature[index])
    val weatherStatus = weatherData.hourlyWeatherStatus[index]
    val weatherStatusDesc = DataFormatter.formatWeatherCodeText(weatherStatus)

    HourlyWeatherLayoutView(
        hours = hours,
        temps = temps,
        weatherStatus = weatherStatus,
        weatherStatusDesc = weatherStatusDesc
    )
}
