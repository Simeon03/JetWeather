package com.example.jetweather.helpers.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetweather.ui.theme.primaryP90

@Composable
fun CardGradientBg(
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(primaryP90)
    ) {
        BoxGradientBg {
            content()
        }
    }
}
