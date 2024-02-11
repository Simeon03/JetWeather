package com.example.jetweather.views.currentDaylight.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography

@Composable
fun Label(text: String) {
    Text(
        text = text,
        style = Typography.labelLarge,
    )
}