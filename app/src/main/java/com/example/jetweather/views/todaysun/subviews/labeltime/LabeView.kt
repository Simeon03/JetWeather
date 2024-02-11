package com.example.jetweather.views.todaysun.subviews.labeltime

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography

@Composable
fun LabelView(text: String) {
    Text(
        text = text,
        style = Typography.labelLarge,
    )
}