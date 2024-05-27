package com.example.jetweather.views.horizontalpager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.views.weathercards.CurrentUvIndexCard
import com.example.jetweather.views.weathercards.CurrentVisibilityCard
import com.example.jetweather.views.weathercards.DaylightCard
import com.example.jetweather.views.weathercards.HourlyWeatherCard

@Composable
fun DailyWeatherCards(
    hourly: HourlyWeatherViewModel,
    current: CurrentWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HourlyWeatherCard(viewModel = hourly)
        DaylightCard(viewModel = current)

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            CurrentUvIndexCard(viewModel = currentHour, modifier = Modifier.weight(1f).padding(end = 8.dp))
            CurrentVisibilityCard(viewModel = currentHour, modifier = Modifier.weight(1f).padding(start = 8.dp))
        }
    }
}
