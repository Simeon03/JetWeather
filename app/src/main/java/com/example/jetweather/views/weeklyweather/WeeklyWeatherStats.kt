package com.example.jetweather.views.weeklyweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.getDayOfWeek
import com.example.jetweather.helper.weatherCode
import com.example.jetweather.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyWeatherStats(viewModel: WeatherViewModel, index: Int) {
    val weatherData by viewModel.weatherData.collectAsState()

    val weeklyMinTemp = weatherData.weeklyMinTemp
    val weeklyMaxTemp = weatherData.weeklyMaxTemp
    val dayOfWeek = weatherData.dayOfWeek
    val weeklyWeatherCode = weatherData.weeklyWeatherCode

    DailyWeatherView(
        minTemp = weeklyMinTemp[index],
        maxTemp = weeklyMaxTemp[index],
        date = getDayOfWeek(dayOfWeek[index]),
        weatherCode = weeklyWeatherCode[index],
        weatherCodeDesc = weatherCode[weeklyWeatherCode[index]] ?: "0",
    )
}