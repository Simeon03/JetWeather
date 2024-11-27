package com.example.jetweather.views.deprecated.horizontalpager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun WeatherHorizontalPager() {
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
                0 -> DailyWeatherCards()
                1 -> WeeklyWeatherCards()
            }
        }
    }
}


