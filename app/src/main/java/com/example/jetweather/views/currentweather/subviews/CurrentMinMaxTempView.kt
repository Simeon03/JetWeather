package com.example.jetweather.views.currentweather.subviews

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentMinMaxTempView(text: String) {
    Text(
        text = text,
        style = Typography.bodyLarge,
        color = Color.Gray,
        modifier = Modifier.padding(5.dp)
    )
}