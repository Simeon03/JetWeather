package com.example.jetweather

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.ui.theme.Gradient2
import com.example.jetweather.ui.theme.Gradient3
import com.example.jetweather.ui.theme.Gradient4
import com.example.jetweather.ui.theme.Gradient5
import com.example.jetweather.ui.theme.JetWeatherTheme
import com.example.jetweather.views.CurrentWeatherView
import com.example.jetweather.views.WeeklyWeatherView

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: WeatherViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetWeatherTheme {
                viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

                val gradient = Brush.verticalGradient(
                    colors = listOf(Gradient1, Gradient2, Gradient3, Gradient4, Gradient5),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient)
                        .systemBarsPadding()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        item {
                            CurrentWeatherView(viewModel = viewModel)
                            Spacer(modifier = Modifier.padding(8.dp))
                            WeeklyWeatherView(viewModel = viewModel)
                        }
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