package com.example.jetweather.views

import android.os.Build
import android.util.Log
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
import com.example.jetweather.getDayOfWeek
import com.example.jetweather.weatherCode
import java.util.Date

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
                Text(text = getDayOfWeek(weeklyWeather?.dailyTemperature?.time?.get(i) ?: "2023-01-01"))
                Text(text = weatherCode[weeklyWeather?.dailyTemperature?.weatherCode?.get(i)].toString())
                Text(text = "${weeklyWeather?.dailyTemperature?.maxTemperature?.get(i)?.toInt()}/${weeklyWeather?.dailyTemperature?.minTemperature?.get(i)?.toInt()}")
            }
        }
    }
}

@Composable
fun DailyWeatherView(minTemp: String, maxTemp: String, date: String, weatherCode: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Text(text = date)
        Text(text = weatherCode)
        Text(text = minTemp)
        Text(text = maxTemp)
    }
}