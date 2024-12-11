package com.example.jetweather.ui.components.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.components.icon.WeatherIcon
import com.example.jetweather.ui.components.text.DailyMinMaxTemp
import com.example.jetweather.ui.components.text.Day

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
        WeatherIcon(
            weatherCode = weatherCode,
            size = 36.dp,
            modifier = Modifier.weight(1f),
        )
        DailyMinMaxTemp(
            text = "$minTemp/$maxTemp",
            modifier = Modifier.weight(2f),
        )
    }
}
