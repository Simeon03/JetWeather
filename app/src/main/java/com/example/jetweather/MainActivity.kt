package com.example.jetweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.jetweather.ui.theme.JetWeatherTheme
import com.example.jetweather.views.CurrentWeatherView

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetWeatherTheme {
                viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
                LazyColumn() {
                    item {
                        CurrentWeatherView(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    lateinit var viewModel: WeatherViewModel

    JetWeatherTheme {
        CurrentWeatherView(viewModel = viewModel)
    }
}