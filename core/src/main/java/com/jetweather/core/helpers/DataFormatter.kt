package com.jetweather.core.helpers

import com.jetweather.core.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DataFormatter {

    fun Int.formatWeatherCodeToText(): Int = this.weatherCodeToText()

    fun String.getPercentageOfDay(): Float = LocalDateTime.parse(this).getPercentageFromHour()

    fun getCurrentTimePercentage(): Float = LocalDateTime.now().getPercentageFromHour()

    fun Float.roundTemp(): String = "${this.toInt()}°"

    fun Int.roundPercent(): String = "${((this / 10) * 10)}%"

    fun String.fetchTime(): String {
        return LocalDateTime.parse(this).format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    fun String.fetchDay(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(this, formatter)
        return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }

    private fun LocalDateTime.getPercentageFromHour(): Float {
        val formatted = this.format(DateTimeFormatter.ofPattern("HH:mm"))
        val time = formatted.split(":")
        val hour = time[0].toFloat()
        val minute = time[1].toFloat()
        return (hour + (minute / 60)) / 24
    }

    fun Int.getUvIndexStatus(): Int {
        return when (this) {
            in 0..2 -> R.string.uv_low
            in 3..5 -> R.string.uv_moderate
            in 6..7 -> R.string.uv_high
            in 8..10 -> R.string.uv_very_high
            else -> R.string.uv_extreme
        }
    }

    fun Int.getVisibilityStatus(): Int {
        return when (this) {
            in 0..800 -> R.string.visibility_extremely_poor
            in 801..1600 -> R.string.visibility_very_poor
            in 1601..5000 -> R.string.visibility_poor
            in 5001..10000 -> R.string.visibility_moderate
            in 10001..16000 -> R.string.visibility_good
            else -> R.string.visibility_clear
        }
    }

    fun Int.getWeatherIcon(): Int {
        return when (this) {
            0, 1 -> R.drawable.sunny
            2, 3, 45, 48 -> R.drawable.cloudy
            51, 53, 55, 56, 57, 61, 63, 65, 66, 67, 80, 81, 82 -> R.drawable.rainy
            73, 75, 77, 85, 86 -> R.drawable.snowy
            95, 96, 99 -> R.drawable.thunderstorm
            else -> R.drawable.cloudy
        }
    }

    private fun Int.weatherCodeToText(): Int {
        return when (this) {
            0 -> R.string.weather_code_0
            1 -> R.string.weather_code_1
            2 -> R.string.weather_code_2
            3 -> R.string.weather_code_3
            45 -> R.string.weather_code_45
            48 -> R.string.weather_code_48
            51 -> R.string.weather_code_51
            53 -> R.string.weather_code_53
            55 -> R.string.weather_code_55
            56 -> R.string.weather_code_56
            57 -> R.string.weather_code_57
            61 -> R.string.weather_code_61
            63 -> R.string.weather_code_63
            65 -> R.string.weather_code_65
            66 -> R.string.weather_code_66
            67 -> R.string.weather_code_67
            71 -> R.string.weather_code_71
            73 -> R.string.weather_code_73
            75 -> R.string.weather_code_75
            77 -> R.string.weather_code_77
            80 -> R.string.weather_code_80
            81 -> R.string.weather_code_81
            82 -> R.string.weather_code_82
            85 -> R.string.weather_code_85
            86 -> R.string.weather_code_86
            95 -> R.string.weather_code_95
            96 -> R.string.weather_code_96
            99 -> R.string.weather_code_99
            else -> R.string.weather_code_other
        }
    }

}