package com.example.jetweather.views.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetweather.core.helpers.DataFormatter.roundPercent
import com.jetweather.core.helpers.DataFormatter.roundTemp
import com.example.jetweather.views.component.HourlyWeather

@Composable
fun HourlyWeatherInfo(
    hours: List<String>,
    temps: List<Float>,
    weatherStatus: List<Int>,
    precipitationProbability: List<Int>
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
                relativeHumidity = precipitationProbability[index].roundPercent()
            )
        }
    }
}
