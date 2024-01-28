package com.example.jetweather

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.model.apiservice.LocationApiService
import com.example.jetweather.model.apiservice.WeatherApiService
import com.example.jetweather.ui.theme.JetWeatherTheme
import com.example.jetweather.viewmodel.BaseWeatherRepository
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.views.MainView
import com.example.jetweather.views.currentweather.CurrentWeatherView
import kotlinx.coroutines.delay
import okhttp3.internal.wait

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherApi = RetrofitInstance.getInstance(OPEN_METEO_BASE_API).create(
            WeatherApiService::class.java
        )
        val googleMapsApi = RetrofitInstance.getInstance(GOOGLE_MAPS_BASE_URL).create(
            LocationApiService::class.java
        )
        val weatherRepository = BaseWeatherRepository(weatherApi, googleMapsApi)

        setContent {
            JetWeatherTheme {
                val viewModel = WeatherViewModel(weatherRepository)
                MainView(viewModel = viewModel)
            }
        }
    }

    companion object {
        private const val OPEN_METEO_BASE_API = "https://api.open-meteo.com/"
        private const val GOOGLE_MAPS_BASE_URL = "https://maps.googleapis.com/"
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