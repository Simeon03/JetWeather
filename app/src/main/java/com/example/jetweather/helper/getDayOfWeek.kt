package com.example.jetweather.helper

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun getDayOfWeek(dateStr: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(dateStr, formatter)
    return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
}