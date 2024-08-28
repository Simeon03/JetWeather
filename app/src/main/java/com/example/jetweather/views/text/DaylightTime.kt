package com.example.jetweather.views.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.ui.theme.primaryP10

@Composable
fun DaylightTime(text: String) {
    Text(
        text = text,
        style = Typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = primaryP10,
    )
}