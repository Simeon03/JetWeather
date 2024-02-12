package com.example.jetweather.views.currentweather

import androidx.compose.runtime.Composable
import com.example.jetweather.viewmodel.Model
import com.example.jetweather.views.currentweather.layouts.CurrentWeatherInfo

@Composable
fun CurrentWeatherView(model: Model) {
    CurrentWeatherInfo(
        currentLocation = model.currentLocation,
        currentTemp = model.currentTemp,
        currentWeatherStatus = model.currentWeatherStatus,
        currentMinTemp = model.currentMinTemp,
        currentMaxTemp = model.currentMaxTemp,
    )
}
