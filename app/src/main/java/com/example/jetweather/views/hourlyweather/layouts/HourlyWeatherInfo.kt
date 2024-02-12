package com.example.jetweather.views.hourlyweather.layouts

import androidx.compose.runtime.Composable
import com.example.jetweather.viewmodel.Model

@Composable
fun HourlyWeatherInfo(
    model: Model,
    index: Int
) {
    HourlyWeather(
        hours = model.hours[index],
        temps = model.hourlyTemps[index],
        weatherStatus = model.hourlyWeatherStatus[index],
        relativeHumidity = model.hourlyRelativeHumidity[index]
    )
}
