package com.example.jetweather

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun getDayOfWeek(dateStr: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(dateStr, formatter)
    return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
}