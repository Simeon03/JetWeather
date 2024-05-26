package com.example.jetweather.views.currentweather.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.primaryP40
import com.example.jetweather.views.WeatherCard
import com.example.jetweather.views.currentweather.texts.CurrentLocation
import com.example.jetweather.views.currentweather.texts.CurrentMinMaxTemp
import com.example.jetweather.views.currentweather.texts.CurrentTemp
import com.example.jetweather.views.currentweather.texts.CurrentWeatherStatus

@Composable
fun CurrentWeatherInfo(
    currentLocation: String,
    currentTemp: String,
    currentWeatherStatus: String,
    currentMinTemp: String,
    currentMaxTemp: String,
) {
    WeatherCard(color = primaryP40) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            CurrentLocation(text = currentLocation)
            CurrentTemp(text = currentTemp)
            CurrentWeatherStatus(text = currentWeatherStatus)
            CurrentMinMaxTemp(minTemp = "L:$currentMinTemp", maxTemp = "H:$currentMaxTemp")
        }
    }
}
