package com.example.jetweather.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.jetweather.ui.theme.Typography

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyWeatherView(viewModel: WeatherViewModel) {
    var weeklyWeather by remember { mutableStateOf<WeeklyWeather?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchWeeklyWeatherData().collect { data ->
            weeklyWeather = data
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
    ) {

        for (i in 0..6) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
            ) {
                DailyWeatherView(
                    minTemp = viewModel.fetchDailyMinTemperature(weeklyWeather, i),
                    maxTemp = viewModel.fetchDailyMaxTemperature(weeklyWeather, i),
                    date = viewModel.fetchDayOfWeek(weeklyWeather, i),
                    weatherCode = viewModel.fetchDailyWeatherCode(weeklyWeather, i)
                )
            }
        }
    }
}

@Composable
fun DailyWeatherView(minTemp: Int, maxTemp: Int, date: String, weatherCode: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray),
    ) {
        Text(text = date, style = Typography.bodyLarge)
        Text(text = weatherCode, style = Typography.bodyMedium)
        Text(text = "$minTemp/$maxTemp", style = Typography.bodyLarge)
    }
}