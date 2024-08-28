package com.example.jetweather.views.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.text.CurrentMinMaxTemp
import com.example.jetweather.views.text.CurrentTemp
import com.example.jetweather.views.text.CurrentWeatherStatus

@Composable
fun CurrentWeatherInfo(
    currentTemp: String,
    currentWeatherStatus: String,
    currentMinTemp: String,
    currentMaxTemp: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top,
    ) {
        CurrentTemp(text = currentTemp)
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CurrentWeatherStatus(text = currentWeatherStatus)
            CurrentMinMaxTemp(minMaxTemp = "$currentMinTemp/$currentMaxTemp")
        }
    }
}