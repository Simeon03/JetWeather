package com.example.jetweather.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.jetweather.data.CurrentWeatherData
import com.example.jetweather.WeatherViewModel

@Composable
fun CurrentWeatherView(modifier: Modifier = Modifier, viewModel: WeatherViewModel) {
    // State for current weather data
    var currentWeatherData by remember { mutableStateOf<CurrentWeatherData?>(null) }

    // Fetch and observe current weather data
    LaunchedEffect(Unit) {
        viewModel.fetchCurrentWeatherData().collect { data ->
            currentWeatherData = data
        }
    }

    // UI layout
    Column(modifier = modifier.fillMaxWidth()) {
        currentWeatherData?.let {
            Text(text = "${it.current.temperature2m}${it.currentUnits.temperature2m}")
        }
    }
}
