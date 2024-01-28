package com.example.jetweather.views.currentweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetweather.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentWeatherView(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val currentWeatherStatus = weatherData.weatherStatus
    val currentTemp = weatherData.currentTemp
    val currentMinTemp = weatherData.currentMinTemp
    val currentMaxTemp = weatherData.currentMaxTemp

    val currentMinMaxTemp = "$currentMinTemp°/$currentMaxTemp°"

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CurrentTemperature(text = currentTemp)
        CurrentWeatherCode(text = currentWeatherStatus)
        CurrentWeatherCode(text = currentMinMaxTemp)
    }
}
