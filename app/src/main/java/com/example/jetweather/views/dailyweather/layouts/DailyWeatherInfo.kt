package com.example.jetweather.views.dailyweather.layouts

import androidx.compose.runtime.Composable
import com.example.jetweather.viewmodel.Model

@Composable
fun DailyWeatherInfo(model: Model, index: Int) {
    DailyWeather(
        minTemp = model.weeklyMinTemp[index],
        maxTemp = model.weeklyMaxTemp[index],
        date = model.weeklyDays[index],
        weatherCode = model.weeklyWeatherStatus[index],
    )
}