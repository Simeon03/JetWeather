package com.example.jetweather.views.dailyweather.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetweather.views.dailyweather.icons.DailyWeatherStatus
import com.example.jetweather.views.dailyweather.texts.DailyMinMaxTemp
import com.example.jetweather.views.dailyweather.texts.Day

@Composable
fun DailyWeather(
    minTemp: String,
    maxTemp: String,
    date: String,
    weatherCode: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Day(
            text = date,
            modifier = Modifier.weight(3f),
        )
        DailyWeatherStatus(
            weatherCode = weatherCode,
            modifier = Modifier.weight(1f),
        )
        DailyMinMaxTemp(
            text = "$minTemp/$maxTemp",
            modifier = Modifier.weight(2f),
        )
    }
}
