package com.example.jetweather.views.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.icon.WeatherIcon
import com.example.jetweather.views.text.HourlyRelativeHumidity
import com.example.jetweather.views.text.HourlyWeatherHour
import com.example.jetweather.views.text.HourlyWeatherTemp

@Composable
fun HourlyWeatherColumn(
    hours: String,
    temps: String,
    weatherStatus: Int,
    relativeHumidity: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HourlyWeatherHour(text = hours)
        WeatherIcon(
            weatherCode = weatherStatus,
            size = 32.dp,
            modifier = modifier
        )
        HourlyRelativeHumidity(text = relativeHumidity)
        HourlyWeatherTemp(text = temps)
    }
}
