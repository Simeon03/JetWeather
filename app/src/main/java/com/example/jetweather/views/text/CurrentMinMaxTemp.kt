package com.example.jetweather.views.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentMinMaxTemp(minTemp: String, maxTemp: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = minTemp,
            style = Typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = maxTemp,
            style = Typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}