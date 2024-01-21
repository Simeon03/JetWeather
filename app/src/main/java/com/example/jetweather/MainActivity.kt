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
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

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
    CoroutineScope(Dispatchers.IO).launch {
        val client = OkHttpClient()

        // Replace with your specific URL and parameters
        val request = Request.Builder()
            .url("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.405&hourly=temperature")
            .build()

        try {
            val response = client.newCall(request).execute()
            val responseData = response.body?.string()

            if (response.isSuccessful && responseData != null) {
                // Handle the response
                withContext(Dispatchers.Main) {
                    Log.d("Data", responseData)
                }
            } else {
                // Handle the error
            }
        } catch (_: Exception) {

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