package com.example.jetweather.views.currentweather.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Gradient4
import com.example.jetweather.views.currentweather.texts.CurrentApparentTemp
import com.example.jetweather.views.currentweather.texts.CurrentLocation
import com.example.jetweather.views.currentweather.texts.CurrentMinMaxTemp
import com.example.jetweather.views.currentweather.texts.CurrentTemp
import com.example.jetweather.views.currentweather.texts.CurrentWeatherStatus

@Composable
fun CurrentWeatherInfo(
    currentLocation: String,
    currentTemp: String,
    currentApparentTemp: String,
    currentWeatherStatus: String,
    currentMinTemp: String,
    currentMaxTemp: String,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Gradient4,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            CurrentLocation(text = currentLocation)
            CurrentTemp(text = currentTemp)
            CurrentApparentTemp(text = "Feels like $currentApparentTemp")
            CurrentWeatherStatus(text = currentWeatherStatus)
            CurrentMinMaxTemp(text = "$currentMinTemp/$currentMaxTemp")
        }
    }
}
