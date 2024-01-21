package com.example.jetweather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetweather.ui.theme.JetWeatherTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    fetchWeatherData()
}

fun fetchWeatherData() {
    val weatherApi = WeatherInstance.getInstance().create(WeatherApiService::class.java)
    CoroutineScope(Dispatchers.IO).launch {
        val response = weatherApi.getWeatherData(52.52f, 13.41f, "temperature_2m_max,temperature_2m_min")
        if (response.isSuccessful) {
            // Extracting the body from the response
            val weatherData = response.body()

            // Log the result (assuming weatherData is correctly parsed)
            Log.d("Weather", weatherData.toString())
            Log.d("Weather", response.toString())
        } else {
            // Handle request error
            Log.e("WeatherError", "Error: ${response.code()}")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetWeatherTheme {
        Greeting("Android")
    }
}