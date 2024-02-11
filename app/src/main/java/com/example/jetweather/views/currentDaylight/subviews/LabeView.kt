package com.example.jetweather.views.currentDaylight.subviews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography

@Composable
fun LabelView(labelText: String) {
    Text(
        text = labelText,
        style = Typography.labelLarge,
    )
}