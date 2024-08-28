package com.example.jetweather.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun WeatherRefreshScreen(
    navController: NavController,
    current: CurrentWeatherViewModel,
    weekly: WeeklyWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel,
) {
    val currentData by current.currentWeatherData.collectAsState()

    val isLoadingCurrent by current.isLoading.collectAsState()
    val isLoadingWeekly by weekly.isLoading.collectAsState()
    val isLoadingHourly by hourly.isLoading.collectAsState()
    val isLoadingCurrentHour by currentHour.isLoading.collectAsState()

    val isRefreshing = isLoadingCurrent || isLoadingWeekly || isLoadingHourly || isLoadingCurrentHour

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { refreshWeather(current, weekly, hourly, currentHour) },
    ) {
        if (!isRefreshing) {
            WeatherScreen(
                navController = navController,
                current = current,
                weekly = weekly,
                hourly = hourly,
                currentHour = currentHour,
                currentData = currentData,
            )
        }
    }
}

fun refreshWeather(
    current: CurrentWeatherViewModel,
    weekly: WeeklyWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel,
) {
    current.fetchWeatherData()
    weekly.fetchWeatherData()
    hourly.fetchWeatherData()
    currentHour.fetchWeatherData()
}
