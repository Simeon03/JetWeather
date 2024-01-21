package com.example.jetweather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherInstance {
    private const val baseUrl = "https://api.open-meteo.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
