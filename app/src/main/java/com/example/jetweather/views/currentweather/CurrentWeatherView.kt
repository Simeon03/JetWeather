package com.example.jetweather.views.currentweather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.data.CurrentWeather
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentWeatherView(viewModel: WeatherViewModel) {
    // State for current weather data
    var currentWeather by remember { mutableStateOf<CurrentWeather?>(null) }

    // Fetch and observe current weather data
    LaunchedEffect(Unit) {
        viewModel.fetchWeatherData().collect { data ->
            currentWeather = data
        }
    }

    // UI layout
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CurrentTemperature(text = viewModel.fetchCurrentTemperature(currentWeather))
        CurrentWeatherCode(text = viewModel.fetchWeatherStatus(currentWeather))
        CurrentWeatherCode(text = viewModel.fetchMinMaxTemperature(currentWeather))
    }
}