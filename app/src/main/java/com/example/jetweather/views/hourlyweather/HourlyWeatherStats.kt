package com.example.jetweather.views.hourlyweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherStats(viewModel: WeatherViewModel, index: Int) {
    val weatherData by viewModel.weatherData.collectAsState()

    val hourlyTemperature = weatherData.hourlyTemperature[index].toInt()

    HourlyWeatherSlotView(hourlyTemperature)
}

@Composable
fun HourlyWeatherSlotView(
    hourlyTemperature: Int
) {
    Column {
        Text(
            text = "$hourlyTemperature"
        )
    }
}
