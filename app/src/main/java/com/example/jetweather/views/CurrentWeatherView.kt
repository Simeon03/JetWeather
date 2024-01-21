package com.example.jetweather.views

import androidx.compose.foundation.background
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
import com.example.jetweather.WeatherViewModel
import com.example.jetweather.data.CurrentWeather
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.weatherCode

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

    val currentTemperature = currentWeather?.data?.temperature?.toInt()
    val temperatureSuffix = currentWeather?.weatherFormat?.temperatureUnit
    val fullTemperature = "$currentTemperature $temperatureSuffix"
    val currentWeatherCode = weatherCode[currentWeather?.data?.weatherCode].toString()
    
    val minTemp = currentWeather?.maxMinTemperature?.minTemperature?.get(0)?.toInt()
    val maxTemp = currentWeather?.maxMinTemperature?.maxTemperature?.get(0)?.toInt()
    val minMaxTemp = "$minTemp $temperatureSuffix / $maxTemp $temperatureSuffix"

    // UI layout
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurrentTemperature(text = fullTemperature)
        CurrentWeatherCode(text = currentWeatherCode)
        CurrentWeatherCode(text = minMaxTemp)
    }
}

@Composable
fun CurrentTemperature(text: String) {
    Text(
        text = text,
        style = Typography.titleLarge,
        color = Color(255, 255, 255),
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun CurrentWeatherCode(text: String) {
    Text(
        text = text,
        style = Typography.bodyLarge,
        color = Color(255, 255, 255),
        modifier = Modifier.padding(5.dp)
    )
}