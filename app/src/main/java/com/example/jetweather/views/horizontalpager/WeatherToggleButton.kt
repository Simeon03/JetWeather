package com.example.jetweather.views.horizontalpager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherToggleButton(
    scope: CoroutineScope,
    pagerState: PagerState,
    page: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(page)
            }
        },
        modifier = modifier,
    ) {
        Text(text = text)
    }
}

