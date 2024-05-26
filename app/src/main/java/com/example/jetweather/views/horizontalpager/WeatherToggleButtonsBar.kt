package com.example.jetweather.views.horizontalpager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherToggleButtonsBar(pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        WeatherToggleButton(
            scope = scope,
            pagerState = pagerState,
            page = 0,
            text = "Today",
            modifier = Modifier.weight(1f),
        )
        WeatherToggleButton(
            scope = scope,
            pagerState = pagerState,
            page = 1,
            text = "Weekly",
            modifier = Modifier.weight(1f),
        )
    }
}

