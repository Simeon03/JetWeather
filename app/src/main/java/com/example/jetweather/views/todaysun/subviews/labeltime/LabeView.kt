package com.example.jetweather.views.todaysun.subviews.labeltime

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun LabelView(text: String) {
    val fontSize = 16.sp
    val fontWeight = FontWeight.Bold

    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
    )
}