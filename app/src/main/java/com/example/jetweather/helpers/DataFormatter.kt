package com.example.jetweather.helpers

import com.example.jetweather.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DataFormatter {

    fun formatTemperatureText(temp: Float): String {
        val roundedTemp = temp.toInt()
        return "$roundedTemp°"
    }

    fun formatRelativeHumidityText(humidity: Int): String {
        val roundedHumidity = ((humidity / 10) * 10)
        return "$roundedHumidity%"
    }

    fun formatWeatherCodeToText(weatherCode: Int): String {
        return weatherText(weatherCode)
    }

    fun formatWeatherCodeToIcon(weatherCodeNumber: Int): Int {
        return weatherIcon(weatherCodeNumber)
    }

    fun formatDay(dateStr: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateStr, formatter)
        return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }

    fun formatTime(timeStr: String): String {
        val dateTime = LocalDateTime.parse(timeStr)
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    fun getPercentageOfDay(timeStr: String): Float {
        val dateTime = LocalDateTime.parse(timeStr)
        return getPercentageFromHour(dateTime)
    }

    fun getCurrentTimePercentage(): Float {
        val dateTime = LocalDateTime.now()
        return getPercentageFromHour(dateTime)
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

    private fun getPercentageFromHour(dateTime: LocalDateTime): Float {
        val formatted = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        val time = formatted.split(":")
        val hour = time[0].toFloat()
        val minute = time[1].toFloat()
        return (hour + (minute / 60)) / 24
    }

    private fun weatherText(weatherCode: Int): String {
        return when (weatherCode) {
            0 -> "Clear Sky" // Sunny
            1 -> "Clear" // Sunny
            2 -> "Partly Cloudy" // Cloudy
            3 -> "Overcast" // Cloudy
            45 -> "Fog" // Foggy
            48 -> "Fog" // Foggy
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
            77 -> "Snow Grains" // wat
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