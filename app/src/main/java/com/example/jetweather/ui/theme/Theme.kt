package com.example.jetweather.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    primaryContainer = Color.Black,
    secondaryContainer = Color(0xFF252525),
    onSecondaryContainer = Color(0xFFBFC8CA),
    tertiary = Color(0xFF5D5D5D),
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    primaryContainer = Color.White,
    secondaryContainer = Color(0xFFE2E2E2),
    onSecondaryContainer = Color(0xFF313131),
    tertiary = Color(0xFFABABAB),
)

private val DynamicColorScheme = ColorScheme(
    primary = primaryP10,
    onPrimary = Color.Unspecified,
    primaryContainer = primaryP90,
    onPrimaryContainer = Color.Unspecified,
    inversePrimary = Color.Unspecified,
    secondary = primaryP60,
    onSecondary = Color.Unspecified,
    secondaryContainer = primaryP80,
    onSecondaryContainer = primaryP60,
    tertiary = primaryP70,
    onTertiary = Color.Unspecified,
    tertiaryContainer = Color.Unspecified,
    onTertiaryContainer = Color.Unspecified,
    background = Color.Unspecified,
    onBackground = Color.Unspecified,
    surface = Color.Unspecified,
    onSurface = Color.Unspecified,
    surfaceVariant = Color.Unspecified,
    onSurfaceVariant = Color.Unspecified,
    surfaceTint = Color.Unspecified,
    inverseSurface = Color.Unspecified,
    inverseOnSurface = Color.Unspecified,
    error = Color.Unspecified,
    onError = Color.Unspecified,
    errorContainer = Color.Unspecified,
    onErrorContainer = Color.Unspecified,
    outline = Color.Unspecified,
    outlineVariant = Color.Unspecified,
    scrim = Color.Unspecified
)

@Composable
fun JetWeatherTheme(
    themePreference: String,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themePreference) {
        "dark" -> DarkColorScheme
        "light" -> LightColorScheme
        "dynamic" -> DynamicColorScheme
        else -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    }

    val lightStatusBars = when (themePreference) {
        "dark" -> false
        "light" -> true
        "dynamic" -> false
        else -> !isSystemInDarkTheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = {
            val view = LocalView.current
            val context = LocalContext.current
            LaunchedEffect(colorScheme.primaryContainer) {
                val window = (context as? Activity)?.window
                window?.let {
                    window.statusBarColor = colorScheme.primaryContainer.toArgb()
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = lightStatusBars
                }
            }
            content()
        }
    )
}
