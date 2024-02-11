package com.example.jetweather.views.todaysun

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.helper.DataFormatter
import com.example.jetweather.helper.gradientBackground
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun TodaySunTime(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val sunriseTime = DataFormatter.getTimeOfDay(weatherData.sunriseTime[0])
    val sunsetTime = DataFormatter.getTimeOfDay(weatherData.sunsetTime[0])

    Card(
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth().background(gradientBackground())) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                SunTimeView(
                    sunriseTime = sunriseTime,
                    sunsetTime = sunsetTime,
                )
            }
        }
    }
}

@Composable
fun SunTimeView(sunriseTime: String, sunsetTime: String) {
    Text(text = "Sunrise: $sunriseTime")
    Text(text = "Sunset: $sunsetTime")
}
