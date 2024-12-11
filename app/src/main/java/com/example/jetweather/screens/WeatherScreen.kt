package com.example.jetweather.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.views.text.CurrentLocation
import com.example.jetweather.views.weathercards.CurrentCards
import com.example.jetweather.views.weathercards.CurrentWeatherCard
import com.example.jetweather.views.weathercards.DailyWeatherCard
import com.example.jetweather.views.weathercards.DaylightCard
import com.example.jetweather.views.weathercards.HourlyWeatherCard

@Composable
fun WeatherScreen(navController: NavController) {
    val current: CurrentWeatherViewModel = hiltViewModel()
    val currentData by current.currentWeatherData.collectAsState()

    Screen(
        containerAppBarTitle = { CurrentLocation(text = currentData.location) },
        iconResId = R.drawable.settings,
        iconDesc = "Settings",
        onClick = { navController.navigate("settings") }
    ) {
        item { CurrentWeatherCard() }
        item { HourlyWeatherCard() }
        item { DailyWeatherCard() }
        item { DaylightCard() }
        item { CurrentCards() }
    }
}