package com.example.jetweather.views.hourlyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.views.hourlyweather.subviews.HourlyWeatherIconView
import com.example.jetweather.views.hourlyweather.subviews.HourlyWeatherSlotHourView
import com.example.jetweather.views.hourlyweather.subviews.HourlyWeatherSlotTemperatureView

@Composable
fun HourlyWeatherLayoutView(
    hours: String,
    temps: String,
    weatherStatus: Int,
    weatherStatusDesc: String,
    relativeHumidity: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HourlyWeatherSlotHourView(text = hours)
        HourlyWeatherIconView(
            weatherCode = weatherStatus,
            modifier = modifier
        )
        HourlyRelativeHumidityView(text = relativeHumidity)
        HourlyWeatherSlotTemperatureView(text = temps)
    }
}

@Composable
fun HourlyRelativeHumidityView(text: String) {
    Text(
        text = text,
        style = Typography.labelLarge,
        textAlign = TextAlign.Center,
        color = Color.LightGray,
    )
}
