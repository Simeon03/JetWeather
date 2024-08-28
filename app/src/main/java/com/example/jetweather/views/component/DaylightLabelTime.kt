package com.example.jetweather.views.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.jetweather.views.text.DaylightLabel
import com.example.jetweather.views.text.DaylightTime

@Composable
fun DaylightLabelTime(
    labelText: String,
    timeText: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DaylightLabel(text = labelText)
        DaylightTime(text = timeText)
    }
}