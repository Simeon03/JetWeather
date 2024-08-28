package com.example.jetweather.views.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.icon.WeatherIcon
import com.example.jetweather.views.text.HourlyPrecipitationProbability
import com.example.jetweather.views.text.HourlyWeatherHour
import com.example.jetweather.views.text.HourlyWeatherTemp

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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HourlyWeatherHour(text = hours)
        WeatherIcon(
            weatherCode = weatherStatus,
            size = 32.dp,
            modifier = modifier
        )
        if (relativeHumidity.replace("%", "").toInt() > 9) {
            HourlyPrecipitationProbability(text = relativeHumidity)
        }
        HourlyWeatherTemp(text = temps)
    }
}

@Preview(device = Devices.PIXEL_4_XL, showBackground = false)
@Composable
fun HourlyWeatherPreview() {
    HourlyWeather(
        hours = "12:00",
        temps = "25°",
        weatherStatus = 3,
        relativeHumidity = "0%",
    )
}
