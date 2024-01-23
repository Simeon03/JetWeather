package com.example.jetweather.views.weeklyweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.jetweather.data.WeeklyWeather
import com.example.jetweather.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyWeatherStats(viewModel: WeatherViewModel, weeklyWeather: WeeklyWeather?, index: Int) {
    DailyWeatherView(
        minTemp = viewModel.fetchDailyMinTemperature(weeklyWeather, index),
        maxTemp = viewModel.fetchDailyMaxTemperature(weeklyWeather, index),
        tempSuffix = viewModel.fetchTempSuffix(weeklyWeather),
        date = viewModel.fetchDayOfWeek(weeklyWeather, index),
        weatherCode = viewModel.fetchDailyWeatherCode(weeklyWeather, index),
        weatherCodeDesc = viewModel.fetchDailyWeatherCodeDesc(weeklyWeather, index),
    )
}