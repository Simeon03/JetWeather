package com.example.jetweather.views.deprecated.horizontalpager

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.jetweather.ui.theme.Typography

@Composable
fun WeatherToggleButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(text = text, style = Typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}

