package com.example.jetweather.helper

import com.example.jetweather.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DataFormatter {

    fun formatTemperatureText(temp: Float): String {
        return temp.toInt().toString() + "°"
    }

    fun formatWeatherCodeText(weatherCodeNumber: Int): String {
        return weatherText(weatherCodeNumber)
    }

    fun formatWeatherCodeIcon(weatherCodeNumber: Int): Int {
        return weatherIcon(weatherCodeNumber)
    }

    fun getDayOfWeek(dateStr: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateStr, formatter)
        return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }

    fun weatherIcon(weatherCode: Int): Int {
        return when (weatherCode) {
            0, 1 -> R.drawable.sunny
            2, 3, 45, 48 -> R.drawable.cloudy
            51, 53, 55, 56, 57, 61, 63, 65, 66, 67, 80, 81, 82 -> R.drawable.rainy
            73, 75, 77, 85, 86 -> R.drawable.snowy
            95, 96, 99 -> R.drawable.thunderstorm
            else -> R.drawable.cloudy
        }
    }

    private fun weatherText(weatherCode: Int): String {
        return when (weatherCode) {
            0 -> "Clear Sky"
            1 -> "Clear"
            2 -> "Partly Cloudy"
            3 -> "Overcast"
            45 -> "Fog"
            48 -> "Fog"
            51 -> "Light Drizzle"
            53 -> "Drizzle"
            55 -> "Heavy Drizzle"
            56 -> "Light Freezing Drizzle"
            57 -> "Dense Freezing Drizzle"
            61 -> "Slight Rain"
            63 -> "Rain"
            65 -> "Heavy Rain"
            66 -> "Light Freezing Rain"
            67 -> "Dense Freezing Rain"
            71 -> "Light Snowfall"
            73 -> "Snowfall"
            75 -> "Heavy Snowfall"
            77 -> "Snow Grains"
            80 -> "Light Rain Showers"
            81 -> "Rain Showers"
            82 -> "Heavy Rain Showers"
            85 -> "Light Snow Showers"
            86 -> "Heavy Snow Showers"
            95 -> "Thunderstorm"
            96 -> "Thunderstorm with Slight Hail"
            99 -> "Thunderstorm with Heavy Hail"
            else -> "Unknown"
        }
    }

}