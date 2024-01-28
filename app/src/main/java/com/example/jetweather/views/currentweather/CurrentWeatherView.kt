package com.example.jetweather.views.currentweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentWeatherView(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()
    val weatherDataLoading by viewModel.isLoading.collectAsState()

    val currentWeatherStatus = weatherData.weatherStatus
    val currentTemp = weatherData.currentTemp
    val currentMinTemp = weatherData.currentMinTemp
    val currentMaxTemp = weatherData.currentMaxTemp
    val currentLocation = weatherData.location

    if (!weatherDataLoading) {
        CurrentWeatherStatsView(
            currentLocation = currentLocation,
            currentTemp = currentTemp,
            currentWeatherStatus = currentWeatherStatus,
            currentMinTemp = currentMinTemp,
            currentMaxTemp = currentMaxTemp,
        )
    }
}
