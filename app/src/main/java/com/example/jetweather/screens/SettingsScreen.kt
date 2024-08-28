package com.example.jetweather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.TemperatureSelection
import com.example.jetweather.views.ThemeSelection
import com.example.jetweather.views.buttons.SettingsNavigationButton

@Composable
fun SettingsScreen(
    navController: NavController,
    current: CurrentWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    weekly: WeeklyWeatherViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsNavigationButton(
                icon = R.drawable.arrow_back,
                description = "Settings",
                onClick = { navController.popBackStack() }
            )
            TemperatureSelection(current, hourly, weekly)
            ThemeSelection(current, hourly, weekly)
        }
    }
}
