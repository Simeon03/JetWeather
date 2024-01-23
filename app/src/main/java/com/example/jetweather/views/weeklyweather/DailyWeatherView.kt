package com.example.jetweather.views.weeklyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            .padding(2.dp, 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = date,
            modifier = Modifier
                .weight(3f)
                .padding(start = 8.dp),
            textAlign = TextAlign.Start,
            style = Typography.titleSmall,
        )
        Icon(
            painter = painterResource(id = weatherCodeIcon(weatherCode)),
            contentDescription = weatherCodeDesc,
            tint = androidx.compose.ui.graphics.Color.Unspecified,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "$maxTemp${formatTemp(tempSuffix)}/$minTemp${formatTemp(tempSuffix)}",
            modifier = Modifier
                .weight(2f)
                .padding(end = 8.dp),
            textAlign = TextAlign.End,
            style = Typography.titleSmall
        )
    }
}
