package com.example.jetweather.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.TemperatureSelection
import com.example.jetweather.views.ThemeSelection
import com.example.jetweather.views.text.CurrentLocation

@Composable
fun SettingsScreen(
    navController: NavController,
    current: CurrentWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    weekly: WeeklyWeatherViewModel,
) {
    Screen(
        containerAppBarTitle = { CurrentLocation(text = "Settings") },
        iconResId = R.drawable.arrow_back,
        iconDesc = "Back",
        onClick = { navController.popBackStack() }
    ) {
        item { TemperatureSelection(current, hourly, weekly) }
        item { ThemeSelection(current, hourly, weekly)  }
    }
}
