package com.example.jetweather.views.deprecated.horizontalpager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherHorizontalPager(
    weekly: WeeklyWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    current: CurrentWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel,
) {
    val pagerState = rememberPagerState(pageCount = { 2 })

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        WeatherToggleButtonsBar(pagerState)
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            pageSpacing = 16.dp,
        ) { page ->
            when (page) {
                0 -> DailyWeatherCards(hourly, current, currentHour)
                1 -> WeeklyWeatherCards(weekly)
            }
        }
    }
}


