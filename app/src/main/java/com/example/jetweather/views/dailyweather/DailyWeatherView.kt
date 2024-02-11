package com.example.jetweather.views.dailyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetweather.views.dailyweather.subviews.DailyMinMaxTempView
import com.example.jetweather.views.dailyweather.subviews.DailyWeatherIconView
import com.example.jetweather.views.dailyweather.subviews.DayOfWeatherView

@Composable
fun DailyWeatherView(
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
        DayOfWeatherView(
            text = date,
            modifier = Modifier.weight(3f),
        )
        DailyWeatherIconView(
            weatherCode = weatherCode,
            modifier = Modifier.weight(1f),
        )
        DailyMinMaxTempView(
            text = "$minTemp/$maxTemp",
            modifier = Modifier.weight(2f),
        )
    }
}
