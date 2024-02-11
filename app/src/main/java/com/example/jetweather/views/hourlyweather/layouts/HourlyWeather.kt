package com.example.jetweather.views.hourlyweather.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.hourlyweather.icons.HourlyWeatherStatus
import com.example.jetweather.views.hourlyweather.texts.HourlyRelativeHumidity
import com.example.jetweather.views.hourlyweather.texts.HourlyWeatherHour
import com.example.jetweather.views.hourlyweather.texts.HourlyWeatherTemp

@Composable
fun HourlyWeather(
    hours: String,
    temps: String,
    weatherStatus: Int,
    relativeHumidity: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HourlyWeatherHour(text = hours)
        HourlyWeatherStatus(
            weatherCode = weatherStatus,
            modifier = modifier
        )
        HourlyRelativeHumidity(text = relativeHumidity)
        HourlyWeatherTemp(text = temps)
    }
}
