package com.example.jetweather.views.todaysun.subviews

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun LabelTimeView(
    label: String,
    time: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LabelView(text = label)
        TimeView(text = time)
    }
}