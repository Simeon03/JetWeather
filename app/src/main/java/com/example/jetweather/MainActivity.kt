package com.example.jetweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetweather.constants.Api.GOOGLE_MAPS_BASE_URL
import com.example.jetweather.constants.Api.OPEN_METEO_BASE_URL
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.model.api.GoogleMaps
import com.example.jetweather.model.api.OpenMeteo
import com.example.jetweather.repos.main.MainRepo
import com.example.jetweather.ui.theme.JetWeatherTheme
import com.example.jetweather.viewmodel.MainViewModel
import com.example.jetweather.viewmodel.Model
import com.example.jetweather.views.FullMainView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherApi = RetrofitInstance.get(OPEN_METEO_BASE_URL).create(
            OpenMeteo::class.java
        )
        val googleMapsApi = RetrofitInstance.get(GOOGLE_MAPS_BASE_URL).create(
            GoogleMaps::class.java
        )
        val weatherRepository = MainRepo(weatherApi, googleMapsApi)
        val viewModel = MainViewModel(weatherRepository)
        val model = Model(viewModel)

        setContent {
            JetWeatherTheme {
                FullMainView(model = model)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    lateinit var model: Model
    JetWeatherTheme {
        FullMainView(model = model)
    }
}