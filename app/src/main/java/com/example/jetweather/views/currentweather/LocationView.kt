package com.example.jetweather.views.currentweather

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.Typography

@Composable
fun LocationView(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Typography.bodyLarge,
        color = Color.White,
    )
}