package com.example.jetweather.views.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetweather.R

@Composable
fun DaylightInfo(
    sunriseTime: String,
    sunsetTime: String
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        DaylightLabelTime(labelText = stringResource(id = R.string.sunrise), timeText = sunriseTime)
        DaylightLabelTime(labelText = stringResource(id = R.string.sunset), timeText = sunsetTime)
    }
}
