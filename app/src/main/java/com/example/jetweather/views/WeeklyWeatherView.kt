package com.example.jetweather.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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

    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            for (i in 0..6) {
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
        modifier = Modifier.fillMaxWidth().padding(2.dp, 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = date,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            style = Typography.bodyLarge
        )
        Text(
            text = weatherCode,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            style = Typography.bodyMedium,
            maxLines = 1,
        )
        Text(
            text = "$minTemp/$maxTemp",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            style = Typography.bodyLarge
        )
    }
}