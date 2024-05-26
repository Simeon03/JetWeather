package com.example.jetweather.views

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.primaryP80

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    color: Color = primaryP80,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        content()
    }
}