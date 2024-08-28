package com.example.jetweather.views.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.ui.theme.primaryP10

@Composable
fun CurrentTemp(text: String) {
    Text(
        text = text,
        style = Typography.headlineMedium,
        color = primaryP10
    )
}