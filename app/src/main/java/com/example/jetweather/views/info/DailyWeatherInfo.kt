package com.example.jetweather.views.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.R
import com.example.jetweather.helpers.DataFormatter.fetchDay
import com.example.jetweather.helpers.DataFormatter.roundTemp
import com.example.jetweather.views.component.CardChip
import com.example.jetweather.views.component.DailyWeather

@Composable
fun DailyWeatherInfo(
    minTemp: List<Float>,
    maxTemp: List<Float>,
    date: List<String>,
    weatherCode: List<Int>,
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CardChip(
            text = "WEEKLY FORECAST",
            iconId = R.drawable.cloud,
        )

        for (i in 0..6) {
            DailyWeather(
                minTemp = minTemp[i].roundTemp(),
                maxTemp = maxTemp[i].roundTemp(),
                date = date[i].fetchDay(),
                weatherCode = weatherCode[i],
            )
        }
    }
}