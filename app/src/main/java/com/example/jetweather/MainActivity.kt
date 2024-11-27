package com.example.jetweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetweather.repos.UserPreferencesRepo
import com.example.jetweather.screens.HomeScreen
import com.example.jetweather.ui.theme.JetWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userPreferencesRepo = UserPreferencesRepo(this)

        setContent {
            MainScreen(userPreferencesRepo = userPreferencesRepo)
        }
    }
}

@Composable
fun MainScreen(userPreferencesRepo: UserPreferencesRepo) {
    val themePreference by userPreferencesRepo.themePreference.collectAsState(initial = "system_default")

    JetWeatherTheme(themePreference = themePreference) {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    lateinit var userPreferencesRepo: UserPreferencesRepo

    MainScreen(userPreferencesRepo = userPreferencesRepo)
}