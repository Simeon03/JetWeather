package com.example.jetweather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.ui.theme.primaryP90
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.buttons.SettingsNavigationButton
import com.example.jetweather.views.horizontalpager.WeatherHorizontalPager
import com.example.jetweather.views.weathercards.CurrentWeatherCard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun WeatherScreen(
    navController: NavController,
    current: CurrentWeatherViewModel,
    weekly: WeeklyWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel,
) {
    val isLoadingCurrent by current.isLoading.collectAsState()
    val isLoadingWeekly by weekly.isLoading.collectAsState()
    val isLoadingHourly by hourly.isLoading.collectAsState()
    val isLoadingCurrentHour by currentHour.isLoading.collectAsState()

    val isRefreshing = isLoadingCurrent || isLoadingWeekly || isLoadingHourly || isLoadingCurrentHour


    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            current.fetchWeatherData()
            weekly.fetchWeatherData()
            hourly.fetchWeatherData()
            currentHour.fetchWeatherData()
        }
    ) {
        if (!isRefreshing) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(primaryP90)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            SettingsNavigationButton(
                                icon = R.drawable.settings,
                                description = "Settings",
                                onClick = { navController.navigate("settings") }
                            )
                            CurrentWeatherCard(viewModel = current)
                        }
                    }
                    item { WeatherHorizontalPager(weekly, hourly, current, currentHour) }
                }
            }
        }
    }
}