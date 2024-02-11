package com.example.jetweather.views.currentweather.subviews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentLocationView(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = Typography.bodyLarge,
    )
}