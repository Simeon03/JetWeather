package com.example.jetweather.views

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import com.example.jetweather.WeatherViewModel
import com.example.jetweather.data.WeeklyWeather

@Composable
fun WeeklyWeatherView(viewModel: WeatherViewModel) {
    var weeklyWeather by remember { mutableStateOf<WeeklyWeather?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchWeeklyWeatherData().collect { data ->
            weeklyWeather = data
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth().background(Color.LightGray),
    ) {
        weeklyWeather?.dailyTemperature?.maxTemperature?.map {
            Text(text = it.toString())
        }
    }
}