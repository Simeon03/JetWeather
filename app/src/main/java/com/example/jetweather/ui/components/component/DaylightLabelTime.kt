package com.example.jetweather.ui.components.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.jetweather.ui.components.text.DaylightLabel
import com.example.jetweather.ui.components.text.DaylightTime

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