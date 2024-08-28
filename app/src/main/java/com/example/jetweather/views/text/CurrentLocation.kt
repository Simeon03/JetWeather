package com.example.jetweather.views.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.ui.theme.primaryP10

@Composable
fun CurrentLocation(text: String) {
    Text(
        text = text,
        color = primaryP10,
        textAlign = TextAlign.Center,
        style = Typography.titleMedium,
    )
}