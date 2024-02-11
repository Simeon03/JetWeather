package com.example.jetweather.helpers.views

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun CardGradientBg(
    content: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp)
    ) {
        BoxGradientBg {
            content()
        }
    }
}
