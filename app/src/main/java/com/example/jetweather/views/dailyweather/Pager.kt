package com.example.jetweather.views.dailyweather

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.currentDaylight.DaylightView
import com.example.jetweather.views.hourlyweather.HourlyWeatherView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherHorizontalPager(
    weekly: WeeklyWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    current: CurrentWeatherViewModel
) {
    val pagerState = rememberPagerState(pageCount = { 2 })

    Column {
        toggleButtons(pagerState)

        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                0 -> WeeklyWeatherCards(weekly)
                1 -> DailyWeatherCards(hourly, current)
            }
        }

    }
}

@Composable
fun WeeklyWeatherCards(weekly: WeeklyWeatherViewModel) {
    DailyWeatherView(viewModel = weekly)
}

@Composable
fun DailyWeatherCards(
    hourly: HourlyWeatherViewModel,
    current: CurrentWeatherViewModel
) {
    Column {
        HourlyWeatherView(viewModel = hourly)
        DaylightView(viewModel = current)
    }
}
