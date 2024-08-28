package com.example.jetweather.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.deprecated.horizontalpager.CurrentCards
import com.example.jetweather.views.deprecated.horizontalpager.WeeklyWeatherCards
import com.example.jetweather.views.text.CurrentLocation
import com.example.jetweather.views.weathercards.CurrentWeatherCard
import com.example.jetweather.views.weathercards.DaylightCard
import com.example.jetweather.views.weathercards.HourlyWeatherCard
import com.example.jetweather.weatherdata.CurrentWeatherData

@Composable
fun WeatherScreen(
    navController: NavController,
    current: CurrentWeatherViewModel,
    weekly: WeeklyWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel,
    currentData: CurrentWeatherData,
) {
    Screen(
        containerAppBarTitle = { CurrentLocation(text = currentData.location) },
        iconResId = R.drawable.settings,
        iconDesc = "Settings",
        onClick = { navController.navigate("settings") }
    ) {
        item { CurrentWeatherCard(viewModel = current) }
        item { HourlyWeatherCard(viewModel = hourly)  }
        item { WeeklyWeatherCards(viewModel = weekly) }
        item { DaylightCard(viewModel = current) }
        item { CurrentCards(viewModel = currentHour) }
    }
}