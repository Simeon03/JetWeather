package com.example.jetweather.views.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.Typography

@Composable
fun HourlyPrecipitationProbability(text: String) {
    Text(
        text = text,
        style = Typography.labelMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary
    )
}
