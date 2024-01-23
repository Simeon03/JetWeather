package com.example.jetweather.views.weeklyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetweather.helper.formatTemp
import com.example.jetweather.helper.weatherCodeIcon
import com.example.jetweather.ui.theme.Typography

@Composable
fun DailyWeatherView(minTemp: Int, maxTemp: Int, tempSuffix: String, date: String, weatherCode: Int, weatherCodeDesc: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp, 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = date,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            style = Typography.bodyLarge
        )
        Icon(
            painter = painterResource(id = weatherCodeIcon(weatherCode)),
            contentDescription = weatherCodeDesc,
            tint = androidx.compose.ui.graphics.Color.Unspecified,
        )
        Text(
            text = "$minTemp${formatTemp(tempSuffix)}/$maxTemp${formatTemp(tempSuffix)}",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            style = Typography.bodyLarge
        )
    }
}