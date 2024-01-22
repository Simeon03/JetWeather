package com.example.jetweather.views.weeklyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Typography

@Composable
fun DailyWeatherView(minTemp: Int, maxTemp: Int, date: String, weatherCode: String) {
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
        Text(
            text = weatherCode,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            style = Typography.bodyMedium,
            maxLines = 1,
        )
        Text(
            text = "$minTemp/$maxTemp",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            style = Typography.bodyLarge
        )
    }
}