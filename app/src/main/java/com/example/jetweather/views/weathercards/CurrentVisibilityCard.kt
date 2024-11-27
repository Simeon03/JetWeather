package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetweather.R
import com.example.jetweather.helpers.DataFormatter.getVisibilityStatus
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.views.weathercards.layout.SimpleWeatherCardLayout
import com.example.jetweather.views.weathercards.layout.WeatherCard

@Composable
fun CurrentVisibilityCard(modifier: Modifier = Modifier) {
    val viewModel: CurrentHourWeatherViewModel = hiltViewModel()
    val currentHour by viewModel.currentHourWeatherData.collectAsState()

    WeatherCard(modifier = modifier) {
        SimpleWeatherCardLayout(
            cardChipText = stringResource(id = R.string.visibility),
            cardChipIconId = R.drawable.cloud,
            valueText = stringResource(currentHour.visibility.getVisibilityStatus()),
        )
    }
}
