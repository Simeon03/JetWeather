package com.example.jetweather.views.weeklyweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.data.WeeklyWeather
import com.example.jetweather.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyWeatherStats(viewModel: WeatherViewModel, weeklyWeather: WeeklyWeather?, index: Int) {
    val weeklyMinTemp by viewModel.weeklyMinTempText.collectAsState()
    val weeklyMaxTemp by viewModel.weeklyMaxTempText.collectAsState()

    DailyWeatherView(
        minTemp = weeklyMinTemp[index],
        maxTemp = weeklyMaxTemp[index],
        date = viewModel.fetchDayOfWeek(weeklyWeather, index),
        weatherCode = viewModel.fetchDailyWeatherCode(weeklyWeather, index),
        weatherCodeDesc = viewModel.fetchDailyWeatherCodeDesc(weeklyWeather, index),
    )
}