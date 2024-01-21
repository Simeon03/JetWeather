package com.example.jetweather.views

import android.util.Log
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
import com.example.jetweather.data.CurrentWeatherData
import com.example.jetweather.WeatherViewModel
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.weatherCode

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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray), // Background to visualize the column area
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        currentWeatherData?.let {
            CurrentTemperature(text = "${it.current.temperature2m}${it.currentUnits.temperature2m}")
            CurrentWeatherCode(text = "${weatherCode[it.current.weatherCode]}")
        }
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