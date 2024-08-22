package com.example.jetweather.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.primaryP90
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.horizontalpager.WeatherHorizontalPager
import com.example.jetweather.views.weathercards.CurrentWeatherCard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FullMainView(
    current: CurrentWeatherViewModel,
    weeklyWeatherViewModel: WeeklyWeatherViewModel,
    hourlyWeatherViewModel: HourlyWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel,
) {
    val isLoadingCurrent by current.isLoading.collectAsState()
    val isLoadingWeekly by weeklyWeatherViewModel.isLoading.collectAsState()
    val isLoadingHourly by hourlyWeatherViewModel.isLoading.collectAsState()
    val isLoadingCurrentHour by currentHour.isLoading.collectAsState()

    val isRefreshing = isLoadingCurrent || isLoadingWeekly || isLoadingHourly || isLoadingCurrentHour


    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            current.fetchWeatherData()
            weeklyWeatherViewModel.fetchWeatherData()
            hourlyWeatherViewModel.fetchWeatherData()
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
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    item { CurrentWeatherCard(viewModel = current) }
                    item { WeatherHorizontalPager(weeklyWeatherViewModel, hourlyWeatherViewModel, current, currentHour) }
                }
            }
        }
    }
}