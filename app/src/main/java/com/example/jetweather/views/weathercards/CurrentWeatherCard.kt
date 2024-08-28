package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.helpers.DataFormatter.roundTemp
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.views.info.CurrentWeatherInfo
import com.example.jetweather.views.weathercards.layout.WeatherCard

@Composable
fun CurrentWeatherCard(viewModel: CurrentWeatherViewModel) {
    val current by viewModel.currentWeatherData.collectAsState()

    WeatherCard(color = Color.Transparent) {
        CurrentWeatherInfo(
            currentTemp = current.temp.roundTemp(),
            currentWeatherStatus = stringResource(current.weatherStatus?.formatWeatherCodeToText() ?: 0),
            currentMinTemp = current.minTemp.roundTemp(),
            currentMaxTemp = current.maxTemp.roundTemp(),
        )
    }
}
