package com.example.jetweather.views.weeklyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.weeklyweather.subviews.DailyMinMaxTempView
import com.example.jetweather.views.weeklyweather.subviews.DailyWeatherIconView
import com.example.jetweather.views.weeklyweather.subviews.DayOfWeatherView

@Composable
fun DailyWeatherView(
    minTemp: String,
    maxTemp: String,
    date: String,
    weatherCode: Int,
    weatherCodeDesc: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp, 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DayOfWeatherView(
            text = date,
            modifier = Modifier
                .weight(3f)
                .padding(start = 8.dp),
        )
        DailyWeatherIconView(
            weatherCode = weatherCode,
            weatherCodeDesc = weatherCodeDesc,
            modifier = Modifier.weight(1f),
        )
        DailyMinMaxTempView(
            minTemp = minTemp,
            maxTemp = maxTemp,
            modifier = Modifier
                .weight(2f)
                .padding(end = 8.dp),
        )
    }
}
