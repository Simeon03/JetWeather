package com.example.jetweather.views.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentMinMaxTemp(minMaxTemp: String) {
    Text(
        text = minMaxTemp,
        style = Typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
    )
}