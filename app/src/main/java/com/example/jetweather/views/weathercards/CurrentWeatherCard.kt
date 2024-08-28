package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.helpers.DataFormatter.roundTemp
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.views.WeatherCard
import com.example.jetweather.views.layout.CurrentWeatherInfo

@Composable
fun CurrentWeatherCard(viewModel: CurrentWeatherViewModel) {
    val viewModel by viewModel.currentWeatherData.collectAsState()

    WeatherCard(color = Color.Transparent) {
        CurrentWeatherInfo(
            currentTemp = viewModel.temp.roundTemp(),
            currentWeatherStatus = stringResource(viewModel.weatherStatus?.formatWeatherCodeToText() ?: 0),
            currentMinTemp = viewModel.minTemp.roundTemp(),
            currentMaxTemp = viewModel.maxTemp.roundTemp(),
        )
    }
}
