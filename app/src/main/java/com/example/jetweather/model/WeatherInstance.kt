package com.example.jetweather.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherInstance {
    private const val BASE_URL = "https://api.open-meteo.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
