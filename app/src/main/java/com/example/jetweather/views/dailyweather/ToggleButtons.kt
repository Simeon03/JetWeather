package com.example.jetweather.views.dailyweather

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun toggleButtons(pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            },
            modifier = Modifier.fillMaxWidth(0.5f),
        ) {
            Text("Week")
        }

        Button(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(1)
                }
            },
            modifier = Modifier.fillMaxWidth(0.5f),
        ) {
            Text("Day")
        }
    }

}

