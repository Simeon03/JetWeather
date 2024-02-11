package com.example.jetweather.views.currentweather.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentMinMaxTemp(text: String) {
    Text(
        text = text,
        style = Typography.bodyLarge,
        color = Color.Gray,
    )
}