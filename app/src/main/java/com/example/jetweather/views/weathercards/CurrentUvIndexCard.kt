package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.jetweather.R
import com.example.jetweather.helpers.DataFormatter.getUvIndexStatus
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.views.SimpleWeatherCardLayout
import com.example.jetweather.views.WeatherCard

@Composable
fun CurrentUvIndexCard(viewModel: CurrentHourWeatherViewModel, modifier: Modifier = Modifier) {
    val currentHourWeather by viewModel.currentHourWeatherData.collectAsState()

    WeatherCard(modifier = modifier) {
        SimpleWeatherCardLayout(
            cardChipText = stringResource(id = R.string.uv_index),
            cardChipIconId = R.drawable.cloud,
            valueText = stringResource(currentHourWeather.uvIndex.toInt().getUvIndexStatus()),
        )
    }
}
