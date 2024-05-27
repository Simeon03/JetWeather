package com.example.jetweather.views.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.helpers.DataFormatter.roundPercent
import com.example.jetweather.helpers.DataFormatter.roundTemp

@Composable
fun HourlyWeatherInfo(
    hours: List<String>,
    temps: List<Float>,
    weatherStatus: List<Int>,
    relativeHumidity: List<Int>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(24) { index ->
            HourlyWeather(
                hours = hours[index],
                temps = temps[index].roundTemp(),
                weatherStatus = weatherStatus[index],
                relativeHumidity = relativeHumidity[index].roundPercent()
            )
        }
    }
}
