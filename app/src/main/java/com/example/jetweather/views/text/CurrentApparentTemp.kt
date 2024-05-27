package com.example.jetweather.views.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentApparentTemp(text: String) {
    Text(
        text = text,
        style = Typography.titleSmall,
        color = Color.LightGray,
    )
}