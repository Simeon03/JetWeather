package com.example.jetweather.viewmodel

import com.example.jetweather.viewmodel.repositories.CurrentWeatherRepository
import com.example.jetweather.viewmodel.repositories.HourlyWeatherRepository
import com.example.jetweather.viewmodel.repositories.LocationRepository
import com.example.jetweather.viewmodel.repositories.WeeklyWeatherRepository

interface WeatherRepository :
    CurrentWeatherRepository,
    WeeklyWeatherRepository,
    HourlyWeatherRepository,
    LocationRepository