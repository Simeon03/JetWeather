package com.example.jetweather.views.horizontalpager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherToggleButton(
    scope: CoroutineScope,
    pagerState: PagerState,
    page: Int,
    text: String,
) {
    Button(
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(page)
            }
        }
    ) {
        Text(text = text)
    }
}

