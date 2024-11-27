package com.example.jetweather.views.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.jetweather.core.helpers.DataFormatter.formatWeatherCodeToText
import com.jetweather.core.helpers.DataFormatter.getWeatherIcon

@Composable
fun WeatherIcon(
    weatherCode: Int,
    size: Dp,
    modifier: Modifier
) {
    Icon(
        painter = painterResource(id = weatherCode.getWeatherIcon()),
        contentDescription = stringResource(weatherCode.formatWeatherCodeToText()),
        tint = Color.Unspecified,
        modifier = modifier.size(size)
    )
}