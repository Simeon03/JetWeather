package com.example.jetweather.views.currentweather.subviews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentMinMaxTempView(text: String) {
    Text(
        text = text,
        style = Typography.bodyLarge,
        color = Color.Gray,
    )
}