package com.example.jetweather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.ui.theme.primaryP10
import com.example.jetweather.ui.theme.primaryP90
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.buttons.SettingsNavigationButton
import com.example.jetweather.views.deprecated.horizontalpager.CurrentCards
import com.example.jetweather.views.deprecated.horizontalpager.WeeklyWeatherCards
import com.example.jetweather.views.text.CurrentLocation
import com.example.jetweather.views.weathercards.CurrentWeatherCard
import com.example.jetweather.views.weathercards.DaylightCard
import com.example.jetweather.views.weathercards.HourlyWeatherCard
import com.example.jetweather.weatherdata.CurrentWeatherData
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
    val currentData by current.currentWeatherData.collectAsState()

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
            Box(modifier = Modifier
                    .fillMaxSize()
                    .background(primaryP90)
            ) {
                Column {
                    WeatherTopAppBar(navController, currentData)
                    WeatherContent(current, weekly, hourly, currentHour)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(navController: NavController, currentData: CurrentWeatherData, ) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 20.dp),
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = primaryP10,
            titleContentColor = primaryP10,
            actionIconContentColor = Color.Unspecified,
        ),
        title = { CurrentLocation(text = currentData.location) },
        navigationIcon = {
            SettingsNavigationButton(
                icon = R.drawable.settings,
                description = "Settings",
                onClick = { navController.navigate("settings") }
            )
        }
    )
}

@Composable
fun WeatherContent(
    current: CurrentWeatherViewModel,
    weekly: WeeklyWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel,
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 24.dp).padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { CurrentWeatherCard(viewModel = current) }
        item { HourlyWeatherCard(viewModel = hourly)  }
        item { WeeklyWeatherCards(viewModel = weekly) }
        item { DaylightCard(viewModel = current) }
        item { CurrentCards(viewModel = currentHour) }
    }
}