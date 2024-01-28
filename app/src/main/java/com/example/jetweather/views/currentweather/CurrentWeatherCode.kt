package com.example.jetweather.views.currentweather

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentWeatherCode(text: String) {
    Text(
        text = text,
        style = Typography.bodyLarge,
        color = Color.White,
        modifier = Modifier.padding(5.dp)
    )
}