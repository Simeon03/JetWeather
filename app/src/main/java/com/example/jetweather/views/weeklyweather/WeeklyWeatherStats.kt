package com.example.jetweather.views.weeklyweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.DataFormatter
import com.example.jetweather.helper.getDayOfWeek
import com.example.jetweather.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyWeatherStats(viewModel: WeatherViewModel, index: Int) {
    val weatherData by viewModel.weatherData.collectAsState()

    val weeklyMinTemp = DataFormatter.formatTemperatureText(weatherData.weeklyMinTemp[index])
    val weeklyMaxTemp = DataFormatter.formatTemperatureText(weatherData.weeklyMaxTemp[index])
    val dayOfWeek = getDayOfWeek(weatherData.dayOfWeek.getOrElse(index) { "2023-02-01" })
    val weeklyWeatherCode = DataFormatter.formatWeatherCodeIcon(weatherData.weeklyWeatherCode[index])
    val weeklyWeatherCodeDesc = DataFormatter.formatWeatherCodeText(weatherData.weeklyWeatherCode[index])

    DailyWeatherView(
        minTemp = weeklyMinTemp,
        maxTemp = weeklyMaxTemp,
        date = dayOfWeek,
        weatherCode = weeklyWeatherCode,
        weatherCodeDesc = weeklyWeatherCodeDesc,
    )
}